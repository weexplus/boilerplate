//
//  JsVersion.h
//  AFNetworking
//
//  Created by 郑江荣 on 2018/4/30.
//

#import <Foundation/Foundation.h>

@interface JsVersion : NSObject

@property (nonatomic,strong) NSString* url;
@property (nonatomic) int js_version;
@property (nonatomic) int native_version;
@property (nonatomic) int mode;
@property (nonatomic,strong) NSString* size;
@property (nonatomic,strong) NSString* app_id;

@end
