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
WX_EXPORT_METHOD(@selector(sendNative:param:))




-(void)regist:(NSString*)key callback:(WXModuleKeepAliveCallback)c
{
    if(self.callbacks==nil)
    {
        self.callbacks=[NSMutableDictionary new];
    }
//    [self.callbacks setObject:c forKey:key];
    [self.callbacks setValue:c forKey:key];
    [self unregist:@"notify"];
    [self regist:@"notify" method:@selector(onNotify:)];
    
}


-(void)dealloc
{
     [self unregist:@"notify"];
}


-(void)onNotify:(NSNotification*)n
{
    NSString *key=  n.userInfo[@"key"];
    NSDictionary *param=  n.userInfo[@"param"];
    WXModuleKeepAliveCallback c=[self.callbacks objectForKey:key];
    if(c!=nil)
    c(param,true);
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


@end
