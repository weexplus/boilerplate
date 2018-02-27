//
//  WXPageModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2017/12/20.
//

#import "WXPageModule.h"
#import "WXNormalViewContrller.h"

@implementation WXPageModule
@synthesize weexInstance;
WX_EXPORT_METHOD_SYNC(@selector(doubleBack))
WX_EXPORT_METHOD_SYNC(@selector(reload))
WX_EXPORT_METHOD_SYNC(@selector(enableBackKey))
WX_EXPORT_METHOD_SYNC(@selector(setBackKeyCallback:))
WX_EXPORT_METHOD_SYNC(@selector(exit))

-(void)doubleBack
{
    
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
-(void)exit
{
    exit(0);
}

@end
