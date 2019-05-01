import Ice, sys
from pathlib import Path  # python3 only
from dotenv import load_dotenv

env_path = Path('.') / './.env'
load_dotenv(dotenv_path=env_path)

sys.path.insert(0, './generated/')

from controllers.Server import ServerI
from controllers.SecurityManager import SecurityManagerI

with Ice.initialize(sys.argv) as communicator:
    adapter = communicator.createObjectAdapterWithEndpoints("StreamingServerAdapter", "default -p 10000")
    adapter.add(ServerI(), communicator.stringToIdentity("Server"))
    adapter.add(SecurityManagerI(), communicator.stringToIdentity("SecurityManager"))
    adapter.activate()
    print("Listening on port 10000")
    communicator.waitForShutdown()