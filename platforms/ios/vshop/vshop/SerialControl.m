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
//    [self.navigationController.navigationBar setHidden:false];
//    [self.navigationController.navigationBar setBarTintColor:[UIColor redColor]];
//    //创建一个高20的假状态栏
//    UIView *statusBarView = [[UIView alloc] initWithFrame:CGRectMake(0, -20, self.view.bounds.size.width, 20)];
//    //设置成绿色
//    statusBarView.backgroundColor=[UIColor redColor];
//    // 添加到 navigationBar 上
//    [self.navigationController.navigationBar addSubview:statusBarView];
    [self push:@"Main/vc1" anim:true];
}
-(void)loadCompelete
{
    [self notify:@"tabrender" value:nil];
    
}
//-(void)viewDidAppear:(BOOL)animated
//{
//     [self.navigationController.navigationBar setHidden:true];
//}

-(void)viewWillAppear:(BOOL)animated
{
    self.navbarVisibility =@"hidden";
    [super viewWillAppear:animated];
    [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleLightContent];
    
}

@end
