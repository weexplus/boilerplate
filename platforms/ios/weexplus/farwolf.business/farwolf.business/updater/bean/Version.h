//
//  Version.h
//  farwolf.business
//
//  Created by 郑江荣 on 2017/4/17.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Version : NSObject


@property (nonatomic) int level;

@property (nonatomic,strong) NSString* name;

@property (nonatomic,strong) NSString* version_name;

@property (nonatomic) int version_code;

@property (nonatomic) int system_type;

@property (nonatomic,strong) NSString* app_id;


@property (nonatomic,strong) NSString* desc;
@property (nonatomic,strong) NSString* size;

@property (nonatomic,strong) NSString* download_url;

@property (nonatomic,strong) NSString* source;
@end
