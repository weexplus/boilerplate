<template>
    <div>
        <!--<head title="测试"></head>-->
        <image @click="tenv" style="width: 200px;height: 200px" src="" placeholder="root:img/logo.png"  ></image>
        <!--<image @click="chat" style="width: 200px;height: 200px" src="root:img/load.gif"></image>-->
        <!--<slider class="slider" interval="3000" ref="slider" auto-play="true"  >-->
            <!--<div @click="gotoBannerDetail(item)"  class="frame" v-for="item in banners" >-->
                <!--<image class="image" resize="cover"    placeholder="root:img/default.png"  :src="item.banner_img"></image>-->
            <!--</div>-->
            <!--<indicator class="indicator"></indicator>-->
        <!--</slider>-->
    </div>
</template>
<script>
  export default{
    props: {},
    data () {
      return {}
    },
    methods: {

      tenv(){
        let device=weex.requireModule('device')
       this.toast( device.mac())
      },
      ok(){
         let im=weex.requireModule('nim')
          im.login({account:'helloworld1',token:'7c2ad9cedfa2971efbbf58fbd1e67378'},(res)=>{
             this.alert(res)
          })
      },
      chat(){
        let im=weex.requireModule('netIm')
        im.openP2P({account:'helloworld1'})
      }
    },
    created () {
    }
  }
</script>
<style scoped>
    .indicator
    {
        width:750px;
        height:316px;
        position:absolute;
        item-color:#777777;
        item-selected-color:#ffffff;
        item-size:20px;
        top:120px;
        left:1px;
    }
    .slider{width:750px;height:316px;}
    .frame{width:750px;height:316px;position:relative;}

    .image{width:750px;height:316px;}
</style>
