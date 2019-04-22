from http import HTTPStatus
from database.Client import Client
import StreamingServer

class ServerI(StreamingServer.Server):
    def __init__(self):
        self.db = Client()
        self.dbData = self.db.data
        self.dbSecurity = self.db.security

    def search(self, key, value, current=None):
        return self._findToTrackSequence(self.dbData.tracks.find({key : value}))

    def list(self, current=None):
        return self._findToTrackSequence(self.dbData.tracks.find())

    def play(self, track, current=None):
        raise NotImplementedError("servant method 'play' not implemented")

    def add(self, track, current=None):
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