//
//  Constant.h
//  TZSmartEmployee
//
//  Created by 天掌科技 on 2017/9/8.
//  Copyright © 2017年 天掌科技. All rights reserved.
//

#ifndef Constant_h
#define Constant_h

/**********************  XG-8.0  ***********************/
// 获取手机系统版本
#define KDeciceVersion [[[UIDevice currentDevice] systemVersion] floatValue]

//是否为iOSX及以上系统
#define iOS8  ([[UIDevice currentDevice].systemVersion doubleValue] >= 8.0)
#define iOS9  ([[UIDevice currentDevice].systemVersion doubleValue] >= 9.0)
#define iOS10 ([[UIDevice currentDevice].systemVersion doubleValue] >= 10.0)
#define iOS11 ([[UIDevice currentDevice].systemVersion doubleValue] >= 11.0)


/********************** Rect Size *************************/
#pragma mark - Size
//屏幕高度
#define KScreenWidth [[UIScreen mainScreen] bounds].size.width
#define KScreenHeight [[UIScreen mainScreen] bounds].size.height

//与iphone6屏幕宽度的单位比例
#define widthB CGRectGetWidth([UIScreen mainScreen].bounds)/375.0f
//与iphone6屏幕高度的单位比例
#define heightB CGRectGetHeight([UIScreen mainScreen].bounds)/667.0f

// 自适应高度和宽度(传入尺寸基于iphone6)
#define Width(width)    (width * widthB)
#define Height(height)  (height * heightB)

//导航栏高度
#define KNavigationBarHeight      64.0f // 顶部naviBar高度
#define KBottomTabbarHeight       49.0f // 底部tabBar高度(系统高度49.0)

//倒计时时间
#define KCodeTimeInternval 60
#define KBtnCornerRadius  5.*widthB

/********************** Font Size *************************/
#pragma mark - Font Size
#define Font(x) [UIFont systemFontOfSize:x * widthB]  //设置字体大小 （传数字）
#define BFont(x) [UIFont boldSystemFontOfSize:x * widthB] //设置字体加粗的大小 （传数字）


/********************** Color *************************/
#pragma mark - Color
#define UIColorFromRGB(rgbValue) [UIColor colorWithRed:((float)((rgbValue & 0xFF0000) >> 16))/255.0 green:((float)((rgbValue & 0xFF00) >> 8))/255.0 blue:((float)(rgbValue & 0xFF))/255.0 alpha:1.0]

//全局颜色
#define kCommonBackgroundColor               UIColorFromRGB(0xf5f5f5)    //用于主标题、分界块背景颜色
#define kCommonBlackColor                    UIColorFromRGB(0x333333)    //黑色
#define kCommonWhiteColor                    UIColorFromRGB(0xffffff)    //白色
#define kCommonTextColor                     UIColorFromRGB(0xa6a6a6)
#define kCommonOtherBackgroundColor          UIColorFromRGB(0xf9f9f9)    //所有页面背景颜色
#define kCommonLineColor                     UIColorFromRGB(0xf0f0f0)    //cell分割线颜色(白色)
#define kCommonGrayColor                     UIColorFromRGB(0x7c7c7c)    //灰色
#define kCommonblueColor                     UIColorFromRGB(0x33b0f1)    //按钮色调(蓝色)
#define kCommonRedColor                      UIColorFromRGB(0xfc2a2a)    //红色
#define kCommonDescribeTextColor             UIColorFromRGB(0xa8a8a8)    //提示文字

/********************** Methods *************************/
// 数据输出
#if DEBUG
#define NSLog(...) NSLog(__VA_ARGS__)
#else
#define NSLog(...)
#endif



#endif /* Constant_h */
