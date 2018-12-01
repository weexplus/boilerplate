#import <Foundation/Foundation.h>

@interface KeyChainStore : NSObject
+ (void)save:(NSString*)service data:(id)data; // 保存
+ (id)load:(NSString*)service;                 // 获取
+ (void)deleteKeyData:(NSString*)service;      // 删除
+(NSString *)getUUID:(NSString*)bundleid;
@end
