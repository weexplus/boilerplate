<template>
  <div>

      <head title="导航子界面"    append="tree">

      </head>
      <div style="width: 750;align-items: center;margin-top: 50">
          <text style="color: #000000;">传过来的参数值：{{param}}</text>
      </div>

      <div  class="btn" @click="back()"  ><text style="color:white" > navigator.backFull({ok:this.param},true) 带参数返回</text></div>
      <div  class="btn" @click="backto()"  ><text style="color:white" >（navigator.backTo('index');夸页返回</text></div>

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
          back()
          {
              var navigator = weex.requireModule('navigator') ;
              navigator.backFull({ok:'这是回传的值'},true);
          }
          ,backto()
          {
              var navigator = weex.requireModule('navigator') ;
              navigator.backTo('root');
          },

      }
     ,
      created:function(){

          var self=this;
          var globalEvent = weex.requireModule('globalEvent') ;
          globalEvent.addEventListener("onPageInit", function (e) {


              var navigator = weex.requireModule('navigator') ;
              self.param=navigator.param().a;
              navigator.setPageId('nav1');


          });
      }
  }
</script>