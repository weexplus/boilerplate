<template>
    <div style="align-items: center;justify-content: center">
        <button @click="show" text="show"></button>
    </div>
</template>
<script>
  export default{
    props: {},
    data () {
      return {}
    },
    methods: {
      show(){
        let zm=weex.requireModule('zoomImage')
        let ary=[];
        ary.push('https://drscdn.500px.org/photo/42393810/q=80_m=2000/fed5ccbf106c289f62e5762df92f1438')
        ary.push('https://drscdn.500px.org/photo/146441995/q=80_m=2000/0a6e687c0750ea05abf709bbd8c3d7f8')
        ary.push('https://drscdn.500px.org/photo/146512755/q=80_m=2000_k=1/62c584ed280fb11bbdb7d1c5451b6676')
        zm.show(ary)
      }
    },
    created () {
    }
  }
</script>
<style scoped>
</style>
