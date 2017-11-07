//
//  WXLoadingView.m
//  Pods
//
//  Created by 郑江荣 on 2017/6/2.
//
//

#import "WXLoadingView.h"
#import "farwolf.h"
#import "WXLayer.h"
@implementation WXLoadingView
WX_EXPORT_METHOD(@selector(start))
WX_EXPORT_METHOD(@selector(stop))


+ (Class)layerClass
{
    return [WXLayer class];
}


-(instancetype)initWithRef:(NSString *)ref type:(NSString *)type styles:(NSDictionary *)styles attributes:(NSDictionary *)attributes events:(NSArray *)events weexInstance:(WXSDKInstance *)weexInstance
{
    if(self= [super initWithRef:ref type:type styles:styles attributes:attributes events:events weexInstance:weexInstance])
    {
        [self updateAttributes:attributes];

        
    }
    return self;
}


-(void)updateAttributes:(NSDictionary *)attributes
{
   
    dispatch_async(dispatch_get_main_queue(), ^{
        if (attributes[@"loadingStyle"])
        {
            NSString *style=[WXConvert NSString:attributes[@"loadingStyle"]];
            if([@"big" isEqualToString:style])
            {
                ((UIActivityIndicatorView*)self.view).activityIndicatorViewStyle=UIActivityIndicatorViewStyleWhiteLarge;
            }
            else
            {
                ((UIActivityIndicatorView*)self.view).activityIndicatorViewStyle=UIActivityIndicatorViewStyleGray;
            }
        }
        else{
               ((UIActivityIndicatorView*)self.view).activityIndicatorViewStyle=UIActivityIndicatorViewStyleGray;
        }
        
        if(attributes[@"color"])
        {
            NSString *color=[WXConvert NSString:attributes[@"color"]];
            ((UIActivityIndicatorView*)self.view).color=[color toColor];
        }
    });

//    [self setNeedsDisplay];
    
    
    
}

-(void)viewDidLoad
{
    [super viewDidLoad];
    [self.view setHidden:false];
     [self setNeedsDisplay];
    [self readyToRender];
    [self start];
       ((UIActivityIndicatorView*)self.view).color=[@"000000" toColor];
}
-(void)start
{
    [((UIActivityIndicatorView*)self.view) startAnimating];
}


-(void)stop
{
      [((UIActivityIndicatorView*)self.view) stopAnimating];
}



-(UIView*)loadView
{
    UIActivityIndicatorView *v= [[UIActivityIndicatorView alloc]init];
    //    v.activityIndicatorViewStyle=UIActivityIndicatorViewStyleGray;
 
    return v;
}
@end
