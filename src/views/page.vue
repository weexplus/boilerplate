<template>
    <div>
        <head :title="title"></head>
        <slot style="flex: 1"></slot>
    </div>
</template>
<script>

  export default{
    components:{},
    props: {
      title:{
        default:'sss'
      }
    },
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
