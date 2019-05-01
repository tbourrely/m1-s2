import sys, Ice, json
sys.path.insert(0, './generated/')
import StreamingServer

with Ice.initialize(sys.argv) as communicator:
    baseSecurityManager = communicator.stringToProxy("SecurityManager:default -p 10000")
    securityManager = StreamingServer.SecurityManagerPrx.checkedCast(baseSecurityManager)

    if not securityManager:
        raise RuntimeError("Invalid proxy")

    menu = "========\nOptions:\n1) Add Token\n2) Remove Token\n3) List Tokens\n4) Add Manager\n5) Remove Manager\n6) List Managers\n7) Exit\n========"
    print(menu)

    choice = int(input("Choice : "))

    while(7 != choice):
        # Add Token
        if (1 == choice):
            filename = input('file path (from tracks directory) : ')
            apiKey = input('Api Key : ')
            result = securityManager.addToken(filename, apiKey)

            if "-1" == result:
                text = "Invalid Api Key"
            elif "-2" == result:
                text = "Invalid file path"
            elif "-3" == result:
                text = "Track not in DB"
            else:
                text = "Token" + result

            print(text)

        # Remove Token
        elif (2 == choice):
            token = input('token : ')
            result = securityManager.removeToken(token)

            text = "Success" if result else "Fail"
            print(text)

        # List Tokens
        elif (3 == choice):
            listT = securityManager.listTokens()

            for token in listT:
                print("=======================")
                print("File : " + token.file)
                print("Token : " + token.token)
                print("=======================")

        # Add Api Key
        elif (4 == choice):
            result = securityManager.addManager()
            print("Api Key : " + result)

        # Remove Api Key
        elif (5 == choice):
            apiKey = input("Api Key : ")
            result = securityManager.removeManager(apiKey)
            text = "Success" if result else "Fail"
            print(text)
        
        # List Api Keys
        elif (6 == choice):
            listM = securityManager.listManagers()

            for manager in listM:
                print("=======================")
                print("Api Key : " + manager.apiKey)
                print("=======================")


        input("Press any key to continue")
        print(menu)
        choice = int(input("Choice : "))
    
    