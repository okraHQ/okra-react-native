#import "OkraReactNative.h"
//#import <OkraWidget/OkraWidget-Swift.h>
#import <UIKit/UIKit.h>
#import "AppDelegate.h"

//#import “IOSNativeToast.h”
//@interface OkraReactNative()
//@property (nonatomic, retain) IOSNativeToast *toast;
//@end



@implementation OkraReactNative
RCT_EXPORT_MODULE()
RCT_EXPORT_METHOD(openOkraWidget: (BOOL *)isWebview key:(NSString *)key token:(NSString *)token products:(NSArray *)products env:(NSString *)env clientName:(NSString *)clientName callback: (RCTResponseSenderBlock)callback ){
    
      dispatch_async(dispatch_get_main_queue(), ^{
          OkraWebView *okraWebView = [[OkraWebView alloc] init];
          UINavigationController* okraWebViewNavigator = [[UINavigationController alloc] initWithRootViewController:okraWebView];
          AppDelegate *delegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];
          okraWebViewNavigator.modalPresentationStyle = UIModalPresentationFullScreen;
          [delegate.window.rootViewController presentViewController:okraWebViewNavigator animated:NO completion:nil];
      });
    
    //self.modalTransitionStyle = UIModalTransitionStyleFlipHorizontal;
    
  NSString* someString = [products componentsJoinedByString:@" "];;
  callback(@[someString]);
}
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
