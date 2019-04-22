import os
from http import HTTPStatus
from database.Client import Client
from secrets import token_urlsafe
import StreamingServer

class SecurityManagerI(StreamingServer.SecurityManager):
    def __init__(self):
        self.db = Client()
        self.dbSecurity = self.db.security

    def addToken(self, file, current=None):
        token = token_urlsafe(int(os.getenv('TOKEN_LENGTH')))

        self.dbSecurity.tokens.insert_one({
            'file' : file,
            'token': token
        })

        return token

    def removeToken(self, token, current=None):
        result = self.dbSecurity.tokens.delete_many({
            'token' : token
        })

        return result.deleted_count > 0
        
    def addManager(self, current=None):
        apiKey = token_urlsafe(int(os.getenv('API_KEY_LENGTH')))  

        self.dbSecurity.authorized_managers.insert_one({
            'api_key' : apiKey
        })

        return apiKey

    def removeManager(self, apiKey, current=None):
        result = self.dbSecurity.authorized_managers.delete_many({
            'api_key' : apiKey
        })

        return result.deleted_count > 0
        
    def listTokens(self, current=None):
        result = []
        tokensList = self.dbSecurity.tokens.find()

        for token in tokensList:
            tokenO = StreamingServer.Token(token.get('file'), token.get('token'))
            result.append(tokenO)

        return result

    def listManagers(self, current=None):
        result = []
        managersList = self.dbSecurity.authorized_managers.find()

        for manager in managersList:
            managerO = StreamingServer.Manager(manager.get('api_key'))
            result.append(managerO)
 
        return result