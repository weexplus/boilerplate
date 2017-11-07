//
//  LPPhotoView.h
//  LPPhotoViewer
//
//  Created by litt1e-p on 16/3/27.
//  Copyright © 2016年 litt1e-p. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol PhotoViewDelegate <NSObject>

- (void)tapHiddenPhotoView;
- (void)dragToDismiss;
@optional
- (void)offsetYForDrag:(CGFloat)offsetY;

@end

@interface LPPhotoView : UIView

@property(nonatomic, strong) UIImageView *imageView;
@property(nonatomic, assign) id<PhotoViewDelegate> delegate;
@property (nonatomic, getter=shouldDisableHorizontalDrag) BOOL disableHorizontalDrag;

- (instancetype)initWithFrame:(CGRect)frame withPhotoUrl:(NSString *)photoUrl;
- (instancetype)initWithFrame:(CGRect)frame withPhotoImage:(UIImage *)image;
- (void)zoomReset;

@end