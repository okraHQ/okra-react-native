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
        var okraWebview = OkraWebView()
        okraWebview.okraOptions = OkraOptions(isWebview: true, key: "101ee499-beed-53ef-b9e4-1846790792a5", token: "5da6358130a943486f33dced", products: ["auth","identity", "balance", "transactions"], env: Environment.production.rawValue, clientName: "Busha")
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
