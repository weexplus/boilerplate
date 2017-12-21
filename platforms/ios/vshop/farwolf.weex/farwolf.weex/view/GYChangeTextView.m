//
//  GYChangeTextView.m
//  GYShop
//
//  Created by mac on 16/6/13.
//  Copyright © 2016年 GY. All rights reserved.
//

#import "GYChangeTextView.h"

#define DEALY_WHEN_TITLE_IN_MIDDLE  3.0
#define DEALY_WHEN_TITLE_IN_BOTTOM  0.0

typedef NS_ENUM(NSUInteger, GYTitlePosition) {
    GYTitlePositionTop    = 1,
    GYTitlePositionMiddle = 2,
    GYTitlePositionBottom = 3
};

@interface GYChangeTextView ()


@property (nonatomic, strong) NSArray *contentsAry;
@property (nonatomic, assign) CGPoint topPosition;
@property (nonatomic, assign) CGPoint middlePosition;
@property (nonatomic, assign) CGPoint bottomPosition;
/*
 *1.控制延迟时间，当文字在中间时，延时时间长一些，如5秒，这样可以让用户浏览清楚内容；
 *2.当文字隐藏在底部的时候，不需要延迟，这样衔接才流畅；
 *3.通过上面的宏定义去更改需要的值
 */

@property (nonatomic, assign) BOOL shouldStop;         /*是否停止*/

@end

@implementation GYChangeTextView

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        self.topPosition    = CGPointMake(self.frame.size.width/2, -self.frame.size.height/2);
        self.middlePosition = CGPointMake(self.frame.size.width/2, self.frame.size.height/2);
        self.bottomPosition = CGPointMake(self.frame.size.width/2, self.frame.size.height/2*3);
        self.shouldStop = NO;
        _textLabel = [[UITextField alloc] init];
        _textLabel.layer.bounds = CGRectMake(0, 0, CGRectGetWidth(frame), CGRectGetHeight(frame));
        _textLabel.layer.position = self.middlePosition;
        _textLabel.textAlignment = NSTextAlignmentLeft;
        _textLabel.adjustsFontForContentSizeCategory=true;
        _textLabel.borderStyle=UITextBorderStyleNone;
        _textLabel.enabled=false;
        [self addSubview:_textLabel];
        self.clipsToBounds = YES;   /*保证文字不跑出视图*/
        if(self.interval==0)
            self.interval=3;
        self.needDealy =  self.interval;    /*控制第一次显示时间*/
        self.currentIndex = 0;
    }
    return self;
}

- (void)changeWordSpaceForLabel:(UILabel *)label WithSpace:(float)space {
    
//    NSString *labelText = label.text;
//    NSMutableAttributedString *attributedString = [[NSMutableAttributedString alloc] initWithString:labelText attributes:@{NSKernAttributeName:@(space)}];
//    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
//    [attributedString addAttribute:NSParagraphStyleAttributeName value:paragraphStyle range:NSMakeRange(0, [labelText length])];
//    label.attributedText = attributedString;
//    [label sizeToFit];
    
}



- (void)animationWithTexts:(NSArray *)textAry {
    self.contentsAry = textAry;
    if(textAry.count==0)
        return;
    self.currentIndex=0;
    self.textLabel.text = [textAry objectAtIndex:0];
    
    [self changeWordSpaceForLabel:_textLabel WithSpace:0.1];
     if([self.textLabel.layer animationKeys].count==0)
    [self startAnimation];
}

- (void)startAnimation {
    __weak typeof(self) weakSelf = self;
//   if([weakSelf.textLabel.layer animationKeys].count>0)
//    [weakSelf.textLabel.layer removeAllAnimations];
//    if(self.needDealy==0)
//        self.needDealy=3;
    [UIView animateWithDuration:0.3 delay:self.needDealy options:UIViewAnimationOptionCurveEaseInOut animations:^{
        if ([weakSelf currentTitlePosition] == GYTitlePositionMiddle) {
            weakSelf.textLabel.layer.position = weakSelf.topPosition;
        } else if ([weakSelf currentTitlePosition] == GYTitlePositionBottom) {
            weakSelf.textLabel.layer.position = weakSelf.middlePosition;
        }
    } completion:^(BOOL finished) {
        if ([weakSelf currentTitlePosition] == GYTitlePositionTop) {
            weakSelf.textLabel.layer.position = weakSelf.bottomPosition;
            weakSelf.needDealy = DEALY_WHEN_TITLE_IN_BOTTOM;
            weakSelf.currentIndex ++;
            weakSelf.textLabel.text = [weakSelf.contentsAry objectAtIndex:[weakSelf realCurrentIndex]];
//              [self changeWordSpaceForLabel:_textLabel WithSpace:0.1];
        } else {
            if(self.interval==0)
                self.interval=3;
            weakSelf.needDealy = self.interval;
        }
        if (!weakSelf.shouldStop) {
            [weakSelf startAnimation];
        } else { //停止动画后，要设置label位置和label显示内容
            weakSelf.textLabel.layer.position = weakSelf.middlePosition;
            weakSelf.textLabel.text = [weakSelf.contentsAry objectAtIndex:[weakSelf realCurrentIndex]];
//            [self changeWordSpaceForLabel:_textLabel WithSpace:0.1];
            NSInteger k=[weakSelf realCurrentIndex];
            [weakSelf.delegate change:k ];
        }
     
    }];
}



- (void)stopAnimation {
    self.shouldStop = YES;
}

- (NSInteger)realCurrentIndex {
    return self.currentIndex % [self.contentsAry count];
}

- (GYTitlePosition)currentTitlePosition {
    if (self.textLabel.layer.position.y == self.topPosition.y) {
        return GYTitlePositionTop;
    } else if (self.textLabel.layer.position.y == self.middlePosition.y) {
        return GYTitlePositionMiddle;
    }
    return GYTitlePositionBottom;
}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    if ([self.delegate respondsToSelector:@selector(gyChangeTextView:didTapedAtIndex:)]) {
        [self.delegate gyChangeTextView:self didTapedAtIndex:[self realCurrentIndex]];
    }
}

@end
