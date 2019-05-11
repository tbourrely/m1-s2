import React from 'react';
import {Animated, View} from 'react-native';

class ImageLoader extends React.Component {
    state = {
        opacity: new Animated.Value(0),
        showLoader: true
    }

    onLoadEnd = () => {
        this.setState({showLoader: false});
        Animated.timing(this.state.opacity, {
            toValue: 1,
            duration: 200,
            useNativeDriver: true
        }).start();
    }

    componentWillReceiveProps(next) {
        if (next.source.uri !== this.props.source.uri || next.trackName !== this.props.trackName) {
            this.state.opacity.setValue(0);
            next.source.uri += '?date' + new Date(); // fix onLoadEnd not firing when image taken from cache
        }
    }

    render() {

        const loader = this.state.showLoader ? (<Animated.Image style={{
            width: "100%",
            height: "100%",
            position: "absolute",
            top: 0,
            left: 0
        }} source={{uri: "https://loading.io/spinners/sketch/lg.scratching-reveal-loader.gif"}}/>) : null;

        return(
            <View>
                <Animated.Image
                    onLoadStart={() => {this.setState({showLoader: true})}}
                    onLoadEnd={this.onLoadEnd}
                    {...this.props}
                    style={[
                        {
                        opacity: this.state.opacity,
                        transform: [
                            {
                                scale: this.state.opacity.interpolate({
                                    inputRange: [0, 1],
                                    outputRange: [0.85, 1],
                                })
                            }
                        ]
                    },
                    this.props.style
                ]}
                />
                {loader}
            </View>
        )
    }
}

export default ImageLoader;