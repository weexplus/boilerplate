<template>
    <div>
        <host style="flex: 1;">
          <div @load="load" style="background-color: blue"></div>
          <div style="background-color: red"></div>
        </host>
    </div>
</template>
<script>
  export default{
    props: {
      index:{
        default:0
      }
    },
    data () {
      return {}
    },
    methods: {
      load(){
        alert('true')
      }
    },
    created () {
    }
  }
</script>
<style scoped>
</style>
