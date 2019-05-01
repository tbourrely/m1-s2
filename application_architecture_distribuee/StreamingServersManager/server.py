from flask import Flask, request, Response
from pathlib import Path  # python3 only
from dotenv import load_dotenv

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

if __name__ == '__main__':
    app.run(debug=True)