<template>
  <div>
      <head title="绝对路径"    append="tree">

      </head>

      <div style="align-items: center;margin-top: 50">
          <text style="color: #ffffff">以dist为根目录计算路径,"root:"等于dist,组件中的图片务必使用这个</text>
          <image style="width: 281;height: 217;margin-top: 20" src="root:img/fail.png"></image>
      </div>
      <a href="root:demo/demo/nav1.js" class="btn">
         <text style="color: white">跳转路径也可以用</text>
      </a>
      <div  @click="gotonext" class="btn">
          <text style="color: white">代码跳转</text>
      </div>

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
          gotonext()
          {
             var nav= weex.requireModule("navigator")
              nav.push('root:demo/demo/nav1.js')
          }



      }
     ,
      created:function(){

          var self=this;
          var globalEvent = weex.requireModule('globalEvent') ;
          globalEvent.addEventListener("onPageInit", function (e) {




          });
      }
  }
</script>