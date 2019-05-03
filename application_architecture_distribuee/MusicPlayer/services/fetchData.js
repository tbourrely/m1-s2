const URI = "http://10.0.2.2:5000"; // StreamingServerManager address:port

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
    const DEFAULT_IMG_URL = "http://dalelyles.com/musicmp3s/no_cover.jpg";
    const ALBUM_NAME = track.album;
    const ALBUM_ARTIST = track.artist;
    const RELEASE_GROUP_REQUEST = `http://musicbrainz.org/ws/2/release-group/?query=releasegroup:${ALBUM_NAME}%20AND%20artist:${ALBUM_ARTIST}%20AND%20type:album&fmt=json`;
    let response = await fetch(RELEASE_GROUP_REQUEST);
    let responseData = await response.json();

    if (responseData.count === undefined || responseData.count === 0)
      return DEFAULT_IMG_URL;

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
