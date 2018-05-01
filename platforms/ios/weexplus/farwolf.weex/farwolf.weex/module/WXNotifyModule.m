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
@synthesize weexInstance;




-(void)regist:(NSString*)key callback:(WXModuleKeepAliveCallback)c
{
    
    if(self.callbacks==nil)
    {
        self.callbacks=[NSMutableDictionary new];
    }
//    [self.callbacks setObject:c forKey:key];
    [self.callbacks setValue:c forKey:key];
    
    
    if(notifymap==nil)
    {
        notifymap=[NSMutableDictionary new];
    }
    
    WXNormalViewContrller *vc= self.weexInstance.viewController;
    if([notifymap objectForKey:vc.sourceURL.absoluteString]==nil)
    {
        [notifymap setObject:@"regist" forKey:vc.sourceURL.absoluteString];
        [self regist:@"notify" method:@selector(onNotify:)];
    }
    
    
//    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(onNotify:) name:@"notify" object:nil];
    
//    [NSNotificationCenter defaultCenter] removeObserver:<#(nonnull NSObject *)#> forKeyPath:<#(nonnull NSString *)#> context:<#(nullable void *)#>
}



//-(void)dealloc
//{
//     [self unregist:@"notify"];
//}


- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self name:@"notify" object:nil];
    
    
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
//    [self notifyDict:@"notify" value:p];
    NSNotification *n=[[NSNotification alloc]initWithName:@"notify" object:nil userInfo:p];
    [[NSNotificationCenter defaultCenter]postNotification:n];

}

-(void)sendNative:(NSString*)key param:(NSDictionary*)param
{
    
    
    
    [self notifyDict:key value:param];
    
}


@end
