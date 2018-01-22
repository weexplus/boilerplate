//
//  WXImgButtonComponent.m
//  Pods
//
//  Created by 郑江荣 on 2017/5/15.
//
//

#import "WXImgButtonComponent.h"
#import "farwolf_weex.h"
@interface WXImgButtonComponent ()
@property (nonatomic, strong) NSString *text;
@property (nonatomic, strong) NSString *normal;
@property (nonatomic, strong) NSString *selected;
@property (nonatomic, strong) NSString *pressed;
@end

@implementation WXImgButtonComponent

    -(instancetype)initWithRef:(NSString *)ref type:(NSString *)type styles:(NSDictionary *)styles attributes:(NSDictionary *)attributes events:(NSArray *)events weexInstance:(WXSDKInstance *)weexInstance
    {
        if(self= [super initWithRef:ref type:type styles:styles attributes:attributes events:events weexInstance:weexInstance])
        {
            _text = [WXConvert NSString:attributes[@"text"]];
            _normal = [WXConvert NSString:attributes[@"normal"]];
            _selected = [WXConvert NSString:attributes[@"selected"]];
            _pressed = [WXConvert NSString:attributes[@"pressed"]];
            [self setText:_text];
            if(_normal!=nil)
            [self setNormal:_normal];
            if(_pressed!=nil)
            [self setPressed:_pressed];
            if(_selected!=nil)
            [self setSelected:_selected];
       
        }
        return self;
    }



    -(void)updateAttributes:(NSDictionary *)attributes
    {
        if (attributes[@"text"])
        {
          _text = [WXConvert NSString:attributes[@"text"]];
          [self setText:_text];
        }
        if (attributes[@"_normal"])
        {
            _normal = [WXConvert NSString:attributes[@"normal"]];
            [self setNormal:_normal];
        }
        if (attributes[@"selected"])
        {
            _selected = [WXConvert NSString:attributes[@"selected"]];
            [self setSelected:_selected];
        }
        if (attributes[@"pressed"])
        {
            _pressed = [WXConvert NSString:attributes[@"pressed"]];
            [self setPressed:_pressed];
        }
        
    }


    -(void)setText:(NSString *)text
    {
        dispatch_async(dispatch_get_main_queue(), ^{
            [((UIButton*)self.view) setTitle:text forState:UIControlStateNormal];
        });
       
    }

   -(void)setNormal:(NSString *)normal
   {
      _normal=normal;
      [self setImage:normal state:UIControlStateNormal];
   }

  -(void)setSelected:(NSString *)selected
   {
     _selected=selected;
     [self setImage:selected state:UIControlStateSelected];
   }


  -(void)setPressed:(NSString *)pressed
  {
      _pressed=pressed;
      [self setImage:pressed state:UIControlStateHighlighted];
  }


-(void)setImage:(NSString *)src state:(UIControlState)state
  {
      if(src==nil)
      {
          return;
      }
       src = [NSURL URLWithString:src relativeToURL:self.weexInstance.scriptURL].absoluteString;
      if([src startWith:@"http"])
      {
          [[SDWebImageManager sharedManager] downloadImageWithURL:[NSURL URLWithString:src] options:0 progress:^(NSInteger receivedSize, NSInteger expectedSize) {
              
          } completed:^(UIImage *image, NSError *error, SDImageCacheType cacheType, BOOL finished, NSURL *imageURL) {
              
              dispatch_async(dispatch_get_main_queue(), ^{
                  [((UIButton*)self.view) setImage:image forState:state];
              });
              
          }];
      }
      else{
          dispatch_async(dispatch_get_main_queue(), ^{
              [((UIButton*)self.view) setImage:[UIImage imageNamed:src] forState:state];
          });

      }

    
  }


-(UIView*)loadView
{
    UIButton *btn= [[UIButton alloc]init];
    [btn setTintColor:[UIColor whiteColor]];
    [btn addTarget:self action:@selector(down) forControlEvents:UIControlEventTouchDown];
    [btn addTarget:self action:@selector(up) forControlEvents:UIControlEventTouchUpInside];
    [btn addTarget:self action:@selector(up) forControlEvents:UIControlEventTouchUpOutside];
    return btn;
}
-(void)down
{
    [((UIButton*)self.view) setHighlighted:true];
}
-(void)up
{
     [((UIButton*)self.view) setHighlighted:false];
}

@end
