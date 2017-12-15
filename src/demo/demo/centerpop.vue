

<template>

 <div style="align-items: center;justify-content: center;background-color: #ffffff">

  <div @click="ok" style="width: 100;height: 100;background-color: red">
      <text>ok</text>
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
                 var pop=weex.requireModule("centerpop")
                pop.show('center.js',{width:500,height:700},{},true);
            }

        },
        created:function(){


        }
    }
</script>