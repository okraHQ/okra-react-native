//
//  Okra.swift
//  OkraReactNative
//
//  Created by Oto-obong Bassey Eshiett on 21/06/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//

import Foundation
import UIKit

@objc(Okra)
class Okra :NSObject, RCTBridgeModule{
    
@objc(openOkraWidget:)
func openOkraWidget(url: String){
    let  guarantor:[String:Any] = ["status":true,"message":"hello nurse","number":1]
    let dataDictionary:[String:Any] = [ "isWebview":true,
                                       "key":"key",
                                       "token":"token",
                                       "products": ["auth","transactions"],
                                       "env":Environment.production.rawValue,
                                       "clientName":"Basey",
                                       "source":"RN-ios",
                                       "color":"#953ab7",
                                       "limit":"24",
                                       "guarantors":guarantor,
                                       "corporate":false,
                                       "connectMessage":"Which account do you want to connect with?",
                                       "callback_url":"",
                                       "redirect_url":"",
                                       "logo":"https://cdn.okra.ng/images/icon.svg",
                                       "widget_success":"Your account was successfully linked to Okra, Inc",
                                       "widget_failed":"Which account do you want to connect with?",
                                       "currency":"NGN",
                                       "exp":"2021-08-06",
                                       "manual":false,
                                       "success_title":"Successfully connect bank",
                                       "success_message":"You have successfully connected your bank account"
    ]
    
        var okraWebview = OkraWebView()
        okraWebview.dataDictionary = dataDictionary
        let webViewNavigator = UINavigationController(rootViewController: okraWebview)
        let delegate = UIApplication.shared.delegate as? AppDelegate
        webViewNavigator.modalPresentationStyle = .fullScreen
        delegate?.window?.rootViewController?.present(webViewNavigator, animated: true)
    }
    
    static func moduleName() -> String! {
          return "react-native-okra-react-native";
    }

    static func requiresMainQueueSetup() -> Bool {
        return true
    }
}
