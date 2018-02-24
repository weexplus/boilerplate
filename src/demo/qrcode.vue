<template>
 <scroller>
     <head title="二维码扫描"  append="tree">
     </head>
        <div  style="margin-bottom:50" >
            <div  class="cl" >
                <div  @click="read()" class="btn"><text style="color:#ffffff" >二维码扫描</text></div>
                <text style="color:#0000000">{{back}}</text>
            </div>
        </div>
 </scroller>
</template>
<style>
.cl
{
    align-items: center;
}
</style>
<style src="./css/style.css"></style>
<script>

    var head =require('./header.vue')
    var globalEvent = weex.requireModule('globalEvent') ;
    globalEvent.addEventListener("onPageInit", function (e) {


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

               var r=weex.requireModule("qr")
                var p={}
                p.color="#ffffff"
                p.bgcolor="#ffb375"
               r.open(p,(res)=>{
                  this.back=res;

               })

            },

        },
        created:function(){


        }
    }
</script>