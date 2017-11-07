//
//  WXAddressBookModule.m
//  Pods
//
//  Created by 郑江荣 on 2017/9/24.
//
//

#import "WXAddressBookModule.h"
#import "farwolf.h"
@implementation WXAddressBookModule
@synthesize weexInstance;

WX_EXPORT_METHOD(@selector(read:))

-(void)read:(WXModuleKeepAliveCallback)callback
{
    RHAddressBook *addressBook = [[RHAddressBook alloc] init];
      if(![self hasRights])
      {
          [addressBook requestAuthorizationWithCompletion:^(bool granted, NSError *error) {
              
              if(granted==true)
              {
                  [self doread:addressBook callback:callback];
                  return;
              }
              else
              {
                  if(![self hasRights])
                  {
                      [self gotoSetting:weexInstance.viewController];
                      return;
                  }
              }
          }];
          return;
      }
    
    
    [self doread:addressBook callback:callback];
 
}


-(void)doread:(RHAddressBook*)addressBook callback: (WXModuleKeepAliveCallback)callback
{
    // 4.获取所有的联系人
    NSArray *peopleArray = addressBook.people;
    // 5.遍历所有的联系人
    NSMutableArray *arry=[NSMutableArray new];
    for (RHPerson *person in peopleArray) {
        
        NSMutableDictionary *dic=[NSMutableDictionary new];
        // 6.获取联系人的姓名
        NSLog(@"%@ %@", person.firstName, person.lastName);
        NSString *fname=person.firstName;
           NSString *lname=person.lastName;
        
        if(fname==nil)
            fname=@"";
        if(lname==nil)
            lname=@"";
        [dic setValue:fname forKey:@"firstName"];
        
        [dic setValue:lname forKey:@"lasttName"];
        
        // 7.获取电话号码
        RHMultiValue *phones = person.phoneNumbers;
         NSMutableArray *tels=[NSMutableArray new];
        for (int i = 0; i < phones.count; i++) {
            // 8.获取电话号码和对应的Label
            NSString *phoneLabel = [phones labelAtIndex:i];
            NSString *phoneValue = [phones valueAtIndex:i];
           
            [tels addObject:phoneValue];
            NSLog(@"%@ %@", phoneLabel, phoneValue);
        }
        [dic setValue:tels forKey:@"phones"];
        [arry addObject:dic];
    }
    callback(@{@"people":arry},false);
}

-(BOOL)hasRights
{
//    RHAuthorizationStatus status = [RHAddressBook authorizationStatus];
    
    if ([RHAddressBook authorizationStatus] != RHAuthorizationStatusAuthorized)
    {
        return false;
    }
    
    return true;
    
    
}


- (void)gotoSetting:(UIViewController *)vc{
 
 [self gotoSet:  [[ @"无法访问读取通讯录，请允许"add: [AppSysInfo appName]] add:@"访问通讯录！"]];

}



-(void)gotoSet:(NSString*)s
{
    UIAlertController *alert = [UIAlertController alertControllerWithTitle:@"提示" message:s preferredStyle: UIAlertControllerStyleAlert];
    
    UIAlertAction *actionCancel = [UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleCancel
                                                         handler:^(UIAlertAction *action) {
                                                             [alert dismiss:true];
                                                         }];
    UIAlertAction *actionok = [UIAlertAction actionWithTitle:@"去设置" style:UIAlertActionStyleDefault handler:^(UIAlertAction *action) {
        
        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:UIApplicationOpenSettingsURLString]];
        
    }];
    
    
    
    [alert addAction:actionok];
    [alert addAction:actionCancel];
    
    [weexInstance.viewController presentViewController:alert animated:YES completion:^{
        
    }];
}


@end
