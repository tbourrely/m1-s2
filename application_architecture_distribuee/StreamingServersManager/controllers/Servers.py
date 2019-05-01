from flask import request, Response, jsonify
from database.Client import Client

client = Client().streaming_manager

def post():
    """Add a server to database
    
    Returns:
        Response -- 
    """
    ip = request.form['ip']

    if client.servers.find_one({'ip': ip}):
        return Response('already exists', status=409)
    
    result = client.servers.insert_one({'ip': ip})

    if result.inserted_id is not None:
        return Response(status=200)

    return Response(status=304)

def get():
    """Return the servers list
    
    Returns:
        json -- available servers list 
    """
    serverList = []
    for server in client.servers.find():
        serverList.append(server.get('ip'))

    return jsonify(serverList)

def delete():
    """Delete a server from database
    
    Returns:
        Response -- 
    """
    ip = request.form['ip']

    result = client.servers.delete_one({'ip': ip})

    if result.deleted_count > 0:
        return Response(status=200)
    
    return Response(status=304)