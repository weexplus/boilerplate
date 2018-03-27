//
//  WXCenterPop.m
//  Pods
//
//  Created by 郑江荣 on 2017/12/6.
//

#import "WXCenterPop.h"

@implementation WXCenterPop
WX_EXPORT_METHOD(@selector(show:style:param:clickdismiss:callback:))
WX_EXPORT_METHOD(@selector(dismiss:))
@synthesize weexInstance;



-(void)show:(NSString*)url  style:(NSMutableDictionary*)style  param:(NSMutableDictionary*)param clickdismiss:(BOOL)clickdismiss callback:(WXModuleKeepAliveCallback)callback
{
    
    
    self.callback = callback;
    __block float width = [style[@"width"] floatValue];
    __block float height = [style[@"height"] floatValue];
    float top = [style[@"top"] floatValue];
    
    width=[Weex length:width instance:weexInstance];
    height=[Weex length:height instance:weexInstance];
    top=[Weex length:top instance:weexInstance];
    __weak typeof (self)weakself=self;
    [WeexFactory renderNew:[Weex getFinalUrl:url weexInstance:weexInstance] compelete:^(WXNormalViewContrller *vc) {
        
        UINavigationController *nav=[[UINavigationController alloc]initWithRootViewController:vc];
        [weakself initBackgroundView];
        weakself.vc=nav;
        vc.freeFrame=true;
        vc.param=param;
        vc.instance.param=param;
        [weakself.backgroundView addSubview:nav.view];
        nav.view.frame = CGRectMake(0, 0, width*0.5, height*0.5);
        nav.view.center = self.backgroundView.center;
        nav.view.backgroundColor=[UIColor clearColor];
        vc.view.backgroundColor=[UIColor clearColor];
        [weakself.weexInstance.viewController addChildViewController:nav];
        [weakself com:nav.view frame:CGRectMake(0, 0, width, height) clickdismiss:clickdismiss];
        
    } fail:^(NSString *msg) {
        
    }  frame:CGRectMake(0, 0, width,height) isPortrait:true];
    [self regist:@"closecenterpop" method:@selector(close:)];
    
}
//
-(void)close:(NSNotification*)no
{
    //    [self dismiss];
    
    [self gone:_vc.view param:no.userInfo];
}


- (void)initBackgroundView {
    self.backgroundView = [[UIView alloc] initWithFrame:weexInstance.viewController.view.bounds];
    self.backgroundView.backgroundColor = [UIColor colorWithWhite:0 alpha:0.5];
    [weexInstance.viewController.view addSubview:self.backgroundView];
}


-(void)dismiss:(NSDictionary*)param
{
    //    [self gone:_vc.view];
    //    [self notify:@"closecenterpop" value:nil];
    [self notifyDict:@"closecenterpop" value:param];
    
}

- (void)com:(UIView*)view frame:(CGRect)frame  clickdismiss:(BOOL)clickdismiss{
    __weak typeof(self) weakSelf = self;
    //    [UIView animateWithDuration:0.1 animations:^{
    //
    //        view.frame = frame;
    //        view.center = self.backgroundView.center;
    //    }completion:^(BOOL finished) {
    //
    //        [weakSelf.vc.view addClick:^{}];
    //        [self.backgroundView addClick:^{
    //            if(clickdismiss)
    //                [weakSelf gone:view param:@{}];
    //        }];
    //    }];
    
    view.frame = frame;
    view.center = self.backgroundView.center;
    
    [weakSelf.vc.view addClick:^{}];
    [self.backgroundView addClick:^{
        if(clickdismiss)
            [weakSelf gone:view param:@{}];
    }];
}


- (void)gone:(UIView*)view  param:(NSDictionary*)param{
    
    __weak typeof(self) weakSelf = self;
    [UIView animateWithDuration:0.1 animations:^{
        
        CGFloat w= view.frame.size.width;
        CGFloat h= view.frame.size.height;
        CGFloat rate=0.9;
        view.frame = CGRectMake(0, 0,0, 0);
        view.center = self.backgroundView.center;
        
        
    }completion:^(BOOL finished) {
        
        [weakSelf.backgroundView removeFromSuperview];
        weakSelf.backgroundView = nil;
        [weakSelf.vc.view removeFromSuperview];
        [weakSelf.vc removeFromParentViewController];
        weakSelf.vc = nil;
        [weakSelf unregist];
        if(_callback)
            _callback(param,false);
        
    }];
}
@end

