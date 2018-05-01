<template>
    <div style="flex: 1;">

        <host  :index="index"   :items="items"  style="position: absolute;left: 0;top: 0;right: 0;bottom: 100;">

        </host>
        <div style="height: 100;width: 750;position: absolute;bottom: 0;left: 0;right: 0;flex-direction: row;background-color: #0088fb">
            <div  @click="change(0)" style="flex: 1;align-items: center;justify-content: center">
                <text>推荐</text>
            </div>
            <div @click="change(1)" style="flex: 1;align-items: center;justify-content: center">
                <text>电视剧</text>
            </div>
            <div @click="change(2)" style="flex: 1;align-items: center;justify-content: center">
                <text>电影</text>
            </div>
            <div @click="change(3)" style="flex: 1;align-items: center;justify-content: center">
                <text>收藏</text>
            </div>
        </div>
        <!--<prerender src="home.js"></prerender>-->

        <!--<prerender src="app/busi/tab/serial.js"></prerender>-->
        <!--<a href="demo/pagedemo.js" style="width: 100;height: 100;background-color: red;justify-content: center;align-items: center"><text>跳转</text></a>-->
    </div>
</template>

<style>


</style>

<script>

    export default {
        components:{ },
        data: {
            logoUrl: 'http://img1.vued.vanthink.cn/vued08aa73a9ab65dcbd360ec54659ada97c.png',
            target: 'World',
            index:0,
            items:['home.js','inputs.js','lopper.js','nav1.js']
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


                const page = weex.requireModule('page');
                page.closeSplash();



            });

        },
    }
</script>