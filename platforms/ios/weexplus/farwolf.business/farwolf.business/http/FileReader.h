//
//  FileReader.h
//  oa
//
//  Created by 郑江荣 on 16/5/15.
//  Copyright © 2016年 郑江荣. All rights reserved.
//

#import "farwolf.h"

@interface FileReader : JsonReader
@property (strong,nonatomic) NSString * weg;

-(void)postFile;
-(id)initWith:(NSString*)weg;
-(void)excuteFile:(UIViewController*)vc
          success:(void(^)(Json*j))success;
-(void)excuteFileFull:(void(^)())start
              success:(void(^)(Json*j))success
                 fail:(void(^)(Json*j,NSInteger code,NSString* msg))fail
            exception:(void(^)())exception
            compelete:(void(^)())compelete;
-(void)excuteFile:(NSString*)url   start:(void(^)())start
          success:(void(^)(NSString*s,NSString*sessionid))success
        exception:(void(^)())exception
        compelete:(void(^)())compelete;
@end
