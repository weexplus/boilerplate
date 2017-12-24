<template>
    <scroller style="flex: 1;align-items:center;background-color: yellow">

        <text style="margin-top: 50;font-size: 35" >我是一只猫</text>

        <image @click="goton" src="root:img/cat.png" style="width: 200;height:200;border-radius: 100"></image>
        <image @click="goton" src="root:img/cat.png" style="width: 200;height:200;border-radius: 100"></image>
        <div style="width: 200;height: 100;background-color: #0085ee;margin-top: 10"></div>
        <div style="width: 200;height: 100;background-color: #0085ee;margin-top: 10"></div>
        <div style="width: 200;height: 100;background-color: #0085ee;margin-top: 10"></div>


    </scroller>
</template>
<style>

    .title {
        padding-top: 40px;
        padding-bottom: 40px;
        font-size: 48px;
    }

    .logo {
        width: 360px;
        height: 156px;
    }

    .desc {
        padding-top: 20px;
        color: #888;
        font-size: 24px;
    }
</style>

<script>
    var head = require('./busi/component/header.vue')
    const net = require('./busi/util/net.js')
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


                nav.setStatusBarStyle('balck');


            });

        },
    }
</script>