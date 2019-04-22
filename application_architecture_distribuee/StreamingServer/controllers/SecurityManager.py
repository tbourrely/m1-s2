import os
from http import HTTPStatus
from database.Client import Client
from secrets import token_urlsafe
from controllers.utils import doesFileExists
import StreamingServer

class SecurityManagerI(StreamingServer.SecurityManager):
    def __init__(self):
        self.db = Client()
        self.dbSecurity = self.db.security
        self.dbData = self.db.data

    def addToken(self, file, apiKey, current=None):
        """Add a token
        
        Arguments:
            file {string} -- The file path that the token protect
            apiKey {string} -- THe api key of the manager that creates the token
        
        Keyword Arguments:
            current {[type]} -- [description] (default: {None})
        
        Returns:
            string -- "-1" if api key is invalid
                      "-2" if the file wanted does not exists the filesystem
                      "-3" if the file wanted does not exists in the list of tracks in database
                      "[token]" the generated token
        """
        isApiValid = self.dbSecurity.authorized_managers.find_one({
            'api_key' : apiKey
        })

        if isApiValid is None:
            return "-1" # api key is invalid

        if not doesFileExists(file):
            return "-2" # file does not exists

        trackInDb = self.dbData.tracks.find_one({'path' : file})

        if trackInDb is None:
            return "-3" # track not registered in DB

        token = token_urlsafe(int(os.getenv('TOKEN_LENGTH')))

        self.dbSecurity.tokens.insert_one({
            'file' : file,
            'token': token
        })

        return token

    def removeToken(self, token, current=None):
        """Remove a token
        
        Arguments:
            token {string} -- the token to remove
        
        Returns:
            bool -- True if deleted
                    False otherwise
        """
        result = self.dbSecurity.tokens.delete_many({
            'token' : token
        })

        return result.deleted_count > 0
        
    def addManager(self, current=None):
        """Add an api key
        
        Returns:
            string -- the generated api key
        """
        apiKey = token_urlsafe(int(os.getenv('API_KEY_LENGTH')))  

        self.dbSecurity.authorized_managers.insert_one({
            'api_key' : apiKey
        })

        return apiKey

    def removeManager(self, apiKey, current=None):
        """Delete an api key
        
        Arguments:
            apiKey {string} -- the api key to remove
        
        Returns:
            bool -- True if deleted
                    False otherwise
        """
        result = self.dbSecurity.authorized_managers.delete_many({
            'api_key' : apiKey
        })

        return result.deleted_count > 0
        
    def listTokens(self, current=None):
        """List the available tokens
        
        Returns:
            list -- tokens
        """
        result = []
        tokensList = self.dbSecurity.tokens.find()

        for token in tokensList:
            tokenO = StreamingServer.Token(token.get('file'), token.get('token'))
            result.append(tokenO)

        return result

    def listManagers(self, current=None):
        """List the available api keys
        
        Returns:
            list -- api keys
        """
        result = []
        managersList = self.dbSecurity.authorized_managers.find()

        for manager in managersList:
            managerO = StreamingServer.Manager(manager.get('api_key'))
            result.append(managerO)
 
        return result