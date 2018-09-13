<template>
    <div style="position: absolute;top: 0;left: 0;right: 0;bottom: 0">
      <div style="flex: 1;background-color: red">
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
    methods: {},
    created () {
    }
  }
</script>
<style scoped>
</style>
