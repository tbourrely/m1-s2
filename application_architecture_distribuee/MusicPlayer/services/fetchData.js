const URI = "http://10.0.2.2:5000"; // StreamingServerManager address:port

import config from '../theme/config'

export default {
  /**
   * List of avilable tracks
   */
  async fetchTracks() {
    try {
      let response = await fetch(URI + "/tracks");
      let responseData = await response.json();
      return responseData;
    } catch (e) {
      console.log(e);
    }
  },

  /**
   * Fetch track cover
   * @param {Object} track
   */
  async fetchCover(track) {
    if (track.album === undefined) return config.DEFAULT_IMG_URL; // no album name => default img

    let releaseRequest = `http://musicbrainz.org/ws/2/release-group/?query=releasegroup:${track.album}`;

    if (track.artist !== undefined)
      releaseRequest += `%20AND%20artist:${track.artist}`;

    releaseRequest += "%20AND%20type:album&fmt=json"; // we want json !!!! \0/
    
    let response = await fetch(releaseRequest);
    let responseData = await response.json();

    if (responseData.count === undefined || responseData.count === 0)
      return config.DEFAULT_IMG_URL;

    const RELEASE_ID = responseData["release-groups"][0]["id"];
    return `http://coverartarchive.org/release-group/${RELEASE_ID}/front-250`;
  },

  async fetchStreamingLink(title = "", artist = "", album = "") {
    try {
      let formData = new FormData();
      formData.append("title", title);
      formData.append("artist", artist);
      formData.append("album", album);

      let response = await fetch(URI + "/play", {
        method: "POST",
        body: formData
      });

      let responseData = await response.json();

      return responseData;
    } catch (e) {
      console.log(e);
    }
  }
};
