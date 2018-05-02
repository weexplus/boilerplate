

<template>
 <div style="align-items: center;justify-content: center;">
  <text style="font-family: erp;font-size: 50">&#xee4e;</text>
  <text style="font-family: erp;font-size: 50">&#xe678;</text>
  <text style="font-family: erp;font-size: 50">&#xe677;</text>
  <text style="font-family: erp;font-size: 50">&#xe671;</text>
  <text style="font-family: erp;font-size: 50">&#xe6a3;</text>
  <text style="font-family: erp;font-size: 50">&#xe677;</text>

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
            read()
            {

               var r=weex.requireModule("addressBook")
                r.read((res)=>{
                    this.back=res;
                })

            },

        },
        created:function(){

            var font=weex.requireModule("font");
//            font.addFont('erp','http://at.alicdn.com/t/font_557063_l3ad49ffyz2utyb9.ttf')
            font.addFont('erp','root:font/nfont.ttf')
        }
    }
</script>