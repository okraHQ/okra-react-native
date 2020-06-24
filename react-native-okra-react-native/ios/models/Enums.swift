//
//  Enums.swift
//  OkraReactNative
//
//  Created by Oto-obong Bassey Eshiett on 21/06/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//

import Foundation
 
public enum Environment :String {
    case dev
    case sandbox
    case staging
    case production
    public func toString() ->String { return self.rawValue }
}
