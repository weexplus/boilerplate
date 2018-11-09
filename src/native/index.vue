<template>
    <div style="background-color: red;width: 750px;height: 1000px">
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
        modal.toast({message:'sss'})
        modal.toast({message:this.push+''})
        this.push('root:demo/index.js')
      }
    },
    created () {

    }
  }
</script>
<style scoped>
</style>
