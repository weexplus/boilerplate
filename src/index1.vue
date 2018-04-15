<template>

    <div style="background-color: yellow">
       <text>this is dex1</text>
    </div>
</template>
<style>

    .btn {
        width: 500;
        height: 100;
        background-color: red;
    }

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
        components: {head, flist},
        data: {
            logoUrl: 'http://img1.vued.vanthink.cn/vued08aa73a9ab65dcbd360ec54659ada97c.png',
            target: weex.config.env.deviceWidth,
            index: 0,
            ty: ''

        },
        methods: {


            dopost(url, param)
            {
//                weg,param,header,start,success,fail,exception,compelete
                return new Promise((resolve, reject) => {

                    var progress=weex.requireModule("progress")
                    var net = weex.requireModule('net')
                    var modal = weex.requireModule('modal')
                    net.post(url,param,{},function(){
                        //start
                        progress.show()
                    },function(e){
                        //success
                        // modal.toast({message:e.res.err})
                        if(e.res.err==0)
                        {
                            resolve(e.res)
                        }
                        else
                        {

                            modal.toast({message:e.res.err})
                        }

                    },function(e){
                        //compelete
                        progress.dismiss()

                    },function(e){
                        // exception
                        modal.toast({message:'网络异常！'})
                    });
                })
            },
            test()
            {
                return new Promise(function (resolve, reject) {
                    setTimeout(function () {
                        resolve('123');
                    }, 1000);
                })
            },
            update: function (e) {
                this.target = 'Weex'
                console.log('target:', this.target)
            },
            showpicker()
            {
                var modal = weex.requireModule("modal")
                modal.toast({message: 'ok'})
            },

            async btnclick()
            {

                var jk;
                jk.op();
//                this.ty= await this.dopost('http://59.110.169.246/movie/movie.do',{})
//                 this.ty=await this.test();

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

            this.target = weex.config.env.deviceWidth + '*' + weex.config.env.deviceHeight;
            var globalEvent = weex.requireModule('globalEvent');
            var self = this;
            globalEvent.addEventListener("onPageInit", function (e) {

              var page=weex.requireModule('page')
//              page.closeSplash()


            });

        },
    }
</script>