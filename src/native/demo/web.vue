<template>
  <div>

      <head title="web"    append="tree">

      </head>
       <div class="btn" @click="load">
           <text>加载</text>
       </div>
      <div class="btn" @click="goForward()">
          <text>前进</text>
      </div>
      <div class="btn" @click="goBack()">
          <text>后退</text>
      </div>
      <div style="flex:1">
          <web   :src="src" ref="web"  bounce="false"  :show-loading="false" style="position: absolute;left: 0;top: 0;right: 0;bottom: 0;background-color: red">

          </web>
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
    var progress=weex.requireModule("progress")
  export default {
      components:{head},
    data () {
      return {
        text: '',
          param:'',
          data:{},
          src:'https://www.baidu.com'
      }
    }
    ,
      methods:{
          load()
          {
//            this.src='https://www.baidu.com'
              this.src="<div>这是富文本<img src='https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=34f6ae03998fa0ec7fc7630b1eac3ed3/4034970a304e251fc3ec88c8af86c9177f3e53e2.jpg'></img></div>"

          },

          goForward()
          {
            this.$refs.web.goForward();
          },
          goBack()
          {
              this.$refs.web.goBack();
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