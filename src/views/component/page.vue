<template>
    <div>
        <head :title="title"></head>
        <scroller style="flex: 1">
            <slot ></slot>
        </scroller>
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
