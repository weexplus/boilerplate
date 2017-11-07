//
//  CCQrCode.h
//  二维码
//
//  Created by 李飞恒 on 16/4/23.
//  Copyright © 2016年 LIFEIHENG. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>
#import <AVFoundation/AVFoundation.h>



typedef void(^CCQrCodeCallbackFunction)(AVCaptureOutput *captureOutput,NSArray *metadataObjects,AVCaptureConnection *connection,AVMetadataMachineReadableCodeObject *metadataObj,NSString *stringValue);

@interface CCQrCode : UIView


@property (nonatomic, strong) UIView *boxView; //scanningView
@property (nonatomic, strong) UIColor *boxColor; //define [UIColor clearColor];
@property (nonatomic) BOOL isReading;
@property (nonatomic, strong) UIView *scanLayer; //扫描线
@property (nonatomic, strong) UIColor *scanColor; //define [UIColor orangeColor];

@property (nonatomic, strong) UIView *colorLayer;

@property (nonatomic, strong) UILabel *describeLabel;

- (BOOL)startReading:(CCQrCodeCallbackFunction)callback;
- (void)stopReading;

@property (nonatomic, strong) AVCaptureSession *captureSession;
@property (nonatomic, strong) AVCaptureVideoPreviewLayer *videoPreviewLayer;

@end











































