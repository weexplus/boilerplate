//
//  WXLocationModule.h
//  AFNetworking
//
//  Created by 郑江荣 on 2018/5/6.
//

#import <Foundation/Foundation.h>
#import <WeexSDK/WeexSDK.h>
#import <MapKit/MapKit.h>
@interface WXLocationModule : NSObject<WXModuleProtocol,CLLocationManagerDelegate>
@property (strong,nonatomic) CLLocationManager* locationManager;
@property(nonatomic,retain)WXModuleKeepAliveCallback callback;
@property(nonatomic)BOOL onece;
@end
