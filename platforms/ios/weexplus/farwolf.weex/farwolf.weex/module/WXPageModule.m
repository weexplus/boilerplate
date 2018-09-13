//
//  WXPageModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2017/12/20.
//

#import "WXPageModule.h"
#import "WXNormalViewContrller.h"
#import "URL.h"
#import "WeexFactory.h"

@implementation WXPageModule
@synthesize weexInstance;
WX_EXPORT_METHOD_SYNC(@selector(doubleBack))
WX_EXPORT_METHOD_SYNC(@selector(reload))
WX_EXPORT_METHOD_SYNC(@selector(preRender:success:))
WX_EXPORT_METHOD_SYNC(@selector(enableBackKey))
WX_EXPORT_METHOD_SYNC(@selector(setBackKeyCallback:))
WX_EXPORT_METHOD_SYNC(@selector(exit))
WX_EXPORT_METHOD_SYNC(@selector(kill))
WX_EXPORT_METHOD_SYNC(@selector(setMainPage:))
WX_EXPORT_METHOD_SYNC(@selector(closeSplash))
WX_EXPORT_METHOD_SYNC(@selector(setKeyboadMode:))
WX_EXPORT_METHOD_SYNC(@selector(pressHome))


-(void)preRender:(NSString*)src success:(WXModuleCallback)success
{
    [WeexFactory preRender:[URL getFinalUrl:src weexInstance:weexInstance] success:^(NSString *url) {
        success(url);
    }];
}
-(void)doubleBack
{
    
}
-(void)setKeyboardMode:(NSString*)mode{
    
}
-(void)setKeyboadMode:(NSString*)mode{
    
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

-(void)closeSplash
{
    
}
-(void)kill
{
    exit(0);
}
-(void)exit
{
    exit(0);
}

-(void)pressHome
{
    exit(0);
}

@end
