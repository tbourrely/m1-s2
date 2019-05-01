import sys, Ice, json
sys.path.insert(0, './generated/')
import StreamingServer

with Ice.initialize(sys.argv) as communicator:
    baseServer = communicator.stringToProxy("Server:default -p 10000")
    server = StreamingServer.ServerPrx.checkedCast(baseServer)

    if not server:
        raise RuntimeError("Invalid proxy")

    menu = "========\nOptions:\n1) Add Track\n2) Remove Track\n3) Search Track\n4) List Tracks\n5) Play\n6) Exit\n========"
    print(menu)

    choice = int(input("Choice : "))

    while (6 != choice) :

        if 1 == choice:
            title = input('Title : ')
            album = input('Album : ')
            artist = input('Artist : ')
            path = input('Path (starting from tracks folder) : ')

            track = StreamingServer.Track(title, album, artist, path)
            result = server.add(track)

            text = "Success" if result.code == 200 else "Fail"
            print(text)
        
        elif 2 == choice:
            path = input("Path : ")

            result = server.search('path', path)

            if len(result) > 0:
                for item in result:
                    r = server.remove(item)
                    text = "Success" if r.code == 200 else "Fail"
                    print(text)
            else:
                print('No match')

        elif 3 == choice:
            key = input('key : ')
            value = input('value : ')

            result = server.search(key, value)

            print("=====================")
            for item in result:
                print("Title : " + item.title)
                print("Album : " + item.album)
                print("Artist : " + item.artist)
                print("Path : " + item.path)
                print("-----------------------")
            print("=====================")

        elif 4 == choice:
            result = server.list()

            print("=====================")
            for item in result:
                print("Title : " + item.title)
                print("Album : " + item.album)
                print("Artist : " + item.artist)
                print("Path : " + item.path)
                print("-----------------------")
            print("=====================")
        
        elif 5 == choice:
            path = input('path : ')
            apiKey = input('apiKey : ')
            result = server.search('path', path)

            if len(result) == 1:
                item = result[0]
                playResult = server.play(item, apiKey)
                text = playResult if playResult != "-1" else "Fail"
                print(text)

            elif len(result) > 1:
                print('Multiple matches ?!?')

            else:
                print('No match')

        input('Press any key to continue')
        print(menu)
        choice = int(input("Choice : "))