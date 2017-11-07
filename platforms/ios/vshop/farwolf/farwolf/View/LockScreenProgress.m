//
//  LockScreenProgress.m
//  oa
//
//  Created by 郑江荣 on 16/4/21.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "LockScreenProgress.h"
 
@implementation LockScreenProgress

-(id)initWith:(UIView*)v
{
   
    self=[super init];
     self.view=v;
    if(v!=nil)
    self.p=[[MBProgressHUD alloc]initWithView:v];
    [self.view addSubview:self.p];
    [self.p show:false];
    [self.p setHidden:true];
    return self;
}


-(void)show:(NSString*)s
{
    self.p.labelText=s;
//    self.p.backgroundColor= [@"000000" toColor:0.3];
   
    [self.p setHidden:false];
}

-(void)show
{
    [self show:@"请稍候..."];
}

-(void)hide
{
     [self.p setHidden:true];
}


@end
