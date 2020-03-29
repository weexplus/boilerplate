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
-(NSData *)compress:(NSUInteger)maxSize {
    // Compress by quality
    CGFloat compression = 1;
    NSData *data = UIImageJPEGRepresentation(self, compression);
    if (data.length < maxSize) return data;
    
    CGFloat max = 1;
    CGFloat min = 0;
    for (int i = 0; i < 6; ++i) {
        compression = (max + min) / 2;
        data = UIImageJPEGRepresentation(self, compression);
        if (data.length < maxSize * 0.9) {
            min = compression;
        } else if (data.length > maxSize) {
            max = compression;
        } else {
            break;
        }
    }
   
    if (data.length < maxSize) return data;
    
     UIImage *resultImage = [UIImage imageWithData:data];
    // Compress by size
    NSUInteger lastDataLength = 0;
    while (data.length > maxSize && data.length != lastDataLength) {
        lastDataLength = data.length;
        CGFloat ratio = (CGFloat)maxSize / data.length;
        CGSize size = CGSizeMake((NSUInteger)(resultImage.size.width * sqrtf(ratio)),
                                 (NSUInteger)(resultImage.size.height * sqrtf(ratio))); // Use NSUInteger to prevent white blank
        UIGraphicsBeginImageContext(size);
        [resultImage drawInRect:CGRectMake(0, 0, size.width, size.height)];
        resultImage = UIGraphicsGetImageFromCurrentImageContext();
        UIGraphicsEndImageContext();
        data = UIImageJPEGRepresentation(resultImage, compression);
    }
    
    return data;
}

-(NSString*)save:(NSString*)path{
    NSData *imageData = UIImageJPEGRepresentation(self, 1.0);
    NSString *documentPath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
    NSArray *ary= [path split:@"/"];
    if(ary.count>1){
        int c=0;
        NSString *temp=documentPath;
        while(c<ary.count-1){
            temp= [[temp add:@"/"] add:ary[c]];
            [temp mkdir];
            c++;
        }
    }
    NSString *totalPath = [[documentPath add:@"/"] add:path];
    [imageData writeToFile:totalPath atomically:YES];
    return totalPath;
 
}
 
@end
