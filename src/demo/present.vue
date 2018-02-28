<template>
  <div>

      <head title="弹出式导航" @backClick="back"    append="tree">

      </head>
      <div style="width: 750;height: 600;align-items: center;justify-content: center">
          <text style="color: #ffffff">参数值：{{param}}</text>
          <text style="color: #ffffff">弹出式导航关闭时只能用dismiss,不能用back</text>
          <div  class="btn" @click="dismiss()"><text style="color:white" >(dismiss);返回</text></div>
      </div>

  </div>
 
</template>
<style>
  .text {
    font-size: 50;
  }

</style>
<style src="./style.css"></style>
<script>
    var head =require('./header.vue')
  export default {
      components:{head},
    data () {
      return {
        text: 'Hello World.',
          param:''
      }
    }
    ,
      methods:{
        ok()
        {

           this.param="dsds";
        },

          dismiss()
          {
              var navigator = weex.requireModule('navigator') ;
              navigator.dismissFull({ok:this.param},true);
          }
          ,
          back()
          {
              var navigator = weex.requireModule('navigator') ;
              navigator.dismiss();
          }


      }
     ,
      created:function(){

          var self=this;
          var globalEvent = weex.requireModule('globalEvent') ;
          globalEvent.addEventListener("onPageInit", function (e) {


              const nav = weex.requireModule('navbar');

              var navigator = weex.requireModule('navigator') ;
              self.param=navigator.param().a;


          });
      }
  }
</script>