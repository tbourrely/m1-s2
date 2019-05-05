import React from "react";
import {
  ScrollView,
  StyleSheet,
  FlatList,
  View,
  Alert,
  TouchableOpacity
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
    tracks: {}
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

  _handleItemClick = async (title, artist, album, cover) => {
    let data = await ajax.fetchStreamingLink(title, artist, album);
    if (data["nbTracks"] === 1) {
      this.props.navigation.state.params.updateCurrentStreamingData(
        data["tracks"][0],
        title,
        artist,
        album,
        cover
      );
      this.props.navigation.navigate("Home");
    } else {
      Alert.alert("", "Could not play track");
    }
  };

  async componentDidMount() {
    let tracks = await ajax.fetchTracks(); // fetch the tracks list
    // fetch and add the cover link to each track
    tracks = await Promise.all(
      tracks.map(async track => {
        track.cover = await ajax.fetchCover(track);
        return track;
      })
    );
    this.setState({ tracks });
  }

  render() {
    return (
      <View style={{ flex: 1 }}>
        <ScrollView style={styles.container}>
          <View style={styles.flatListWrapper}>
            <FlatList
              data={this.state.tracks}
              keyExtractor={this._keyExtractor}
              renderItem={this._renderItem}
            />
          </View>
        </ScrollView>

        <TouchableOpacity
          style={styles.goToPlayerButton}
          onPress={() => this.props.navigation.navigate("Home")}
        >
          <MaterialIcons name="album" size={40} color={colors.playerIcon} />
        </TouchableOpacity>
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
    paddingTop: 40,
    paddingBottom: 20
  },
  goToPlayerButton: {
    position: "absolute",
    bottom: 10,
    right: 10
  }
});
