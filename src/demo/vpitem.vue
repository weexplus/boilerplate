

<template>

        <div   class="cl">

          <div style="background-color: red;height: 500;width:750"></div>

        </div>




</template>

<style>
.cl
{



}


</style>

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