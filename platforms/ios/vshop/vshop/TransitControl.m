//
//  TransitControl.m
//  xinao
//
//  Created by 郑江荣 on 2017/11/14.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "TransitControl.h"
#import "farwolf.h"
#import "SplashControl.h"
@interface TransitControl ()

@end

@implementation TransitControl

- (void)viewDidLoad {
    [super viewDidLoad];

}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)viewWillAppear:(BOOL)animated
{
    [self.navigationController.navigationBar setHidden:true];
//    SplashControl *vc=   [self fromStoryBoard:@"Main/SplashControl" ];
    [self  present:@"Main/root" anim:false];
   
//    [self.navigationController present:vc animated:false];
 
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
