import React, { Component } from 'react';
import { Platform, Dimensions, ActivityIndicator, View, StyleSheet, NativeModules} from 'react-native';
import { WebView } from 'react-native-webview';
import DeviceInfo from 'react-native-device-info';
const { OkraUSSD } = NativeModules;
export default class OkraView extends Component {

    constructor(props) {
        super(props);
        this.state = {}
        OkraUSSD.initHover();
        this.okraOptions = this.props.okraOptions;
    }
    okraOptions = {
        ...this.props.okraOptions,
        isWebview : true,
        source : Platform.OS === 'android' ? 'rn-android' : 'rn-ios',
        uuid : DeviceInfo.getUniqueId(),
        isHybridUSSDEnabled:true,
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
                source={{ uri: 'https://e53afce750bc.ngrok.io/mobile.html' }}
                javaScriptEnabled={true}
               // injectedJavaScript={this.INJECTED_JAVASCRIPT}
                onLoadEnd={syntheticEvent => {

                    console.log("the end of life ", this.INJECTED_JAVASCRIPT)
                    this.webref.injectJavaScript(this.INJECTED_JAVASCRIPT);
                    this.setState({loaded : true})
                }}
                onNavigationStateChange={this.handleWebViewNavigationStateChange}
                // onMessage={this.onWebViewMessage.bind(this)}
                onMessage={(event) => {
                    console.log("MESSAGE ----", event)
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

    hasUssdFeature = async () => {
      let response =  await OkraUSSD.hasUssdFeature()
        console.log("THIS RESPONSEE -- ",response)
        return response
    }

    permissionOn = async (type) => {
        let hasPermissionOn = await OkraUSSD.permissionOn(type.data)
        console.log("THIS IS THE PERMISSION RESULT ", hasPermissionOn)
        return hasPermissionOn
    }

    askForPermission =  (type) => {
         OkraUSSD.switchPermissionOn(type.data)
    }

    openUSSD = (data) =>{
        OkraUSSD.openUSSD(data.data)
    }

     async onWebViewMessage(event) {
         // post back reply as soon as possible to enable sending the next message
         console.log("SENDING MESSAGE---1 ", event.nativeEvent.data)
         this.webref.postMessage(event.nativeEvent.data);
         let msgData;
         try {
             msgData = JSON.parse(event.nativeEvent.data);
         } catch (err) {
             console.warn(err);
             return;
         }
         // invoke target function
         console.log("MESSAGE DATA ------ ", msgData)
         const response = await this[msgData.targetFunc].apply(this, [msgData]);
         console.log("Function response === ", response)
         // trigger success callback
         msgData.isSuccessfull = true;
         msgData.args = [response];
         console.log("SENDING MESSAGE--- 2 ", JSON.stringify(msgData))

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
