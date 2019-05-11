import React from 'react';
import 
{
    Modal,
    FlatList,
    View,
    ScrollView,
    Text,
    TouchableOpacity,
    StyleSheet
} from 'react-native';

import {MaterialIcons} from "@expo/vector-icons";
import colors from './../theme/colors';

class TrackListModal extends React.Component {
    state = {
        visible: false
    }

    _keyExtractor = (item, index) => item.title;

    _renderItem = ({item}) => (
        <View style={styles.itemWrapper}>
            <Text style={styles.title}>{item.title}</Text>
            <Text style={styles.album}>{item.album}</Text>
            <Text style={styles.artist}>{item.artist}</Text>
        </View>);

    render() {
        return (
            <View>
                <Modal
                    visible={this.state.visible}
                    onRequestClose={() => {}}
                    animationType='slide'
                >
                    <View style={styles.modalWrapper}>
                        <TouchableOpacity onPress={() => {this.setState({visible: false})}}>
                            <MaterialIcons name={"close"} size={40} color={colors.playerIcon}/>
                        </TouchableOpacity>

                        <Text style={styles.modalTitle}>{this.props.title}</Text>
                    </View>

                    <ScrollView>
                        <FlatList
                            data={this.props.data}
                            keyExtractor={this._keyExtractor}
                            renderItem={this._renderItem}
                        />
                    </ScrollView>
                </Modal>

                <TouchableOpacity onPress={() => {this.setState({visible: true})}}>
                    <MaterialIcons name={this.props.icon} size={50} color={colors.playerIcon}/>
                </TouchableOpacity>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    itemWrapper: {
        flex: 0,
        flexDirection: 'row',
        paddingHorizontal: 20,
    }, 
    title: {
        fontWeight: 'bold'
    },
    album: {
        marginHorizontal: 10
    },
    artist: {
        fontStyle: 'italic'
    },
    modalWrapper: {
        flex: 0,
        alignItems: 'center',
        flexDirection: 'row',
        marginVertical: 20
    },
    modalTitle: {
        fontSize: 30,
        marginLeft: 20
    }
  });

export default TrackListModal;