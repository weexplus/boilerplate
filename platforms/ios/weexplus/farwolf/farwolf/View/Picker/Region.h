//
//  Region.h
//  farwolf
//
//  Created by 郑江荣 on 2017/4/27.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Region : NSObject
@property(nonatomic,strong) NSString* _id;
@property(nonatomic,strong) NSString* code;
@property(nonatomic,strong) NSString* parentCode;
@property(nonatomic,strong) NSString* regionName;
@property(nonatomic) int regionType;
@property(nonatomic,strong) NSString* abbname;
@property(nonatomic,strong) NSString* completeSpellings;
@property(nonatomic,strong) NSString* abbreviation;
@end
