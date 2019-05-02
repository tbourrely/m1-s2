from flask import request, Response, jsonify
from database.Client import Client
import operator, sys, Ice, os

sys.path.insert(0, './generated/')
import StreamingServer

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


def play():
    """Find the tracks matching the request and return json containing the number of tracks and the streaming links

    possible form-data parameters:
        - title
        - artist
        - album
    
    Returns:
        string(json) -- [description]
    """
    title = request.form.get('title', None)
    artist = request.form.get('artist', None)
    album = request.form.get('album', None)

    search = {}

    if title:
        search['title'] = title

    if artist:
        search['artist'] = artist

    if album:
        search['album'] = album

    matchingTracks = []
    for t in client.tracks.find(search):
        matchingTracks.append({
            'title': t.get('title'),
            'artist': t.get('artist'),
            'album': t.get('album'),
            'path': t.get('path'),
            'servers': t.get('servers')
        })

    if len(matchingTracks) == 0:
        return jsonify({'nbTracks': 0, 'tracks': []})

    if len(matchingTracks) == 1:
        return jsonify({'nbTracks': 1, 'tracks': [
            requestLink(matchingTracks[0].get('servers')[0], matchingTracks[0])
        ]}) # take the first server as we only want to play one track
    
    linksList = []

    for track in matchingTracks:
        for s in track.get('servers'):
            link = requestLink(s, track)

            if link:
                linksList.append(link)
                break

                
    return jsonify({
        'nbTracks': len(linksList),
        'tracks': linksList
    })


def requestLink(serverIp, track):
    """Try to get the streaming link for a track
    
    Arguments:
        serverIp {string} -- the server's ip address
        track {dict} -- a dictionnary representing a track, transformed in {StreamingServer.Track} later in the code
    
    Returns:
        boolean|string -- False if could not fetch the link, otherwise string (link)
    """
    with Ice.initialize(sys.argv) as communicator:
        try:
            baseServer = communicator.stringToProxy("Server:default -h {0} -p {1}".format(serverIp, os.getenv('ICE_PORT')))
            server = StreamingServer.ServerPrx.checkedCast(baseServer)

            if not server:
                return False
            
            trackObject = StreamingServer.Track(
                track.get('title'),
                track.get('album'),
                track.get('artist'),
                track.get('path')
            )
            playResult = server.play(trackObject, os.getenv('API_KEY'))

            return playResult if playResult != '-1' else False

        except Ice.ConnectFailedException:
            return False


