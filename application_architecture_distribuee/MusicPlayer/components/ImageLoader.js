import React from 'react';
import {Image, View} from 'react-native';
import colors from "./../theme/colors";

class ImageLoader extends React.Component {
    state = {
        showLoader: true
    }

    onLoadEnd = () => {
        this.setState({showLoader: false});
    }

    shouldComponentUpdate(nextProps, nextState) {
        return this.props.trackName !== nextProps.trackName 
            || this.props.source.uri !== nextProps.source.uri 
            || this.state.showLoader !== nextState.showLoader;
    }

    render() {
        const loader = this.state.showLoader ? (<View style={{
            backgroundColor: colors.appBackground,
            width: "100%",
            height: "100%",
            position: "absolute",
            top: 0,
            left: 0
        }}>
            <Image style={{width: "100%", height: "100%"}} source={{uri: "https://loading.io/spinners/sketch/lg.scratching-reveal-loader.gif"}}/>
        </View>) : null;

        return(
            <View>
                <Image
                    onLoadStart={() => {this.setState({showLoader: true})}}
                    onLoadEnd={this.onLoadEnd}
                    {...this.props}
                    style={this.props.style}
                />
                {loader}
            </View>
        )
    }
}

export default ImageLoader;