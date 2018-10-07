<template>
    <drawerlayout :leftWidth="500" style="width:750px;flex: 1;">
        <wraper slot="drawer" >
            <!--<div style="background-color: #0088fb"></div>-->
            <div  style="background-color: red;width: 500px;flex: 1"><text>ssss</text></div>
        </wraper>
        <wraper slot="content" >
            <div  style="background-color: #81ff57;flex: 1;width: 750px;"><text>xxxx</text></div>
        </wraper>
    </drawerlayout>
</template>
<script>
    let wraper =require('../../component/wraper.vue')
  export default{
    components: {wraper},
    props: {},
    data () {
      return {}
    },
    methods: {},
    created () {
    }
  }
</script>
<style scoped>
</style>
