<template>
    <scroller style="flex:1">
        <div>

         <div  class="btn" @click="pushwidthparam()"><text style="color:white" >push带参数</text></div>
          <text>返回参数：{{callbackdata}}</text>
          <div  class="btn" @click="present()"><text style="color:white" >present</text></div>


        </div>
    </scroller>
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
          callbackdata:''
      }
    }
    ,
      methods:{
        present(){
            var self=this;
            var nav = weex.requireModule('navigator') ;
            nav.presentFull('present.js',{a:'okoko'},true,function (e) {
                self.callbackdata=e.ok;
            },true);
        },
        pushwidthparam()
        {

            var self=this;
            var nav = weex.requireModule('navigator') ;
            nav.setPageId('navigator');
//            nav.pushParam('nav1.js',{a:'这是传过来的值'});
            nav.pushFull('nav1.js',{a:'这是传过来的值'},function (e) {
              self.callbackdata=e.ok;
            },true);
        }

      }
     ,
      created:function(){
          var globalEvent = weex.requireModule('globalEvent') ;
          globalEvent.addEventListener("onPageInit", function (e) {


              const nav = weex.requireModule('navbar');
              nav.setTitle('导航控制');
              nav.setBack(true);
              nav.setRightImage('img/scan.png',function(res){

                  var modal = weex.requireModule('modal') ;
                  modal.alert({message:"ok"})
              });


          });

      }
  }
</script>