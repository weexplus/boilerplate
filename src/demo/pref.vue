<template>
  <div>

      <head title="固定存储类似于cookie"    append="tree">

      </head>
      <input type="text" v-model="text" style="width: 750;padding-left: 10;height: 100;color: #000000" placeholder="请输入要存储的值"/>
    <text style="color: #000000">{{data}}</text>
    <div class="btn" @click="save">
        <text style="color: #ffffff">存储字符串</text>
    </div>

    <div class="btn" @click="get">
        <text style="color: #ffffff">获取字符串</text>
    </div>
      <div class="btn" @click="remove">
          <text style="color: #ffffff">删除</text>
      </div>
    <div class="btn" @click="saveObj">
        <text style="color: #ffffff">存储对象{a:1}</text>
    </div>

    <div class="btn" @click="getObj">
        <text style="color: #ffffff">获取对象</text>
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
        text: '',
          param:'',
          data:{}
      }
    }
    ,
      methods:{
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