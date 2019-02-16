//
//  UIImage+Farwolf.m
//  AFNetworking
//
//  Created by 郑江荣 on 2019/2/15.
//

#import "UIImage+Farwolf.h"

@implementation UIImage (Farwolf)

- (NSString*)saveToDisk:(NSString*)path {
    NSArray *paths =NSSearchPathForDirectoriesInDomains(NSDocumentDirectory,NSUserDomainMask,YES);
    NSString *filePath = [[paths objectAtIndex:0]stringByAppendingPathComponent:
                          [NSString stringWithFormat:path]];  // 保存文件的名称
    BOOL result =[UIImagePNGRepresentation(self) writeToFile:filePath   atomically:YES]; // 保存成功会返回YES
    return filePath;
}
    
 
@end
