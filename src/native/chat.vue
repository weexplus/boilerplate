<template>
    <div style="align-items: center;justify-content: center">
        <!--<head title="测试"></head>-->
        <button @click="testWatch" style="margin-top: 20px"  text="helloworld1"  ></button>
        <!--<button @click="chat11" style="margin-top: 20px"  text="11"  ></button>-->
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
      return {
        name:''
      }
    },
    watch:{
      name(n,o){
        this.toast(o)
      }
    },
    methods: {

      testWatch(){
        this.name=this.name+'xxx'
      },
      chathello(){
        let im = weex.requireModule('nim')
        let p={account: 'helloworld1'}
        p.navBarBgColor='#ff553e'
        p.theme='white'
        im.openP2P(p)
      },

      onLoad(){
        let im = weex.requireModule('nim')
        im.regist({appKey:'86984f84905337af232230f9e140b113'})
        im.login({account: '11', token: 'd42846b9d9ff0a4092bb3625203f3ed1'}, (res) => {
          this.alert('登录成功')

        })
      }

    },
    created () {
    }
  }
</script>
<style scoped>
    .indicator {
        width: 750px;
        height: 316px;
        position: absolute;
        item-color: #777777;
        item-selected-color: #ffffff;
        item-size: 20px;
        top: 120px;
        left: 1px;
    }

    .slider {
        width: 750px;
        height: 316px;
    }

    .frame {
        width: 750px;
        height: 316px;
        position: relative;
    }

    .image {
        width: 750px;
        height: 316px;
    }
</style>
