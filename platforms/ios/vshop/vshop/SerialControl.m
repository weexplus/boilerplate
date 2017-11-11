//
//  SerialControl.m
//  vshop
//
//  Created by 郑江荣 on 2017/7/16.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "SerialControl.h"
#import "SplashControl.h"

@implementation SerialControl
- (void)viewDidLoad {
    self.page=[SplashControl getPage:@"电视剧"];
    [super viewDidLoad];
    [self viewWillAppear:true];
    [self regist:@"gotovc" method:@selector(gotovc)];
 
}



-(void)gotovc
{
//    if(_hasload)
//        return;
//    _hasload=true;
    [self push:@"Main/vc1" anim:true];
}
-(void)loadCompelete
{
    [self notify:@"tabrender" value:nil];
    
}

-(void)viewWillAppear:(BOOL)animated
{
    self.navbarVisibility =@"hidden";
    [super viewWillAppear:animated];
    [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleLightContent];
}

@end
