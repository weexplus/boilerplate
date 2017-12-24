//
//  WXDebuggerUtility.m
//  Pods
//
//  Created by yangshengtao on 16/12/30.
//
//

#import "WXDebuggerUtility.h"
#import <UIKit/UIKit.h>
#import <objc/runtime.h>

@implementation WXDebuggerUtility

+ (id)switchInvocationReture:(NSInvocation *)invocation {
    if (!invocation) {
        return nil;
    }
    
    const char * returnType = [invocation.methodSignature methodReturnType];
    
    id returnValue;
    switch (returnType[0] == _C_CONST ? returnType[1] : returnType[0]) {
        case _C_ID: {
            // 1.id
            void *value;
            [invocation getReturnValue:&value];
            id object = (__bridge id)value;
            
            returnValue = object;
            break;
        }
        
#define WXDEVTOOL_ID_RET_CASE(typeString, type) \
case typeString: {                      \
type value;                         \
[invocation getReturnValue:&value];  \
returnValue = @(value); \
break; \
}
        // 2.number
        WXDEVTOOL_ID_RET_CASE(_C_CHR, char)
        WXDEVTOOL_ID_RET_CASE(_C_UCHR, unsigned char)
        WXDEVTOOL_ID_RET_CASE(_C_SHT, short)
        WXDEVTOOL_ID_RET_CASE(_C_USHT, unsigned short)
        WXDEVTOOL_ID_RET_CASE(_C_INT, int)
        WXDEVTOOL_ID_RET_CASE(_C_UINT, unsigned int)
        WXDEVTOOL_ID_RET_CASE(_C_LNG, long)
        WXDEVTOOL_ID_RET_CASE(_C_ULNG, unsigned long)
        WXDEVTOOL_ID_RET_CASE(_C_LNG_LNG, long long)
        WXDEVTOOL_ID_RET_CASE(_C_ULNG_LNG, unsigned long long)
        WXDEVTOOL_ID_RET_CASE(_C_FLT, float)
        WXDEVTOOL_ID_RET_CASE(_C_DBL, double)
        WXDEVTOOL_ID_RET_CASE(_C_BOOL, BOOL)
        
        case _C_STRUCT_B: {
            NSString *typeString = [NSString stringWithUTF8String:returnType];
            if ([typeString rangeOfString:@"NSRange"].location != NSNotFound) {
                NSRange range;
                [invocation getReturnValue:&range];
                returnValue = NSStringFromRange(range);
                break;
            }
            
#define WXDEVTOOL_STRUCT_FUNCTION(_type)  NSStringFrom ## _type
#define WXDEVTOOL_STRUCT_RET_CASE(_type)                             \
            if ([typeString rangeOfString:@#_type].location != NSNotFound) {   \
                _type value;                                                   \
                [invocation getReturnValue:&value];                            \
                returnValue = WXDEVTOOL_STRUCT_FUNCTION(_type)(value); \
                break;                                                         \
            }
            // 4.struct
            WXDEVTOOL_STRUCT_RET_CASE(CGRect)
            WXDEVTOOL_STRUCT_RET_CASE(CGPoint)
            WXDEVTOOL_STRUCT_RET_CASE(CGSize)
        }
        case _C_CHARPTR:
        case _C_PTR:
        case _C_VOID:
        case _C_CLASS: {
            returnValue = nil;
            break;
        }
    }
    
    return returnValue;
}

@end
