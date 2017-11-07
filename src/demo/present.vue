<template>
  <div>


      <text>参数值：{{param}}</text>

      <div  class="btn" @click="dismiss()"><text style="color:white" >(dismiss);返回</text></div>
  </div>
 
</template>
<style>
  .text {
    font-size: 50;
  }

</style>
<style src="./css/style.css"></style>
<script>

  export default {
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


      }
     ,
      created:function(){

          var self=this;
          var globalEvent = weex.requireModule('globalEvent') ;
          globalEvent.addEventListener("onPageInit", function (e) {


              const nav = weex.requireModule('navbar');
              nav.setTitle('带参数页面');
              nav.setTitleColor('#ffffff');
              nav.setBack(true);
              nav.setBackgroundColor('#000000');
              nav.setRightImage('img/scan.png',function(res){

                  modal.alert({message:"ok"})
              });
              var navigator = weex.requireModule('navigator') ;
              self.param=navigator.param().a;


          });
      }
  }
</script>