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
-(void)docheck:(NSDictionary*)param
{
    
  
    
     NSString *url=[param objectForKey:@"url"];
     NSString *appid=[param objectForKey:@"appid"];
     BOOL failtoast=[[param objectForKey:@"failtoast"] boolValue];
     BOOL showprogress=[[param objectForKey:@"showprogress"] boolValue];
     NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
    NSString *vcode = [infoDictionary objectForKey:@"CFBundleVersion"];
     UpdateChecker *u=[UpdateChecker new];
    u.appid=appid;
    u.url=url;
    [u doCheck:appid vcode:vcode showprogress:showprogress failtoast:failtoast vc:[self topViewController] success:^(Version *v) {
        
        
    }];
    
}

- (UIViewController *)topViewController {
    UIViewController *resultVC;
    resultVC = [self _topViewController:[[UIApplication sharedApplication].keyWindow rootViewController]];
    while (resultVC.presentedViewController) {
        resultVC = [self _topViewController:resultVC.presentedViewController];
    }
    return resultVC;
}

- (UIViewController *)_topViewController:(UIViewController *)vc {
    if ([vc isKindOfClass:[UINavigationController class]]) {
        return [self _topViewController:[(UINavigationController *)vc topViewController]];
    } else if ([vc isKindOfClass:[UITabBarController class]]) {
        return [self _topViewController:[(UITabBarController *)vc selectedViewController]];
    } else {
        return vc;
    }
    return nil;
}


-(void)showDialog
{
    
}
@end
