from flask import Flask, request, Response
import os, base64
from pathlib import Path  # python3 only
from dotenv import load_dotenv

env_path = Path('.') / '.env'
load_dotenv(dotenv_path=env_path)

from database.Client import Client
from controllers.utils import doesFileExists

app = Flask(__name__)

def streamFile(fileName):
    path = './' + os.getenv('FILES_DIR') + '/' + fileName
    with open(path, 'rb') as r:
        data = r.read(1024)
        while data:
            yield data
            data = r.read(1024)

        r.close()

@app.route("/stream/<string:encodedFileName>")
def stream(encodedFileName):
    filename = base64.b64decode(encodedFileName.encode()).decode()
    fileExists = doesFileExists(filename)

    if not fileExists:
        return Response(status=404)
    
    token = request.args.get('token')

    if token is None or 0 == len(token):
        return Response(status=401)

    tokenFromDB = Client().security.tokens.find_one({'token' : token, 'file': filename})

    if tokenFromDB is None:
        return Response(status=401)
    # TODO
    # delete token ?
    return Response(streamFile(filename), mimetype="audio/aac")

if __name__ == '__main__':
    app.run(debug=False, port=5000, host=('0.0.0.0'))
