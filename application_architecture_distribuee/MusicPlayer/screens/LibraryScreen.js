import React                                    from 'react';
import {ScrollView, StyleSheet, FlatList, Text} from 'react-native';
import {ExpoLinksView}                          from '@expo/samples';
import FlatListItem                             from './../components/FlatListItem';

export default class LibraryScreen extends React.Component {
    static navigationOptions = {
        title: 'Library',
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
            <ScrollView style={styles.container}>
                <FlatList
                    data={this._data()}
                    keyExtractor={this._keyExtractor}
                    renderItem={this._renderItem}
                />
            </ScrollView>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex           : 1,
        backgroundColor: 'rgb(250, 251, 252)'
    },
});
