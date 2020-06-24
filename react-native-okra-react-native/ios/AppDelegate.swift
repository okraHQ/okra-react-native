//
//  AppDelegate.swift
//  OkraReactNative
//
//  Created by Oto-obong Bassey Eshiett on 22/06/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//

import Foundation

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
var window: UIWindow?
var bridge: RCTBridge!
func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
   let jsCodeLocation: URL
   jsCodeLocation = RCTBundleURLProvider.sharedSettings().jsBundleURL(forBundleRoot: "index", fallbackResource:nil)
   let rootView = RCTRootView(bundleURL: jsCodeLocation, moduleName: "react-native-okra-react-native", initialProperties: nil, launchOptions: launchOptions)
   let rootViewController = UIViewController()
   rootViewController.view = rootView
   self.window = UIWindow(frame: UIScreen.main.bounds)
   self.window?.rootViewController = rootViewController
   self.window?.makeKeyAndVisible()
   return true
  }
}
