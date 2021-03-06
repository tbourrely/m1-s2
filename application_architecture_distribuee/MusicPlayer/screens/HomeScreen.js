import React from "react";
import {
  Image,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
  Alert,
  ActivityIndicator
} from "react-native";

import { Audio, Permissions, FileSystem } from "expo";
import wit from "../services/wit";
import ajax from "./../services/fetchData";

import { MaterialIcons } from "@expo/vector-icons";

import colors from "./../theme/colors";
import config from './../theme/config';

import ImageLoader from './../components/ImageLoader';
import TrackListModal from './../components/TrackListModal';

export default class HomeScreen extends React.Component {
  static navigationOptions = {
    header: null
  };

  constructor(props) {
    super(props);

    this.state = {
      recording: false, // is it recording ?
      playing: false, // is a track playing ?
      audioPermission: false, // does this app has audio permissions ?
      currentStreamingData: { // currenty played track
        artist: null,
        title: null,
        album: null,
        cover: null,
        streamingLink: null
      },
      previous: false,
      next: false,
      fetching: false
    };

    this.history = []; // previous tracks (only when multiple tracks are returned from the 'play' request)
    this.followingTracks = []; // following tracks (only when multiple tracks are returned from the 'play' request)

    this.recorder = null;
    this.player = null;
  }

  async testGetData() {
    const data = await ajax.fetchStreamingLink(
      '',
      'muse',
      ''
    );
    this._handleStreamingServerResponse(data);
  }

  componentDidMount() {
    Permissions.getAsync(Permissions.AUDIO_RECORDING).then(({ status }) => {
      if (status !== "granted") {
        this._askAudioPermission();
      } else {
        this.setAudioPermission(true);
      }
    });

    // this.testGetData();
  }

  componentDidUpdate(prevProps, prevState) {
    if (
      this.state.currentStreamingData.streamingLink !== null &&
      prevState.currentStreamingData.streamingLink !==
        this.state.currentStreamingData.streamingLink
    ) {
      this._stopPlaying();
      this._startPlaying();
    }
  }

  async _updateCurrentStreamingData(trackUrl, title, artist, album, cleanHistoryAndFollowing = false) {
    let newState = {};

    if (cleanHistoryAndFollowing === true) {
      this.history = [];
      this.followingTracks = [];

      newState['next'] = false;
      newState['previous'] = false;
    }

    coverUrl = await ajax.fetchCover({album: album, artist: artist});
    
    newState['currentStreamingData'] = {
      title: title,
      artist: artist,
      album: album,
      cover: coverUrl,
      streamingLink: trackUrl
    };

    this.setState(newState);
  }

  _resetCurrentStreamingData() {
    this.setState({
      currentStreamingData: {
        title: null,
        artist: null,
        album: null,
        cover: null,
        streamingLink: null
      }
    });
  }

  setAudioPermission(status) {
    this.setState({ audioPermission: status });
  }

  setRecording(recordingStatus) {
    this.setState({ recording: recordingStatus });
  }

  setPlaying(playingStatus) {
    this.setState({ playing: playingStatus });
  }

  _resetHistory() {
    this.history = [];
    this.setState({previous: false});
  }

  _resetFollowingTracks() {
    this.followingTracks = [];
    this.setState({next: false});
  }

  _askAudioPermission() {
    Permissions.askAsync(Permissions.AUDIO_RECORDING).then(({ status }) => {
      if (status === "granted") {
        this.setAudioPermission(true);
      }
    });
  }

  _handleRecorderPress() {
    if (this.state.audioPermission) {
      if (!this.state.recording) {
        this._startRecording();
      } else {
        this._stopRecording();
      }
    } else {
      this._askAudioPermission();
    }
  }

  _handleStopPress() {
    this._stopPlayer();
    this.history = [];
    this.followingTracks = []; 
    this.setState({
      playing: false,
      currentStreamingData: {
        title: null,
        artist: null,
        album: null,
        cover: null,
        streamingLink: null,
      },
      previous: false,
      next: false
    });
  }

  async _goToNext() {
    let newState = {};

    // push the current track to the history
    currentTrack = this.state.currentStreamingData;
    this.history.push({
      link: currentTrack.streamingLink,
      artist: currentTrack.artist,
      title: currentTrack.title,
      album: currentTrack.album
    });

    if (this.history.length === 1 && !this.state.previous) {
      newState['previous'] = true;
    }

    // play the following track
    track = this.followingTracks.shift();

    if (!this.followingTracks.length && this.state.next) {
      newState['next'] = false;
    }

    coverUrl = await ajax.fetchCover({album: track.album, artist: track.artist});

    newState['currentStreamingData'] = {
      title: track.title,
      artist: track.artist,
      album: track.album,
      cover: coverUrl,
      streamingLink: track.link
    };

    this.setState(newState);
  }

