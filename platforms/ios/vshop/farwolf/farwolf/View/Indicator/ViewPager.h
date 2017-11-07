//
//  ViewPager.h
//  oa
//
//  Created by 郑江荣 on 16/6/8.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>
@protocol PageChangeDelegate;
@interface ViewPager : UIView<UIScrollViewDelegate>
@property (weak, nonatomic) IBOutlet UIView *layout;
@property (weak, nonatomic) IBOutlet UIScrollView *scrollview;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *width;
@property (nonatomic,strong)NSMutableArray <UIView*>*data;
-(void)load;
-(void)addPageView:(UIView*)v;
-(void)addPageViewController:(UIViewController*)vc;
+(ViewPager*)create;
-(void)load;
-(void)selectPage:(int)index;
@property(nullable,nonatomic,weak) id<PageChangeDelegate>   delegate;
@end

@protocol PageChangeDelegate<NSObject>
@optional
-(void)pageChange:(int)index view:(UIView*)v;



@end
