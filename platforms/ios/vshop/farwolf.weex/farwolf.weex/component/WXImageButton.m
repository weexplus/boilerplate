//
//  WXImageButton.m
//  Pods
//
//  Created by 郑江荣 on 2017/5/15.
//
//

#import "WXImageButton.h"
#import "farwolf_weex.h"
#import "WXLayer.h"
@implementation WXImageButton

+ (Class)layerClass
{
    return [WXLayer class];
}

-(void)load
{
    self.userInteractionEnabled=true;
      __weak typeof(self) weakSelf = self;
    if(self.normal!=nil)
  
    [self setImageSource:self.normal compelete:^(UIImage *img) {
        weakSelf.normal_img=img;
    }];
    [@"" replace:@"" withString:@""];
    [[UIButton new] addTarget:self action:@selector(load) forControlEvents:UIControlEventTouchDown];
   
}

-(void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event
{
    if(self.pressed_img!=nil)
    {
        self.image= self.pressed_img;
        [self.delegate complete];
        return;
    }
    NSString *s=self.pressed;
    if(s==nil)
    {
        s=self.normal;
    }
    __weak typeof(self) weakSelf = self;
    [self setImageSource:s compelete:^(UIImage *img) {
         weakSelf.pressed_img=img;
    }];
    
}

-(void)setImageSource:(NSString*)url compelete:(void(^)(UIImage *img))compelete
{
    if([url startWith:@"http"])
    {
        [[SDWebImageManager sharedManager] downloadImageWithURL:[NSURL URLWithString:url] options:0 progress:^(NSInteger receivedSize, NSInteger expectedSize) {
            
            
        } completed:^(UIImage *image, NSError *error, SDImageCacheType cacheType, BOOL finished, NSURL *imageURL) {
            
            self.image=image;
            [self.delegate complete];
            compelete(image);
            
        }];
    }
    else
    {
        self.image=[UIImage imageNamed:url];
    }
}

-(void)touchesEnded:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event
{
    if(self.normal_img)
    {
        self.image= self.normal_img;
           [self.delegate complete];
        return;
    }
    __weak typeof(self) weakSelf = self;
    [self setImageSource:self.normal compelete:^(UIImage *img) {
        weakSelf.normal_img=img;
    }];
   
}



@end
