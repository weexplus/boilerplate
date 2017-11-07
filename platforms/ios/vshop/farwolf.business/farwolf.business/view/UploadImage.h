//
//  UploadImage.h
//  oa
//
//  Created by 郑江荣 on 16/5/15.
//  Copyright © 2016年 郑江荣. All rights reserved.
//


#import <UIKit/UIKit.h>
#import "TOCropViewController.h"
#import "farwolf.h"
#define UPLOAD_IMG @"file.json"
@protocol ImageSelectDelegate;
@interface UploadImage : UIView<UIActionSheetDelegate, UIImagePickerControllerDelegate, UINavigationControllerDelegate ,TOCropViewControllerDelegate,JsonProtocol>

@property(nullable,nonatomic,weak) id<ImageSelectDelegate>   delegate;

@property(nonatomic) CGFloat aspX;
@property(nonatomic) CGFloat aspY;
@property(nonatomic) CGFloat quality;
@property(nonatomic) BOOL autoUpload;
@property(nonatomic,strong) UIColor* themeColor;
@property(nonatomic,strong) UIColor* titleColor;
@property(nonatomic,strong) UIColor* cancelColor;
-(void)setAsp:(CGFloat)aspX aspY:(CGFloat)aspY;
- (void)showPickImage;
- (void)showPickImage:(UIColor*)themecolor titleColor:(UIColor*) titleColor cancelColor:(UIColor*) cancelColor;
- (void)showPickImage:(UIColor*)themecolor titleColor:(UIColor*) titleColor cancelColor:(UIColor*) cancelColor vc:(UIViewController*)vc;
-(void)openCamera;
-(void)openPhoto;
@end

@protocol ImageSelectDelegate<NSObject>
@optional
-(void)imageSelect:(UIImage*)img;
-(void)imageUploadSuccess:(NSString*)url;

@end
