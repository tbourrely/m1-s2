import sys, Ice, json, pymongo
sys.path.insert(0, './generated/')
import StreamingServerManager

class ManagerI(StreamingServerManager.Manager):
    def __init__(self, *args, **kwargs):
        self.trackList = []

    def search(self, key, value, current=None):
        return []

    def list(self, current=None):
        return []

    def play(self, track, current=None):
        return ""

    def add(self, track, current=None):
        return StreamingServerManager.Status(200, 'OK')

    def remove(self, track, current=None):
        return StreamingServerManager.Status(200, 'OK')