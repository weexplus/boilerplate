<template>

   <div style="background-color: yellow">
       <!--<head title="demo"  @titleClick="update"></head>-->
       <image src="ds"  placeholder="root:img/cat.png" style="width: 200;height: 200;background-color: red"></image>
   </div>
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
    var flist = require('./demo/component/flist.vue')
    const net = require('./demo/util/net.js')
    var p = undefined;
    export default {
        components: {head,flist},
        data: {
            logoUrl: 'http://img1.vued.vanthink.cn/vued08aa73a9ab65dcbd360ec54659ada97c.png',
            target: weex.config.env.deviceWidth,
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

            this.target=weex.config.env.deviceWidth+'*'+weex.config.env.deviceHeight;
            var globalEvent = weex.requireModule('globalEvent');
            var self = this;
            globalEvent.addEventListener("onPageInit", function (e) {

                net.default.post('movie.do',{},(res)=>{
                     var modal=weex.requireModule('modal')
                    modal.alert({message:res})
                })


            });

        },
    }
</script>