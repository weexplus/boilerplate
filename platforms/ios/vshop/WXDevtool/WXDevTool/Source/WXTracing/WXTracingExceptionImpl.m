//
//  WXTracingExceptionImpl.m
//  Pods
//
//  Created by 齐山 on 2017/7/10.
//
//

#import "WXTracingExceptionImpl.h"
#import "WXTracingViewControllerManager.h"
#import <WeexSDK/WXUtility.h>
#import <WeexSDK/WXSDKManager.h>
#import "WXDebugger.h"
#import "WXTracingUtility.h"
#import "WXTracingViewControllerManager.h"

static const CGFloat WXTacingDefaultFontSize = 16.0;
static const CGFloat WXTacingDefaultWidth = 230.0;
static const CGFloat WXTacingDefaultHeight = 30.0;
static const CGFloat WXTacingDefaultPadding = 30.0;


@interface WXTracingToastInfo : NSObject

@property (nonatomic, strong) UIView *toastView;
@property (nonatomic, weak) UIView *superView;
@property (nonatomic, assign) double duration;

@end


@implementation WXTracingToastInfo

@end

@interface WXTracingToastManager : NSObject

@property (strong, nonatomic) NSMutableArray<WXTracingToastInfo *> *toastQueue;
@property (strong, nonatomic) UIView *toastingView;

+ (WXTracingToastManager *)sharedManager;

@end

@implementation WXTracingToastManager
+ (WXTracingToastManager *)sharedManager{
    static WXTracingToastManager * shareInstance;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        shareInstance = [[WXTracingToastManager alloc] init];
        shareInstance.toastQueue = [NSMutableArray new];
    });
    return shareInstance;
}

@end

@implementation WXTracingExceptionImpl

- (void)onJSException:(WXJSExceptionInfo*) exception
{
    dispatch_async(dispatch_get_main_queue(), ^{
        NSString *instanceId =  [[[WXSDKManager bridgeMgr] getInstanceIdStack] firstObject];
        NSMutableDictionary *taskData = [WXTracingManager getTracingData];
        WXTracingTask *task = [taskData objectForKey:instanceId];
        NSString *strTmp = [NSString stringWithFormat:@"<Weex>[exception]bundleJSType:%@\r\n%@ appVersion:%@ osVersion:%@ platform:%@ deviceModel:%@\r\n",task.bundleJSType,exception.description,[[WXUtility getEnvironment] objectForKey:@"appVersion"],[[WXUtility getEnvironment] objectForKey:@"osVersion"],[[WXUtility getEnvironment] objectForKey:@"platform"],[[WXUtility getEnvironment] objectForKey:@"deviceModel"]];
        NSString *strMsg = [NSString stringWithFormat:@"%zd: %@ %@ %@",[[WXTracingViewControllerManager sharedInstance].messages count],[WXTracingUtility tracingTime] ,[[WXUtility getEnvironment] objectForKey:@"appName"],strTmp];
        if(strTmp.length>0){
            NSMutableArray *messages = [WXTracingViewControllerManager sharedInstance].messages;
            [messages addObject:strMsg];
            [self showAlert:strMsg];
        }

    });
    
}

