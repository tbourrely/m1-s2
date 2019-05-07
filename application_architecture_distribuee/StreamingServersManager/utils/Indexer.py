import sys, Ice
import os
from pathlib import Path  # python3 only
from dotenv import load_dotenv
from utils.Logger import Logger

env_path = Path('.') / '.env'
load_dotenv(dotenv_path=env_path)

from database.Client import Client

sys.path.insert(0, './generated/')
import StreamingServer


client = Client().streaming_manager
logger = Logger('indexer')

DEFAULT_PORT = os.getenv('ICE_PORT')

# --------------------------------------------------------------------------------------------------

def cleanTracksDb():
    client.tracks.delete_many({})

def testIfExists(path):
    result = client.tracks.find_one({'path': path})

    if result is not None:
        return True
    else:
        return False

def updateServerList(path, server):
    result = client.tracks.update_one(
        {'path': path},
        {'$push': {'servers': server}}
    )

    if result.modified_count > 0:
        return True
    
    return False

def insertTrack(track, server):
    result = client.tracks.insert_one({
        'title': track.title,
        'artist': track.artist,
        'album': track.album,
        'path': track.path,
        'servers': [server]
    })

    if result.inserted_id is not None:
        return True
    
    return False

def connectAndList(serverIp, port):
    with Ice.initialize(sys.argv) as communicator:

        try:
            baseServer = communicator.stringToProxy("Server:default -h {0} -p {1}".format(serverIp, port))
            server = StreamingServer.ServerPrx.checkedCast(baseServer)

            if not server:
                raise RuntimeError("Invalid proxy")

            trackList = server.list()
            
            for track in trackList:
                logger.write('Track title : ' + track.title)

                if testIfExists(track.path):
                    if updateServerList(track.path, serverIp):
                        logger.write('--> updated')
                    else:
                        logger.write('--> could not update')
                else:
                    if insertTrack(track, serverIp):
                        logger.write('--> inserted')
                    else:
                        logger.write('--> could not insert')

        except:
            logger.write('could not connect to server')

# --------------------------------------------------------------------------------------------------

def run():
    logger.write('----------------------------------------------------')
    logger.write('--------           START INDEXING           --------')
    logger.write('----------------------------------------------------')
    cleanTracksDb() # delete all documents
    for server in client.servers.find():
        logger.write('--- Server : ' + server.get('ip') + ' ---')
        connectAndList(server.get('ip'), DEFAULT_PORT)
        logger.write('-----------------------------------------')
    logger.write('----------------------------------------------------')
    logger.write('--------          STOPPED INDEXING          --------')
    logger.write('----------------------------------------------------')


