//
//  WXUpdateModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/3/27.
//

#import "WXUpdateModule.h"
#import "UpdateChecker.h"
#import "farwolf.h"
#import "Config.h"

@implementation WXUpdateModule
@synthesize weexInstance;
WX_EXPORT_METHOD(@selector(doCheck:fail:))
WX_EXPORT_METHOD(@selector(download:))
WX_EXPORT_METHOD(@selector(doCheckJs:))
WX_EXPORT_METHOD(@selector(checkDownloadApk:))
WX_EXPORT_METHOD(@selector(hotUpdate:start:progress:compelete:exception:))

-(void)doCheck:(NSDictionary*)param fail:(WXModuleCallback)fail
{
    
    
    
    NSString *url=[param objectForKey:@"url"];
    NSString *appid=[param objectForKey:@"appid"];
    NSString *theme=[param objectForKey:@"theme"];
    BOOL failtoast=[[param objectForKey:@"failtoast"] boolValue];
    BOOL showprogress=[[param objectForKey:@"showprogress"] boolValue];
    NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
    NSString *vcode = [infoDictionary objectForKey:@"CFBundleVersion"];
    UpdateChecker *u=[UpdateChecker new];
    u.appid=appid;
    u.url=url;
    
    [u doCheck:appid vcode:vcode showprogress:showprogress failtoast:failtoast vc:[weexInstance.viewController TopViewController] success:^(Version *v) {
        
        
    } theme:theme fail:^(){
        fail(@{});
    }];
    
}

-(void)checkDownloadApk:(WXModuleCallback)callback
{
  
}

-(void)doCheckJs:(NSDictionary*)param
{
    
    
    
    NSString *url=[param objectForKey:@"url"];
    NSString *appid=[param objectForKey:@"appid"];
    NSString *jsversion=[Config jsVersion];
    NSString *theme=[param objectForKey:@"theme"];
    BOOL failtoast=[[param objectForKey:@"failtoast"] boolValue];
    BOOL showprogress=[[param objectForKey:@"showprogress"] boolValue];
    NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
    NSString *nativecode = [infoDictionary objectForKey:@"CFBundleVersion"];
    UpdateChecker *u=[UpdateChecker new];
    u.appid=appid;
    u.url=url;
    [u doCheckJs:appid jsversion:jsversion nativecode:nativecode showprogress:showprogress failtoast:failtoast vc:[weexInstance.viewController TopViewController] success:^(Version *v) {
        
    } theme:theme];
    
    
}


-(void)download:(NSString*)url
{
    NSURL * ul = [NSURL URLWithString:url];
    [[UIApplication sharedApplication] openURL:ul];
}


-(void)hotUpdate:(NSMutableDictionary*)param start:(WXModuleCallback)start progress:(WXModuleCallback)progress compelete:(WXModuleCallback)compelete exception:(WXModuleCallback)exception
{
    
    NSString* url=param[@"url"];
    int version=param[@"version"];
    int mode=param[@"mode"];
    JsVersion *j=[JsVersion new];
    j.js_version=version;
    j.mode=mode;
    
    UpdateChecker *up=[UpdateChecker new];
    start(nil);
    [up updateJs:url version:j progress:^(float p) {
        progress(@(p));
    } compelete:^(NSString *path) {
        compelete(path);
    }];
   
}


-(void)showDialog
{
    
}
@end

