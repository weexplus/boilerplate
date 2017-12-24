//
//  UIView+Farwolf.h
//  oa
//
//  Created by 郑江荣 on 16/3/10.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>
typedef void(^clickcallback)();
@interface UIView (Farwolf)
@property (strong,nonatomic)  clickcallback oneClick;
@property (strong,nonatomic)  clickcallback dbClick;
@property (strong,nonatomic)  clickcallback threeClick;

-(UIViewController*)getCurrentVc;
-(UIViewController*)getCurrentVc:(Class)c;
-(void)addClick:(SEL)method host:(NSObject*)host;
-(void)addDoubleClick:(SEL )method host:(NSObject *)host;
-(void)addClick:(void(^)())click;
-(void)addDoubleClick:(void(^)())click;

-(void)add3Click:(void(^)())click;
-(UIView*)findViewByType:(Class)c;
-(UIView*)findAllViewByType:(Class)c;
-(UIView*)findOneViewByType:(Class)c;
-(void)addSubviewFull:(UIView *)view;
-(CGFloat) screenWidth;
-(CGFloat) screenHeight;
-(void)toast:(NSString*)msg;
-(void)disMissKeyboard;
-(void)distributeSpacingHorizontallyWith:(NSArray*)views;
-(void)distributeSpacingVerticallyWith:(NSArray*)views;
-(void)equalDivideHorizontally:(NSArray<UIView*>*)n padding:(CGFloat)p;
@property   (assign, nonatomic) IBInspectable CGFloat CornerRadius;
@property   (assign, nonatomic) IBInspectable CGFloat BorderWidth;
@property   (weak, nonatomic) IBInspectable UIColor* BorderColor;
@property   (weak, nonatomic) IBInspectable UIColor* LayerBackground;
@property   (assign, nonatomic) IBInspectable UIImage* ImageBackground;


@end