- (void)showAlert:(NSString *)message
{
    if([WXTracingViewControllerManager  isLoadTracing]){
        return;
    }
    
    NSString *msg = [NSString stringWithFormat:@"%@\r\nIf you want to know more, please open weex MNT",message];
    UIAlertController * alert = [UIAlertController
                                 alertControllerWithTitle:@"JS Exception"
                                 message:msg
                                 preferredStyle:UIAlertControllerStyleAlert];
    
    NSMutableParagraphStyle   *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    [paragraphStyle setLineSpacing:1.5];//行间距
    paragraphStyle.alignment = NSTextAlignmentLeft;//文本对齐方式 左右对齐（两边对齐
    
    NSMutableAttributedString *alertControllerMessageStr = [[NSMutableAttributedString alloc] initWithString:msg];
    [alertControllerMessageStr addAttribute:NSParagraphStyleAttributeName value:paragraphStyle range:NSMakeRange(0, [msg length])];//设置段落样式

    [alertControllerMessageStr addAttribute:NSForegroundColorAttributeName value:COLOR_TRACING_EXCEPTION range:NSMakeRange(0, [msg length])];
    [alertControllerMessageStr addAttribute:NSFontAttributeName value:[UIFont systemFontOfSize:16] range:NSMakeRange(0, [msg length])];
    [alert setValue:alertControllerMessageStr forKey:@"attributedMessage"];
    
    //Add Buttons
    
    UIAlertAction* copyButton = [UIAlertAction
                                actionWithTitle:@"复制"
                                style:UIAlertActionStyleDefault
                                handler:^(UIAlertAction * action) {
                                    UIPasteboard *pasteboard = [UIPasteboard generalPasteboard];
                                    [pasteboard setString:msg];
                                    UIView *superView =  [[[UIApplication sharedApplication] windows] objectAtIndex:0];
                                    if (superView) {
                                        [self toast:@"已成功复制到到剪切板" duration:1.0];
                                    }
                                }];
    
    UIAlertAction* screenButton = [UIAlertAction
                                 actionWithTitle:@"截屏"
                                 style:UIAlertActionStyleDefault
                                 handler:^(UIAlertAction * action) {
                                     [self saveImg];
                                 }];
    
    UIAlertAction* cancelButton = [UIAlertAction
                               actionWithTitle:@"Cancel"
                               style:UIAlertActionStyleCancel
                               handler:^(UIAlertAction * action) {
                                   
                               }];
    
    //Add your buttons to alert controller
    [alert addAction:screenButton];
    [alert addAction:copyButton];
    [alert addAction:cancelButton];
    UIViewController *rootViewController = [[[UIApplication sharedApplication] keyWindow] rootViewController];

    [rootViewController presentViewController:alert animated:YES completion:nil];
}

-(void)saveImg
{
    UIWindow *screenWindow = [[UIApplication sharedApplication] keyWindow];

    UIGraphicsBeginImageContextWithOptions(screenWindow.frame.size, NO, 0);
    CGContextRef ctx = UIGraphicsGetCurrentContext();
    // 将要保存的view绘制到上下文中
    [screenWindow.layer renderInContext:ctx];
    UIImage *viewImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    UIImageWriteToSavedPhotosAlbum(viewImage, self, @selector(image:didFinishSavingWithError:contextInfo:), nil);
}

- (void)image:(UIImage *)image didFinishSavingWithError:(NSError *)error contextInfo:(void *)contextInfo{
    if (error) {
        UIView *superView =  [[[UIApplication sharedApplication] windows] objectAtIndex:0];
        if (superView) {
            [self toast:@"屏幕保存失败" duration:1.0];
        }
    }else{
        UIView *superView =  [[[UIApplication sharedApplication] windows] objectAtIndex:0];
        if (superView) {
            [self toast:@"屏幕保存成功" duration:1.0];
        }
    }
}


- (void)toast:(NSString *)message duration:(double)duration
{
    UIView *superView =  [[[UIApplication sharedApplication] windows] objectAtIndex:0];
    UIView *toastView = [self toastViewForMessage:message superView:superView];
    WXTracingToastInfo *info = [WXTracingToastInfo new];
    info.toastView = toastView;
    info.superView = superView;
    info.duration = duration;
    [[WXTracingToastManager sharedManager].toastQueue addObject:info];
    
    if (![WXTracingToastManager sharedManager].toastingView) {
        [self showToast:toastView superView:superView duration:duration];
    }
}

