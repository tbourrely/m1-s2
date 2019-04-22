import sys, Ice, json
sys.path.insert(0, './generated/')
import StreamingServer

with Ice.initialize(sys.argv) as communicator:
    base = communicator.stringToProxy("StreamingServer:default -p 10000")
    server = StreamingServer.ServerPrx.checkedCast(base)
    if not server:
        raise RuntimeError("Invalid proxy")

    
    list = server.list()
    print(list)

    # menu = "========\nOptions:\n1) Add\n2) Remove\n3) Search\n========"
    # print(menu)

    # choice = int(input("Choice : "))

    # if (1 == choice):
    #     artist = input('Artist : ')
    #     name = input('Name : ')
    #     year = input('Year : ')
    #     file = 'filepath'
    #     track = MP3Collection.Track(artist, name, year, file)
    #     collection.add(track)
    # elif (2 == choice):
    #     name = input('Name : ')
    #     result = collection.search('name', name)

    #     if 'None' != result:
    #         result = json.loads(result)
    #         track = MP3Collection.Track(
    #             result.get('artist'), 
    #             result.get('name'),
    #             result.get('year'),
    #             result.get('file')
    #         )
    #         collection.remove(track)
    #     else:
    #         print('No matching file to remove')

    # else:
    #     name = input('Name : ')
    #     result = collection.search('name', name)

    #     if result != 'None':
    #         print("Artist : " + result.artist)
    #         print("Name : " + result.name)
    #         print("Year : " + result.year)
    #         print("File : " + result.file)
    #     else :
    #         print("No match found")