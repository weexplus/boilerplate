//
//  WXKeyboardModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/11/3.
//

#import "WXKeyboardModule.h"
#import "farwolf.h"
#import "IQKeyboardManager.h"
@implementation WXKeyboardModule
WX_EXPORT_METHOD(@selector(enableAutoContorl:))
WX_EXPORT_METHOD(@selector(setKeyboardMode:))
WX_EXPORT_METHOD(@selector(setKeyboardContorl:dismiss:))
WX_EXPORT_METHOD(@selector(hide:))

@synthesize weexInstance;



-(void)setKeyboardMode:(NSString*)mode{
    
}
-(void)enableAutoContorl:(BOOL)enable
{
     [ IQKeyboardManager   sharedManager ] .enable   =   enable;
}

-(void)setKeyboardContorl:(WXModuleKeepAliveCallback)show dismiss:(WXModuleKeepAliveCallback)dismiss
{
    self.onshow=show;
    self.onhide=dismiss;
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWasShown:) name:UIKeyboardWillShowNotification object:nil];
    // 键盘消失的通知
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillBeHiden:) name:UIKeyboardWillHideNotification object:nil];
   
}
- (void)keyboardWasShown:(NSNotification *)notification
{
    // 获取键盘的高度
    CGRect frame = [[[notification userInfo] objectForKey:UIKeyboardFrameEndUserInfoKey] CGRectValue];
    CGFloat h=frame.size.height/self.weexInstance.pixelScaleFactor;
    self.onshow(@{@"height":@(h)}, true);
    
}
- (void)keyboardWillBeHiden:(NSNotification *)notification
{
     self.onhide(@{}, true);
}

-(BOOL)hide:(WXModuleCallback)callback{
    dispatch_async(dispatch_get_main_queue(), ^{
       BOOL t=  [[[UIApplication sharedApplication] keyWindow] endEditing:YES];
        if(callback)
        callback(@{@"res":@(t)});
        
    });
    
}


-(void)dealloc{
    [[NSNotificationCenter defaultCenter]removeObserver:self];
}

@end
