<template>
    <div style="position: absolute;top: 0;left: 0;right: 0;bottom: 0" @load="load" @show="show">
      <div style="flex: 1;">
          <slot style="flex: 1"></slot>
      </div>
    </div>
</template>
<script>
  export default{
    props: {},
    data () {
      return {}
    },
    methods: {
      load(param){
        this.$emit('load',param)
      },
      show(param){
        this.$emit('show',param)
      }
    },
    created () {
    }
  }
</script>
<style scoped>
</style>
