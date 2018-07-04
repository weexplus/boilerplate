//
//  ZipDownloaderControl.h
//  AFNetworking
//
//  Created by 郑江荣 on 2018/4/30.
//

#import <UIKit/UIKit.h>
#import "JsVersion.h"

@interface ZipDownloaderControl : UIViewController
@property (unsafe_unretained, nonatomic) IBOutlet UIProgressView *progress;
@property (unsafe_unretained, nonatomic) IBOutlet UILabel *percent;
@property (unsafe_unretained, nonatomic) IBOutlet UILabel *lable;
@property (strong, nonatomic)   NSString *url;
@property (strong, nonatomic) JsVersion *jsVersion;

-(void)updateJs:(NSString*)url;
@end
