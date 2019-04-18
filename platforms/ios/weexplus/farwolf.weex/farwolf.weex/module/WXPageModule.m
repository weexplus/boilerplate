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
WX_EXPORT_METHOD(@selector(doubleBack))
WX_EXPORT_METHOD(@selector(reload))
WX_EXPORT_METHOD(@selector(preRender:success:))
WX_EXPORT_METHOD(@selector(enableBackKey:))
WX_EXPORT_METHOD(@selector(setBackKeyCallback:))
WX_EXPORT_METHOD(@selector(exit))
WX_EXPORT_METHOD(@selector(kill))
WX_EXPORT_METHOD(@selector(setMainPage:))
WX_EXPORT_METHOD(@selector(closeSplash))
WX_EXPORT_METHOD(@selector(pressHome))
WX_EXPORT_METHOD(@selector(setKeyboardMode:))
WX_EXPORT_METHOD_SYNC(@selector(getTopPage))




-(void)preRender:(NSString*)src success:(WXModuleCallback)success
{
    [WeexFactory preRender:[URL getFinalUrl:src weexInstance:weexInstance] success:^(NSString *url) {
        success(url);
    }];
}
-(void)doubleBack
{
    
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

-(void)setKeyboardMode:(NSString*)mode
{
    
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

-(NSString*)getTopPage{
    WXNormalViewContrller *vc= (WXNormalViewContrller*)self.weexInstance.viewController.TopViewController;
    if(vc==nil)
        return @"";
    if(vc.instance==nil)
        return @"";
    NSString *url=vc.instance.scriptURL.absoluteString;
    if([url contains:@"random"]){
        url=[url split:@"?random"][0];
    }
    return url;
}

@end

