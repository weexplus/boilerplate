<template>
  <div>


      <text>参数值：{{param}}</text>
      <div  class="btn" @click="back()"  ><text style="color:white" > navigator.backFull({ok:this.param},true) 带参数返回</text></div>
      <div  class="btn" @click="backto()"  ><text style="color:white" >（navigator.backTo('index');跳跃返回</text></div>

  </div>
 
</template>
<style>
  .text {
    font-size: 50;
  }

  .btn{


      background-color:#0085ee;
      height:100;

      margin-top:50;
      margin-left: 50;
      margin-right: 50;
      border-radius:10;
      align-items:center;
      justify-content:center;

  }
  .btn:active{background-color:#006ce7;}
</style>

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
          back()
          {
              var navigator = weex.requireModule('navigator') ;
              navigator.backFull({ok:this.param},true);
          }
          ,backto()
          {
              var navigator = weex.requireModule('navigator') ;
              navigator.backTo('index');
          },

      }
     ,
      created:function(){

          var self=this;
          var globalEvent = weex.requireModule('globalEvent') ;
          globalEvent.addEventListener("onPageInit", function (e) {


              const nav = weex.requireModule('navbar');
              nav.setTitle('带参数页面');
              nav.setBack(true);

              nav.setRightImage('img/scan.png',function(res){

                  modal.alert({message:"ok"})
              });
              var navigator = weex.requireModule('navigator') ;
              self.param=navigator.param().a;
              navigator.setPageId('nav1');


          });
      }
  }
</script>