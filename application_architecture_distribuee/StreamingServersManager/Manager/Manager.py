import sys, Ice
from http import HTTPStatus

from settings.Settings import Settings
from database.DBClient import DBClient

sys.path.insert(0, './..//generated/')
import StreamingServerManager

class ManagerI(StreamingServerManager.Manager):
    def __init__(self):
        self.settings = Settings()
        self.DBClient = DBClient(self.settings)
        self.db = self.DBClient.getClient().streaming_manager

    def search(self, key, value, current=None):
        return self.db.tracks.find({key: value})

    def list(self, current=None):
        return self.db.tracks.find()

    def play(self, track, current=None):
        raise NotImplementedError("Method 'play' not implemented")

    def add(self, track, server, current=None):
        """ Add a song
            If the track already exists, only the server field is updated, 
            to contains the server that requested to add the file
        
        :type self: ManagerI
        :param self:
    
        :type track: StreamingServersManager.Track
        :param track:
    
        :type server: StreamingServersManager.Server
        :param server:
    
        :type current:
        :param current:
    
        :raises:
    
        :rtype:
        """
        alreadyExisting = self._search_existing_track(track)
        serverID = self._find_or_create(server.ip).get('_id')
        
        if alreadyExisting == None:
            # does not exists yet !
            self.db.tracks.insert_one({
                'title': track.title,
                'artist': track.artist,
                'album': track.album,
                'path': track.path,
                'servers': [serverID]
            })
        elif serverID not in alreadyExisting.get('servers'):
            # already exists BUT the server IP is not in yet !
            self.db.tracks.update_one(
                {'_id': alreadyExisting.get('_id')},
                {'$push': {'servers': serverID}}
            )
            
        return StreamingServerManager.Status(200, 'OK')

    def remove(self, track, server, current=None):
        """ Remove a track from the database
        :type self:
        :param self:
    
        :type track: StreamingServersManager.Track
        :param track:
    
        :type server: StreamingServersManager.Server
        :param server:
    
        :type current:
        :param current:
    
        :raises:
    
        :rtype:
        """    
        serverFromDB = self._find_server(server.ip)
        trackFromDB = self._search_existing_track(track)

        if serverFromDB == None or trackFromDB == None:
            return StreamingServerManager.Status(304, 'NOT MODIFIED')

        serverId = serverFromDB.get('_id')
        trackId = trackFromDB.get('_id')

        if serverId not in trackFromDB.get('servers'):
            return StreamingServerManager.Status(304, 'NOT MODIFIED')

        serverListLength = len(trackFromDB.get('servers'))

        if 1 == serverListLength:
            self.db.tracks.delete_one({'_id': trackId})
        else:
            self.db.tracks.update_one(
                {'_id': trackId},
                {'$pull': {'servers': serverId}}
            )

        # delete the server if it does not contains any songs anymore
        songsLeftForServer = self.db.tracks.count_documents({'servers': serverId})
        if 0 == songsLeftForServer:
            self.db.servers.delete_one({'_id': serverId})

        return StreamingServerManager.Status(200, 'OK')

    def _search_existing_track(self, track):
        """ Search for a song in the database
        :type self:
        :param self:
    
        :type track: StreamingServersManager.Track
        :param track:
    
        :raises:
    
        :rtype:
        """
        return self.db.tracks.find_one({
                'title': track.title,
                'artist': track.artist,
                'album': track.album
            })
    
    def _find_server(self, serverIp):
        """ Find server in DB by it's IP
        """    
        return self.db.servers.find_one({'ip': serverIp})
    
    def _find_or_create(self, serverIp):
        """ Find a server by it's IP or create it
        """
        existing = self._find_server(serverIp)

        if existing != None:
            return existing
        
        inserted_id = self.db.servers.insert_one({'ip': serverIp}).inserted_id

        return self.db.servers.find_one({'_id': inserted_id})