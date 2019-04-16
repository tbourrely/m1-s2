#pragma once

module Sharing 
{
    class User 
    {
        string username;
        string firstname;
        string lastname;
        string password;
        bool connected;
    }

    sequence<MP3Collection::Track> trackList;
    sequence<User> userList;

    class Playlist 
    {
        string id;
        User creator;
        string name;
        trackList tracks;
        userList authorizedUsers;
    }

    interface Auth
    {
        bool login(string username, string password);
        bool logout(string username);
    }
}