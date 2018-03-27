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

@property (nonatomic,strong) NSString* versionName;

@property (nonatomic) int versionCode;

@property (nonatomic) int systemType;

@property (nonatomic,strong) NSString* appId;


@property (nonatomic,strong) NSString* desc;
@property (nonatomic,strong) NSString* size;

@property (nonatomic,strong) NSString* downloadUrl;

@property (nonatomic,strong) NSString* source;
@end
