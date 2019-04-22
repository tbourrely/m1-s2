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
        string play(Track track, string apiKey);
        Status add(Track track);
        Status remove(Track track);
    }

    class Token {
        string file;
        string token;
    }

    class Manager {
        string apiKey;
    }

    sequence<Token> tokenList;
    sequence<Manager> managerList;

    interface SecurityManager {
        string addToken(string file, string apiKey);
        bool removeToken(string token);
        string addManager();
        bool removeManager(string apiKey);
        tokenList listTokens();
        managerList listManagers();
    }
}
    