//
//  ErrorControl.h
//  Pods
//
//  Created by 郑江荣 on 2017/5/11.
//
//

#import <UIKit/UIKit.h>

@interface ErrorControl : UIViewController
@property(nonatomic,strong)NSString* errmsg;
@property (strong, nonatomic)  UIButton *btn;


@property (strong, nonatomic)  UITextView *errText;

@property (strong,nonatomic)   typeof(void(^)()) onClose;

@end




