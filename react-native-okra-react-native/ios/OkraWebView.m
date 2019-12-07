#import "OkraWebView.h"
#import <WebKit/WebKit.h>

@interface OkraWebView ()


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
    
    WKWebView *webView = [[WKWebView alloc] initWithFrame:CGRectMake(0, 0, 1024, 768)];
    NSString *urlString = @"https://www.google.com";
    NSURL *url = [NSURL URLWithString:urlString];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    [webView loadRequest:request];
    [self.view addSubview:webView];
}

- (void)viewDidAppear:(BOOL)animated{
    NSLog(@"viewDidAppear loaded successfully");
}



@end

