#pragma once

module MP3Collection
{
    struct Track {
        string artist;
        string name;
        string year;
        string file;
    };

    interface Collection
    {
        string search(string key, string value);
        void add(Track track);
        void remove(Track track);
    }
}