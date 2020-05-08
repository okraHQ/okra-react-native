# okra-react-native

This is a react native library for implementing okra widget.

### Get Started
This library would help you add Okra widget to your react native android app in no time. All you need to do is ...

### Install
1. Clone the git library using:

``` git
git clone https://github.com/okraHQ/okra-react-native.git
```

2. Pack the library with npm:
``` npm
npm pack
```
this would create a `tgz` extention file named `react-native-okra-react-native-1.0.0.tgz`. 

3. Run a `pod install` in the ios folder of your project root directory, to install all IOS cocoa pod dependencies. Note: ios would crash if this is not done. 

4. If you plan to have an ios app with react native, you might need to set a swift version in the pod file of your ios directory in your react-native application folder.  

![All forms page](https://i.imgur.com/1RCECsmr.png)

5. Install this file in your react native project using the following command:
``` npm
npm install ${path}/react-native-okra-react-native-1.0.0.tgz
```
`${path}` refers to the absolute path `C:\Users\Java\Documents\Github\okra-react-native\react-native-okra-react-native\react-native-okra-react-native-1.0.0.tgz` where the packed npm module is located. 

6. Link the native dependecies in the library (okra-react-native is built on native dependencies).
In the root of your react-native application, Run:

``` npm
$ react-native link react-native-okra-react-native
```
this is the final step needed to install the dependency. 

7. **Note for Android**, The `OkraReactNativePackage()` package needs to be provided in the getPackages method of the MainApplication.java file. This file exists under the android folder in your react-native application directory. The path to this file is: your-react-native-project/android/app/src/main/java/com/your-app-name/MainApplication.java.

``` Java
@Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
          new OkraReactNativePackage()
      );
}
```

![All forms page](https://i.imgur.com/Kquz7qx.png)


### Usage


``` javascript
import {DeviceEventEmitter} from 'react-native';

import OkraReactNative from 'react-native-okra-react-native'

OkraReactNative.openOkraWidget({
        clientName: 'mark',
        key : 'c81f3e05-7a5c-5727-8d33-1113a3c7a5e4',
        token:'5d8a35224d8113507c7521ac',
        environment: 'sandbox',
        products: ['auth', 'transactions', 'balance', 'identity']
        options: {},
        source: 'link',
        color:  '#2542a5',
        limit: '24',
        corporate: false,
        connectMessage: 'Which account do you want to connect with?',
        guarantors: {"status":false,"message":"","number":0},
        callback_url: 'https://dev-api.okra.ng/v1/callback',
        redirect_url: '',
        logo: 'https://okra-identity-images-dev.s3.amazonaws.com/1587082961792.png',
        filter: {"industry_type":"all","banks":["ecobank-nigeria","fidelity-bank","first-bank-of-nigeria","first-city-monument-bank","guaranty-trust-bank","heritage-bank","polaris-bank","stanbic-ibtc-bank","standard-chartered-bank","sterling-bank","union-bank-of-nigeria","united-bank-for-africa","wema-bank","unity-bank","alat","access-bank"]},
        widget_success: 'Your account was successfully linked to Okra, Inc',
        widget_failed: 'An unknown error occurred, please try again.',
        currency: 'NGN', 
        exp: '', 
        success_title: '', 
        success_message: '',
      });
      
  DeviceEventEmitter.addListener('onSuccess',
    (e) => {
      console.log("onSuccess ", e);
    })
  DeviceEventEmitter.addListener('onError',
    (e) => {
      console.log("onError ", e);
    }
  )
  DeviceEventEmitter.addListener('onClose',
    (e) => {
      console.log("onClose ", e);
    }
  )
```

## OkraOptions

|Name                   | Type           | Required            | Default Value       | Description         |
|-----------------------|----------------|---------------------|---------------------|---------------------|
|  `key `               | `String`       | true                |  undefined          | Your public key from Okra.
|  `token`              | `String`       | true                |  undefined          | Your pubic Key from okra. Use test key for test mode and live key for live mode
|  `products`           | `Array[String]`| true                |  undefined          | The Okra products you want to use with the widget.
|  `env`                | `String`       | true                |  undefined          | 
|  `clientName`         | `String`       | true                |  undefined          | Name of the customer using the widget on the application

