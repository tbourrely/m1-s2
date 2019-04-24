import os
from http import HTTPStatus
from database.Client import Client
import components.Token as Token
import StreamingServer

class ServerI(StreamingServer.Server):
    def __init__(self):
        self.db = Client()
        self.dbData = self.db.data

    def search(self, key, value, current=None):
        """Search a track by a given value for a given key
        
        Arguments:
            key {string} -- which field to seach on
            value {string} -- the value to search for
        
        Returns:
            list -- the matching tracks
        """
        return self._findToTrackSequence(self.dbData.tracks.find({key : value}))

    def list(self, current=None):
        """List the available tracks
        
        Returns:
            list -- tracks
        """
        return self._findToTrackSequence(self.dbData.tracks.find())

    def play(self, track, apiKey, current=None):
        creationResult = Token.create(track.path, apiKey)

        if (
            creationResult != "-1" and
            creationResult != "-2" and
            creationResult != "-3"
        ):
            return os.getenv('HOST') + '/stream/' + track.path + '?token=' + creationResult
        else:
            return "-1"

    def add(self, track, current=None):
        """Add a track
        
        Arguments:
            track {StreamingServer.Track} -- the track to add
        
        Returns:
            StreamingServer.Status -- 200 if ok
                                      304 otherwise
        """
        result = self.dbData.tracks.insert_one({
            'title': track.title,
            'artist': track.artist,
            'album': track.album,
            'path': track.path
        })

        if result.inserted_id is not None:
            return StreamingServer.Status(HTTPStatus.OK.value, HTTPStatus.OK.phrase)
        else:
            return StreamingServer.Status(HTTPStatus.NOT_MODIFIED.value, HTTPStatus.NOT_MODIFIED.phrase)

    def remove(self, track, current=None):
        """Remove a track
        
        Arguments:
            track {StreamingServer.Track} -- the track to remove
        
        Returns:
            StreamingServer.Status -- 200 if ok
                                      304 otherwise
        """
        result = self.dbData.tracks.delete_many({
            'title': track.title,
            'artist': track.artist,
            'album': track.album,
            'path': track.path
        })

        if result.deleted_count > 0:
            return StreamingServer.Status(HTTPStatus.OK.value, HTTPStatus.OK.phrase)
        else:
            return StreamingServer.Status(HTTPStatus.NOT_MODIFIED.value, HTTPStatus.NOT_MODIFIED.phrase)

    def _findToTrackSequence(self, findResult):
        """Transform a mongodb result to a StreamingServer.trackSequence
        
        Arguments:
            findResult {mongodb result} -- mongodb result
        
        Returns:
            StreamingServer.trackSequence -- a sequence of tracks
        """
        result = []

        for track in findResult:
            trackO = StreamingServer.Track(
                track.get('title'),
                track.get('album'),
                track.get('artist'),
                track.get('path')
            )
            result.append(trackO)

        return result