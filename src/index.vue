<template>
  <div style="flex: 1;justify-content: center;align-items: center">
    <image style="width: 100;height: 100;border-radius: 50"  placeholder="root:img/cat.png"  src="http://59.110.169.246/movie/tx/1.jpg"></image>
    <image style="width: 100;height: 100;border-radius: 50" src="root:img/cat.png"></image>
    <image style="width: 100;height: 100;border-radius: 50" src="root:img/cat.png"></image>
    <!--<host  :index="index"  :items="['app/busi/tab/serial.js','app/busi/tab/movie.js']" style="width: 750;height:1000;background-color: red">-->

    <!--</host>-->
    <!--<div style="height: 100;width: 750;position: absolute;bottom: 0;left: 0;right: 0;flex-direction: row">-->
    <!--<div  @click="change(0)" style="flex: 1;align-items: center;justify-content: center">-->
    <!--<text>tab1</text>-->
    <!--</div>-->
    <!--<div @click="change(1)" style="flex: 1;align-items: center;justify-content: center">-->
    <!--<text>tab2</text>-->
    <!--</div>-->
    <!--</div>-->

    <!--<prerender src="app/busi/tab/serial.js"></prerender>-->
    <!--<prerender src="app/busi/tab/movie.js"></prerender>-->
    <!--<a href="demo/pagedemo.js" navbarVisibility="hidden" style="width: 100;height: 100;background-color: red;justify-content: center;align-items: center"><text>跳转</text></a>-->
    <div style="width: 100;height: 100;background-color: red"   @longpress="showpicker">

    </div>
  </div>
</template>

<style>

  .title { padding-top:40px; padding-bottom: 40px; font-size: 48px; }
  .logo { width: 360px; height: 156px; }
  .desc { padding-top: 20px; color:#888; font-size: 24px;}
</style>

<script>
    var head =require('./component/header.vue')
    const net =require('./busi/util/net.js')
    var p=undefined;
    export default {
        components:{head},
        data: {
            logoUrl: 'http://img1.vued.vanthink.cn/vued08aa73a9ab65dcbd360ec54659ada97c.png',
            target: 'World',
            index:0
        },
        methods: {
            update: function (e) {
                this.target = 'Weex'

                console.log('target:', this.target)
            },
            showpicker()
            {
                var modal=weex.requireModule("modal")
                modal.toast({message:'ok'})
//                var url='http://59.110.169.246/movie/tx/p1.txt'
//                var progress=weex.requireModule("progress")
//                var modal=weex.requireModule("modal")
//                var net=weex.requireModule("net")
//                net.post(url,{},{},function(){
//                    //start
//                    progress.show();
//                },function(e){
//                    //success
//                    // modal.toast({message:e.res.err})
//                    modal.alert({message:e.res})
//
//                },function(e){
//                    //compelete
//                    progress.dismiss();
//                },function(e){
//                    // exception
//
//                });
            },

            change(i)
            {
                this.index=i;
            },
            show()
            {
                var modal=weex.requireModule("modal")
                var p=weex.config.env.osVersion
//              p=p.replace('.', "")
//          p=p.replace(/./g,"");
                p= p.replace(/\./g,'')
                modal.alert({message:p})
            }
        }
        ,


        created:function(){


            var globalEvent = weex.requireModule('globalEvent') ;
            var self=this;
            globalEvent.addEventListener("onPageInit", function (e) {


                const nav = weex.requireModule('navbar');
                nav.hide();


            });

        },
    }
</script>