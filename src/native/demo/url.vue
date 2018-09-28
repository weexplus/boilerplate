<template>
  <div>
      <head title="url" :back="false"  append="tree">

      </head>
      <scroller>
          <a class="btn" href="./navigator.js">
            <text class="text">./navigator.js</text>
          </a>
          <a class="btn" href="root:demo/app.js">
              <text class="text">root:demo/app.js</text>
          </a>
          <div class="btn" @click="push">
              <text class="text">navigator.js</text>
          </div>
          <div class="btn" @click="pusha">
              <text class="text">./navigator.js</text>
          </div>
          <div class="btn" @click="pushn">
              <text class="text">../input.js</text>
          </div>
          <image src="../../img/default.png" style="width: 100;height: 100">

          </image>
          <image src="root:img/default.png" style="width: 100;height: 100">

          </image>
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
          push()
          {
              var navigator = weex.requireModule('navigator') ;
              navigator.push('navigator.js')
          },
          pusha()
          {
              var navigator = weex.requireModule('navigator') ;
              navigator.push('./navigator.js')
          },
          pushn()
          {
              var navigator = weex.requireModule('navigator') ;
              navigator.push('../input.js')
          }

      }
     ,
      created:function(){

          var self=this;
          var globalEvent = weex.requireModule('globalEvent') ;
          globalEvent.addEventListener("onPageInit", function (e) {

              var navigator = weex.requireModule('navigator') ;
              navigator.setPageId('index');
              navigator.setRoot('index');




          });
      }
  }
</script>