import pymongo
import urllib.parse

username = urllib.parse.quote_plus('admin')
password = urllib.parse.quote_plus(":X.;@{xhdCD~")

client = pymongo.MongoClient("mongodb+srv://" + username + ":" + password + "@cluster0-ynurm.mongodb.net/test?retryWrites=true")
db = client.streaming_manager
items = db.items

result = items.find()

print(result)