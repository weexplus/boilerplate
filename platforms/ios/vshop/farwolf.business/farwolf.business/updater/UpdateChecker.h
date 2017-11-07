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
-(void)doCheck:(void(^)(Version*v))success;

@end
