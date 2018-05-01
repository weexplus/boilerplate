//
//  UpdateChecker.h
//  farwolf.business
//
//  Created by 郑江荣 on 2017/4/17.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Version.h"
@interface UpdateChecker : UIView

@property(nonatomic,strong) NSString*  url;
@property(nonatomic,strong) NSString*  appid;
-(void)doCheck:(NSString*)appid vcode:(NSString*)vcode  showprogress:(BOOL)showprogress failtoast:(BOOL)failtoast vc:(UIViewController*)vc success :(void(^)(Version*v))success theme:(NSString*)theme;

-(void)doCheckJs:(NSString*)appid jsversion:(NSString*)jsversion nativecode:(NSString*)nativecode showprogress:(BOOL)showprogress failtoast:(BOOL)failtoast vc:(UIViewController*)vc success :(void(^)(Version*v))success theme:(NSString*)theme;

-(void)updateJs:(NSString*)url  progress:(void(^)(float))progress compelete:(void(^)(NSString*))compelete;
@end
