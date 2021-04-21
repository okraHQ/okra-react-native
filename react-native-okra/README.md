# okra-react-native

This is a react native library for **non-expo** users to help implement okra widget

### Get Started
This library would help you add Okra widget to your react native IOS/Android app in no time. 

### Install Okra-Expo
1. install react-native-okra
``` npm
$ npm install react-native-okra@0.0.4-ussd
```

2. open the android folder and add this to the build.gradle
``` Groovy
allprojects {
    repositories {
   maven {
                url 'https://pro.maven.usehover.com/releases'
                credentials {
                     username project.OkraUSSDUsername
                     password project.OkraUSSDPassword
                }
                authentication { basic(BasicAuthentication) }
                content { includeGroup "com.hover" }
            }
}
}
```

3. Add this to your gradle.properties (Note: The values would be given to you) and sync
``` Groovy
OkraUSSDUsername=xxx 
OkraUSSDPassword=xxx
```

4. In your MainApplication add this to the getPackages function

 
``` java
import com.reactlibrary.OkraUSSDPackage;

  protected List<ReactPackage> getPackages() {
          @SuppressWarnings("UnnecessaryLocalVariable")
          List<ReactPackage> packages = new PackageList(this).getPackages();
            packages.add(new OkraUSSDPackage());
          return packages;
        }
```


5. link react-native-okra
``` npm
$ react-native link react-native-okra
```

6. Pod install: enter the ios directory in the root directory the project and run:
``` pod
$ pod install
```

### Install react-native-webview and Device info plugin

1. install react-native-webview
``` npm
npm install --save react-native-webview
```

2. link react-native-webview
``` npm
$ react-native link react-native-webview
```

3. install react-native-device-info
``` npm
npm install --save react-native-device-info
```

4. link react-native-device-info
``` npm
$ react-native link react-native-device-info
```

5. Pod install: enter the ios directory in the root directory the project and run:
``` pod
$ pod install
```






### Usage 

``` javascript
import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import OkraView from 'react-native-okra-expo';

export default function App() {
  var okraOptions = {
    callback_url:"https://webhook.site/ded54b3f-f4f5-4fa1-86c3-0def6098fb4d",
    clientName:"client",
    color:"#953ab7",
    connectMessage:"Which account do you want to connect with?",
    currency:"NGN",
    env:"production", // for sandbox use production-sandbox
    exp:"2020-08-06",
    filter:{
      banks: ['access-bank', 'guaranty-trust-bank'],
      industry_type: 'all',
    },
    options:{saverid: 'this is it'},
    isCorporate:false,
    ussd:true,
    key:"key",
    limit:"24",
    logo:"https://cdn.okra.ng/images/icon.svg",
    products:['auth', 'balance', 'identity', 'transactions'],
    redirect_url:"redirect",
    success_message:"this is the success message",
    success_title:"it has entered success",
    token:"token",
    widget_failed:"",
    widget_success:"Your account was successfully linked to Okra, Inc"
  }
  return (
    <OkraView
    okraOptions={okraOptions}
    onClose={response => {
      console.log('on close');
    }}
    onSuccess={response => {
      console.log('on success', response);
    }}
    onError={response => {
      console.log('on error');
    }}
/>
  );
}
```



## OkraOptions

|Name                   | Type           | Required            | Default Value       | Description         |
|-----------------------|----------------|---------------------|---------------------|---------------------|
|  `key `               | `String`       | true                |  undefined          | Your public key from Okra.
|  `token`              | `String`       | true                |  undefined          | Your pubic Key from okra. Use test key for test mode and live key for live mode
|  `products`           | `Array[String]`| true                |  undefined          | The Okra products you want to use with the widget.
|  `env`                | `String`       | true                |  undefined          | 'production' or 'production-sandbox'
|  `clientName`         | `String`       | true                |  undefined          | Name of the customer using the widget on the application
|  `onClose`            | `function`     | true                |  undefined          | This acts as a callback that gets fired when the OkraView Component has been closed. It is intended to hold your navigation code, to navigate to previous page or any intended page. 
