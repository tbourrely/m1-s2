import pymongo
import urllib.parse
import os


class Client:
    instance=None

    def __new__(self):
        if not Client.instance:
            username = urllib.parse.quote_plus(os.getenv('MONGODB_USER'))
            password = urllib.parse.quote_plus(os.getenv('MONGODB_PASSWORD'))

            Client.instance = pymongo.MongoClient("mongodb+srv://" + username + ":" + password+ "@cluster0-ynurm.mongodb.net/test?retryWrites=true")

        return Client.instance
