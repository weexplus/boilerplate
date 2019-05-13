//
//  UIImage+Farwolf.m
//  AFNetworking
//
//  Created by 郑江荣 on 2019/2/15.
//

#import "UIImage+Farwolf.h"
#import "NSString+Farwolf.h"
@implementation UIImage (Farwolf)

- (NSString*)saveToDisk:(NSString*)path {
    NSArray *paths =NSSearchPathForDirectoriesInDomains(NSDocumentDirectory,NSUserDomainMask,YES);
    NSString *filePath = [[paths objectAtIndex:0]stringByAppendingPathComponent:
                          [NSString stringWithFormat:path]];  // 保存文件的名称
    BOOL result =[UIImagePNGRepresentation(self) writeToFile:filePath   atomically:YES]; // 保存成功会返回YES
    return filePath;
}
- (UIImage *)toColor:(NSString *)color
{
    UIColor *c=[color toColor];
    UIGraphicsBeginImageContextWithOptions(self.size, NO, self.scale);
    CGContextRef context = UIGraphicsGetCurrentContext();
    CGContextTranslateCTM(context, 0, self.size.height);
    CGContextScaleCTM(context, 1.0, -1.0);
    CGContextSetBlendMode(context, kCGBlendModeNormal);
    CGRect rect = CGRectMake(0, 0, self.size.width, self.size.height);
    CGContextClipToMask(context, rect, self.CGImage);
    [c setFill];
    CGContextFillRect(context, rect);
    UIImage*newImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    return newImage;
}
 
@end
