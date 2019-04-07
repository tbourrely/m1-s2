import pymongo
import urllib.parse
from dotenv import load_dotenv
from pathlib import Path
import os
env_path = Path('..') / '.env'
load_dotenv(dotenv_path=env_path)


username = urllib.parse.quote_plus(os.getenv('MONGODB_USER'))
password = urllib.parse.quote_plus(os.getenv('MONGODB_PASSWORD'))

client = pymongo.MongoClient("mongodb+srv://" + username + ":" + password + "@cluster0-ynurm.mongodb.net/test?retryWrites=true")
db = client.streaming_manager
items = db.tracks

result = items.find({'title': 'arose'})

for song in result:
    print(song)