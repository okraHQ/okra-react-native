import React, { Component } from 'react';
import { Platform, Dimensions, ActivityIndicator, View, StyleSheet, NativeModules,SafeAreaView} from 'react-native';
import { WebView } from 'react-native-webview';
import DeviceInfo from 'react-native-device-info';
const { OkraUSSD } = NativeModules;
export default class OkraView extends Component {

    constructor(props) {
        super(props);
        this.state = {}
        this.okraOptions = this.props.okraOptions;
        if(Platform.OS === 'android'){
            OkraUSSD.initHover();
        }
    }
    okraOptions = {
        ...this.props.okraOptions,
        isWebview : true,
        source : Platform.OS === 'android' ? 'rn-android' : 'rn-ios',
        uuid : DeviceInfo.getUniqueId(),
        isHybridUSSDEnabled: Platform.OS === 'android',
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
            <SafeAreaView style={{ flex: 1 }}>
            <WebView
                ref={r => (this.webref = r)}
                source={{ uri: 'https://mobile.okra.ng' }}
                javaScriptEnabled={true}
               // injectedJavaScript={this.INJECTED_JAVASCRIPT}
                onLoadEnd={syntheticEvent => {
                    if(Platform.OS === 'android'){
                        OkraUSSD.initOptions(this.json)
                    }
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
                        }else if(response.type === "callFunction"){
                            this.onWebViewMessage(event)
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
            </SafeAreaView>
        );
    }

    handleWebViewNavigationStateChange = newNavState => {
        const { url } = newNavState;
        if (!url) return;

        if (url.includes('shouldClose=true')) {
            this.props.onClose({})
        }
    }

    hasUssdFeature = async () => {
      let response =  await OkraUSSD.hasUssdFeature()
        return response
    }

    permissionOn = async (type) => {
        let hasPermissionOn = await OkraUSSD.permissionOn(type.data)
        return hasPermissionOn
    }

    askForPermission =  (type) => {
         OkraUSSD.switchPermissionOn(type.data)
    }


    openUSSD = (data) =>{
        OkraUSSD.openUSSD(data.data)
    }
    startUSSDPayment = (data) =>{
        OkraUSSD.startUSSDPayment(data.data)
    }

     async onWebViewMessage(event) {
         // post back reply as soon as possible to enable sending the next message
         this.webref.postMessage(event.nativeEvent.data);
         let msgData;
         try {
             msgData = JSON.parse(event.nativeEvent.data);
         } catch (err) {
             console.warn(err);
             return;
         }
         const response = await this[msgData.targetFunc].apply(this, [msgData]);
         msgData.isSuccessfull = true;
         msgData.args = [response];
         this.webref.postMessage(JSON.stringify(msgData))
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
