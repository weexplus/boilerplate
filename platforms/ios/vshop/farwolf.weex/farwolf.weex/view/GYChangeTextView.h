//
//  GYChangeTextView.h
//  GYShop
//
//  Created by mac on 16/6/13.
//  Copyright © 2016年 GY. All rights reserved.
//

#import <UIKit/UIKit.h>

@class GYChangeTextView;

@protocol GYChangeTextViewDelegate <NSObject>

- (void)gyChangeTextView:(GYChangeTextView *)textView didTapedAtIndex:(NSInteger)index;
-(void)change:(NSInteger)index;

@end

@interface GYChangeTextView : UIView
@property (nonatomic, strong) UITextField *textLabel;

@property (nonatomic, assign) id<GYChangeTextViewDelegate> delegate;
@property (nonatomic, assign) CGFloat needDealy;
@property (nonatomic, assign) CGFloat interval;
@property (nonatomic, assign) NSInteger currentIndex;  /*当前播放到那个标题了*/
- (NSInteger)realCurrentIndex;
- (void)animationWithTexts:(NSArray *)textAry;
- (void)stopAnimation;

@end
