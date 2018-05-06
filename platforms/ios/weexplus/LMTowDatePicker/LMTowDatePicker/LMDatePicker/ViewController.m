//
//  ViewController.m
//  LMTowDatePicker
//
//  Created by 天掌科技 on 2018/1/8.
//  Copyright © 2018年 天掌科技. All rights reserved.
//

#import "ViewController.h"
#import "LMDisposeChooseTimeView.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)touchUpChooseTimerView:(id)sender
{
    LMDisposeChooseTimeView *chooseTimeView = [[LMDisposeChooseTimeView alloc] initWithFrame:self.view.bounds];
    [chooseTimeView showInView:self.view];
    chooseTimeView.chooseDateWithTimerBlock = ^(NSString *dateWithTimerStr) {
        NSLog(@"dateWithTimerStr  %@",dateWithTimerStr);
    };
}

@end
