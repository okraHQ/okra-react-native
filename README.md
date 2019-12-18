# okra-react-native

This is a react native library for implementing okra widget

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


### Usage


``` javascript
import OkraReactNative from 'react-native-okra-react-native'

OkraReactNative.openOkraWidget({
        clientName: 'mark',
        key : 'c81f3e05-7a5c-5727-8d33-1113a3c7a5e4',
        token:'5d8a35224d8113507c7521ac',
        environment: 'sandbox',
        products: ['auth', 'transactions', 'balance', 'identity']
      });
```

## OkraOptions

|Name                   | Type           | Required            | Default Value       | Description         |
|-----------------------|----------------|---------------------|---------------------|---------------------|
|  `key `               | `String`       | true                |  undefined          | Your public key from Okra.
|  `token`              | `String`       | true                |  undefined          | Your pubic Key from okra. Use test key for test mode and live key for live mode
|  `products`           | `Array[String]`| true                |  undefined          | The Okra products you want to use with the widget.
|  `env`                | `String`       | true                |  undefined          | 
|  `clientName`         | `String`       | true                |  undefined          | Name of the customer using the widget on the application
