//
//  WXLogViewController.h
//  Pods
//
//  Created by 齐山 on 2017/7/10.
//
//

#import <UIKit/UIKit.h>

@interface WXLogViewController : UIViewController<UITextViewDelegate>

@property(nonatomic,strong)UITextView *textView;

- (instancetype)initWithFrame:(CGRect )frame;

@end
