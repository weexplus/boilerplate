

<template>
 <div style="align-items: center;justify-content: center;">
   <text style="color:#000000">{{back}}</text>
   <div @click="open" style="width:200;height: 200;background-color: red"></div>
 </div>

</template>

<style>
.cl
{

    align-items: center;

}


</style>
<style src="./style.css"></style>
<script>

    var head =require('./header.vue')
    var globalEvent = weex.requireModule('globalEvent') ;
    globalEvent.addEventListener("onPageInit", function (e) {
//        const nav = weex.requireModule('navbar');
//        nav.setTitle('网络请求');
//        var navigator = weex.requireModule('navigator') ;

    });

    export default {
        components:{head},
        data () {
            return {
               back:""
            }
        },
        methods: {
            open()
            {
                var pick=weex.requireModule('timepicker')
                pick.pickTime({},(res)=>{
                    this.back=res;
                })

                var location=weex.requireModule('location')
                location.start({once:true},(res)=>{
                    this.back=res;
                })
            }

        },
        created:function(){

            var font=weex.requireModule("font");
//            font.addFont('erp','http://at.alicdn.com/t/font_557063_l3ad49ffyz2utyb9.ttf')
            font.addFont('erp','root:font/nfont.ttf')
        }
    }
</script>