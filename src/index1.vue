<template>
    <div style="background-color: yellow;align-items: center;justify-content: center">
       <input  :type="type" style="width: 750px;height: 50px"  placeholder="xxxx"/>
        <div @click="changexxx()" style="width: 100px;height: 100px;background-color: red"></div>
        <text>{{type}}</text>
    </div>
</template>
<style>


</style>

<script>
    var head = require('./demo/component/header.vue')
    var flist = require('./demo/component/flist.vue')
    const net = require('./demo/util/net.js')
    var p = undefined;
    export default {
        components: {head, flist},
        data(){
             return  {
                 type:'password'
             }


        },
        methods: {


            changexxx()
            {

                var modal=weex.requireModule('modal')
                modal.toast({message:'sss'})
              this.type = this.type=='password'?'text':'password'
//                this.type='text'
            },
            dopost(url, param)
            {
//                weg,param,header,start,success,fail,exception,compelete
                return new Promise((resolve, reject) => {

                    var progress = weex.requireModule("progress")
                    var net = weex.requireModule('net')
                    var modal = weex.requireModule('modal')
                    net.post(url, param, {}, function () {
                        //start
                        progress.show()
                    }, function (e) {
                        //success
                        // modal.toast({message:e.res.err})
                        if (e.res.err == 0) {
                            resolve(e.res)
                        }
                        else {

                            modal.toast({message: e.res.err})
                        }

                    }, function (e) {
                        //compelete
                        progress.dismiss()

                    }, function (e) {
                        // exception
                        modal.toast({message: '网络异常！'})
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

//                var navigator = weex.requireModule('navigator')
//                navigator.push('demo/drawlayout.js')
//              page.closeSplash()


            });

        },
    }
</script>