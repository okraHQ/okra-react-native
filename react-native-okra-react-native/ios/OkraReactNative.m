#import "OkraReactNative.h"
#import <UIKit/UIKit.h>
#import "AppDelegate.h"
#import "OkraOptions.h"
#import <React/RCTConvert.h>

//#import “IOSNativeToast.h”
//@interface OkraReactNative()
//@property (nonatomic, retain) IOSNativeToast *toast;
//@end

@implementation OkraReactNative
RCT_EXPORT_MODULE()
RCT_EXPORT_METHOD(openOkraWidget: (NSDictionary *)details){
    
    
    //(BOOL *)isWebview key:(NSString *)key token:(NSString *)token products:(NSArray *)products env:(NSString *)env clientName:(NSString *)clientName)
    
    
    //OkraOptions *okraOptions = [[OkraOptions alloc]init];
    //okraOptions.clientName = clientName;
    
      dispatch_async(dispatch_get_main_queue(), ^{
          OkraWebView *okraWebView = [[OkraWebView alloc] init];
          okraWebView.key = [RCTConvert NSString:details[@"key"]];
          okraWebView.token = [RCTConvert NSString:details[@"token"]];
          okraWebView.products = [RCTConvert NSArray:details[@"products"]];
          okraWebView.env = [RCTConvert NSString:details[@"environment"]];
          okraWebView.clientName = [RCTConvert NSString:details[@"clientName"]];
          UINavigationController* okraWebViewNavigator = [[UINavigationController alloc] initWithRootViewController:okraWebView];
          //NSString * myString = [okraWebView.createWebURL];
          AppDelegate *delegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];
          okraWebViewNavigator.modalPresentationStyle = UIModalPresentationFullScreen;
          [delegate.window.rootViewController presentViewController:okraWebViewNavigator animated:NO completion:nil];
      });
    
    //self.modalTransitionStyle = UIModalTransitionStyleFlipHorizontal;
    
  //NSString* someString = [products componentsJoinedByString:@" "];
    //NSString* someString = okraOptions->clientName;
  //callback(@[someString]);
}

/**
[self convertProductArrayToString]

- (NSString *) convertProductArrayToString{
    
    NSString * myString = @"Hello World";
    NSLog(@"%@", myString);
    
   return @"cool guy";
}
**/
@end

/**
 
 RCT_EXPORT_METHOD(openOkraWidget: (BOOL *)isWebview, key:(NSString *)key token:(NSString *)token products:(NSArray *)products env:(NSString *)env, clientName:(NSString *)clientName){
     
    // [Okra createWithBaseController:self okraOptions:[[OkraOptions alloc] initWithIsWebview:isWebview key:key token:token products: products env:env clientName:clientName]];
     
     self.modalTransitionStyle = UIModalTransitionStyleFlipHorizontal;
     [self presentedViewController: [[UIViewController alloc] initWithNibName:@"OkraWebView" bundle:nil] animated:YES];
     
   NSString* someString = @"widget successfully opened";
   callback(@[someString]);
 }
 */

