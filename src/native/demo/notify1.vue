

<template>

        <div  >
            <head title="跨页面无线通知"   append="tree">

            </head>
            <div  class="cl" >
                <div   class="btn"  @click="send()"><text  style="color:#ffffff">发送并返回</text></div>


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
    var globalEvent = weex.requireModule('globalEvent') ;
    globalEvent.addEventListener("onPageInit", function (e) {
//        const nav = weex.requireModule('navbar');
//        nav.setTitle('通知');
//        nav.setBack(true);

        const notify = weex.requireModule('notify');
        notify.regist("key",function (res) {
            const modal = weex.requireModule('modal');
            modal.toast({ message: res.data });
        });


    });



    export default {
        components:{head},
        data () {
            return {

            }
        },
        methods: {
            send()
            {
                const notify = weex.requireModule('notify');
                notify.send("key",{data:'notify1数据'})
                var navigator = weex.requireModule('navigator') ;
                navigator.back()

            },

        },
        created:function(){


        }
    }
</script>