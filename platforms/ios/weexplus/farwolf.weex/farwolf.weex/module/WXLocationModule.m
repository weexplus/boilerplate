//
//  WXLocationModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2018/5/6.
//

#import "WXLocationModule.h"
#import "farwolf.h"

@implementation WXLocationModule

WX_EXPORT_METHOD(@selector(start:callback:))
WX_EXPORT_METHOD(@selector(stop))
//开始定位

-(void)start:(NSMutableDictionary*)param callback:(WXModuleKeepAliveCallback)callback{
    
    self.callback=callback;
    self.onece=true;
    if(param[@"once"]==nil)
    {
        self.onece=param[@"once"];
    }
    self.locationManager=nil;
    self.locationManager = [[CLLocationManager alloc] init];
    self.locationManager.delegate = self;
    self.locationManager.desiredAccuracy = kCLLocationAccuracyBest;
    self.locationManager.distanceFilter = 100.0f;
    
    if ([[[UIDevice currentDevice]systemVersion]doubleValue] >8.0)
    {
        [self.locationManager requestWhenInUseAuthorization];
    }
    if ([[UIDevice currentDevice].systemVersion floatValue] >= 8.0) {
        
//        _locationManager.allowsBackgroundLocationUpdates =YES;
    }
    
    [self.locationManager startUpdatingLocation];
    
    
    
}

- (void)locationManager:(CLLocationManager *)manager didChangeAuthorizationStatus:(CLAuthorizationStatus)status {
    
    switch (status) {
         case kCLAuthorizationStatusNotDetermined:
            
            if ([self.locationManager respondsToSelector:@selector(requestAlwaysAuthorization)]) {
                
                [self.locationManager requestWhenInUseAuthorization];
                
            }break;
            
        default:break;
            
    }
    
}

-(void)stop
{
      [_locationManager stopUpdatingLocation];
}

- (void)locationManager:(CLLocationManager *)manager didUpdateLocations:(NSArray<CLLocation *> *)locations {
    
    
    CLLocation *newLocation = locations[0];
    CLLocationCoordinate2D oldCoordinate = newLocation.coordinate;
    NSLog(@"旧的经度：%f,旧的纬度：%f",oldCoordinate.longitude,oldCoordinate.latitude);
    if(self.onece)
    [manager stopUpdatingLocation];
   
    CLGeocoder *geocoder = [[CLGeocoder alloc]init];
 
    [geocoder reverseGeocodeLocation:newLocation completionHandler:^(NSArray<CLPlacemark *> *_Nullable placemarks, NSError * _Nullable error) {
        for (CLPlacemark *place in placemarks) {

            NSLog(@"name,%@",place.name);                      // 位置名
            NSLog(@"thoroughfare,%@",place.thoroughfare);      // 街道
            NSLog(@"subThoroughfare,%@",place.subThoroughfare);// 子街道
            NSLog(@"locality,%@",place.locality);              // 市
            NSLog(@"subLocality,%@",place.subLocality);        // 区
            NSLog(@"country,%@",place.country);                // 国家
            
            
            NSMutableDictionary *res=[NSMutableDictionary new];
            res[@"lat"]=@(oldCoordinate.latitude);
            res[@"lon"]=@(oldCoordinate.longitude);
            res[@"country"]=place.country;
            res[@"province"]=place.addressDictionary[@"State"];
            res[@"address"]=place.addressDictionary[@"FormattedAddressLines"][0];
            
            res[@"city"]=place.locality;
           
            res[@"subLocality"]=place.addressDictionary[@"subLocality"];
            res[@"distreet"]=[place.thoroughfare add:place.subThoroughfare];
            _callback(res,true);
            
            
        }
        
        
        
    }];
    
    
    
}
@end
