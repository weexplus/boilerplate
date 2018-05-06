#ifdef __OBJC__
#import <UIKit/UIKit.h>
#else
#ifndef FOUNDATION_EXPORT
#if defined(__cplusplus)
#define FOUNDATION_EXPORT extern "C"
#else
#define FOUNDATION_EXPORT extern
#endif
#endif
#endif

#import "WeexPluginConfigParser.h"
#import "WeexPluginLoader.h"
#import "WeexPluginManager.h"
#import "Weexplugin.h"

FOUNDATION_EXPORT double WeexPluginLoaderVersionNumber;
FOUNDATION_EXPORT const unsigned char WeexPluginLoaderVersionString[];

