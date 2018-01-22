//
//  RenderControl.h
//  AFNetworking
//
//  Created by 郑江荣 on 2017/12/4.
//

#import <UIKit/UIKit.h>

@interface RenderControl : UIViewController
@property(nonatomic,strong)NSString*url;
@property(nonatomic,strong)NSString*img;
-(id)initWithImage:(NSString*)url img:(NSString*)img;
@end
