import Ice, sys
from pathlib import Path  # python3 only
from dotenv import load_dotenv

env_path = Path('.') / './.env'
load_dotenv(dotenv_path=env_path)

sys.path.insert(0, './generated/')

from controllers.Server import ServerI

with Ice.initialize(sys.argv) as communicator:
    adapter = communicator.createObjectAdapterWithEndpoints("StreamingServerAdapter", "default -p 10000")
    object = ServerI()
    adapter.add(object, communicator.stringToIdentity("StreamingServer"))
    adapter.activate()
    print("Listening on port 10000")
    communicator.waitForShutdown()