  async _goToPrevious() {
    let newState = {};

    // set the the next track as the current one
    currentTrack = this.state.currentStreamingData;
    this.followingTracks.unshift({
      link: currentTrack.streamingLink,
      artist: currentTrack.artist,
      title: currentTrack.title,
      album: currentTrack.album
    });

    if (this.followingTracks.length && !this.state.next) {
      newState['next'] = true;
    }
    
    // set the current track as the previous one
    track = this.history.pop();
    if (!this.history.length && this.state.previous) {
      newState['previous'] = false;
    }
    
    coverUrl = await ajax.fetchCover({album: track.album, artist: track.artist});

    newState['currentStreamingData'] = {
      title: track.title,
      artist: track.artist,
      album: track.album,
      cover: coverUrl,
      streamingLink: track.link
    };

    this.setState(newState);
  }

  async _handleWitResponse(response) {
    console.log(response);

    if (response['entities'] !== undefined && Object.keys(response['entities']).length && response['entities']['intent'] !== undefined) {
      const $entities = response['entities'];
      const entitiesLength = Object.keys(response['entities']).length

      const intentList = $entities['intent'];

      // reducer that reduce to the item with the highest confidence level
      const highestConfidenceLevel = (acc, curr) => {
        if (acc === undefined)
          return curr;
        
        if (curr.confidence > acc.confidence)
          return curr;

        else return acc;
      };

      // get the intent with the highest confidence level
      const intent = intentList.reduce(highestConfidenceLevel);

      switch(intent.value) {
        case "pause":
          this._pausePlaying();
          break;

        case "stop":
          this._handleStopPress();
          break;

        case "next":
          if (!this.followingTracks.length) {
            Alert.alert('', "No following tracks");
            return;
          }

          this._goToNext();
          break;

        case "previous":
          if (!this.history.length) {
            Alert.alert('', "No previous track");
            return;
          }

          this._goToPrevious();
          break;

        case "play":
          if (entitiesLength === 1) {
            this._startPlaying();
          } else {
            const artist = undefined !== $entities['artist'] ? $entities['artist'].reduce(highestConfidenceLevel).value : "";
            const title = undefined !== $entities['track'] ? $entities['track'].reduce(highestConfidenceLevel).value : "";
            const album = undefined !== $entities['album'] ? $entities['album'].reduce(highestConfidenceLevel).value : "";
  
            const data = await ajax.fetchStreamingLink(
              title,
              artist,
              album
            );
            this._handleStreamingServerResponse(data);
          }
          break;

        default:
          Alert.alert("", "Sorry, i did not understand your request !");
          break;
      }
    }

    this.setState({fetching: false});
  }

  async _handleStreamingServerResponse(response) {
    console.log(response);
    if (response.nbTracks === 0) {
      Alert.alert('', "No match !");
      return;
    }
    
    this.history = [];
    let newState = {
      previous: false,
    };

    track = response.tracks[0];
    
    // set the following tracks with the new correct value
    // if one song is retrieved, no following tracks
    // otherwise, all the retrieved tracks except the first are the following tracks
    if (response.nbTracks === 1) {
      this.followingTracks = [];
      newState['next'] = false;
    } else {
      response.tracks.shift();
      this.followingTracks = response.tracks;
      newState['next'] = true;
    }

    coverUrl = await ajax.fetchCover({album: track.album, artist: track.artist});

    newState['currentStreamingData'] = {
      title: track.title,
      artist: track.artist,
      album: track.album,
      cover: coverUrl,
      streamingLink: track.link
    };

    this.setState(newState);
  }

  async _startRecording() {
    if (null === this.recorder) {
      this.recorder = new Audio.Recording();
    }

    try {
      await this.recorder.prepareToRecordAsync(Audio.RECORDING_OPTIONS_PRESET_LOW_QUALITY);

      this.recorder.startAsync().then(status => {
        if (status.isRecording) {
          this.setRecording(true);
        }
      });
    } catch (e) {
      Alert.alert("", "An error occured, impossible to start");
    }
  }

  _stopRecording() {
    if (this.recorder !== null) {
      try {
        const uri = this.recorder.getURI();
        this.recorder.stopAndUnloadAsync().then(status => {
          if (status.isDoneRecording) {
            this.recorder = null;
            this.setState({
              recording: false,
              fetching: true
            });
            
            wit.getIntentFromAudio({uri, name: 'test', 'type': 'audio/3gpp'}).then(resp => this._handleWitResponse(resp));
          }
        });
      } catch (e) {
        Alert.alert("", "An error occured, impossible to stop");
      }
    }
  }

  async _startPlayer() {
    if (null === this.state.currentStreamingData.streamingLink) {
      throw "Null can't be played !";
    }

    if (null === this.player) {
      this.player = new Audio.Sound();
      await this.player.loadAsync({
        uri: this.state.currentStreamingData.streamingLink
      });
    }

    if (null !== this.player) await this.player.playAsync();
  }

  _pausePlayer() {
    if (null !== this.player) {
      this.player.pauseAsync();
    }
  }

  _stopPlayer() {
    if (null !== this.player) {
      this.player.stopAsync();
      this.player = null;
    }
  }

