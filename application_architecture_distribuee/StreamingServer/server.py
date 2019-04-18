from flask import Flask, request
import os
from pathlib import Path  # python3 only
from dotenv import load_dotenv

env_path = Path('.') / '.env'
load_dotenv(dotenv_path=env_path)

import controllers.tokens as tokensController
import controllers.apiKey as apiKeyController

app = Flask(__name__)


# def streamFile(fileName):
#     path = './' + FILES_DIR + '/' + fileName
#     with open(path, 'rb') as r:
#         data = r.read(1024)
#         while data:
#             yield data
#             data = r.read(1024)

#         r.close()

# @app.route("/stream/<string:filename>", methods=['GET'])
# def stream(filename):
#     fileExists = doesFileExists(filename)

#     if not fileExists:
#         return Response(status=404)
    
#     token = request.args.get('token')

#     if token is None or 0 == len(token):
#         return Response(status=401)

#     tokenFromDB = security_db.tokens.find_one({'token' : token, 'file': filename})

#     if tokenFromDB is None:
#         return Response(status=401)

#     print(tokenFromDB)
    
#     # TODO
#     # Return stream + delete token
#     # rv = Response(streamFile(filename), mimetype="audio/aac")
    
#     return Response('token : ' + token)

@app.route('/token', methods=['GET', 'DELETE', 'POST'])
def token():
    if 'GET' == request.method:
        return tokensController.getTokenList()
    elif 'DELETE' == request.method:
        return tokensController.deleteToken()
    else:
        return tokensController.createToken()

@app.route('/api_key', methods=['GET', 'DELETE', 'POST'])
def apiKey():
    if 'GET' == request.method:
        return apiKeyController.getApiKeyList()
    elif 'POST' == request.method:
        return apiKeyController.postApiKey()
    else:
        return apiKeyController.deleteApiKey()

if __name__ == '__main__':
    app.run(debug=True)
