

<template>

 <div style="align-items: center;justify-content: center;background-color: #ffffff">

  <div @click="ok" style="width: 100;height: 100;background-color: red;align-items: center;justify-content: center">
      <text>open</text>
  </div>
     <div @click="close" style="width: 100;height: 100;background-color: red;align-items: center;justify-content: center;margin-top: 20">
         <text>close</text>
     </div>
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

    });

    export default {
        components:{head},
        data () {
            return {
               back:"",
                header:{}
            }
        },
        methods: {
            ok()
            {
                 var pop=weex.requireModule("slidpop")
                 pop.show('center.js',{},600,{},'bottom')
            },
            close()
            {
                var pop=weex.requireModule("slidpop")
                pop.dismiss();
            }


        },
        created:function(){


        }
    }
</script>