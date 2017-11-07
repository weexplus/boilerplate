<template>
  <div style="flex: 1;background-color: red">

    <host  :index="index"  :items="['app/busi/tab/mainpage.js','app/busi/tab/serial.js','app/busi/tab/movie.js','app/demo/hmain.js']" style="position: absolute;left: 0;top: 0;right: 0;bottom: 100; background-color: red;">

    </host>
    <div style="height: 100;width: 750;position: absolute;bottom: 0;left: 0;right: 0;flex-direction: row;background-color: #0088fb">
      <div  @click="change(0)" style="flex: 1;align-items: center;justify-content: center">
        <text>tab1</text>
      </div>
      <div @click="change(1)" style="flex: 1;align-items: center;justify-content: center">
        <text>tab2</text>
      </div>
      <div @click="change(2)" style="flex: 1;align-items: center;justify-content: center">
        <text>tab2</text>
      </div>
      <div @click="change(3)" style="flex: 1;align-items: center;justify-content: center">
        <text>tab2</text>
      </div>
    </div>

    <!--<prerender src="app/busi/tab/serial.js"></prerender>-->
    <!--<a href="demo/pagedemo.js" style="width: 100;height: 100;background-color: red;justify-content: center;align-items: center"><text>跳转</text></a>-->
  </div>
</template>

<style>

  .title { padding-top:40px; padding-bottom: 40px; font-size: 48px; }
  .logo { width: 360px; height: 156px; }
  .desc { padding-top: 20px; color:#888; font-size: 24px;}
</style>

<script>

    export default {
        components:{ },
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