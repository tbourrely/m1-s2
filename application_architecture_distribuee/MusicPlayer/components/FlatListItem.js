import React from "react";
import { View, Text, Image, TouchableOpacity } from "react-native";
import colors from "./../theme/colors";

export default class FlatListItem extends React.PureComponent {
  _capitalize(s) {
    return s && s[0].toUpperCase() + s.slice(1);
  }

  _pressedItem() {
    this.props.pressHandler(
      this.props.title,
      this.props.artist,
      this.props.album,
      this.props.cover
    );
  }

  render() {
    return (
      <TouchableOpacity onPress={this._pressedItem.bind(this)}>
        <View
          style={{
            flex: 1,
            flexDirection: "row",
            paddingVertical: 10,
            paddingHorizontal: 20,
            alignItems: "center"
          }}
        >
          <View style={{ width: 70, height: 70 }}>
            <Image
              source={{
                uri: `${this.props.cover}`
              }}
              style={{ width: "100%", height: "100%", borderRadius: 5 }}
            />
          </View>

          <View
            style={{
              flex: 1,
              flexDirection: "column",
              paddingLeft: 30
            }}
          >
            <Text
              style={{
                fontSize: 20,
                fontWeight: "bold",
                color: colors.playerText
              }}
            >
              {this._capitalize(this.props.title)}
            </Text>
            <View
              style={{
                flex: 0,
                flexWrap: "wrap",
                flexDirection: "column"
              }}
            >
              <Text
                style={{
                  fontWeight: "bold",
                  color: colors.playerText
                }}
              >
                {this.props.album}
              </Text>
              <Text
                style={{
                  fontStyle: "italic",
                  color: colors.playerText
                }}
              >
                {this.props.artist}
              </Text>
            </View>
          </View>
        </View>
      </TouchableOpacity>
    );
  }
}
