//
//  EnteryControl.h
//  AFNetworking
//
//  Created by 郑江荣 on 2017/11/30.
//

#import <UIKit/UIKit.h>

@interface EntryControl : UIViewController
@property(nonatomic,strong)NSString*url;
@property(nonatomic,strong)NSString*img;
-(id)initWithImage:(NSString*)url img:(NSString*)img;
@end
