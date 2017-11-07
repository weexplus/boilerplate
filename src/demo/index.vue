

<template>
<scroller style="background-color: #efeff4;" show-scrollbar="false">
        <div style="flex-direction: row">
            <div  class="cl" >
                <a href="navigator.js" class="btn"><text style="color:#ffffff" >导航</text></a>
                <a href="list.js" class="btn">  <text style="color:#ffffff">列表</text></a>
                <a href="recycler.js" class="btn"> <text style="color:#ffffff">瀑布流</text></a>
                <a href="recycler1.js" class="btn"> <text style="color:#ffffff">recycler</text></a>
                <a href="switch.js" class="btn" > <text style="color:#ffffff">switch</text></a>
                <a href="wechat.js" class="btn"> <text style="color:#ffffff">wechat</text></a>
                <a href="viewpager.js" class="btn"> <text style="color:#ffffff">viewpager</text></a>
                <a href="templatelistholder.js" class="btn"> <text style="color:#ffffff">templatelistholder</text></a>
            </div>
            <div class="cl"  >
                <a href="notify.js" class="btn"><text  style="color:#ffffff">无线通知</text></a>
                <a href="photo.js" class="btn">  <text style="color:#ffffff">照相</text></a>
                <a href="net.js" class="btn"> <text style="color:#ffffff">网络请求</text></a>
                <a href="input.js" class="btn"> <text style="color:#ffffff">input</text></a>
                <a href="refresh.js" class="btn"> <text style="color:#ffffff">refresh</text></a>
                <a href="loading.js" class="btn"> <text style="color:#ffffff">loading</text></a>
                <a href="tabpage.js" class="btn"> <text style="color:#ffffff">tab</text></a>
                <a href="pickerdemo.js" class="btn"> <text style="color:#ffffff">地址选择</text></a>
            </div>


        </div>

</scroller>


</template>

<style>
.cl
{
    flex:1;
    align-items: center;

}


</style>
<style src="./css/style.css"></style>
<script>


    var globalEvent = weex.requireModule('globalEvent') ;
    globalEvent.addEventListener("onPageInit", function (e) {
        const nav = weex.requireModule('navbar');
        nav.setTitle('主页');
        var navigator = weex.requireModule('navigator') ;
        navigator.setPageId('index');
        navigator.setRoot('index');
    });

    export default {

        data () {
            return {

            }
        },
        methods: {
            jk()
            {

                const modal = weex.requireModule('modal');
                modal.toast({ message: 'native toast' });

            },

        },
        created:function(){

            this.$on('rightClick', function(e) {
                const modal = weex.requireModule('modal');
                modal.toast({ message: '右边点击' });
            })
        }
    }
</script>