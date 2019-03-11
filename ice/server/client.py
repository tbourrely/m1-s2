import sys, Ice, json
sys.path.insert(0, './generated/')
import MP3Collection

with Ice.initialize(sys.argv) as communicator:
    base = communicator.stringToProxy("MP3Collection:default -p 10000")
    collection = MP3Collection.CollectionPrx.checkedCast(base)
    if not collection:
        raise RuntimeError("Invalid proxy")

    testTrack = MP3Collection.Track('August Burns Red', 'Carpe Diem', '2017', 'filepath')
    collection.add(testTrack)

    menu = "========\nOptions:\n1) Add\n2) Remove\n3) Search\n========"
    print(menu)

    choice = int(input("Choice : "))

    if (1 == choice):
        artist = input('Artist : ')
        name = input('Name : ')
        year = input('Year : ')
        file = 'filepath'
        track = MP3Collection.Track(artist, name, year, file)
        collection.add(track)
    elif (2 == choice):
        name = input('Name : ')
        result = collection.search('name', name)

        if 'None' != result:
            result = json.loads(result)
            track = MP3Collection.Track(
                result.get('artist'), 
                result.get('name'),
                result.get('year'),
                result.get('file')
            )
            collection.remove(track)
        else:
            print('No matching file to remove')

    else:
        name = input('Name : ')
        result = collection.search('name', name)

        if result != 'None':
            track = json.loads(result)
            print("Artist : " + track.get('artist'))
            print("Name : " + track.get('name'))
            print("Year : " + track.get('year'))
            print("File : " + track.get('file'))
        else :
            print("No match found")