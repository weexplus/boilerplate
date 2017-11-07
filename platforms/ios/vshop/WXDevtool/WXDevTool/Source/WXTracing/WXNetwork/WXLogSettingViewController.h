//
//  WXLogSettingViewController.h
//  Pods
//
//  Created by 齐山 on 2017/7/19.
//
//

#import <UIKit/UIKit.h>
@protocol WXLogSettingViewControllerDelegate<NSObject>
- (void)selectLogLevel:(NSInteger)logLevel;
@end

@interface WXLogSettingViewController : UIViewController

@property(nonatomic,weak)id <WXLogSettingViewControllerDelegate> delegate;

@end
