//
//  WXUpdateModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/3/27.
//

#import "WXUpdateModule.h"
#import "UpdateChecker.h"
#import "farwolf.h"

@implementation WXUpdateModule
@synthesize weexInstance;
WX_EXPORT_METHOD(@selector(docheck:))
WX_EXPORT_METHOD(@selector(download::))
-(void)docheck:(NSDictionary*)param
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
    [u doCheck:appid vcode:vcode showprogress:showprogress failtoast:failtoast vc:[weexInstance.viewController topViewController] success:^(Version *v) {
        
        
    } theme:theme];
    
}

-(void)download:(NSString*)url
{
    NSURL * ul = [NSURL URLWithString:url];
    [[UIApplication sharedApplication] openURL:ul];
}



-(void)showDialog
{
    
}
@end

