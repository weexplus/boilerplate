//
//  MineControl.m
//  vshop
//
//  Created by 郑江荣 on 2017/5/25.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "MineControl.h"
#import "SplashControl.h"
@interface MineControl ()

@end

@implementation MineControl

- (void)viewDidLoad {
      self.page=[SplashControl getPage:@"我的"];
    [super viewDidLoad];
//     self.sourceURL = [[NSBundle mainBundle] URLForResource:@"app/login" withExtension:@"js"];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)loadCompelete
{
    [self notify:@"tabrender" value:nil];
    
}


-(UIStatusBarStyle)preferredStatusBarStyle
{
    return UIStatusBarStyleLightContent;
}

//-(void)viewWillAppear:(BOOL)animated
//{
//    [super viewWillAppear:animated];
//    [[UIApplication sharedApplication] setStatusBarStyle:UIStatusBarStyleLightContent];
//     [self.navigationController.navigationBar setTranslucent:true];
//}
//
//-(void)viewWillDisappear:(BOOL)animated
//{
//    
//    [self.navigationController.navigationBar setTranslucent:false];
//}

/*
 
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
