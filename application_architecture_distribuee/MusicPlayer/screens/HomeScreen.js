import React        from 'react';
import {
    Image,
    Platform,
    StyleSheet,
    Text,
    TouchableOpacity,
    View,
}                   from 'react-native';
import Slider from 'react-native-slider';
import {WebBrowser} from 'expo';

import {MaterialIcons} from '@expo/vector-icons';

export default class HomeScreen extends React.Component {
    static navigationOptions = {
        header: null,
    };

    render() {
        return (
            <View style={{flex: 1, flexDirection: 'column', backgroundColor: 'rgb(250, 251, 252)'}}>
                <View style={{flex: 3, justifyContent: 'center', alignItems: 'center', paddingTop: 50}}>
                    <View style={{width: 300, height: 300}}>
                        <Image source={{uri: 'https://m.media-amazon.com/images/I/71fC8ZDzrnL._SS500_.jpg'}}
                               style={{width: '100%', height: '100%', borderRadius: 10}}/>
                    </View>

                    <View style={{marginTop: 20}}>
                        <Text style={{textAlign: 'center', fontSize: 25, fontWeight: 'bold', color: '#011627'}}>A
                            Million on My Soul</Text>
                        <Text style={{textAlign: 'center', fontSize: 20, color: '#011627'}}>Alexiane</Text>
                    </View>
                </View>

                <View style={{paddingLeft: 40, paddingRight: 40}}>
                    <Slider
                        minimumValue={0}
                        maximumValue={100}
                        step={1}
                        value={50}
                        minimumTrackTintColor={'rgb(251, 68, 132)'}
                        maximumTrackTintColor={'rgb(229, 230, 232)'}
                        thumbTintColor={'#FFF'}
                        trackStyle={{
                            height: 5,
                            borderRadius: 5
                        }}
                        thumbStyle={{
                            height: 30,
                            width: 30,
                            borderRadius: 30,
                            shadowColor: "#000",
                            shadowOffset: {
                                width: 0,
                                height: 10,
                            },
                            shadowOpacity: 0.53,
                            shadowRadius: 13.97,
                            elevation: 21,
                        }}
                    />
                </View>

                <View style={{
                    flex          : 1,
                    flexDirection : 'row',
                    justifyContent: 'center',
                    alignItems    : 'center',
                }}>
                    <TouchableOpacity>
                        <MaterialIcons name="mic-none" size={50} color="black"/>
                    </TouchableOpacity>
                    <TouchableOpacity style={{marginLeft: 25}}>
                        <MaterialIcons name="pause" size={50} color="black"/>
                    </TouchableOpacity>
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container             : {
        flex           : 1,
        backgroundColor: '#fff',
    },
    developmentModeText   : {
        marginBottom: 20,
        color       : 'rgba(0,0,0,0.4)',
        fontSize    : 14,
        lineHeight  : 19,
        textAlign   : 'center',
    },
    contentContainer      : {
        paddingTop: 30,
    },
    welcomeContainer      : {
        alignItems  : 'center',
        marginTop   : 10,
        marginBottom: 20,
    },
    welcomeImage          : {
        width     : 100,
        height    : 80,
        resizeMode: 'contain',
        marginTop : 3,
        marginLeft: -10,
    },
    getStartedContainer   : {
        alignItems      : 'center',
        marginHorizontal: 50,
    },
    homeScreenFilename    : {
        marginVertical: 7,
    },
    codeHighlightText     : {
        color: 'rgba(96,100,109, 0.8)',
    },
    codeHighlightContainer: {
        backgroundColor  : 'rgba(0,0,0,0.05)',
        borderRadius     : 3,
        paddingHorizontal: 4,
    },
    getStartedText        : {
        fontSize  : 17,
        color     : 'rgba(96,100,109, 1)',
        lineHeight: 24,
        textAlign : 'center',
    },
    tabBarInfoContainer   : {
        position       : 'absolute',
        bottom         : 0,
        left           : 0,
        right          : 0,
        ...Platform.select({
            ios    : {
                shadowColor  : 'black',
                shadowOffset : {height: -3},
                shadowOpacity: 0.1,
                shadowRadius : 3,
            },
            android: {
                elevation: 20,
            },
        }),
        alignItems     : 'center',
        backgroundColor: '#fbfbfb',
        paddingVertical: 20,
    },
    tabBarInfoText        : {
        fontSize : 17,
        color    : 'rgba(96,100,109, 1)',
        textAlign: 'center',
    },
    navigationFilename    : {
        marginTop: 5,
    },
    helpContainer         : {
        marginTop : 15,
        alignItems: 'center',
    },
    helpLink              : {
        paddingVertical: 15,
    },
    helpLinkText          : {
        fontSize: 14,
        color   : '#2e78b7',
    },
});
