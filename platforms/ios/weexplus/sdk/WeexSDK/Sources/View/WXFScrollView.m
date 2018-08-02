//
//  WXScrollView.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/8/1.
//

#import "WXFScrollView.h"

@implementation WXFScrollView

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

-(BOOL)gestureRecognizerShouldBegin:(UIGestureRecognizer *)gestureRecognizer
{
    if([gestureRecognizer isKindOfClass:[UIPanGestureRecognizer class]])
    {
        UIPanGestureRecognizer *pan=(UIPanGestureRecognizer*)gestureRecognizer;
        if([pan translationInView:self].x>0.0f&&self.contentOffset.x==0.0f)
        {
            return NO;
        }
    }
    return [super gestureRecognizerShouldBegin:gestureRecognizer];
}


@end