- (UIView *)toastViewForMessage:(NSString *)message superView:(UIView *)superView
{
    CGFloat padding = WXTacingDefaultPadding;
    UILabel *messageLabel = [[UILabel alloc] initWithFrame:CGRectMake(padding/2, padding/2, WXTacingDefaultWidth, WXTacingDefaultHeight)];
    messageLabel.numberOfLines =  0;
    messageLabel.textAlignment = NSTextAlignmentCenter;
    messageLabel.text = message;
    messageLabel.font = [UIFont boldSystemFontOfSize:WXTacingDefaultFontSize];
    messageLabel.lineBreakMode = NSLineBreakByTruncatingTail;
    messageLabel.textColor = [UIColor whiteColor];
    messageLabel.backgroundColor = [UIColor clearColor];
    [messageLabel sizeToFit];
    
    UIView *toastView = [[UIView alloc] initWithFrame:
                         CGRectMake(
                                    (superView.frame.size.width-messageLabel.frame.size.width-padding)/2,
                                    (superView.frame.size.height-messageLabel.frame.size.height-padding)/2,
                                    messageLabel.frame.size.width+padding,
                                    messageLabel.frame.size.height+padding
                                    )];
    
    CGPoint point = CGPointZero;
    UIWindow *window = [[[UIApplication sharedApplication] windows] objectAtIndex:0];
    
    // adjust to screen orientation
    UIInterfaceOrientation orientation = (UIInterfaceOrientation)[[UIApplication sharedApplication] statusBarOrientation];
    switch (orientation) {
        case UIDeviceOrientationPortrait: {
            point = CGPointMake(window.frame.size.width/2, window.frame.size.height/2);
            break;
        }
        case UIDeviceOrientationPortraitUpsideDown: {
            toastView.transform = CGAffineTransformMakeRotation(M_PI);
            float width = window.frame.size.width;
            float height = window.frame.size.height;
            point = CGPointMake(width/2, height/2);
            break;
        }
        case UIDeviceOrientationLandscapeLeft: {
            toastView.transform = CGAffineTransformMakeRotation(M_PI/2); //rotation in radians
            point = CGPointMake(window.frame.size.width/2, window.frame.size.height/2);
            break;
        }
        case UIDeviceOrientationLandscapeRight: {
            toastView.transform = CGAffineTransformMakeRotation(-M_PI/2);
            point = CGPointMake(window.frame.size.width/2, window.frame.size.height/2);
            break;
        }
        default:
            break;
    }
    
    toastView.center = point;
    toastView.frame = CGRectIntegral(toastView.frame);
    
    [toastView addSubview:messageLabel];
    toastView.layer.cornerRadius = 7;
    toastView.backgroundColor=[UIColor colorWithWhite:0 alpha:0.7];
    
    return toastView;
}

- (void)showToast:(UIView *)toastView superView:(UIView *)superView duration:(double)duration
{
    if (!toastView || !superView) {
        return;
    }
    
    [WXTracingToastManager sharedManager].toastingView = toastView;
    [superView addSubview:toastView];
    
    [UIView animateWithDuration:0.2 delay:duration options:UIViewAnimationOptionCurveEaseInOut animations:^{
        toastView.transform = CGAffineTransformConcat(toastView.transform, CGAffineTransformMakeScale(0.8, 0.8)) ;
    } completion:^(BOOL finished) {
        [UIView animateWithDuration:0.2 delay:0.2 options:UIViewAnimationOptionCurveEaseInOut animations:^{
            toastView.alpha = 0;
        } completion:^(BOOL finished){
            [toastView removeFromSuperview];
            [WXTracingToastManager sharedManager].toastingView = nil;
            
            NSMutableArray *queue = [WXTracingToastManager sharedManager].toastQueue;
            if (queue.count > 0) {
                [queue removeObjectAtIndex:0];
                if (queue.count > 0) {
                    WXTracingToastInfo *info = [queue firstObject];
                    [self showToast:info.toastView superView:info.superView duration:info.duration];
                }
            }
        }];
    }];
}
@end
