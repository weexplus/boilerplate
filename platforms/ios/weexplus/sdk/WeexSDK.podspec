# coding: utf-8

Pod::Spec.new do |s|

  s.name         = "WeexSDK"

  s.version      = "0.20.0"

  s.summary      = "WeexSDK Source."

  s.description  = <<-DESC
                   A framework for building Mobile cross-platform UI
                   DESC

  s.homepage     = "https://github.com/alibaba/weex"
  s.license = {
    :type => 'Copyright',
    :text => <<-LICENSE
           Alibaba-INC copyright
    LICENSE
  }
  s.authors      = {
                    "cxfeng1"      => "cxfeng1@gmail.com",
                    "boboning"     => "ningli928@163.com",
                    "yangshengtao" => "yangshengtao1314@163.com",
                    "kfeagle"      => "sunjjbobo@163.com",
                    "acton393"     => "zhangxing610321@gmail.com",
                    "wqyfavor"     => "wqyfavor88@gmail.com",
                    "doumafang "   => "doumafang@gmail.com"
                   }
  s.platform     = :ios
  s.ios.deployment_target = '8.0'

  # use for public
  # s.source =  {
  #  :git => 'https://github.com/apache/incubator-weex.git',
  #  :tag => #{s.version}
  # }

  # use for playground
  s.source =  { :path => '.' }

  s.source_files = 'WeexSDK/Sources/**/*.{h,m,mm,c,cpp,cc}',
                    'weex_core/Source/base/**/*.{h,hpp,m,mm,c,cpp,cc}',
                    'weex_core/Source/core/**/*.{h,hpp,m,mm,c,cpp,cc}',
                    'weex_core/Source/wson/**/*.{h,hpp,m,mm,c,cpp,cc}',
                    'weex_core/Source/third_party/**/*.{h,hpp,m,mm,c,cpp,cc}',
                    'weex_core/Source/include/**/*.{h,hpp,m,mm,c,cpp,cc}'
  s.exclude_files = 'weex_core/Source/**/*android.{h,hpp,m,mm,c,cpp,cc}',
                    'weex_core/Source/base/android',
                    'weex_core/Source/base/base64',
                    'weex_core/Source/base/crash',
                    'weex_core/Source/base/utils',
                    'weex_core/Source/base/thread',
                    'weex_core/Source/base/third_party',
                    'weex_core/Source/base/message_loop',
                    'weex_core/Source/base/time_point.*',
                    'weex_core/Source/base/time_utils.*',
                    'weex_core/Source/base/time_unit.*',
                    'weex_core/Source/third_party/IPC',
                    'weex_core/Source/core/network/android/',
                    'weex_core/Source/include/JavaScriptCore/',
                    'weex_core/Source/include/wtf'

  s.private_header_files = 'WeexSDK/Sources/Component/RecycleList/WXJSASTParser.h'
  s.public_header_files = 'WeexSDK/Sources/WeexSDK.h',
                          'WeexSDK/Sources/Layout/WXComponent+Layout.h',
                          'WeexSDK/Sources/Debug/WXDebugTool.h',
                          'WeexSDK/Sources/Loader/WXResourceLoader.h',
                          'WeexSDK/Sources/WebSocket/WXWebSocketHandler.h',
                          'WeexSDK/Sources/Module/WXVoiceOverModule.h',
                          'WeexSDK/Sources/Module/WXPrerenderManager.h',
                          'WeexSDK/Sources/Module/WXModalUIModule.h',
                          'WeexSDK/Sources/Component/WXListComponent.h',
                          'WeexSDK/Sources/Component/WXScrollerComponent.h',
                          'WeexSDK/Sources/Component/WXRichText.h',
                          'WeexSDK/Sources/Component/WXIndicatorComponent.h',
                          'WeexSDK/Sources/Component/WXAComponent.h',
                          'WeexSDK/Sources/Component/Recycler/WXRecyclerComponent.h',
                          'WeexSDK/Sources/Controller/WXBaseViewController.h',
                          'WeexSDK/Sources/Controller/WXRootViewController.h',
                          'WeexSDK/Sources/View/WXView.h',
                          'WeexSDK/Sources/View/WXErrorView.h',
                          'WeexSDK/Sources/Protocol/*.h',
                          'WeexSDK/Sources/Network/WXResourceRequestHandler.h',
                          'WeexSDK/Sources/Network/WXResourceRequest.h',
                          'WeexSDK/Sources/Network/WXResourceResponse.h',
                          'WeexSDK/Sources/Model/WXSDKInstance.h',
                          'WeexSDK/Sources/Model/WXJSExceptionInfo.h',
                          'WeexSDK/Sources/Model/WXComponent.h',
                          'WeexSDK/Sources/Monitor/WXMonitor.h',
                          'WeexSDK/Sources/Monitor/WXExceptionUtils.h',
                          'WeexSDK/Sources/Monitor/WXAnalyzerCenter.h',
                          'WeexSDK/Sources/Manager/WXSDKManager.h',
                          'WeexSDK/Sources/Manager/WXBridgeManager.h',
                          'WeexSDK/Sources/Manager/WXComponentManager.h',
                          'WeexSDK/Sources/Engine/WXSDKEngine.h',
                          'WeexSDK/Sources/Engine/WXSDKError.h',
                          'WeexSDK/Sources/Utility/WXConvert.h',
                          'WeexSDK/Sources/Utility/WXUtility.h',
                          'WeexSDK/Sources/Utility/WXLog.h',
                          'WeexSDK/Sources/Utility/WXDefine.h',
                          'WeexSDK/Sources/Utility/WXType.h',
                          'WeexSDK/Sources/Utility/NSObject+WXSwizzle.h',
                          'WeexSDK/Sources/Utility/WXAppConfiguration.h',
                          'WeexSDK/Sources/Performance/WXApmForInstance.h',
                          'WeexSDK/Sources/Bridge/JSContext+Weex.h',
                          'weex_core/Source/core/layout/flex_enum.h',
                          'weex_core/Source/core/layout/layout.h',
                          'weex_core/Source/core/layout/style.h'

  s.module_map = 'WeexSDK.modulemap'

  # 0.21.0 版本开始不再需要 native-bundle-main.js
  s.resources = 'WeexSDK/Resources/*.js','WeexSDK/Resources/wx_load_error@3x.png'

  s.user_target_xcconfig  = { 'FRAMEWORK_SEARCH_PATHS' => "'$(PODS_ROOT)/WeexSDK'" }
  s.requires_arc = true
  s.prefix_header_file = 'WeexSDK/Sources/Supporting Files/WeexSDK-Prefix.pch'

  s.xcconfig = { "OTHER_LINK_FLAG" => '$(inherited) -ObjC' }
  s.pod_target_xcconfig = { 'USER_HEADER_SEARCH_PATHS' => '${PROJECT_DIR}/../sdk/weex_core/Source/  ${PODS_ROOT}/WeexSDK/weex_core/Source/',
    'GCC_PREPROCESSOR_DEFINITIONS' => 'OS_IOS=1' }

  s.frameworks = 'CoreMedia','MediaPlayer','AVFoundation','AVKit','JavaScriptCore','GLKit','OpenGLES','CoreText','QuartzCore','CoreGraphics'
  
  s.libraries = 'c++'

end
