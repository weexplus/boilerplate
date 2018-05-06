//
//  UILabel+Extension.m
//  Bidking
//
//  Created by jiangxj on 16/1/29.
//  Copyright © 2016年 jiangxj. All rights reserved.
//

#import "UILabel+Extension.h"

@implementation UILabel (Extension)

// 获取文字高度
- (CGFloat)textHeight
{
    // 设置文字的属性
    NSMutableDictionary *attributes = [NSMutableDictionary dictionary];
    attributes[NSFontAttributeName] = self.font;
    
    // 行高
//    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
//    [paragraphStyle setLineSpacing:lineSpacing];
//    attributes[NSParagraphStyleAttributeName] = paragraphStyle;

    // 将文字的区域范围传入，选择属性，传入文字的属性
    CGSize realSize = [self.text boundingRectWithSize:CGSizeMake(self.frame.size.width, MAXFLOAT) options:NSStringDrawingUsesLineFragmentOrigin attributes:attributes context:nil].size;
    
    return realSize.height;
}

// 获取文字宽度
- (CGFloat)textWidth
{
    //设置文字的属性
    NSMutableDictionary *attributes = [NSMutableDictionary dictionary];
    attributes[NSFontAttributeName] = self.font;

    // 将文字的区域范围传入，选择属性，传入文字的属性
    CGSize realSize = [self.text boundingRectWithSize:CGSizeMake(MAXFLOAT, self.frame.size.height) options:NSStringDrawingUsesLineFragmentOrigin attributes:attributes context:nil].size;
    
    return realSize.width;
}

// 根据文字自适应高度
- (void)autoHeight
{
    CGRect frame = self.frame;
    frame.size.height = [self textHeight];
    
    self.frame = frame;
}

// 根据文字自适应宽度
- (void)autoWidth
{
    CGRect frame = self.frame;
    frame.size.width = [self textWidth];
    
    self.frame = frame;
}

@end
