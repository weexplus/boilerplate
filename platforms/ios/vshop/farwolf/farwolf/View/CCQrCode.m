//
//  CCQrCode.m
//  二维码
//
//  Created by 李飞恒 on 16/4/23.
//  Copyright © 2016年 LIFEIHENG. All rights reserved.
//

#import "CCQrCode.h"
#import <AudioToolbox/AudioToolbox.h>

#define kCCPx(n) (n*0.5)

@interface CCQrCode ()<AVCaptureMetadataOutputObjectsDelegate>
{
    BOOL _isForward;
    CGRect _boxFrame;
}
@property (nonatomic, copy) CCQrCodeCallbackFunction funcation;



@end

@implementation CCQrCode

- (instancetype)initWithFrame:(CGRect)frame {
    self = [super initWithFrame:frame];
    if (self) {
        _isForward = NO;
        
        self.colorLayer = [[UIView alloc] initWithFrame:self.bounds];
        
        
        self.scanColor = [UIColor orangeColor];
        self.boxColor = [UIColor clearColor];
        
    }
    return self;
}

- (void)boxRect {
    _boxFrame = CGRectMake(_videoPreviewLayer.bounds.size.width*0.15f, _videoPreviewLayer.bounds.size.height*0.2f, self.bounds.size.width-self.bounds.size.width*0.3, self.bounds.size.width-self.bounds.size.width*0.3);
}

- (BOOL)startReading:(CCQrCodeCallbackFunction)callback {
    
    
    NSError *error;
    AVCaptureDevice *device = [AVCaptureDevice defaultDeviceWithMediaType:AVMediaTypeVideo];
    AVCaptureDeviceInput *input = [AVCaptureDeviceInput deviceInputWithDevice:device error:&error];
    if (!input) {
        NSLog(@"%@",error.localizedDescription);
        return NO;
    }
    AVCaptureMetadataOutput *output = [[AVCaptureMetadataOutput alloc] init];
    _captureSession = [[AVCaptureSession alloc] init];
    [_captureSession addInput:input];
    [_captureSession addOutput:output];
    dispatch_queue_t dispatchQueue;
    dispatchQueue = dispatch_queue_create("AVQueue", NULL);
    [output setMetadataObjectsDelegate:self queue:dispatchQueue];
    [output setMetadataObjectTypes:@[AVMetadataObjectTypeQRCode]];
    
    _videoPreviewLayer = [[AVCaptureVideoPreviewLayer alloc] initWithSession:_captureSession];
    [_videoPreviewLayer setVideoGravity:AVLayerVideoGravityResizeAspectFill];
    [_videoPreviewLayer setFrame:self.bounds];
    [self.layer addSublayer:_videoPreviewLayer];
    //添加图层到PreviewLayer上
    [_videoPreviewLayer addSublayer:self.colorLayer.layer];
    
    [self boxRect];
    
    
    _boxView = [[UIView alloc] initWithFrame:_boxFrame];
    _boxView.layer.borderColor = self.boxColor.CGColor;
    //    _boxView.layer.borderWidth = 0.5f;
    _boxView.clipsToBounds = YES;
    [self.colorLayer addSubview:_boxView];
    
    
    //扫描线
    _scanLayer = [[UIView alloc] initWithFrame:CGRectMake(0, 0, _boxView.bounds.size.width, 1)];
    _scanLayer.backgroundColor = self.scanColor;
    [_boxView addSubview:_scanLayer];
    //扫描线尾巴
    //    CAGradientLayer *layer = [CAGradientLayer layer];
    //    layer.frame = _scanLayer.bounds;
    //    layer.startPoint = CGPointMake(0, 0);
    //    layer.endPoint   = CGPointMake(0, 0);
    //    layer.locations  = @[@(0), @(1), @(0)];
    //    layer.colors = @[(__bridge id)[UIColor colorWithWhite:0 alpha:0.0].CGColor,
    //                     (__bridge id)[UIColor colorWithWhite:0 alpha:1.0].CGColor,
    //                     (__bridge id)[UIColor colorWithWhite:0 alpha:0.0].CGColor];
    //
    //    _scanLayer.layer.mask = layer;
    UIImageView *canvas = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"canvas"]];
    canvas.frame = _scanLayer.bounds;
    [_scanLayer addSubview:canvas];
    _scanLayer.layer.mask = canvas.layer;
    
    //中间镂空的矩形框
    CGRect myRect = _boxFrame;
    
    
    //背景
    UIBezierPath *path = [UIBezierPath bezierPathWithRoundedRect:[UIScreen mainScreen].bounds cornerRadius:0];
    //镂空
    UIBezierPath *circlePath = [UIBezierPath bezierPathWithRoundedRect:myRect cornerRadius:0];
    [path appendPath:circlePath];
    [path setUsesEvenOddFillRule:YES];
    
    CAShapeLayer *fillLayer = [CAShapeLayer layer];
    fillLayer.path = path.CGPath;
    fillLayer.fillRule = kCAFillRuleEvenOdd;
    fillLayer.fillColor = [UIColor blackColor].CGColor;
    fillLayer.opacity = 0.7;
    [self.colorLayer.layer addSublayer:fillLayer];
    
    self.funcation = callback;
    
    //边框
    UIImageView *qrcodecase = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"qrcodecase"]];
    qrcodecase.frame = CGRectMake(_boxFrame.origin.x-2, _boxFrame.origin.y-2, _boxFrame.size.width+4, _boxFrame.size.height+4);
    [self addSubview:qrcodecase];
    
    self.describeLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, CGRectGetMaxY(qrcodecase.frame)+10, self.frame.size.width, 21)];
    self.describeLabel.textAlignment = NSTextAlignmentCenter;
    self.describeLabel.textColor = [UIColor colorWithWhite:1.000 alpha:0.500];
    self.describeLabel.text = @"将取景框对准二维码自动扫描";
    self.describeLabel.font = [UIFont systemFontOfSize:13];
    [self.colorLayer addSubview:self.describeLabel];
    
    
    NSTimer *timer = [NSTimer scheduledTimerWithTimeInterval:0.2f target:self selector:@selector(moveScanLayer) userInfo:nil repeats:YES];
    [timer fire];
    [_captureSession startRunning];
    return YES;
}



