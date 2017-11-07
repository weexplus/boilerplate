//
//  TabSelector.h
//  oa
//
//  Created by 郑江荣 on 16/3/10.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface TabSelector : NSObject

@property(nonatomic,strong) NSString* unselect;
@property(nonatomic,strong) NSString* select;

-(id)initWithImg:(NSString*) unselect select:(NSString*)select;
@end
