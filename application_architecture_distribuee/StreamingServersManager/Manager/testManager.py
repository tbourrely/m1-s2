from Manager import ManagerI
import sys

sys.path.insert(0, './..//generated/')
import StreamingServerManager

manager = ManagerI()

# songs = manager.search("album", "revival")

# for song in songs:
#     print(song.get('title'))

# server = StreamingServerManager.Server("192.168.0.8")
# trackA = StreamingServerManager.Track("Castle", "Eminem", "rEvival", "none")
# status = manager.add(trackA, server)
# print(status)

# server = StreamingServerManager.Server("192.168.0.8")
# trackA = StreamingServerManager.Track("Castle", "Eminem", "rEvival", "none")
# status = manager.remove(trackA, server)
# print(status)