//
//  UIViewController+Farwolf.h
//  oa
//
//  Created by 郑江荣 on 16/3/10.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface UIViewController (Farwolf)


-(UIViewController*_Nullable)fromStoryBoard:(NSString*_Nonnull)name Id:(NSString*_Nonnull)Id;
-(UIViewController*_Nullable)fromStoryBoard:(NSString *_Nonnull)Id;
-(void)addVc:(UIViewController*_Nonnull)vc;

-(void)remove;

-(void)show:(UIViewController*_Nonnull)vc;

-(void)navigate:(UIViewController*_Nonnull)viewControllerToPresent:(BOOL*_Nullable) animated competion:(void (^ __nullable)(void))completion;

-(void)back:(BOOL)flag;
-(void)dismiss:(BOOL)flag completion:(void (^ __nullable)(void))completion;
-(void)dismiss:(BOOL)flag;

-(void)popVc:(Class)c;
-(void)pop:(BOOL)animated;
-(void)pop;


-(UIViewController*)push:(NSString*)Id anim:(BOOL) anim;
-(UIViewController*)present:(NSString*)Id anim:(BOOL) anim;
-(UIView*)fromXib:(NSString*)name;



-(CGFloat) screen;
-(CGFloat) screenWidth;
-(CGFloat) screenHeight;

-(CGFloat)titleHeight;
-(CGFloat)navBarHeight;
-(CGFloat)statusBarHeight;

-(void)toast:(NSString*)msg;
-(void)closeKeyboard;
-(void)closeKeyBoardOntouch;
-(void)setBackBar:(NSString*)txt color:(NSString*)color;

-(void) makeNavBarTransparent;
-(BOOL)checkLocationRights;
-(BOOL)checkNetRights;
-(BOOL)checkCameraRights;
-(BOOL)checkPhotoRights;
- (UIViewController *)topViewController;

@end
