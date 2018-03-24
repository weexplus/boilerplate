//
//  WXPageModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2017/12/20.
//

#import "WXPageModule.h"
#import "WXNormalViewContrller.h"
#import "URL.h"

@implementation WXPageModule
@synthesize weexInstance;
WX_EXPORT_METHOD_SYNC(@selector(doubleBack))
WX_EXPORT_METHOD_SYNC(@selector(reload))
WX_EXPORT_METHOD_SYNC(@selector(enableBackKey))
WX_EXPORT_METHOD_SYNC(@selector(setBackKeyCallback:))
WX_EXPORT_METHOD_SYNC(@selector(exit))
WX_EXPORT_METHOD_SYNC(@selector(setMainPage:))


-(void)doubleBack
{
    
}
-(void)reload
{
    WXNormalViewContrller *vc=(WXNormalViewContrller*) weexInstance.viewController;
    [vc refreshWeex];
}
-(void)enableBackKey:(BOOL)enable
{
    
}
-(void)setBackKeyCallback:(WXModuleKeepAliveCallback)callback
{
    
}
-(void)setMainPage:(NSString*)url
{
    NSURL *ul= [URL getFinalUrl:url weexInstance:weexInstance];
    NSString *s=[ul relativeString];
    if([s startWith:@"file:///"])
    {
        NSArray *n= [s split:@"/app/"];
        s=[@"app/" add: n[1] ];
    }
    [s save:@"mainurl"];
}
-(void)exit
{
    exit(0);
}

@end
