//
//  WXLooperText.m
//  Pods
//
//  Created by 郑江荣 on 2017/8/16.
//
//

#import "WXLooperText.h"
#import "Masonry.h"
#import "farwolf.h"
#import <UIKit/UIKit.h>
@implementation WXLooperText


WX_EXPORT_METHOD(@selector(getIndex:))

- (instancetype)initWithRef:(NSString *)ref type:(NSString *)type styles:(NSDictionary *)styles attributes:(NSDictionary *)attributes events:(NSArray *)events weexInstance:(WXSDKInstance *)weexInstance
{
    if (self = [super initWithRef:ref type:type styles:styles attributes:attributes events:events weexInstance:weexInstance]) {
    
        if(attributes[@"color"])
        {
            self.color = [WXConvert NSString:attributes[@"color"]]?:@"#000000";
        }
        if(attributes[@"interval"])
        {
            _interval =[WXConvert CGFloat:attributes[@"interval"]]?:3000;
        }
        if(attributes[@"textAlign"])
        {
            _textAlign = [WXConvert NSString:attributes[@"textAlign"]]?:@"left";
            
        }
        if(attributes[@"fontSize"])
        {
            _fontSize = [WXConvert CGFloat:attributes[@"fontSize"]]?:30;
            
        }
        if(attributes[@"data"])
        {
            _data = attributes[@"data"];
        }
        
    }
    
//     UIFont *font = [WXUtility fontWithSize:_fontSize textWeight:_fontWeight textStyle:_fontStyle fontFamily:_fontFamily scaleFactor:self.weexInstance.pixelScaleFactor useCoreText:[self useCoreText]];
    return self;
}

-(void)updateAttributes:(NSDictionary *)attributes
{
    [super updateAttributes:attributes];
    if(attributes[@"color"])
    {
        self.color = [WXConvert NSString:attributes[@"color"]]?:@"#000000";
        if(self.textview!=nil)
        {
            [self.textview.textLabel setTextColor:[self.color toColor]];
        }
    }
    if(attributes[@"interval"])
    {
        _interval =[WXConvert CGFloat:attributes[@"interval"]]?:3000;
        self.textview.interval=_interval;
        self.textview.needDealy=_interval;
    }
    if(attributes[@"textAlign"])
    {
        _textAlign = [WXConvert NSString:attributes[@"textAlign"]]?:@"left";
        [self resetAlign];
    }
    if(attributes[@"data"])
    {
        _data = attributes[@"data"];
        [self.textview animationWithTexts:_data];
    }
    if(attributes[@"fontSize"])
    {
       UIFont *font=  [WXUtility fontWithSize:_fontSize textWeight:0 textStyle:WXTextStyleNormal fontFamily:@"" scaleFactor:self.weexInstance.pixelScaleFactor];
        [self.textview.textLabel setFont:font];
    }
    

    
}

-(UIView*)loadView
{

    UIView *v=[[UIView alloc]init];
    
    return v;
}


-(void)viewDidLoad
{
    [super viewDidLoad];
    [self resizeFrame];
    
    UIFont *font=  [WXUtility fontWithSize:_fontSize* self.weexInstance.pixelScaleFactor textWeight:0 textStyle:WXTextStyleNormal fontFamily:@"" scaleFactor:self.weexInstance.pixelScaleFactor];

    [self.textview.textLabel  setFont:font];
    self.textview.delegate=self;
//    UIFont *f=[UIFont systemFontOfSize:15];
//    [self.textview.textLabel setFont:f];
    
}

- (void)gyChangeTextView:(GYChangeTextView *)textView didTapedAtIndex:(NSInteger)index {
    NSLog(@"%ld",index);
}

-(void)change:(NSInteger)index
{
    
    [self fireEvent:@"change" params: @{@"index":@(index)}];
}

- (void)resizeFrame
{
    
    CGRect rect = self.calculatedFrame;
    [self.view setFrame:rect];
    GYChangeTextView *tView=[[GYChangeTextView alloc]initWithFrame:self.calculatedFrame];
    tView.needDealy=self.interval;
    tView.interval=self.interval;
    
    self.textview=tView;
    if(_data!=nil)
    {
        [self.textview animationWithTexts:_data];
    }
    [self resetAlign];
    [self.textview.textLabel setTextColor:[self.color toColor]];
    [self.view addSubviewFull:self.textview];
 
}

-(void)getIndex:(WXModuleCallback)callback
{
    callback(@{@"index" : @([self.textview realCurrentIndex])});
}

-(void)resetAlign
{
    if(self.textview==nil)
        return;
    if([@"center" isEqualToString:_textAlign])
    {
        self.textview.textLabel.textAlignment=NSTextAlignmentCenter;
    }
    else if([@"right" isEqualToString:_textAlign])
    {
         self.textview.textLabel.textAlignment=NSTextAlignmentRight;
    }
    else
    {
         self.textview.textLabel.textAlignment=NSTextAlignmentLeft;
    }
    
}


@end
