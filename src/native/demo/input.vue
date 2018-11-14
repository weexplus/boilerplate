<template>
    <div class="wrapper">
        <div class="transform" @click="goto">
            <text class="title">Transformed element</text>
        </div>
    </div>
</template>

<style>
    .transform {
        align-items: center;
        transform: translate(150px,200px) rotate(20deg);
        transform-origin: 0 -250px;
        border-color:red;
        border-width:2px;
    }
    .title {font-size: 48px;}
</style>
<script>

  export default{
    components: {},
    props: {},
    data () {
      return {

      }
    },
    methods: {
      goto(){
         let wechat=weex.requireModule('jpush')
          this.toast(wechat.getJPushId+'')
      },
      onLoad(){
        let navbar=weex.requireModule('navbar')
        navbar.setStatusBarStyle('black')
      }
    },
    mounted(){
    },
    created () {

    }
  }
</script>