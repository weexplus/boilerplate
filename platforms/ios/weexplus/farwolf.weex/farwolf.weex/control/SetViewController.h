//
//  SetViewController.h
//  xinao
//
//  Created by 郑江荣 on 2017/8/29.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "DebugScocket.h"
#import "WXNormalViewContrller.h"

@interface SetViewController : UIViewController
@property (weak, nonatomic) IBOutlet UILabel *url;
@property (weak, nonatomic) IBOutlet UILabel *debugip;
@property (weak, nonatomic) IBOutlet UIButton *debugbtn;
@property (unsafe_unretained, nonatomic) IBOutlet UIButton *cachebtn;
@property (weak, nonatomic)   WXNormalViewContrller *vc;

@end
