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

        if (1 == choice):
            filename = input('file name : ')
            result = securityManager.addToken(filename)

            print("Token : " + result)

        elif (2 == choice):
            token = input('token : ')
            result = securityManager.removeToken(token)

            text = "Success" if result else "Fail"
            print(text)

        elif (3 == choice):
            listT = securityManager.listTokens()

            for token in listT:
                print("=======================")
                print("File : " + token.file)
                print("Token : " + token.token)
                print("=======================")

        elif (4 == choice):
            result = securityManager.addManager()
            print("Api Key : " + result)

        elif (5 == choice):
            apiKey = input("Api Key : ")
            result = securityManager.removeManager(apiKey)
            text = "Success" if result else "Fail"
            print(text)
        
        elif (6 == choice):
            listM = securityManager.listManagers()

            for manager in listM:
                print("=======================")
                print("Api Key : " + manager.apiKey)
                print("=======================")

        print(menu)
        choice = int(input("Choice : "))
    
    