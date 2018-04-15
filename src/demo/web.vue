<template>
  <div>

      <head title="web"    append="tree">

      </head>
       <div class="btn" @click="load">
           <text>加载</text>
       </div>
      <div style="flex:1">
          <web :src="src" bounce="false" @pagestart="pagestart" @pagefinish="pagefinish"  @error="pagefinish" style="position: absolute;left: 0;top: 0;right: 0;bottom: 0;background-color: red">

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
            this.src='https://www.baidu.com'
          },
          pagestart()
          {
              progress.show()
          },
          pagefinish()
          {
              progress.dismiss()
          },

          save()
          {
              var pref=weex.requireModule("pref")
              pref.set('key',this.text);
              var modal=weex.requireModule("modal")
              modal.toast({message:'存储成功'});

          },

          get()
          {
              var pref=weex.requireModule("pref")
              var s= pref.get('key');
              var modal=weex.requireModule("modal")
              modal.toast({message:'存储成功的值'+s});
          },
          remove()
            {
                var pref=weex.requireModule("pref")
                pref.remove('key')
                pref.remove('objkey')
                var s= pref.get('key');
                var modal=weex.requireModule("modal")
                modal.toast({message:'删除成功：'+s});
            },
          saveObj()
          {
              var pref=weex.requireModule("pref")
              var obj={};
              obj.a=1;
              obj.b=2;
              pref.setObj('objkey',obj);
              var modal=weex.requireModule("modal")
              modal.toast({message:'存储成功'});
          },

          getObj()
          {
              var pref=weex.requireModule("pref")
              var p=  pref.getObj('objkey');
              this.data=p;
//              var modal=weex.requireModule("modal")
//              modal.toast({message:p});
          },

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