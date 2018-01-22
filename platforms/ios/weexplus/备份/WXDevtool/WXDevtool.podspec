# coding: utf-8
Pod::Spec.new do |s|

  s.name         = "WXDevtool"
  s.version      = "0.15.3"
  s.summary      = "WXDevtool Source."

  s.description  = <<-DESC
                   WXDevtool Source description
                   DESC

  s.homepage     = "https://github.com/weexteam/weex-devtool-iOS"
  s.license = {
    :type => 'Copyright',
    :text => <<-LICENSE
           Alibaba-INC copyright
    LICENSE
  }
  s.authors      = {
                     "kfeagle" =>"songhaibohust@gmail.com"
                   }
  s.platform     = :ios
  s.ios.deployment_target = '7.0'
  
  # cocoapods
  # s.source =  { :http => 'https://gw.alicdn.com/bao/uploaded/LB1cntPSFXXXXauXpXXXXXXXXXX.zip?spm=a1z3i.a4.0.0.75ba3c681StKKI&file=LB1cntPSFXXXXauXpXXXXXXXXXX.zip' }

  # mtl
  s.source =  { :path => '.' }
  s.source_files = 'WXDevTool/Source/**/*.{h,m,mm,c}'
  s.prefix_header_file = 'WXDevTool/Source/Supporting Files/TBWXDevTool.pch'

  s.requires_arc = true
  s.vendored_frameworks = 'TBWXDevTool.framework'
  s.frameworks = 'Foundation','CoreData','ImageIO','CoreData','CFNetwork','UIKit','CoreGraphics','Security'
  s.libraries = 'z'
  s.dependency 'WeexSDK'
end
