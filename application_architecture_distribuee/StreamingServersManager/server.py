from flask import Flask, request, Response
from pathlib import Path  # python3 only
import subprocess
from dotenv import load_dotenv
import sys

env_path = Path('.') / '.env'
load_dotenv(dotenv_path=env_path)

# controllers
from controllers import Servers

app = Flask(__name__)

@app.route('/servers', methods=['GET', 'POST', 'DELETE'])
def handleServers():
    if request.method == 'GET':
        return Servers.get()
    elif request.method == 'POST':
        return Servers.post()
    else:
        return Servers.delete()

@app.route('/tracks/index', methods=['GET'])
def handleTracksIndex():
    subprocess.Popen([sys.executable, "runIndexer.py"])
    return Response('indexing started')

if __name__ == '__main__':
    app.run(debug=True)