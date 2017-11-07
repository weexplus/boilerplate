//
//  LocationPicker.h
//  farwolf
//
//  Created by 郑江荣 on 2017/4/27.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "farwolf.h"
@interface LocationPicker : UIView<UIPickerViewDelegate, UIPickerViewDataSource>
@property (weak, nonatomic) IBOutlet UIPickerView *picker;
@property (strong, nonatomic)  NSArray<Region*>*regions;
@property (strong, nonatomic)  NSArray<Region*>*l1;
@property (strong, nonatomic)  NSArray<Region*>*l2;
@property (strong, nonatomic)  NSArray<Region*>*l3;
@property (strong,nonatomic)   typeof(void(^)(Region*,Region*,Region*)) onSelect;
@property ( nonatomic)  NSInteger first_row;
@property ( nonatomic)  NSInteger second_row;
@property ( nonatomic)  NSInteger third_row;
-(void)load:(NSArray<Region*>*)l;
-(void)show;
-(void)hide;
@end
