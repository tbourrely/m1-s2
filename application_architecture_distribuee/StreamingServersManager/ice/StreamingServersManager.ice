#pragma once

module StreamingServerManager
{
    class Server {
        string id;
        string ip;
    };

    sequence<Server> serverSequence;

    class Track {
        string id;
        string title;
        string artist;
        string album;
        string path;
        serverSequence servers;
    };

    sequence<Track> trackSequence;

    class Status {
        int code;
        string message;
    }
    
    interface Manager
    {
        trackSequence search(string key, string value);
        trackSequence list();
        string play(Track track);
        Status add(trackSequence trackList);
        Status remove(trackSequence trackList);
    }
}