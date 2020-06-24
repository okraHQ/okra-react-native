//
//  OkraOptions.swift
//  OkraReactNative
//
//  Created by Oto-obong Bassey Eshiett on 21/06/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//

import Foundation

@objc public class OkraOptions : NSObject {
    @objc var isWebview: Bool
    @objc var key: String
    @objc var token: String
    @objc var products : Array<String>
    @objc var env: String
    @objc var clientName: String
    @objc var color: String
    @objc var limit: String
    @objc var isCorporate: Bool
    @objc var connectMessage: String
    @objc var guarantors: Guarantor
    @objc var callback_url: String
    @objc var redirect_url: String
    @objc var logo: String
    @objc var widget_success: String
    @objc var currency: String
    @objc var exp: String
    @objc var success_title: String
    @objc var success_message: String
    @objc var source : String = "ios"
    
    
    @objc public init(isWebview: Bool,key: String,token: String,products:Array<String>,env: String,clientName: String) {
        self.isWebview = isWebview
        self.key = key
        self.token = token
        self.products = products
        self.env = env
        self.clientName = clientName
        self.color = ""
        self.limit = ""
        self.isCorporate = false
        self.connectMessage = ""
        self.guarantors = Guarantor(status: false, message: "", number: 2)
        self.callback_url = ""
        self.redirect_url = ""
        self.logo = ""
        self.widget_success = ""
        self.currency = ""
        self.exp = ""
        self.success_title = ""
        self.success_message = ""
    }
    
    func encode() -> OkraOptionParser {
        return OkraOptionParser(isWebview: true, key: key, token: token, products: products, env: env, clientName: clientName, color: color, limit: limit, isCorporate: isCorporate, connectMessage: connectMessage, guarantors: GuarantorParser(status: guarantors.status, message: guarantors.message, number: guarantors.number), callback_url: callback_url, redirect_url: redirect_url, logo: logo, widget_success: widget_success, currency: currency, exp: exp, success_title: success_title, success_message: success_message)
    }
}

