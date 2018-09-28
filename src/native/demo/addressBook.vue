

<template>
 <scroller>
     <head title="读取联系人"  append="tree">

     </head>
        <div  style="margin-bottom:50" >
            <div  class="cl" >
                <div  @click="read()" class="btn"><text style="color:#ffffff" >读取联系人</text></div>
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

               var r=weex.requireModule("addressBook")
                r.read((res)=>{
                    this.back=res;
                })

            },

        },
        created:function(){


        }
    }
</script>