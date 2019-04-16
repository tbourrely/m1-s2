from flask import Flask, Response, request
import pymongo
import urllib.parse
from dotenv import load_dotenv
import os
import controllers.tokens as tokensController

load_dotenv()
TOKEN_LENGTH = 24
FILES_DIR = 'tracks'

username = urllib.parse.quote_plus(os.getenv('MONGODB_USER'))
password = urllib.parse.quote_plus(os.getenv('MONGODB_PASSWORD'))

client = pymongo.MongoClient("mongodb+srv://" + username + ":" + password+ "@cluster0-xlc7s.mongodb.net/security?retryWrites=true")
security_db = client.security


app = Flask(__name__)

def streamFile(fileName):
    path = './' + FILES_DIR + '/' + fileName
    with open(path, 'rb') as r:
        data = r.read(1024)
        while data:
            yield data
            data = r.read(1024)

        r.close()



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


if __name__ == '__main__':
    app.run(debug=True)
