#import <Foundation/Foundation.h>

@interface OkraOptions{
   NSString *env;
   NSString *token;
   NSString *key;
   NSString *clientName;
   NSMutableArray <NSString *> *products;
    
    //(BOOL *)isWebview key:(NSString *)key token:(NSString *)token products:(NSArray *)products env:(NSString *)env clientName:(NSString *)clientName
}

@property(nonatomic, retain) NSString *clientName;

@end
