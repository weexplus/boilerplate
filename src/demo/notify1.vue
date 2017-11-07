

<template>

        <div style="flex-direction: row">
            <div  class="cl" >
                <div   class="btn"  @click="send()"><text  style="color:#ffffff">发送</text></div>


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
<style src="./css/style.css"></style>
<script>


    var globalEvent = weex.requireModule('globalEvent') ;
    globalEvent.addEventListener("onPageInit", function (e) {
        const nav = weex.requireModule('navbar');
        nav.setTitle('通知');
        nav.setBack(true);

        const notify = weex.requireModule('notify');
        notify.regist("key",function (res) {
            const modal = weex.requireModule('modal');
            modal.toast({ message: res.data });
        });


    });



    export default {

        data () {
            return {

            }
        },
        methods: {
            send()
            {
                const notify = weex.requireModule('notify');
                notify.send("key",{data:'这是数据'})
                var navigator = weex.requireModule('navigator') ;
                navigator.back()

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