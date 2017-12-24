//
//  PickerType.h
//  farwolf
//
//  Created by 郑江荣 on 2017/4/27.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface PickerType : NSObject
@property(nonatomic,strong) NSString *code;
@property(nonatomic,strong) NSString *name;
-(id)initWith:(NSString*)code name:(NSString*)name;
@end
