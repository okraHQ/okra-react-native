import React, { Component } from 'react';
import { Platform, Dimensions, ActivityIndicator, View, StyleSheet} from 'react-native';
import { WebView } from 'react-native-webview';
import DeviceInfo from 'react-native-device-info';

export default class OkraView extends Component {

    constructor(props) {
        super(props);
        this.state = {}
        this.okraOptions = this.props.okraOptions;
    }

    okraOptions = {
        ...this.props.okraOptions,
        isWebview : true,
        source : Platform.OS === 'android' ? 'rn-android' : 'rn-ios',
        uuid : DeviceInfo.getUniqueId(),
        deviceInfo : {
            deviceName : DeviceInfo.getBrand(),
            deviceModel : DeviceInfo.getModel(),
            longitude : 0,
            latitude : 0,
            platform : Platform.OS
        }
    };
    
    width = Math.round(Dimensions.get('window').width);
    height = Math.round(Dimensions.get('window').height);

    json = JSON.stringify(this.okraOptions);

    INJECTED_JAVASCRIPT = `openOkraWidget('${this.json}')`;
    render() {
        return (
            <View style={{ flex: 1 }}>
            <WebView
                ref={r => (this.webref = r)}
                source={{ uri: 'https://v2-mobile.okra.ng/' }}
                javaScriptEnabled={true}
                injectedJavaScript={this.INJECTED_JAVASCRIPT}
                onLoadEnd={syntheticEvent => {
                    console.log("the end of life ", this.INJECTED_JAVASCRIPT)
                    this.webref.injectJavaScript(this.INJECTED_JAVASCRIPT);
                    this.setState({loaded : true})
                }}
                onNavigationStateChange={this.handleWebViewNavigationStateChange}
                onMessage={(event) => {
                    if(event.nativeEvent.data){
                        let response  = JSON.parse(event.nativeEvent.data);
                        if(response.type === "onSuccess"){
                            this.props.onSuccess(response.data)
                        }else if(response.type === "onError"){
                            this.props.onError(response.data)
                        }else{
                            this.props.onClose(response.data)
                        }
                     }
                }}
            />
            {!this.state.loaded && (<ActivityIndicator
              color= {this.props.color ? this.props.color : "green"}
              style={this.styles.centerSpinner}
              size="large"/>)}
              </View>
        );
    }

    handleWebViewNavigationStateChange = newNavState => {
        const { url } = newNavState;
        if (!url) return;

        if (url.includes('shouldClose=true')) {
            this.props.onClose({})
        }
    }

    styles = StyleSheet.create({
        centerSpinner: {
            position: 'absolute',
            left: 0,
            top: 0,
            bottom: 0,
            right: 0,
        }
    })
}
