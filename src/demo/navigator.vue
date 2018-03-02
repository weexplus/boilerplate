<template>
    <div style="flex:1">
        <head title="导航" append="tree">

        </head>
        <div style="width: 750;height: 600;align-items: center;justify-content: center">
            <div class="btn" @click="push()">
                <text style="color:white">push不带参数</text>
            </div>
            <div class="btn" @click="lanscape()">
                <text style="color:white">横屏跳转</text>
            </div>
            <div class="btn" @click="pushwidthparam()">
                <text style="color:white">push带参数</text>
            </div>
            <text style="color:#000000">返回参数：{{callbackdata}}</text>
            <div class="btn" @click="present()">
                <text style="color:white">present</text>
            </div>
        </div>
    </div>
</template>
<style>
    .text {
        font-size: 50;
    }

</style>
<style src="./style.css"></style>
<script>
    var head = require('./header.vue')
    export default {
        components: {head},
        data () {
            return {
                text: 'Hello World.',
                callbackdata: ''
            }
        }
        ,
        methods: {
            push()
            {
                var nav = weex.requireModule('navigator');
                //相对路径写法，也可以root:绝对路径
                nav.push('net.js')
            },
            present(){
                var self = this;
                var nav = weex.requireModule('navigator');
                nav.presentFull(
                    {
                        url: 'present.js',
                        param: {a: 'okoko'}
                    }, (e) => {
                        if (e != undefined)
                            self.callbackdata = e.ok;
                    });

            },
            lanscape()
            {
                var nav = weex.requireModule('navigator');

                nav.pushFull({
                    url: 'navlanscape.js',
                    param: {a: '这是传过来的值'},
                    isPortrait:false
                }, (e) => {
                    if (e != undefined)
                        self.callbackdata = e.ok;
                })
            },
            pushwidthparam()
            {

                var self = this;
                var nav = weex.requireModule('navigator');

                nav.setPageId('index');
//            nav.pushParam('nav1.js',{a:'这是传过来的值'});
                nav.pushFull({
                    url: 'nav1.js',
                    param: {a: '这是传过来的值'},
                    isPortrait:true
                }, (e) => {
                    if (e != undefined)
                        self.callbackdata = e.ok;
                })

            }

        }
        ,
        created: function () {
            var globalEvent = weex.requireModule('globalEvent');
            globalEvent.addEventListener("onPageInit", function (e) {


                var navigator = weex.requireModule('navigator');
                navigator.addBackGestureSelfControl();


            });

        }
    }
</script>