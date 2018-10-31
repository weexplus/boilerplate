Pod::Spec.new do |s|

  s.name         = "WXDevtool"

  s.version      = "0.16.3"

  s.summary      = "WXDevtool Source."

  s.description  = <<-DESC
                   WXDevtool Source description
                   DESC

  s.homepage     = "https://github.com/weexteam/weex-devtool-iOS"

  s.license = {
    :type => "Copyright",
    :text => <<-LICENSE
           Alibaba-INC copyright
    LICENSE
  }

  s.authors      = {
                     "mario" =>"faterrole@gmail.com",
                     "kfeagle" =>"songhaibohust@gmail.com"
                   }

  s.platform     = :ios

  s.ios.deployment_target = "8.0"
  
  # cocoapods
  s.source =   { :path => '.' }
  s.source_files = "WXDevTool/Source/**/*.{h,m,mm,c}"
  s.requires_arc = true
  s.frameworks = "Foundation","CoreData","ImageIO","CoreData","CFNetwork","UIKit","CoreGraphics","Security"
  s.libraries = "z"

  s.dependency "WeexSDK"
  s.dependency "SocketRocket",'~> 0.4.2' 
end