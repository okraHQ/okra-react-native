# okra-react-native

This is a react native library for implementing okra widget

### Get Started
This library would help you add Okra widget to your react native android app in no time. All you need to do is ...

### Install
1. Clone the git library using:

``` git
git clone https://github.com/okraHQ/okra-react-native.git
```

2. Pack the library with npm (Inside the root directory):
``` npm
npm pack
```
this would create a `tgz` extention file named `okra-react-native-expo-1.0.0.tgz`. 

3. Install this file in your react native project using the following command:
``` npm
npm install ${path}/okra-react-native-expo-1.0.0.tgz
```
`${path}` refers to the absolute path to the `okra-react-native-expo-1.0.0.tgz` file for example: `C:\Users\Java\Documents\Github\okra-react-native\react-native-okra-react-native\okra-react-native-expo-1.0.0.tgz` where the packed npm module is located. 


### Usage


``` javascript
import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import {OkraView} from 'okra-react-native-expo';

export default function App() {
  return (
    <OkraView
      clientName = "mesut ozil"
      okraKey = "ea0059da-2d35-56b8-b3df-1280226b920f"
      token = "5da64ac38e94d862392d092d"
      products = {["auth","transactions","balance","income"]}
      onClose = {function(){alert("this is my concept")}}
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
|  `env`                | `String`       | true                |  undefined          | 
|  `clientName`         | `String`       | true                |  undefined          | Name of the customer using the widget on the application
