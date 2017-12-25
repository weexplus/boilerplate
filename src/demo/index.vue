<template>

    <div>
        <head title="weexplus demo" :back="false"></head>
        <scroller style="flex: 1;">
            <text class="group">组件</text>
        <a href="./host.js" style="border-top-width: 1;border-top-color: #999999;margin-top: 20" class="item">
            <text class="item_text">tab框架</text>
            <div  class="item_flex"></div>
            <image class="item_img" src="root:img/arrow.png" ></image>
         </a>
            <a href="./drawlayout.js" class="item">
                <text class="item_text">侧滑框架</text>
                <div  class="item_flex"></div>
                <image class="item_img" src="root:img/arrow.png" ></image>
            </a>
            <a href="./looper.js" class="item">
                <text class="item_text">text轮播器（looper）</text>
                <div  class="item_flex"></div>
                <image class="item_img" src="root:img/arrow.png" ></image>
            </a>
            <a href="./floading.js" class="item">
                <text class="item_text">小菊花组件（floading）</text>
                <div  class="item_flex"></div>
                <image class="item_img" src="root:img/arrow.png" ></image>
            </a>

            <text class="group">模块</text>
            <a href="./navigator.js" style="border-top-width: 1;border-top-color: #999999;margin-top: 20" class="item">
                <text class="item_text">导航</text>
                <div  class="item_flex"></div>
                <image class="item_img" src="root:img/arrow.png" ></image>
            </a>
            <a href="./notify.js" class="item">
                <text class="item_text">通知</text>
                <div  class="item_flex"></div>
                <image class="item_img" src="root:img/arrow.png" ></image>
            </a>
            <a href="./photo.js" class="item">
                <text class="item_text">相机相册（图片上传）</text>
                <div  class="item_flex"></div>
                <image class="item_img" src="root:img/arrow.png" ></image>
            </a>
            <a href="./static.js" class="item">
                <text class="item_text">静态存储（static）</text>
                <div  class="item_flex"></div>
                <image class="item_img" src="root:img/arrow.png" ></image>
            </a>
            <a href="./picker.js" class="item">
                <text class="item_text">选择器（picker）</text>
                <div  class="item_flex"></div>
                <image class="item_img" src="root:img/arrow.png" ></image>
            </a>

            <a href="./progress.js" class="item">
                <text class="item_text">无状态进度条（progress）</text>
                <div  class="item_flex"></div>
                <image class="item_img" src="root:img/arrow.png" ></image>
            </a>
            <a href="./qr.js" class="item">
                <text class="item_text">二维码（qr）</text>
                <div  class="item_flex"></div>
                <image class="item_img" src="root:img/arrow.png" ></image>
            </a>
            <a href="./net.js" class="item">
                <text class="item_text">网络访问（net）</text>
                <div  class="item_flex"></div>
                <image class="item_img" src="root:img/arrow.png" ></image>
            </a>

        </scroller>
    </div>

</template>
<style>

    .title {
        padding-top: 40px;
        padding-bottom: 40px;
        font-size: 48px;
    }

    .item_text{
        margin-left: 20;
        font-size: 35;
    }
    .item_flex{
       flex: 1;

    }
    .item_img{
       width: 25;
        height: 40;
        margin-right: 20;

    }
    .item {
        height: 100;
        flex-direction: row;
        align-items: center;
        border-bottom-color: #999999;
        border-bottom-width: 1;

    }
    .item:active
    {
        background-color: #eeeeee;
    }
    .group
    {
        margin-top: 30;margin-left: 20;color: red;font-size: 40;
    }


</style>

<script>
    var head = require('./component/header.vue')
    const net = require('./util/net.js')
    var p = undefined;
    export default {
        components: {head},
        data: {
            logoUrl: 'http://img1.vued.vanthink.cn/vued08aa73a9ab65dcbd360ec54659ada97c.png',
            target: 'World',
            index: 0

        },
        methods: {
            update: function (e) {
                this.target = 'Weex'
                console.log('target:', this.target)
            },
            showpicker()
            {
                var modal = weex.requireModule("modal")
                modal.toast({message: 'ok'})
            },


            goton()
            {
                var nav = weex.requireModule("navigator")
                nav.push('Login.js');
            },


            change(i)
            {

                this.index = i;
            },
            show()
            {
                var modal = weex.requireModule("modal")
                var p = weex.config.env.osVersion
                p = p.replace(/\./g, '')
                modal.alert({message: p})
            }
        }
        ,


        created: function () {


            var globalEvent = weex.requireModule('globalEvent');
            var self = this;
            globalEvent.addEventListener("onPageInit", function (e) {


                const nav = weex.requireModule('navbar');
//                nav.hide();

                const navigator = weex.requireModule('navigator');
                navigator.addBackGestureSelfControl();


                nav.setStatusBarStyle('white');


            });

        },
    }
</script>