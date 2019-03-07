import React               from 'react';
import {View, Text, Image} from "react-native";
import colors              from './../theme/colors';

export default class FlatListItem extends React.PureComponent {

    _capitalize(s) {
        return s && s[0].toUpperCase() + s.slice(1);
    }

    render() {
        return (
            <View style={{
                flex             : 1,
                flexDirection    : 'row',
                paddingVertical  : 10,
                paddingHorizontal: 20,
                alignItems       : 'center'
            }}>
                <View style={{width: 70, height: 70}}>
                    <Image source={{uri: 'https://m.media-amazon.com/images/I/71fC8ZDzrnL._SS500_.jpg'}}
                           style={{width: '100%', height: '100%', borderRadius: 5}}/>
                </View>

                <Text style={{
                    marginLeft: 30,
                    fontSize  : 20,
                    fontWeight: 'bold',
                    color     : colors.playerText,
                }}>
                    {this._capitalize(this.props.title)}
                </Text>
            </View>
        );
    }
}