//
//  WXPhotoModule.h
//  Pods
//
//  Created by 郑江荣 on 2017/5/19.
//
//

#import <Foundation/Foundation.h>
#import "farwolf_weex.h"
#import "UploadImage.h"
@interface WXPhotoModule : NSObject<WXModuleProtocol,ImageSelectDelegate>
@property(nonatomic,strong)WXModuleKeepAliveCallback callback;
@property(nonatomic,strong)UploadImage *uploadImage;


@end
