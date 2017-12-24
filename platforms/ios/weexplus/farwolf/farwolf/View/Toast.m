//
//  Toast.m
//  oa
//
//  Created by 郑江荣 on 16/3/10.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "Toast.h"
#import "MBProgressHUD.h"

@implementation Toast

-(id)initWithView:(UIView*)view
{
    self=[super init];
    if(self)
    {
        self.view=view;
    }
    return self;
}

+(Toast*)create:(UIView*)view
{
    return [[Toast alloc]initWithView:view];
}

-(void)toast:(NSString*)s
{
    [self toast:s time:1 x:0 y:100];
}
-(void)toast:(NSString*)s time:(NSTimeInterval)time x:(float) x y:(float)y
{
    if(self.view==nil)
        return;
    MBProgressHUD *p=[MBProgressHUD showHUDAddedTo:self.view animated:true];
    p.mode = MBProgressHUDModeText;
    p.labelText=s;
    p.yOffset=y;
    p.xOffset=x;
    p.removeFromSuperViewOnHide=true;
   [p hide:true afterDelay:time];
    
    
}






@end
