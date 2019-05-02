from flask import request, Response, jsonify
from database.Client import Client

client = Client().streaming_manager

def get():
    """Return the list of available tracks
    
    Returns:
        list -- track list
    """
    tracksList = []

    for track in client.tracks.find():
        tracksList.append({
            'title': track.get('title'),
            'artist': track.get('artist'),
            'album': track.get('album'),
            'path': track.get('path'),
            'servers': track.get('servers')
        })

    return jsonify(tracksList)