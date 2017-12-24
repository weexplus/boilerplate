//
//  WXFDivComponent.m
//  Pods
//
//  Created by 郑江荣 on 2017/6/13.
//
//

#import "WXFDivComponent.h"
#import "farwolf.h"

@implementation WXFDivComponent

WX_EXPORT_METHOD(@selector(enable:))
- (instancetype)initWithRef:(NSString *)ref type:(NSString *)type styles:(NSDictionary *)styles attributes:(NSDictionary *)attributes events:(NSArray *)events weexInstance:(WXSDKInstance *)weexInstance
{
    self.tempstyles=styles;
        _disabled = [attributes[@"disabled"] boolValue];
//    self.view.userInteractionEnabled=_disabled;
    NSMutableDictionary *temp=[self getFinalStyles:styles];
    self = [super initWithRef:ref type:type styles:temp attributes:attributes events:events weexInstance:weexInstance];
    return self;
}

-(void)viewDidLoad
{
    [super viewDidLoad];
    self.view.userInteractionEnabled=_disabled;
}


//-(void)setDisabled:(BOOL)disabled
//{
//    if(disabled==self.disabled)
//        return;
//    self.disabled=disabled;
//    [self updateStyles:self.styles];
//    self.view.userInteractionEnabled=_disabled;
//}

-(NSDictionary*)getFinalStyles:(NSDictionary*)styles
{
    NSArray *keys=  self.tempstyles.allKeys;
    NSMutableDictionary *nd=self.tempstyles;
    NSMutableDictionary *temp=[NSMutableDictionary new];
    NSMutableArray *dislist=[NSMutableArray new];
    for(NSString *key in keys)
    {
        if([key contains:@"disabled"])
        {
             NSString *at=  [key split:@":"][0];
            [dislist addObject:at];
        }
    
    }
    for(NSString *key in keys)
    {
        BOOL has=false;
        for(NSString *at in dislist)
        {
            if([key contains:at])
            {
                has=true;
                break;
            }
        }
        if(has)
        {
            if(_disabled)
            {
               if([key contains:@"disabled"])
               {
                   
                   [temp setObject:nd[key] forKey:[key replace:@":disabled" withString:@""]];
               }
                
            }
            else
            {
                if(![key contains:@"disabled"])
                {
                    [temp setObject:nd[key] forKey:key];
                }
            }
        }
        else
        {
            if(![temp.allKeys containsObject:key])
            [temp setObject:nd[key] forKey:key];
        }
        
    }
    
    
    return temp;

}


-(void)enable:(BOOL)enable
{
    self.disabled=!enable;
    [self updateStyles:self.tempstyles];
    self.view.userInteractionEnabled=_disabled;
    
}

-(void)updateStyles:(NSDictionary *)styles
{
    
    [super updateStyles:[self getFinalStyles:styles]];
    
}


-(void)updateAttributes:(NSDictionary *)attributes
{
    
     self.disabled=[attributes[@"disabled"] boolValue];
        self.view.userInteractionEnabled=_disabled;
     [self updateStyles:self.tempstyles];
}

@end
