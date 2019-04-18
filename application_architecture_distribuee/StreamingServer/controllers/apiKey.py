from database.Client import Client
from flask import Response, request
from bson.json_util import dumps
from secrets import token_urlsafe
import os

security_db = Client().security
authorized_managers_db = security_db.authorized_managers

def postApiKey():
    API_KEY_LENGTH = int(os.getenv('API_KEY_LENGTH'))
    apiKey = token_urlsafe(API_KEY_LENGTH)

    while authorized_managers_db.find_one({'api_key' : apiKey}) is not None:
        apiKey = token_urlsafe(API_KEY_LENGTH)

    inserted_id = authorized_managers_db.insert_one({'api_key' : apiKey}).inserted_id

    return Response(dumps(authorized_managers_db.find_one({'_id' : inserted_id})), mimetype='text/json')
    

def deleteApiKey():
    apiKey = request.args.get('api_key')

    if not apiKey:
        return Response(status=400)

    result = authorized_managers_db.delete_one({'api_key' : apiKey})

    if 1 == result.deleted_count:
        return Response(status=200)

    return Response(status=304)

def getApiKeyList():
    return Response(dumps(authorized_managers_db.find()), mimetype='text/json')