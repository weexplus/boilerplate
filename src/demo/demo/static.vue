<template>
  <div>

      <head title="内存存储"    append="tree">

      </head>
      <div style="width: 160;height: 160;border-radius: 80;margin-left: 20">
          <image src="root:img/cat.png" style="border-radius: 80;position: absolute;left: 0;right: 0;bottom: 0;top: 0;"></image>
      </div>
      <input type="text" v-model="text" style="width: 750;padding-left: 10;height: 100;color: #000000" placeholder="请输入要存储的值"/>
    <text style="color: #ffffff">{{data}}</text>
    <div class="btn" @click="save">
        <text style="color: #ffffff">存储字符串</text>
    </div>

    <div class="btn" @click="get">
        <text style="color: #ffffff">获取字符串</text>
    </div>
    <div class="btn" @click="saveObj">
        <text style="color: #ffffff">存储对象{a:1}</text>
    </div>

    <div class="btn" @click="getObj">
        <text style="color: #ffffff">获取对象</text>
    </div>

      <div class="btn" @click="gonext">
          <text style="color: #ffffff">跳转到下一个界面</text>
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
              var pref=weex.requireModule("static")
//              pref.setString('key',this.text);
              pref.setString('key',11111);
              var modal=weex.requireModule("modal")
              modal.toast({message:'存储成功'});

          },

          get()
          {
              var pref=weex.requireModule("static")
              var s= pref.getString('key');
              var modal=weex.requireModule("modal")
              modal.toast({message:'存储成功的值:'+s});
          },
          saveObj()
          {
              var pref=weex.requireModule("static")
              var obj={};
              obj.a=1;
              obj.b=2;
              pref.set('objkey',obj);
              var modal=weex.requireModule("modal")
              modal.toast({message:'存储成功'});
          },
          gonext()
          {
              var nav=weex.requireModule("navigator")
              nav.push('static1.js');
          },

          getObj()
          {
              var pref=weex.requireModule("static")
              var p=  pref.get('objkey');
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