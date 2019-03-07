import React                                                            from 'react';
import {ScrollView, StyleSheet, FlatList, View, Text, TouchableOpacity} from 'react-native';
import FlatListItem                                                     from './../components/FlatListItem';
import colors                                                           from './../theme/colors';
import {MaterialIcons}                                                  from '@expo/vector-icons';

export default class LibraryScreen extends React.Component {
    static navigationOptions = {
        header: null,
    };

    _keyExtractor = (item, index) => item.id;

    _renderItem = ({item}) => (
        <FlatListItem
            id={item.id}
            title={item.title}
        />
    );

    _data = _ => {
        let items = [];

        for (let i = 0; i < 30; i++) {
            items.push({id: i.toString(), title: "a million on my soul"});
        }

        return items;
    };

    render() {
        return (
            <View style={{flex: 1}}>
                <ScrollView style={styles.container}>
                    <View style={styles.flatListWrapper}>
                        <FlatList
                            data={this._data()}
                            keyExtractor={this._keyExtractor}
                            renderItem={this._renderItem}
                        />
                    </View>
                </ScrollView>

                <TouchableOpacity style={styles.goToPlayerButton}
                                  onPress={() => this.props.navigation.navigate('HomeStack')}>
                    <MaterialIcons name='album' size={40} color={colors.playerIcon}/>
                </TouchableOpacity>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container       : {
        flex           : 1,
        backgroundColor: colors.appBackground,
    },
    flatListWrapper : {
        paddingTop   : 40,
        paddingBottom: 20
    },
    goToPlayerButton: {
        position: 'absolute',
        bottom  : 10,
        right   : 10
    }
});
