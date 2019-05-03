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
import Slider from "react-native-slider";
import { Audio, Permissions, FileSystem } from "expo";

import { MaterialIcons } from "@expo/vector-icons";

import colors from "./../theme/colors";

export default class HomeScreen extends React.Component {
  static navigationOptions = {
    header: null
  };

  static RECORDING_OPTIONS = {
    android: {
      extension: ".wav",
      outputFormat: Audio.RECORDING_OPTION_ANDROID_OUTPUT_FORMAT_MPEG_4,
      audioEncoder: Audio.RECORDING_OPTION_ANDROID_AUDIO_ENCODER_DEFAULT
    },
    ios: {
      extension: ".wav",
      audioQuality: Audio.RECORDING_OPTION_IOS_AUDIO_QUALITY_MAX,
      sampleRate: 44100,
      numberOfChannels: 2,
      bitRate: 128000
    }
  };

  constructor(props) {
    super(props);

    this.state = {
      recording: false,
      playing: false,
      audioPermission: false,
      currentStreamingUrl: null
    };

    this.recorder = null;
    this.player = null;
  }

  componentDidMount() {
    Permissions.getAsync(Permissions.AUDIO_RECORDING).then(({ status }) => {
      if (status !== "granted") {
        this._askAudioPermission();
      } else {
        this.setAudioPermission(true);
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

  async _startRecording() {
    if (null === this.recorder) {
      this.recorder = new Audio.Recording();
    }

    try {
      await this.recorder.prepareToRecordAsync(HomeScreen.RECORDING_OPTIONS);

      this.recorder.startAsync().then(status => {
        if (status.isRecording) {
          this.setRecording(true);
          // Alert.alert("recording", "started");
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
            this.setRecording(false);
            // Alert.alert("recording", "stoped");
            //
            // FileSystem.getInfoAsync(uri).then(res => {
            //     console.log(res);
            // });
          }
        });
      } catch (e) {
        Alert.alert("", "An error occured, impossible to stop");
      }
    }
  }

  async _startPlaying() {
    if (null === this.player) {
      this.player = new Audio.Sound();
      try {
        await this.player.loadAsync({
          uri:
            "http://172.18.99.2:5000/stream/ZW1pbmVtL3Jldml2YWwvYXJvc2UuYWFj?token=oW4BMfXypNjPd9VWR6x7lklZUdQNJE97"
        });
      } catch (e) {
        Alert.alert("", "An error occured, impossible to load player");
      }
    }

    try {
      await this.player.playAsync();
    } catch (e) {
      Alert.alert("", "An error occured, impossible to play");
      console.log(e);
    }
  }

  _stopPlaying() {
    if (null !== this.player) {
      this.player.pauseAsync();
    }
  }

  _togglePlaying() {
    playingState = !this.state.playing;

    if (playingState) {
      // must play
      //   Alert.alert("Must play...");
      this._startPlaying();
    } else {
      // must stop
      //   Alert.alert("Must stop playing");
      this._stopPlaying();
    }

    this.setPlaying(playingState);
  }

  render() {
    Alert.alert("", this.props.navigation.params || "");
    const micIcon = this.state.recording ? "stop" : "mic-none";

    const playPauseIcon = this.state.playing ? "pause" : "play-arrow";

    const imgURI =
      "https://m.media-amazon.com/images/I/71fC8ZDzrnL._SS500_.jpg";
    const title = "A Million on My Soul";
    const artist = "Alexiane";

    const middleButton = this.state.recording ? (
      <View style={styles.mediaButtonsPlayPause}>
        <ActivityIndicator size={50} color={colors.playerIcon} />
      </View>
    ) : (
      <TouchableOpacity
        style={styles.mediaButtonsPlayPause}
        onPress={this._togglePlaying.bind(this)}
      >
        <MaterialIcons
          name={playPauseIcon}
          size={50}
          color={colors.playerIcon}
        />
      </TouchableOpacity>
    );

    return (
      <View style={styles.home}>
        <View style={styles.player}>
          <View style={styles.playerImgWrapper}>
            <Image source={{ uri: imgURI }} style={styles.playerImg} />
          </View>

          <View style={styles.playerInfos}>
            <Text style={styles.playerTitle}>{title}</Text>
            <Text style={styles.playerArtist}>{artist}</Text>
          </View>
        </View>

        <View style={styles.sliderWrapper}>
          <Slider
            minimumValue={0}
            maximumValue={100}
            step={1}
            value={50}
            minimumTrackTintColor={colors.minimumTrackColor}
            maximumTrackTintColor={colors.maximumTrackColor}
            thumbTintColor={colors.thumbColor}
            trackStyle={styles.sliderTrack}
            thumbStyle={styles.sliderThumb}
          />
        </View>

        <View style={styles.mediaButtonsWrapper}>
          <TouchableOpacity onPress={this._handleRecorderPress.bind(this)}>
            <MaterialIcons name={micIcon} size={50} color={colors.playerIcon} />
          </TouchableOpacity>

          {middleButton}

          <TouchableOpacity
            style={styles.mediaButtonsPlayPause}
            onPress={() => this.props.navigation.navigate("LinksStack")}
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
    paddingTop: 50
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
  mediaButtonsWrapper: {
    flex: 1,
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "center"
  },
  mediaButtonsPlayPause: {
    marginLeft: 80
  }
});
