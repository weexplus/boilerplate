//
//  UIImage+Farwolf.h
//  AFNetworking
//
//  Created by 郑江荣 on 2019/2/15.
//

#import <UIKit/UIKit.h>

@interface UIImage (Farwolf)
- (NSString*)saveToDisk:(NSString*)path;
- (UIImage *)toColor:(NSString *)color;
-(NSData *)compress:(NSUInteger)maxSize;
-(NSString*)save:(NSString*)path;
@end

