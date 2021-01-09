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

## Data Dictionary

### Auth
Field | Required | Description
---|---|---
**id**<br>`ObjectID` | **Yes** | Unique Auth ID (Unique Okra Identifier)
**validated**<br>`Boolean` | **Yes** | Customer authentication status
**bank**<br>`ObjectID` | **Yes** | Unique Bank ID (Unique Okra Identifier)
**customer**<br>`ObjectID` | **Yes** | Unique Customer ID (Unique Okra Identifier)
**record**<br>`ObjectID` | **Yes** | Unique Record ID (Unique Okra Identifier)
**owner**<br>`ObjectID` | **Yes** | Unique Company ID (Unique Okra Identifier) (Your Client Token)
**created_at**<br>`Object` | **Yes** | Date Auth was fetched
**last_updated**<br>`Object` | **Yes** | Last Date Auth was fetched

### Balance
Field | Required | Description
---|---|---
**id**<br>`ObjectID` | **Yes** | Unique Balance ID (Unique Okra Identifier)
**available_balance**<br>`Integer` | **Yes** | Amount of available funds in account
**ledger_balance**<br>`Integer` | **Yes** | Closing balance of account
**currency**<br>`String` | **Yes** | The currency of the account
**connected**<br>`Boolean` | **Yes** | Customer connection status (Did they choose to connect this account to you)
**env**<br>`String` | **Yes** | Okra API Env the transaction was pulled from **production** or **production-sandbox**
**bank**<br>`ObjectID` | **Yes** | Unique Bank ID (Unique Okra Identifier)
**accounts**<br>`ObjectID` | **Yes** | Unique Account ID (Unique Okra Identifier)
**customer**<br>`ObjectID` | **Yes** | Unique Customer ID (Unique Okra Identifier)
**record**<br>`Array of ObjectID` | **Yes** | Unique Record ID (Unique Okra Identifier)
**created_at**<br>`Object` | **Yes** | Date Balance was fetched
**last_updated**<br>`Object` | **Yes** | Last Date Balance was fetched

### Identity
Field | Required | Description
---|---|---
**id**<br>`ObjectID` | **Yes** | Unique Identifier ID (Unique Okra Identifier)
**firstname**<br>`String` | **Yes** | Customer First Name
**middlename**<br>`String` | **Yes** | Customer Middle Name
**lastname**<br>`String` | **Yes** | Customer Last Name
**next_of_kins**<br>`Identity Object` | **Yes** | Customer Next of Kins
**dob**<br>`Date` | **Yes** | Customer Date of Birth
**verified**<br>`String` | **Yes** | BVN Validation status
**score**<br>`String` | **Yes** | Unique Okra Score
**dti**<br>`String` | **Yes** | Customer Debt to Income Score
**fullname**<br>`String` | **Yes** | Customer Fullname
**company_name**<br>`String` | **Yes | Company Name if Corporate Identity
**nin**<br>`String` | **Yes** | Customer NIN Number
**national_id**<br>`String` | **Yes** | Customer National ID Number
**drivers_lisence**<br>`String` | **Yes** | Customer Driver's License Number
**nimc**<br>`String` | **Yes** | Customer National Identity Management Commission (NIMC) Number
**voters_id**<br>`String` | **Yes** | Customer Voter's ID Number
**rc_number**<br>`String` | **Yes** | Company's Registered Company Number if Corporate Identity
**phone**<br>`Array of String` | **Yes** | Customer Phone Number
**last_login**<br>`String` | **Yes** | Customer Last Login via Okra
**email**<br>`Array of String` | **Yes** | Customer Email address
**address**<br>`Array of String` | **Yes** | Customer
**mothers_maiden**<br>`String` | **Yes** | Customer Mother's Maiden Name
**photo_ids**<br>`Array of Object` | **Yes** | Customer's photo ID
**env**<br>`String` | **Yes** | Okra API Env the transaction was pulled from **production** or **production-sandbox**
**bank**<br>`ObjectID` | **Yes** | Unique Bank ID (Unique Okra Identifier)
**accounts**<br>`ObjectID` | **Yes** | Unique Account ID (Unique Okra Identifier)
**customer**<br>`ObjectID` | **Yes** | Unique Customer ID (Unique Okra Identifier)
**record**<br>`Array of ObjectID` | **Yes** | Unique Record ID (Unique Okra Identifier)
**created_at**<br>`Object` | **Yes** | Date Balance was fetched
**last_updated**<br>`Object` | **Yes** | Last Date Balance was fetched

### Transaction
Field | Required | Description
---|---|---
**id**<br>`ObjectID` | **Yes** | Unique Transaction ID (Unique Okra Identifier)
**debit**<br>`Integer` | **No**| Amount deducted from account 
**credit**<br>`Integer` | **No**| Amount credited to account
**trans_date**<br>`Date` | **Yes** | Date transaction occurred
**cleared_date**<br>`Date` | **Yes** | Date transaction cleared at bank
**unformatted_trans_date**<br>`String` | **Yes** | Date transaction occurred (from bank)
**unformatted_cleared_date**<br>`String` | **Yes** | Date transaction cleared (from bank)
**branch**<br>`String` | **No**| Branch transactions occurred
**ref**<br>`String` | **No**| Bank reference ID (from bank)
**env**<br>`String` | **Yes** | Okra API Env the transaction was pulled from **production** or **production-sandbox**
**code**<br>`String` | **No**| Bank Code (from bank)
**benefactor**<br>`ObjectID` | **No**| Customer ID of sender (within Okra)
**code**<br>`String` | **No**| Bank Code (from bank)
**notes**<br>`Object` | **Yes** | Breakdown of Narrative from bank
**bank**<br>`ObjectID` | **Yes** | Unique Bank ID (Unique Okra Identifier)
**account**<br>`ObjectID` | **Yes** | Unique Account ID (Unique Okra Identifier)
**record**<br>`Array of ObjectID` | **Yes** | Unique Record ID (Unique Okra Identifier)
**created_at**<br>`Object` | **Yes** | Date transactions was fetched
**last_updated**<br>`Object` | **Yes** | Last Date transactions was fetched

### Notes Data Dictionary
Field | Required | Description
---|---|---
**desc**<br>`String` | **Yes** | Narrative / Description of transaction (combination of bank and user entered information)
**topics**<br>`Array of String` | **Yes** | Topics within the desc
**places**<br>`Array of String` | **Yes** | Places mentioned within the desc
**people**<br>`Array of String` | **Yes** | People mentioned within the desc
**actions**<br>`Array of String` | **Yes** | Actions mentioned within the desc
**subject**<br>`Array of String` | **Yes** | Subject of the desc
**preposition**<br>`Array of String` | **Yes** | Prepositions within desc to understand intent
