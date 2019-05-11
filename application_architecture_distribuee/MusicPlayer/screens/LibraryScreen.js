import React from "react";
import {
  ScrollView,
  StyleSheet,
  View,
  Alert,
  TouchableOpacity,
  SectionList,
  Text,
  Picker,
  Image
} from "react-native";
import FlatListItem from "./../components/FlatListItem";
import colors from "./../theme/colors";
import { MaterialIcons } from "@expo/vector-icons";
import ajax from "./../services/fetchData";

export default class LibraryScreen extends React.Component {
  static navigationOptions = {
    header: null
  };

  state = {
    tracks: {},
    albumList: [],
    artistList: [],
    filter: 'artist',
    showLoader: true
  };

  _keyExtractor = (item, index) => item.path;

  _renderItem = ({ item }) => (
    <FlatListItem
      title={item.title}
      artist={item.artist}
      album={item.album}
      cover={item.cover}
      pressHandler={this._handleItemClick.bind(this)}
    />
  );

  _renderSectionHeader = ({section: {title}}) => (
    <Text style={styles.sectionHeader}>{title}</Text>
  );

  _handleItemClick = async (title, artist, album) => {
    let data = await ajax.fetchStreamingLink(title, artist, album);
    if (data["nbTracks"] === 1) {
      track = data['tracks'][0]
      this.props.navigation.state.params.updateCurrentStreamingData(
        track.link,
        title,
        artist,
        album,
        true
      );
      this.props.navigation.navigate("Home");
    } else {
      Alert.alert("", "Could not play track");
    }
  };

  _filterByArtist = _ => {
    return this.state.artistList.map(artistName => {
      return {title: artistName, data: this.state.tracks.filter(track => track.artist === artistName)}
    });
  };
  _filterByAlbum = _ => {
    return this.state.albumList.map(albumName => {
      return {title: albumName, data: this.state.tracks.filter(track => track.album === albumName)}
    });
  };

  async componentDidMount() {
    let tracks = await ajax.fetchTracks(); // fetch the tracks list
    let artistList = [];
    let albumList = [];


    // fetch and add the cover link to each track
    tracks = await Promise.all(
      tracks.map(async track => {
        if (!artistList.includes(track.artist)) artistList.push(track.artist);
        if (!albumList.includes(track.album)) albumList.push(track.album);

        track.cover = await ajax.fetchCover(track);
        return track;
      })
    );

    this.setState({ tracks, artistList, albumList, showLoader: false });
  }

  render() {
    let sections = [];

    if (this.state.filter === 'album') sections = this._filterByAlbum();
    if (this.state.filter === 'artist') sections = this._filterByArtist();

    const loader = this.state.showLoader ? (<View style={styles.loader}>
      <Image style={{
        width: 200,
        height: 200,
      }} source={{uri: "https://loading.io/spinners/sketch/lg.scratching-reveal-loader.gif"}}/>
    </View>) : null;

    return (
      <View style={{ flex: 1 }}>
        <ScrollView style={styles.container}>
          <Picker
            style={styles.picker}
            selectedValue={this.state.filter}
            onValueChange={value => this.setState({filter: value})}
            mode={'dropdown'}
          >
            <Picker.Item label='Artist' value='artist'/>
            <Picker.Item label='Album' value='album'/>
          </Picker>
          
          <View style={styles.flatListWrapper}>
            <SectionList
              renderItem={this._renderItem}
              keyExtractor={this._keyExtractor}
              sections={sections}
              renderSectionHeader={this._renderSectionHeader}
            />
          </View>
        </ScrollView>

        <TouchableOpacity
          style={styles.goToPlayerButton}
          onPress={() => this.props.navigation.navigate("Home")}
        >
          <MaterialIcons name="album" size={40} color={colors.playerIcon} />
        </TouchableOpacity>

        {loader}
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.appBackground
  },
  flatListWrapper: {
    paddingTop: 20,
    paddingBottom: 20
  },
  goToPlayerButton: {
    position: "absolute",
    bottom: 10,
    right: 10
  },
  picker: {
    marginTop: 40,
    marginLeft: 20,
    width: 150,
    height: 50
  },
  sectionHeader: {
    fontWeight: 'bold',
    fontSize: 30,
    paddingLeft: 20,
    paddingRight: 20
  },
  loader: {
    position: 'absolute',
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: colors.appBackground,
    height: "100%",
    width: "100%",
    top: 0,
    left: 0,
  }
});
