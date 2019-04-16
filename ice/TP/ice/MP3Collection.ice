#pragma once

module MP3Collection
{
    class Track {
        string artist;
        string name;
        string year;
        string file;
    }

    interface Collection
    {
        Track search(string key, string value);
        void add(Track track);
        void remove(Track track);
    }
}