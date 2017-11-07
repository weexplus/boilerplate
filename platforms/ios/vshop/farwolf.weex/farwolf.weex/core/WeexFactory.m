//
//  WeexFactory.m
//  Pods
//
//  Created by 郑江荣 on 2017/6/1.
//
//

#import "WeexFactory.h"
#import "Weex.h"
@implementation WeexFactory


+ (void)render:(NSURL *)sourceURL  compelete:(void(^)(Page*))complete
{
 
    if([Weex getBaseUrl] ==nil||[[Weex getBaseUrl] isEqualToString:@""])
        [Weex setBaseUrl:sourceURL.absoluteString];
    Page *p=[Page new];
    p.instance = [[WXSDKInstance alloc] init];
    p.instance.frame = CGRectMake(0.0f, 0.0f, [UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height);
    p.instance.pageObject = self;
    NSString *newURL = nil;
    
    if ([sourceURL.absoluteString rangeOfString:@"?"].location != NSNotFound) {
        newURL = [NSString stringWithFormat:@"%@&random=%d", sourceURL.absoluteString, arc4random()];
    } else {
        newURL = [NSString stringWithFormat:@"%@?random=%d", sourceURL.absoluteString, arc4random()];
    }
    [p.instance renderWithURL:[NSURL URLWithString:newURL] options:@{@"bundleUrl":sourceURL.absoluteString} data:nil];
    
 
    __strong __typeof(p) weakP = p;
    p.instance.onCreate = ^(UIView *view) {
 
        view.frame=CGRectMake(0.0f, 0.0f, [UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height);
        weakP.weexView=view;
        complete(weakP);
        
    
    };
    
    
    p.instance.onFailed = ^(NSError *error) {
        
        NSString *msg=error.userInfo[@"NSLocalizedDescription"];
        NSLog(@"%@", msg);
    
    };
    
    
    p.instance.renderFinish = ^(UIView *view) {
//        [weakSelf _updateInstanceState:WeexInstanceAppear];
//        [_instance fireGlobalEvent:@"onPageInit" params:nil];
        p.hasload=true;
    };
 
    
}

+ (void)renderNew:(NSURL *)sourceURL  compelete:(void(^)(WXNormalViewContrller*))complete  frame:(CGRect)frame
{
    
    if([Weex getBaseUrl] ==nil||[[Weex getBaseUrl] isEqualToString:@""])
        [Weex setBaseUrl:sourceURL.absoluteString];
    Page *p=[Page new];
    p.instance = [[WXSDKInstance alloc] init];
    p.instance.frame = CGRectMake(0.0f, 0.0f, [UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height);
    p.instance.pageObject = self;
    NSString *newURL = nil;
    
    if ([sourceURL.absoluteString rangeOfString:@"?"].location != NSNotFound) {
        newURL = [NSString stringWithFormat:@"%@&random=%d", sourceURL.absoluteString, arc4random()];
    } else {
        newURL = [NSString stringWithFormat:@"%@?random=%d", sourceURL.absoluteString, arc4random()];
    }
    [p.instance renderWithURL:[NSURL URLWithString:newURL] options:@{@"bundleUrl":sourceURL.absoluteString} data:nil];
    
    
    __strong __typeof(p) weakP = p;
    p.instance.onCreate = ^(UIView *view) {
        
        //        view.frame=CGRectMake(0.0f, 0.0f, [UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height);
        weakP.weexView=view;
        
        WXNormalViewContrller *vc=[[WXNormalViewContrller alloc]initWithSourceURL:sourceURL.absoluteString];
        vc.hidesBottomBarWhenPushed = YES;
        vc.page=p;
        vc.navbarVisibility=@"hidden";
        vc.instance=p.instance;
        p.instance.frame=frame;
        UIWindow *window = [UIApplication sharedApplication].keyWindow;
        UIViewController *rootViewController = window.rootViewController;
        [rootViewController.view addSubview:p.weexView];
        [rootViewController addChildViewController:vc];
        p.instance.renderFinish = ^(UIView *view) {
            [vc removeFromParentViewController];
            [p.weexView removeFromSuperview];
            complete(vc);
        };
        
        
        
    };
    
    
    p.instance.onFailed = ^(NSError *error) {
        
        NSString *msg=error.userInfo[@"NSLocalizedDescription"];
        NSLog(@"%@", msg);
        
    };
    
    
    
    
    
    
}


+(NSString*)getUrl:(NSString*)url instance:(WXSDKInstance*)instance
{
    if([url startWith:@"root:"])
    {
        url=[url replace:@"root:" withString:[Weex getBaseUrl]];
    }
    NSString *newURL = url;
    if ([url hasPrefix:@"//"]) {
        newURL = [NSString stringWithFormat:@"http:%@", url];
    } else if (![url hasPrefix:@"http"]) {
        newURL = [NSURL URLWithString:url relativeToURL:instance.scriptURL].absoluteString;
    }
    return newURL;
}



@end
