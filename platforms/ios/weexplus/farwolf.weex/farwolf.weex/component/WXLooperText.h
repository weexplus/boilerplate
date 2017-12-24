//
//  WXLooperText.h
//  Pods
//
//  Created by 郑江荣 on 2017/8/16.
//
//

#import <WeexSDK/WeexSDK.h>
#import"GYChangeTextView.h"
@interface WXLooperText : WXComponent<GYChangeTextViewDelegate>
@property (nonatomic,strong)GYChangeTextView *textview;
@property (nonatomic,strong)UIView *host;
@property (nonatomic,strong)NSString *color;
@property (nonatomic,strong)NSString *textAlign;
@property (nonatomic,strong)NSArray *data;
@property (nonatomic)CGFloat interval;
@property (nonatomic)CGFloat fontSize;

@end
