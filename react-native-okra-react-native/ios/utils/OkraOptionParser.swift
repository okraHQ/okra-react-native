//
//  OkraOptionParser.swift
//  OkraReactNative
//
//  Created by Oto-obong Bassey Eshiett on 21/06/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//
import Foundation

struct OkraOptionParser: Codable {
    var isWebview: Bool
    var key: String
    var token: String
    var products : Array<String>
    var env: String
    var clientName: String
    var color: String
    var limit: String
    var isCorporate: Bool
    var connectMessage: String
    var guarantors: GuarantorParser
    var callback_url: String
    var redirect_url: String
    var logo: String
    var widget_success: String
    var currency: String
    var exp: String
    var success_title: String
    var success_message: String
    var source : String = "RN-ios"
}

