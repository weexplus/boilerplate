//
//  Picker.h
//  oa
//
//  Created by 郑江荣 on 16/5/12.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "PickerType.h"
@interface Picker : UIView<UIPickerViewDelegate, UIPickerViewDataSource>
@property (weak, nonatomic) IBOutlet UIPickerView *picker;
@property (retain, nonatomic)  NSMutableArray<PickerType*> *data;


@property (strong,nonatomic)   typeof(void(^)(PickerType*)) onSelect;
-(void)select:(NSString*)s;
-(void)reloadData;
-(void)show;
-(void)dismiss;
@end
