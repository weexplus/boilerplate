//
//  UpdateDialogControl.h
//  AFNetworking
//
//  Created by 郑江荣 on 2018/3/27.
//

#import <UIKit/UIKit.h>
#import "Version.h"

@interface UpdateDialogControl : UIViewController
@property (unsafe_unretained, nonatomic) IBOutlet UILabel *versionname;
@property (unsafe_unretained, nonatomic) IBOutlet UILabel *sizename;
@property (unsafe_unretained, nonatomic) IBOutlet UITextView *desc;
@property (unsafe_unretained, nonatomic) IBOutlet UIView *updateonly;
@property (unsafe_unretained, nonatomic) IBOutlet UIView *root;
@property (unsafe_unretained, nonatomic) IBOutlet UIView *ignoreroot;
@property (unsafe_unretained, nonatomic) IBOutlet UISwitch *check;

@property (strong, nonatomic)   Version *version;

@property(nonatomic,strong) NSString* themeColor;
-(void)initBean:(Version*)v;
@end
