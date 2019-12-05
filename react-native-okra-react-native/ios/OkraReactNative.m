#import "OkraReactNative.h"
#import <OkraWidget/OkraWidget-Swift.h>

 @implementation MyFancyLibrary
 RCT_EXPORT_MODULE()
 RCT_EXPORT_METHOD(greetings:(RCTResponseSenderBlock)callback){
     
     [Okra createWithBaseController:self okraOptions:[[OkraOptions alloc] initWithIsWebview: <#(BOOL)#>  key:@"c81f3e05-7a5c-5727-8d33-1113a3c7a5e4" token:@"5d8a35224d8113507c7521ac" products:[NSArray arrayWithObjects:@"auth",@"transactions", nil] env:@"production" clientName:@"John"]];

   
   NSString* someString = @"crash";
   callback(@[someString]);
 }
 @end


