import os
from secrets import token_urlsafe
from database.Client import Client
from controllers.utils import doesFileExists
import StreamingServer

db = Client()

def create(file, apiKey):
    """Add a token in db
    
    Arguments:
        file {string} -- The file path that the token protect
        apiKey {string} -- THe api key of the manager that creates the token
    
    Returns:
        string -- "-1" if api key is invalid
                    "-2" if the file wanted does not exists the filesystem
                    "-3" if the file wanted does not exists in the list of tracks in database
                    "[token]" the generated token
    """
    isApiValid = db.security.authorized_managers.find_one({
        'api_key' : apiKey
    })

    if isApiValid is None:
        return "-1" # api key is invalid

    if not doesFileExists(file):
        return "-2" # file does not exists

    trackInDb = db.data.tracks.find_one({'path' : file})

    if trackInDb is None:
        return "-3" # track not registered in DB

    token = token_urlsafe(int(os.getenv('TOKEN_LENGTH')))

    db.security.tokens.insert_one({
        'file' : file,
        'token': token
    })

    return token

def remove(token):
    """Remove a token
    
    Arguments:
        token {string} -- the token to remove
    
    Returns:
        bool -- True if deleted
                False otherwise
    """
    result = db.security.tokens.delete_many({
        'token' : token
    })

    return result.deleted_count > 0

def listTokens():
    """List the available tokens
    
    Returns:
        list -- tokens
    """
    result = []
    tokensList = db.security.tokens.find()

    for token in tokensList:
        tokenO = StreamingServer.Token(token.get('file'), token.get('token'))
        result.append(tokenO)

    return result
