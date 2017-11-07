//
//  WXNavigationModule.m
//  farwolf.weex
//
//  Created by 郑江荣 on 2017/5/3.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "WXNavigationModule.h"

@implementation WXNavigationModule
@synthesize weexInstance;
WX_EXPORT_METHOD(@selector(push:))
WX_EXPORT_METHOD(@selector(pushParam:param:))
WX_EXPORT_METHOD(@selector(pushFull:param:navbarVisibility:callback:animated:))
WX_EXPORT_METHOD(@selector(back))
WX_EXPORT_METHOD(@selector(backFull:animated:))
WX_EXPORT_METHOD(@selector(presentFull:param:navbarVisibility:createNav:callback:animated:))
WX_EXPORT_METHOD(@selector(present:))
WX_EXPORT_METHOD(@selector(dismiss))
WX_EXPORT_METHOD(@selector(dismissFull:animated:))
WX_EXPORT_METHOD(@selector(backTo:))
WX_EXPORT_METHOD(@selector(setPageId:))
WX_EXPORT_METHOD_SYNC(@selector(param))
WX_EXPORT_METHOD(@selector(setRoot:))

WX_EXPORT_METHOD(@selector(addBackGestureSelfControl))
WX_EXPORT_METHOD(@selector(invokeNativeCallBack:))

-(void)push:(NSString *)url
{
    [self pushFull:url param:nil navbarVisibility:@"hidden"  callback:nil animated:true];
    
}
-(void)pushParam:(NSString *)url param:(NSDictionary*)param
{
     [self pushFull:url param:param navbarVisibility:@"hidden" callback:nil animated:true];
}
-(void)pushFull:(NSString *)url param:(NSDictionary*)param  navbarVisibility:(NSString*) navbarVisibility callback:(WXModuleKeepAliveCallback)callback animated:(BOOL)animated
{
    if([url startWith:@"root:"])
    {
        url=[url replace:@"root:" withString:[Weex getBaseUrl]];
      
    }
//    url= [Weex getFinalUrl:url weexInstance:weexInstance];
    NSString *newURL = url;
    
    if ([url hasPrefix:@"//"]) {
        newURL = [NSString stringWithFormat:@"http:%@", url];
    } else if (![url hasPrefix:@"http"]) {
        newURL = [NSURL URLWithString:url relativeToURL:weexInstance.scriptURL].absoluteString;
    }
    
 
 
    [WeexFactory render:[NSURL URLWithString:newURL] compelete:^(Page *p) {
        WXNormalViewContrller *vc=[[WXNormalViewContrller alloc]initWithSourceURL:url];
        vc.hidesBottomBarWhenPushed = YES;
     
        vc.page=p;
        vc.instance=p.instance;
        vc.param=param;
        vc.callback=callback;
        vc.navbarVisibility=navbarVisibility;

    
        UIWindow *window = [UIApplication sharedApplication].keyWindow;
        UIViewController *rootViewController = window.rootViewController;
        
        [rootViewController.view addSubview:p.weexView];
        [rootViewController addChildViewController:vc];
//        [vc ]

        p.instance.renderFinish = ^(UIView *view) {
//            view.frame=CGRectMake(0, 0, 0, 0);
            [vc removeFromParentViewController];
            [p.weexView removeFromSuperview];
             [[weexInstance.viewController navigationController] pushViewController:vc animated:animated];
        };
        
    }];
}

-(id)param
{
    WXNormalViewContrller *vc=weexInstance.viewController;
    return vc.param;
}

-(void)setPageId:(NSString*)pid
{
    WXNormalViewContrller *vc=weexInstance.viewController;
    vc.pageid=pid;
}

-(void)backFull:(NSDictionary*)param animated:(BOOL)animated
{
    WXNormalViewContrller *vc=  weexInstance.viewController;
    if(vc.callback!=nil)
    vc.callback(param, false);
    [vc back:animated];
}
-(void)backTo:(NSString*)pageid
{
    WXNormalViewContrller *vc=  weexInstance.viewController;
    NSArray *n=vc.navigationController.viewControllers;
    WXNormalViewContrller *tvc=nil;
    for (WXNormalViewContrller* v in n) {
       
        if([pageid isEqualToString:v.pageid])
        {
            tvc=v;
        }
    }
    if(tvc!=nil)
    [vc.navigationController popToViewController:tvc animated:true];
    
}

-(void)back
{
    [self backFull:nil animated:true];
}

-(void)present:(NSString *)url
{
    [self presentFull:url param:nil navbarVisibility:@"hidden" createNav:true callback:nil animated:true];
}

-(void)presentFull:(NSString *)url param:(NSDictionary*)param navbarVisibility:(NSString*) navbarVisibility   createNav:(BOOL)createNav callback:(WXModuleKeepAliveCallback)callback animated:(BOOL)animated
{
    if([url startWith:@"root:"])
    {
        url=[url replace:@"root:" withString:[Weex getBaseUrl]];
    }
    NSString *newURL = url;
    if ([url hasPrefix:@"//"]) {
        newURL = [NSString stringWithFormat:@"http:%@", url];
    } else if (![url hasPrefix:@"http"]) {
        newURL = [NSURL URLWithString:url relativeToURL:weexInstance.scriptURL].absoluteString;
    }
 
    
    
    [WeexFactory render:[NSURL URLWithString:newURL] compelete:^(Page *p) {
        WXNormalViewContrller *vc=[[WXNormalViewContrller alloc]initWithSourceURL:url];
        vc.navbarVisibility=navbarVisibility;
        vc.hidesBottomBarWhenPushed = YES;
        vc.page=p;
        vc.instance=p.instance;
        vc.param=param;
        vc.callback=callback;
        if(createNav)
        {
            UINavigationController *nav=[[UINavigationController alloc]initWithRootViewController:vc];
            [weexInstance.viewController presentViewController:nav animated:animated completion:^{
//                 [weexInstance fireGlobalEvent:@"onPageInit" params:nil];
            }];
        }
        else
        {
            [weexInstance.viewController presentViewController:vc animated:animated completion:^{
            }];
        }

        
    }];
    
   
 
}

-(void)invokeNativeCallBack:(NSObject*)res
{
    
    if(((WXNormalViewContrller*)weexInstance.viewController).nativeCallback!=nil)
    ((WXNormalViewContrller*)weexInstance.viewController).nativeCallback(res);
}


-(void)dismiss
{
    WXNormalViewContrller *vc= weexInstance.viewController;
  
    if(vc.navigationController!=nil)
    {
        [vc.navigationController dismiss:true];
    }
    else
        [vc dismiss:true];
}
-(void)setRoot:(NSString*)rootid
{
    
}


-(void)addBackGestureSelfControl
{
//    if(  weexInstance.viewController.navigationController.interactivePopGestureRecognizer.delegate==nil)
    weexInstance.viewController.navigationController.interactivePopGestureRecognizer.delegate=nil;
     weexInstance.viewController.navigationController.interactivePopGestureRecognizer.delegate = self;
}
-(void)dismissFull:(NSDictionary*)param animated:(BOOL)animated
{
    
    WXNormalViewContrller *vc= weexInstance.viewController;
    if(vc.callback!=nil)
    vc.callback(param, false);
    if(vc.navigationController!=nil)
    {
        [vc.navigationController dismiss:animated];
    }
    else
    [vc dismiss:animated];
}

- (BOOL)gestureRecognizerShouldBegin:(UIGestureRecognizer *)gestureRecognizer
{
    if (weexInstance.viewController.navigationController.viewControllers.count == 1)
    {
        return NO;
    }
    else
    {
        return YES;
    }
}
@end
