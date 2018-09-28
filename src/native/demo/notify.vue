

<template>

        <div  >
            <head title="无线通知"   append="tree">

            </head>
            <text style="align-items:center;margin-left: 50;margin-top: 30;color: #000000">{{data}}</text>
            <div  class="cl" >
                <div   class="btn"  @click="send()"><text  style="color:#ffffff">发送</text></div>

                <a   href="notify1.js" class="btn"   ><text  style="color:#ffffff">跨页面无线通知</text></a>

            </div>

        </div>




</template>

<style>
.cl
{
    flex:1;
    align-items: center;

}


</style>
<style src="./style.css"></style>
<script>

    var head =require('./header.vue')

    export default {
        components:{head},
        data () {
            return {
                data:''
            }
        },
        methods: {
            send()
            {
                const notify = weex.requireModule('notify');
                notify.send("key",{data:'这是数据'})
            },

        },
        created:function(){

            this.$on('rightClick', function(e) {
                const modal = weex.requireModule('modal');
                modal.toast({ message: '右边点击' });
            })

            var self=this;
            var globalEvent = weex.requireModule('globalEvent') ;
            globalEvent.addEventListener("onPageInit", function (e) {
                const nav = weex.requireModule('navbar');
//                nav.setTitle('通知');
//                nav.setBack(true);

                const notify = weex.requireModule('notify');
                notify.regist("key",function (res) {
                    self.data=res.data;
                });


            });
        }
    }
</script>