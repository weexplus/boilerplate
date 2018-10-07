<template>
    <page title="主页">
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
      }
    },
    created () {
    }
  }
</script>
<style scoped>

</style>
