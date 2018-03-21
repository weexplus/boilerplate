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
WX_EXPORT_METHOD(@selector(pushFull:callback:))
WX_EXPORT_METHOD(@selector(back))
WX_EXPORT_METHOD(@selector(backFull:animated:))
WX_EXPORT_METHOD(@selector(presentParam:param:))
WX_EXPORT_METHOD(@selector(presentFull:callback:))

WX_EXPORT_METHOD(@selector(present:))
WX_EXPORT_METHOD(@selector(dismiss))
WX_EXPORT_METHOD(@selector(dismissFull:animated:))
WX_EXPORT_METHOD(@selector(backTo:))
WX_EXPORT_METHOD(@selector(setPageId:))
WX_EXPORT_METHOD_SYNC(@selector(param))
WX_EXPORT_METHOD(@selector(setRoot:))

WX_EXPORT_METHOD(@selector(addBackGestureSelfControl))
WX_EXPORT_METHOD(@selector(invokeNativeCallBack:))




//-(void)push:(NSMutableDictionary*)param
//{
//   NSString *url=  [param  objectForKey:@"url"];
//   NSMutableDictionary *p=  [param  objectForKey:@"param"];
//   WXModuleKeepAliveCallback callback= [param  objectForKey:@"callback"];
//   BOOL animated=[param  objectForKey:@"animated"];
//  [self pushFull:url param:param callback:callback animated:animated];
//
//}

-(void)push:(NSString *)url
{
     NSMutableDictionary *dic=[NSMutableDictionary new];
     [dic setValue:url forKey:@"url"];
     [dic setValue:nil forKey:@"param"];
     [dic setValue:@(true) forKey:@"animated"];
     [dic setValue:@(true) forKey:@"isPortrait"];
    [self pushFull:dic callback:nil];

}
-(void)pushParam:(NSString *)url param:(NSDictionary*)param
{
    NSMutableDictionary *dic=[NSMutableDictionary new];
    [dic setValue:url forKey:@"url"];
    [dic setValue:param forKey:@"param"];
    [dic setValue:@(true) forKey:@"animated"];
    [dic setValue:@(true) forKey:@"isPortrait"];
    [self pushFull:dic callback:nil];
    
//     [self pushFull:url param:param callback:nil animated:true];
}

-(void)pushFull:(NSDictionary*)parameters   callback:(WXModuleKeepAliveCallback)callback
{
    NSString *url=[parameters objectForKey:@"url"];
    NSDictionary *param=[parameters objectForKey:@"param"];
    BOOL animated=true;
    if([parameters objectForKey:@"animated"]!=nil)
    {
       animated=[parameters[@"animated"] boolValue];
    }
    BOOL isPortrait=true;
    if([parameters objectForKey:@"isPortrait"]!=nil)
    {
        isPortrait=[parameters[@"isPortrait"] boolValue];
       
    }
    if(!isPortrait)
    {
        [self presentFull:parameters callback:callback];
        return;
    }
    
    [WeexFactory renderNew:[URL getFinalUrl:url weexInstance:weexInstance] compelete:^(WXNormalViewContrller *vc) {
        
        vc.param=param;
        vc.callback = callback;
        vc.instance.param=param;
        [[weexInstance.viewController navigationController] pushViewController:vc animated:animated];
        
    } fail:^(NSString *msg) {
        
    }  frame:[UIApplication sharedApplication].keyWindow.bounds isPortrait:isPortrait];
    
    
}

//-(void)pushFull:(NSString *)url param:(NSDictionary*)param   callback:(WXModuleKeepAliveCallback)callback animated:(BOOL)animated
//{
//
//    [WeexFactory renderNew:[URL getFinalUrl:url weexInstance:weexInstance] compelete:^(WXNormalViewContrller *vc) {
//
//         vc.param=param;
//         vc.callback = callback;
//         vc.instance.param=param;
//         [[weexInstance.viewController navigationController] pushViewController:vc animated:animated];
//
//    } fail:^(NSString *msg) {
//
//    }  frame:[UIApplication sharedApplication].keyWindow.bounds];
//
//
//}

-(id)param
{
//    WXNormalViewContrller *vc=weexInstance.viewController;
    return weexInstance.param;
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
    UIViewController *tvc=nil;
    for (UIViewController* v in n) {
       
        if([v isKindOfClass:[WXNormalViewContrller class]])
        {
            WXNormalViewContrller *wx=v;
            if([pageid isEqualToString:wx.pageid])
            {
                tvc=wx;
                break;
            }
        }
        NSMutableArray *nx=[NSMutableArray new ];
        [self getChildVc:v array:nx];
        for(UIViewController *vx in nx)
        {
            if([vx isKindOfClass:[WXNormalViewContrller class]])
            {
                WXNormalViewContrller *wx=vx;
                if([pageid isEqualToString:wx.pageid])
                {
                    tvc=v;
                    break;
                }
            }
        }
            
      
    }
    if(tvc!=nil)
    [vc.navigationController popToViewController:tvc animated:true];
    
}


-(void)getChildVc:(UIViewController *)vc array:(NSMutableArray*)array
{
    if(vc.childViewControllers.count>0)
    {
        [array addObjectsFromArray:vc.childViewControllers];
        for(UIViewController *v in vc.childViewControllers)
        {
            [self getChildVc:v array:array];
        }
    }
    
}

-(void)back
{
    [self backFull:nil animated:true];
}

-(void)present:(NSString *)url
{
    NSMutableDictionary *dic=[NSMutableDictionary new];
    [dic setValue:url forKey:@"url"];
    [dic setValue:nil forKey:@"param"];
    [dic setValue:@(true) forKey:@"animated"];
    [dic setValue:@(true) forKey:@"isPortrait"];
    [self presentFull:dic callback:nil];
}

-(void)presentParam:(NSString*)url param:(NSDictionary*)param
{
    NSMutableDictionary *dic=[NSMutableDictionary new];
    [dic setValue:url forKey:@"url"];
    [dic setValue:param forKey:@"param"];
    [dic setValue:@(true) forKey:@"animated"];
    [dic setValue:@(true) forKey:@"isPortrait"];
    [self presentFull:dic callback:nil];
}
-(void)presentFull:(NSDictionary*)parameters   callback:(WXModuleKeepAliveCallback)callback
{
   
    NSString *url=[parameters objectForKey:@"url"];
    NSDictionary *param=[parameters objectForKey:@"param"];
    BOOL animated=true;
    if([parameters objectForKey:@"animated"]!=nil)
    {
        animated=[parameters[@"animated"] boolValue];
    }
    BOOL isPortrait=true;
    if([parameters objectForKey:@"isPortrait"]!=nil)
    {
        isPortrait=[parameters[@"isPortrait"] boolValue];
        
    }
   
    
    
    [WeexFactory renderNew:[URL getFinalUrl:url weexInstance:weexInstance] compelete:^(WXNormalViewContrller *vc) {
        
        UINavigationController *nav=[[UINavigationController alloc]initWithRootViewController:vc];
        vc.param=param;
        vc.callback=callback;
        vc.instance.param=param;
        [weexInstance.viewController presentViewController:nav animated:animated completion:^{
            
        }];
        
    } fail:^(NSString *msg) {
    
    }   frame:[UIApplication sharedApplication].keyWindow.bounds isPortrait:isPortrait];
    
   
 
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
