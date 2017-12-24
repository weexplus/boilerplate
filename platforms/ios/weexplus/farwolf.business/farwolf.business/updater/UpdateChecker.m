//
//  UpdateChecker.m
//  farwolf.business
//
//  Created by 郑江荣 on 2017/4/17.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "UpdateChecker.h"
#import "farwolf.h"
#import "UpdateJsonReader.h"
#import "UpdateJson.h"
@implementation UpdateChecker

 
-(void)doCheck:(void(^)(Version*v))success
{
    UpdateJsonReader *j=[[UpdateJsonReader alloc]init];
    j.url=self.url;
    [j excute:[self getCurrentVc]  success:^(Json *j) {
       Version *v=  [Version yy_modelWithJSON:[j getDict:@"version"]];
       success(v);
    } usePost:true];
}




@end
