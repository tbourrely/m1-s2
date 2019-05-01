db.auth('streamingserver', 'wEjjASLg5Jsqjtcv')

// data
db = db.getSiblingDB('data');
// allow case insensitive search
db.createCollection('tracks', {
    collation: {
        locale: 'en_US',
        strength: 1
    }
});
// TODO: check if needed
db.tracks.createIndex({title: 1});
db.tracks.createIndex({artist: 1});
db.tracks.createIndex({album: 1});
db.tracks.createIndex({path: 1});
const trackList = [
    {
        title: 'arose',
        artist: 'eminem',
        album: 'revival',
        path: 'eminem/revival/arose.aac'
    },
    {
        title: 'believe',
        artist: 'eminem',
        album: 'revival',
        path: 'eminem/revival/believe.aac'
    }
];
db.tracks.insertMany(trackList);

// security
db = db.getSiblingDB('security');
db.authorized_managers.insertOne({api_key: 'NLLmSur0f3tWHFLcIrs08nj7Mdt0Y5WQkiLqvEtTbNc'});
db.tokens.insertOne({file: 'eminem/revival/arose.aac', token: 'oW4BMfXypNjPd9VWR6x7lklZUdQNJE97'});
