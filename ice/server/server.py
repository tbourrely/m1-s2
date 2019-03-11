import sys, Ice, json
sys.path.insert(0, './generated/')
import MP3Collection

class MP3CollectionI(MP3Collection.Collection):
    def __init__(self):
        self.trackList = []

    def add(self, track, current=None):
        self.trackList.append(track)
        print("new track added")
        self.showList()

    def remove(self, track, current=None):
        self.trackList.remove(track)
        print("a track has been removed")
        self.showList()

    def search(self, key, value, current=None):
        for track in self.trackList:
            if getattr(track, key) == value:
                return json.dumps(track.__dict__)

        return 'None'

    def showList(self):
        print("=========")
        for track in self.trackList:
            print(track.name)
        print("=========")

with Ice.initialize(sys.argv) as communicator:
    adapter = communicator.createObjectAdapterWithEndpoints("MP3CollectionAdapter", "default -p 10000")
    object = MP3CollectionI()
    adapter.add(object, communicator.stringToIdentity("MP3Collection"))
    adapter.activate()
    print("Listening on port 10000")
    communicator.waitForShutdown()