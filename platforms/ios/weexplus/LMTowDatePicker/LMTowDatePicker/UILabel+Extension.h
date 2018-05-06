//
//  UILabel+Extension.h
//  Bidking
//
//  Created by jiangxj on 16/1/29.
//  Copyright © 2016年 jiangxj. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UILabel (Extension)

/**
 获取label的高度(需先设置字体、文字、宽度)

 @return label文字的高度
 */
- (CGFloat)textHeight;

/**
 *  获取label的宽度(需先设置字体、文字、高度)
 *
 *  @return label宽度
 */
- (CGFloat)textWidth;


/**
 根据文字自适应高度(需先设置字体、文字、宽度)
 */
- (void)autoHeight;

/**
 根据文字自适应高度(需先设置字体、文字、高度)
 */
- (void)autoWidth;

@end
