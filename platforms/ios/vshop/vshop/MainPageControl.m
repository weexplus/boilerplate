//
//  MainPageControl.m
//  vshop
//
//  Created by 郑江荣 on 2017/6/20.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "MainPageControl.h"
#import "SplashControl.h"
@interface MainPageControl ()

@end

@implementation MainPageControl

- (void)viewDidLoad {
       self.page=[SplashControl getPage:@"推荐"];
        [self.navigationController.navigationBar setHidden:true];
    [super viewDidLoad];

//    [[UIApplication sharedApplication]setStatusBarStyle:UIStatusBarStyleLightContent];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(UIStatusBarStyle)preferredStatusBarStyle
{
    return UIStatusBarStyleLightContent;
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
/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
