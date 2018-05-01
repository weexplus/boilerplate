<template>
  <div>
      <head title="demo" :back="false"  append="tree">

      </head>
      <scroller>
           <text> {{param}}</text>
          <a class="btn" href="./navigator.js">
          <text class="text">导航</text>
          </a>
          <a class="btn" href="./notify.js">
              <text class="text">通知</text>
          </a>
          <a class="btn" href="./photo.js">
              <text class="text">相机/相册</text>
          </a>
          <a class="btn" href="./net.js">
              <text class="text">网络请求</text>
          </a>
          <a class="btn" href="./pref.js">
              <text class="text">固定存储</text>
          </a>
          <a class="btn" href="./static.js">
              <text class="text">内存存储（static全app变量）</text>
          </a>
          <a class="btn" href="./root.js">
              <text class="text">绝对路径</text>
          </a>
      </scroller>
  </div>
 
</template>
<style>
  .text {
    font-size: 50;
      color:#ffffff;
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

              var modal = weex.requireModule('modal') ;
              modal.toast({message:'home init'})


          });
      }
  }
</script>