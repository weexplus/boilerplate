//
//  ProgressModule.m
//  Pods
//
//  Created by 郑江荣 on 2017/7/4.
//
//

#import "ProgressModule.h"

@implementation ProgressModule
@synthesize weexInstance;

WX_EXPORT_METHOD(@selector(show))
WX_EXPORT_METHOD(@selector(showFull:cancel:))
WX_EXPORT_METHOD(@selector(dismiss))



-(void)show
{
    [self showFull:@"加载中..." cancel:true];
}
-(void)showFull:(NSString*)txt cancel:(BOOL)cancel
{
    if(_p==nil)
        _p=[[LockScreenProgress alloc]initWith:self.weexInstance.viewController.view];
    [_p show:txt];
}


-(void)dismiss
{
    [_p hide];
    
}

@end

