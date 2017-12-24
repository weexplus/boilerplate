//
//  WXFarwolfModule.m
//  Pods
//
//  Created by 郑江荣 on 2017/6/15.
//
//

#import "WXFarwolfModule.h"
#import "Weex.h"


@implementation WXFarwolfModule
@synthesize weexInstance;



WX_EXPORT_METHOD(@selector(getBaseDir))
WX_EXPORT_METHOD(@selector(setBaseDir:))
-(id)getBaseDir
{
    return [Weex getBaseDir];
}

-(void)setBaseDir:(NSString*)basedir
{
    [Weex setBaseDir:basedir];
}

@end
