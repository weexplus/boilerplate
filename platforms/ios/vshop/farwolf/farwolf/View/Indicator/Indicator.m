//
//  Indicator.m
//  oa
//
//  Created by 郑江荣 on 2017/3/16.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "Indicator.h"
#import "farwolf.h"
@implementation Indicator





-(void)layoutView
{
    [super layoutView];
    [self addClicks];
}
-(void)addClicks
{
    
    for(IndicatorItem *it in self.views)
    {
        int index= [self.views indexOfObject:it];
        it.tag=index;
        [it addClick:@selector(ItemClick:) host:self];
      
    }
}

-(void)ItemClick:(UITapGestureRecognizer *)recognizer
{
    int index=recognizer.view.tag;
    [self selectOne:index];
    [self.delegate select:index];
}


-(void)selectOne:(int)index
{
    for(IndicatorItem *it in self.views)
    {
        if([self.views indexOfObject:it]==index)
        {
            [it select];
            [self.delegate select:index];
        }
        else
        {
            [it unselect];
        }
    }
}



@end
