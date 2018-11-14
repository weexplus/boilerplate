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
        let modal=weex.requireModule('modal')
        this.push('root:demo/index.js')
      }
    },
    created () {

    }
  }
</script>
<style scoped>
</style>
