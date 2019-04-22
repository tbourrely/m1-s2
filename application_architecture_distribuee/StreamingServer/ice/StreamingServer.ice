# pragma once

module StreamingServer {
    class Track {
        string title;
        string album;
        string artist;
        string path;
    }

    sequence<Track> trackSequence;

    class Status {
        int code;
        string message;
    }

    interface Server {
        trackSequence search(string key, string value);
        trackSequence list();
        string play(Track track);
        Status add(Track track);
        Status remove(Track track);
    }

    class Token {
        string file;
        string token;
    }

    class Manager {
        string api_key;
    }

    interface SecurityManager {
        string addtoken(string file, string token);
        bool removeToken(string token);
        string addManager();
        bool removeManager(string apiKey);
    }
}
    