  async _startPlaying() {
    try {
      await this._startPlayer();
      this.setPlaying(true);
    } catch (e) {
      /* do not do anything ! */
    }
  }

  _pausePlaying() {
    this._pausePlayer();
    this.setPlaying(false);
  }

  _stopPlaying() {
    this._stopPlayer();
    this.setPlaying(false);
  }

  _togglePlaying() {
    playingState = !this.state.playing;

    if (playingState) {
      this._startPlaying();
    } else {
      this._pausePlaying();
    }
  }

  render() {
    const micIcon = this.state.recording ? "stop" : "mic-none";

    const playPauseIcon = this.state.playing ? "pause" : "play-arrow";

    const imgURI =
      this.state.currentStreamingData.cover ||
      config.DEFAULT_IMG_URL;
    const title = this.state.currentStreamingData.title || "-";
    const artist = this.state.currentStreamingData.artist || "-";

    let middleButton = (
      <TouchableOpacity
        style={styles.mediaButtonsPlayPause}
        onPress={this._togglePlaying.bind(this)}
        onLongPress={this._handleStopPress.bind(this)}
      >
        <MaterialIcons
          name={playPauseIcon}
          size={50}
          color={colors.playerIcon}
        />
      </TouchableOpacity>
    );

    if (this.state.recording || this.state.fetching) {
      middleButton = (
        <View style={styles.mediaButtonsPlayPause}>
          <ActivityIndicator size={50} color={this.state.recording ? colors.playerIcon : 'red'} />
        </View>
      )
    }

    const previousButton = this.state.previous ? (
      <TouchableOpacity onPress={this._goToPrevious.bind(this)}>
        <MaterialIcons name={"skip-previous"} size={50} color={colors.playerIcon}/>
      </TouchableOpacity>
    ) : null;

    const nextButton = this.state.next ? (
      <TouchableOpacity onPress={this._goToNext.bind(this)}>
        <MaterialIcons name={"skip-next"} size={50} color={colors.playerIcon}/>
      </TouchableOpacity>
    ) : null;
      
    const historyListButton = this.state.previous ? <TrackListModal data={this.history} title={"Previous"} icon={"history"}/> : null;
    const followingListButton = this.state.next ? <TrackListModal data={this.followingTracks} title={"Next"} icon={"playlist-play"}/> : null;

    return (
      <View style={styles.home}>
        <View style={styles.player}>
          <View style={styles.playerImgWrapper}>
            <ImageLoader source={{ uri: imgURI }} style={styles.playerImg} trackName={title} />
          </View>

          <View style={styles.playerInfos}>
            <Text style={styles.playerTitle}>{title}</Text>
            <Text style={styles.playerArtist}>{artist}</Text>
          </View>
        </View>

        <View style={styles.listButtonsWrapper}>
          {historyListButton}
          {followingListButton}
        </View>

        <View style={styles.mediaButtonsWrapper}>
          <TouchableOpacity onPress={this._handleRecorderPress.bind(this)}>
            <MaterialIcons name={micIcon} size={50} color={colors.playerIcon} />
          </TouchableOpacity>

          {previousButton}          

          {middleButton}

          {nextButton}

          <TouchableOpacity
            onPress={() =>
              this.props.navigation.navigate("Links", {
                updateCurrentStreamingData: this._updateCurrentStreamingData.bind(this)
              })
            }
          >
            <MaterialIcons name="list" size={50} color={colors.playerIcon} />
          </TouchableOpacity>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  home: {
    flex: 1,
    flexDirection: "column",
    backgroundColor: colors.appBackground
  },
  player: {
    flex: 3,
    justifyContent: "center",
    alignItems: "center",
    paddingTop: 80
  },
  playerImgWrapper: {
    width: 300,
    height: 300
  },
  playerImg: {
    width: "100%",
    height: "100%",
    borderRadius: 10
  },
  playerInfos: {
    marginTop: 20
  },
  playerTitle: {
    textAlign: "center",
    fontSize: 25,
    fontWeight: "bold",
    color: colors.playerText
  },
  playerArtist: {
    textAlign: "center",
    fontSize: 20,
    color: colors.playerText
  },
  sliderWrapper: {
    paddingLeft: 40,
    paddingRight: 40
  },
  sliderTrack: {
    height: 5,
    borderRadius: 5
  },
  sliderThumb: {
    height: 30,
    width: 30,
    borderRadius: 30,
    shadowColor: "#000",
    shadowOffset: {
      width: 0,
      height: 10
    },
    shadowOpacity: 0.53,
    shadowRadius: 13.97,
    elevation: 21
  },
  listButtonsWrapper: {
    flex: 0,
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    paddingLeft: 28,
    paddingRight: 28
  },
  mediaButtonsWrapper: {
    flex: 1,
    flexDirection: "row",
    justifyContent: "space-around",
    alignItems: "center",
    paddingLeft: 20,
    paddingRight: 20
  }
});