- (void)stopReading {
    [_captureSession stopRunning];
    _captureSession = nil;
    [_scanLayer removeFromSuperview];
    [_videoPreviewLayer removeFromSuperlayer];
}




- (void)moveScanLayer {
    
    CGRect frame = _scanLayer.frame;
    
    if (_scanLayer.frame.origin.y < (self.bounds.size.width-self.bounds.size.width*0.4+kCCPx(self.boxView.frame.size.height))) {
        //首尾式动画
        [UIView beginAnimations:nil context:nil];
        //执行动画
        //设置动画执行时间
        [UIView setAnimationDuration:2.f];
        //设置代理
        [UIView setAnimationDelegate:self];
        frame.origin.y += 25;
        _scanLayer.frame = frame;
        [UIView commitAnimations];
        
    } else {
        frame.origin.y = 0;
        _scanLayer.frame = frame;
        [self moveScanLayer];
    }
    
}
static SystemSoundID shake_sound_male_id = 0;

- (void)captureOutput:(AVCaptureOutput *)captureOutput didOutputMetadataObjects:(NSArray *)metadataObjects fromConnection:(AVCaptureConnection *)connection {
    if (metadataObjects != nil && [metadataObjects count] > 0) {
        if (_isForward == NO) {
            NSString *path = [[NSBundle mainBundle] pathForResource:@"5383" ofType:@"wav"];
            if (path) {
                //注册声音到系统
                AudioServicesCreateSystemSoundID((__bridge CFURLRef)[NSURL fileURLWithPath:path],&shake_sound_male_id);
                AudioServicesPlaySystemSound(shake_sound_male_id); //播放注册的声音，（此句代码，可以在本类中的任意位置调用，不限于本方法中）
//                AudioServicesPlaySystemSound(kSystemSoundID_Vibrate); //让手机震动
            }
            _isForward = YES;
            
            
            AVMetadataMachineReadableCodeObject *metadataObj = [metadataObjects objectAtIndex:0];
            
            if ([[metadataObj type] isEqualToString:AVMetadataObjectTypeQRCode]) {
                NSLog(@"扫描:%@",metadataObj.stringValue);
                self.funcation(captureOutput, metadataObjects, connection, metadataObj, metadataObj.stringValue);
                
                //限制作2秒后可扫描
                __weak typeof(self) weakSelf = self;
                dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(2 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                    [weakSelf setIsForward];
                });
            }
            
        }
    }
}

- (void)setIsForward {
    _isForward = NO;
}

@end



























