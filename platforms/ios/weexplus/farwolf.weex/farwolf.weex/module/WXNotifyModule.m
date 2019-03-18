//
//  WXNotifyModule.m
//  Pods
//
//  Created by 郑江荣 on 2017/5/18.
//
//

#import "WXNotifyModule.h"

@implementation WXNotifyModule

WX_EXPORT_METHOD(@selector(regist:callback:))
WX_EXPORT_METHOD(@selector(send:param:))
WX_EXPORT_METHOD(@selector(sendUnRegist))
WX_EXPORT_METHOD(@selector(sendNative:param:))
WX_EXPORT_METHOD(@selector(setNumber:))



-(void)sendUnRegist
{
    WXNormalViewContrller *vc= self.weexInstance.viewController;
    NSMutableDictionary *p=[NSMutableDictionary new];
    [p setValue:vc.sourceURL.absoluteString forKey:@"url"];
    [self notifyDict:@"removeUrl" value:p];
    
}

-(void)regist:(NSString*)key callback:(WXModuleKeepAliveCallback)c
{
    if(self.callbacks==nil)
    {
        self.callbacks=[NSMutableDictionary new];
    }
    //    [self.callbacks setObject:c forKey:key];
    NSMutableArray *ary=self.callbacks[key];
    if(ary==nil){
        ary=[NSMutableArray new];
    }
    [ary addObject:c];
    [self.callbacks setValue:ary forKey:key];
    
    
    
}






-(void)dealloc
{
    [self unregist:@"notify"];
}


-(void)onNotify:(NSNotification*)n
{
    
    NSString *key=  n.userInfo[@"key"];
    NSDictionary *param=  n.userInfo[@"param"];
    NSMutableArray *ary= [self.callbacks objectForKey:key];
    //    WXModuleKeepAliveCallback c=[self.callbacks objectForKey:key];
    
    
    if ([NSThread isMainThread])
    {
        if(ary!=nil){
            for(WXModuleKeepAliveCallback c in ary){
                if(c!=nil)
                    c(param,true);
            }
        }
        
    }
    else
    {
        dispatch_sync(dispatch_get_main_queue(), ^{
            //Update UI in UI thread here
            if(ary!=nil){
                for(WXModuleKeepAliveCallback c in ary){
                    if(c!=nil)
                        c(param,true);
                }
            }
            
            
        });
    }
    
    
}

-(void)removeUrl:(NSNotification*)n
{
    NSString *url=  n.userInfo[@"url"];
    WXNormalViewContrller *vc= self.weexInstance.viewController;
    if([url isEqualToString:vc.sourceURL.absoluteString])
    {
        
        [self unregist];
    }
    
}

- (UIViewController *)getCurrentVC
{
    UIViewController *result = nil;
    
    UIWindow * window = [[UIApplication sharedApplication] keyWindow];
    if (window.windowLevel != UIWindowLevelNormal)
    {
        NSArray *windows = [[UIApplication sharedApplication] windows];
        for(UIWindow * tmpWin in windows)
        {
            if (tmpWin.windowLevel == UIWindowLevelNormal)
            {
                window = tmpWin;
                break;
            }
        }
    }
    
    UIView *frontView = [[window subviews] objectAtIndex:0];
    id nextResponder = [frontView nextResponder];
    
    if ([nextResponder isKindOfClass:[UIViewController class]])
        result = nextResponder;
    else
        result = window.rootViewController;
    
    return result;
}


- (instancetype)init
{
    if (self = [super init]) {
        WXNormalViewContrller *vc= self.weexInstance.viewController;
        [self regist:@"removeUrl" method:@selector(removeUrl:)];
        [self regist:@"notify" method:@selector(onNotify:)];
        
        
    }
    
    return self;
}



-(void)send:(NSString*)key param:(NSDictionary*)param
{
    
    NSMutableDictionary *p=[NSMutableDictionary new];
    [p setValue:key forKey:@"key"];
    [p setValue:param forKey:@"param"];
    [self notifyDict:@"notify" value:p];
    
}

-(void)sendNative:(NSString*)key param:(NSDictionary*)param
{
    
    
    
    [self notifyDict:key value:param];
    
}


-(void)setNumber:(int)number{
    [UIApplication sharedApplication].applicationIconBadgeNumber=number;
}




@end

