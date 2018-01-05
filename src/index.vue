<template>
    <scroller style="flex: 1;background-color: yellow">
        <head title="demo"  @titleClick="update"></head>
      <div @click="update" style="width: 750px;height: 100px; background-color: red" >

      </div>
        <image style="width: 200;height: 200" :src="logoUrl"></image>
        <image style="width: 200;height: 200" src="root:img/cat.png"></image>
        <text>{{target}}</text>


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
    var head = require('./demo/component/header.vue')
    const net = require('./demo/util/net.js')
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