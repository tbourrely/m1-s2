import pymongo
import urllib.parse

class DBClient:
    def __init__(self, settings):
        self.username = urllib.parse.quote_plus(settings.get('MONGODB_USER'))
        self.password = urllib.parse.quote_plus(settings.get('MONGODB_PASSWORD'))
        self.mongoClient = pymongo.MongoClient("mongodb+srv://" + self.username + ":" + self.password + "@cluster0-ynurm.mongodb.net/test?retryWrites=true")

    def getClient(self):
        return self.mongoClient
