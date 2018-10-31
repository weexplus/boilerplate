<template>
    <page title="主页">
        <head style="sss"></head>
        <scroller style="flex: 1">
            <div style="flex-direction: row;flex-wrap: wrap">
                <button @Click="goto(item)" :width="220" style="margin-left: 25px;margin-top: 20px" :text="item.name" v-for="item in dx"></button>
            </div>
        </scroller>
    </page>
</template>
<script>
    import data from './navData'
  export default{
    components: {},
    props: {},
    data () {
      return {
        dx:data
      }
    },
    methods: {
      goto(item){
        this.toast(item.url)
//         this.push(item.url)
      },
      onLoad(){
        let navbar=weex.requireModule('navbar')
        navbar.setStatusBarStyle('black')
      }
    },
    mounted(){
      window.router
      debugger
    },
    created () {

    }
  }
</script>
<style scoped>

</style>
