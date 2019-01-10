<template>
    <div style="">
    </div>
</template>
<script>
  export default{
    props: {},
    data () {
      return {}
    },
    methods: {
      onLoad(){
        let navigator=weex.requireModule('navigator')
        navigator.presentFull({url:'root:index.js',animated:false})
      }
    },
    created () {

    }
  }
</script>
<style scoped>
</style>
