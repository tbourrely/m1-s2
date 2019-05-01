import pymongo
import urllib.parse
import os


class Client:
    instance=None

    def __new__(self):
        if not Client.instance:
            Client.instance = pymongo.MongoClient(
                host=os.getenv('MONGODB_HOST'),
                port=int(os.getenv('MONGODB_PORT')),
                username=os.getenv('MONGODB_USER'),
                password=os.getenv('MONGODB_PASSWORD'),
                authSource='admin'
            )

        return Client.instance
