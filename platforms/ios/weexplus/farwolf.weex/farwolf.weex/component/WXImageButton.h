//
//  WXImageButton.h
//  Pods
//
//  Created by 郑江荣 on 2017/5/15.
//
//

#import <UIKit/UIKit.h>
@protocol downloadcompelete;
@interface WXImageButton : UIImageView
@property (nonatomic, strong) NSString *normal;
@property (nonatomic, strong) NSString *selected;
@property (nonatomic, strong) NSString *pressed;
@property (nonatomic, strong) UIImage *pressed_img;
@property (nonatomic, strong) UIImage *normal_img;
@property(nullable,nonatomic,weak) id<downloadcompelete>   delegate;
-(void)load;
@end


@protocol downloadcompelete<NSObject>
@optional
-(void)complete;

@end
