

<template>
 <div style="align-items: center;justify-content: center;background-color: red">
   <text >ssdsdsds</text>
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
        const nav = weex.requireModule('navbar');
        nav.setTitle('网络请求');
        var navigator = weex.requireModule('navigator') ;

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