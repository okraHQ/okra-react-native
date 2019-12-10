//
//  OkraWebView.h
//  OkraReactNative
//
//  Created by Malachy Egbuna on 07/12/2019.
//  Copyright © 2019 Facebook. All rights reserved.
//
#import <UIKit/UIKit.h>

@interface OkraWebView : UIViewController
@property (nonatomic, retain) NSString *env;
@property (nonatomic, retain) NSString *token;
@property (nonatomic, retain) NSString *key;
@property (nonatomic, retain) NSString *clientName;
@property (nonatomic, retain) NSArray <NSString *> *products;

@end

