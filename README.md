# farwolf 的脚手架

##2017-07-21新增fpicker组件
官方的仅支持一列，类似地域3级联动的我写了个，我这个支持1-3列，联动的自己去写，pickerdemo.vue里面有个全国的省市区联动，你们可以参照着写

.新增vuelist组件flist，可以节省很多核心代码，大家参考fpickerdemo.vue的写法写

## demo及api

在farwolf.weex/src/demo下，个人精力有限无法出api文档，各位找到farwolf.weex/platforms/android/farwolf.weex/src/main/java/com/farwolf/weex/module/下面的java源码，
所有@JsMethod标注的方法就是暴露给js的方法

## 特色:
1.使用原生路由，官方也是，但是官方有页面转场有白屏现象，预加载技术独此一家，保证切换的时候无白屏现象，本地js有效，网络js无效

2.页面参数传递，页面参数回传，跨页面返回

3.无线通知，原生基于eventbus实现

4.相机拍照，相册选取照片

5.网络访问的简易封装

6.原生代码的static变量接口，比如登录的user对象可以放里面，

7.android端报错会弹出界面提示，官方的是白屏，都不知道发生了啥

8.也继承了二维码扫描，方便调试

9.双击刷新界面，三机弹出调试界面，android端如果底层是scroller，这2个就不能用，暂时没有解决

## 使用方法:

git clone  https://github.com/farwolf2010/farwolf.weex.git

## android:
```android:

必须使用android studio :

open an existing Android studio Porject

选择farwolf.weex/platforms/android/vshop目录

修改：farwolf.weex/platforms/android/vshop/local.properties 里面的 sdk.dir=你自己的android sdk路径

等待下载相应插件

然后菜单：build, make 无报错，run->run app 即可看到效果

双击可以刷新，但是跟布局如果是scoller无效，三击可以打开二维码扫描，可以先把scroller注释掉，调完了再恢复，推荐直接用ios调，ios无此问题，调完了Android做个适配就完了
```

## ios:

```ios:
使用xcode 打开：farwolf.weex/platforms/ios/vshop/vshop.xcworkspace
看清楚是.xcworkspace 结尾的哪个，不是.project
run
即可看到效果，双击刷新，三击打开二维码扫描


