//
//  UpdateChecker.m
//  farwolf.business
//
//  Created by 郑江荣 on 2017/4/17.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "UpdateChecker.h"
#import "farwolf.h"
#import "UpdateJsonReader.h"
#import "UpdateJson.h"
#import "UpdateDialogControl.h"
@implementation UpdateChecker

 
-(void)doCheck:(NSString*)appid vcode:(NSString*)vcode  showprogress:(BOOL)showprogress failtoast:(BOOL)failtoast vc:(UIViewController*)vc success :(void(^)(Version*v))success;
{
    UpdateJsonReader *j=[[UpdateJsonReader alloc]init];
    j.url=self.url;
    [j addParam:@"appid" value:appid];
    [j addParam:@"systype" value:@"1"];
    [j addParam:@"vcode" value:vcode];
    
 
    
    LockScreenProgress *p= [[LockScreenProgress alloc]initWith:[self getCurrentVc].view];
    [j excuteFull:^{
        if(showprogress)
        [p show];
    } success:^(Json *j) {
        
         Version *v=  [Version yy_modelWithJSON:[j getDict:@"version"]];
        NSString *lastchecktime= [self getSaveValue:@"lastchecktime"];
        if(v.level==0)
        {
            if(lastchecktime!=nil&&![lastchecktime isEqualToString:@""])
            {
                
                NSTimeInterval now=  [[NSDate date] timeIntervalSince1970];
                if(now -[lastchecktime doubleValue]<1000*60*60*24*7)
                {
                    return;
                }
                else
                {
                    //             [[@"" addDouble:now] save:@"lastchecktime"];
                    [self remove:@"lastchecktime"];
                }
            }
        }
        
       
        UpdateDialogControl *cvc=[[UpdateDialogControl alloc]initWithNibName:@"updater" bundle:nil];
        cvc.view.frame=[UIApplication sharedApplication].keyWindow.frame;
        [cvc initBean:v];
        [vc.parentViewController addVc:cvc];
        
        success(v);
    } fail:^(Json *j, NSInteger code, NSString *msg) {
        if(failtoast)
        {
            [[self getCurrentVc] toast:msg];
        }
    } exception:^{
        if(failtoast)
        {
              [[self getCurrentVc] toast:@"网络异常！"];
        }
    } compelete:^{
         if(showprogress)
          [p hide];
    }];
}






@end
