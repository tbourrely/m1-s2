from secrets import token_urlsafe
from bson.json_util import dumps
from flask import Response, request
from controllers.utils import doesFileExists


def createToken():
    filename = request.form['file']

    if not filename:
        return Response(status=400)

    fileExists = doesFileExists(filename)

    if not fileExists:
        return Response(status=404)

    apiKey = request.form['api_key']

    if apiKey is None or 0 == len(apiKey):
        return Response(status=401)

    apiKeyFromDB = security_db.authorized_managers.find_one({'api_key' : apiKey})

    if apiKeyFromDB is None:
        return Response(status=401)

    newtoken = {
        'file' : filename,
        'token' : token_urlsafe(TOKEN_LENGTH)
    }

    while security_db.tokens.find_one({'token' : newtoken.get('token')}) is not None:
        newtoken['token'] = token_urlsafe(TOKEN_LENGTH)

    security_db.tokens.insert_one(newtoken)

    return Response('Token created')


def deleteToken():
    token = request.args.get('token')

    if not token:
        return Response(status=400)

    result = security_db.tokens.delete_one({'token' : token})

    if 1 == result.deleted_count:
        return Response(status=200)
    
    return Response(status=304)

def getTokenList():
    return Response(dumps(security_db.tokens.find()), mimetype='text/json')