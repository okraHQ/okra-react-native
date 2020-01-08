#import "OkraWebView.h"
#import "OkraOptions.h"
#import <WebKit/WebKit.h>

@interface OkraWebView (){
 OkraOptions *okraOptions;
}
@end

@implementation OkraWebView


- (void)viewDidLoad {
    [super viewDidLoad];
    /**UILabel *yourLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, 300, 70)];
    [yourLabel setTextColor:[UIColor brownColor]];
    [yourLabel setBackgroundColor:[UIColor clearColor]];
    [yourLabel setFont:[UIFont fontWithName: @"Trebuchet MS" size: 14.0f]];
    yourLabel.text = @"this is it";
    [self.view addSubview:yourLabel]; **/
    CGRect screen = [[UIScreen mainScreen] bounds];
    WKWebView *webView = [[WKWebView alloc] initWithFrame:CGRectMake(0, 0, CGRectGetWidth(screen), CGRectGetHeight(screen))];
    NSString *urlString = [self createWebURL];
    NSURL *url = [NSURL URLWithString:urlString];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    [webView loadRequest:request];
    [self.view addSubview:webView];
}

- (void)viewDidAppear:(BOOL)animated{
    NSLog(@"viewDidAppear loaded successfully");
}


- (NSString *) createWebURL{

    //https://demo-dev.okra.ng/link.html?env=dev&isWebview=true&token=5d8a35224d8113507c7521ac&products=%5B%22auth%22,%22transactions%22%5D&key=c81f3e05-7a5c-5727-8d33-1113a3c7a5e4&clientName=Basey'
    
    UIDevice *currentDevice = [UIDevice currentDevice];
    NSString *deviceId = [[currentDevice identifierForVendor] UUIDString];
    
    NSString *url = @"https://demo-dev.okra.ng/link.html?";
    
    url = [url stringByAppendingString:@"isWebview=true&"];
    
    url = [url stringByAppendingString:@"env="];
    url = [url stringByAppendingString:self.env];
    
    url = [url stringByAppendingString:@"&"];
    
    url = [url stringByAppendingString:@"token="];
    url = [url stringByAppendingString:self.token];
    
    url = [url stringByAppendingString:@"&"];
    
    url = [url stringByAppendingString:@"products="];
    url = [url stringByAppendingString: [self convertProductArrayToString : self.products]];
    
    url = [url stringByAppendingString:@"&"];
    
    url = [url stringByAppendingString:@"clientName="];
    url = [url stringByAppendingString:self.clientName];
    
    url = [url stringByAppendingString:@"&"];
    
    url = [url stringByAppendingString:@"source=RN-ios"];
    
    url = [url stringByAppendingString:@"&"];
    
    url = [url stringByAppendingString:@"uuid="];
    url = [url stringByAppendingString:deviceId];
    
    //http://webhook.site/efd5d5be-3a56-4455-bc51-83ec66f4c2f4?source=RN-ios&uuid=3E24DB28-8DCC-487C-80E7-EBE0DF90EFD4
   
    return url;
}

- (NSString *) convertProductArrayToString:(NSMutableArray <NSString *>*) products{
    
    NSString *formattedArray = @"[";
   
    for (int index = 0; index < [products count]; index++){
        
        if(index == ([products count] - 1)){
            //formattedArray.append("\"\(name)\"")
            formattedArray = [formattedArray stringByAppendingString:[products objectAtIndex:index]];
        }else{
           formattedArray = [formattedArray stringByAppendingString:[products objectAtIndex:index]];
            formattedArray = [formattedArray stringByAppendingString:@", "];
        }
        
    }
     formattedArray = [formattedArray stringByAppendingString:@"]"];
 
   return formattedArray;
}

@end

