//
//  LMLeftDatePicker.h
//  LMTowDatePicker
//
//  Created by 天掌科技 on 2018/1/8.
//  Copyright © 2018年 天掌科技. All rights reserved.
//

#import <UIKit/UIKit.h>
typedef void(^chooseDateStr) (NSString *dateStr);
@interface LMLeftDatePicker : UIView

@property (nonatomic, copy) chooseDateStr dateBlock;

@property (nonatomic ,strong) NSString *nowDateStr;

@end
