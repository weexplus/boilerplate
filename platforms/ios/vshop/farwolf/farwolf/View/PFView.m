//
//  PFView.m
//  oa
//
//  Created by 郑江荣 on 2017/3/16.
//  Copyright © 2017年 郑江荣. All rights reserved.
//

#import "PFView.h"
#import "Masonry.h"


@implementation PFView



-(void)awakeFromNib
{
    [super awakeFromNib];
    [self layoutView];
}


-(void)addView:(UIView*)v
{
    if(self.views==nil)
        self.views=[NSMutableArray new];
    [self.views addObject:v];
    [self addSubview:v];
}


-(void)layoutView
{
    
//    [self distributeSpacingHorizontallyWith:self.views];
    UIView *first= _views[0];
//    UIView *last= _views[_views.count-1];
    
    for(UIView *v in _views)
    {
        if([_views indexOfObject:v]==0)
        {
                [v mas_makeConstraints:^(MASConstraintMaker *make) {
                
                    make.left.equalTo(self.mas_left).with.offset(0);
                    make.top.equalTo(self.mas_top).with.offset(0);
                    make.bottom.equalTo(self.mas_bottom).with.offset(0);
                    if(_views.count>1)
                    {
                          UIView *next=_views[1];
                         make.right.equalTo(next.mas_left).with.offset(0);
                    }
                    else
                    {
                         make.right.equalTo(self.mas_right).with.offset(0);
                        
                    }
                
                }];
                if(_views.count==1)
                {
                    break;
                }
        }
        else if([_views indexOfObject:v]==_views.count-1)
        {
            [v mas_makeConstraints:^(MASConstraintMaker *make) {
                
                
                UIView *pre=_views[[_views indexOfObject:v]-1];
                make.left.equalTo(pre.mas_right).with.offset(0);
                make.right.equalTo(self.mas_right).with.offset(0);
                make.top.equalTo(first.mas_top).with.offset(0);
                make.width.equalTo(first.mas_width);
                make.height.equalTo(first.mas_height);
              
                
            }];
            if(_views.count==2)
            {
                
                break;
            }
        }
        else
        {
            [v mas_makeConstraints:^(MASConstraintMaker *make) {
                
                 UIView *pre=_views[[_views indexOfObject:v]-1];
                 UIView *next=_views[[_views indexOfObject:v]+1];
                 make.left.equalTo(pre.mas_right).with.offset(0);
                 make.right.equalTo(next.mas_left).with.offset(0);
                 make.top.equalTo(first.mas_top).with.offset(0);
                 make.width.equalTo(first.mas_width);
                 make.height.equalTo(first.mas_height);
                
            }];

        }
    }
    
    
    
    
}



@end
