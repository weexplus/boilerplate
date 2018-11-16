<template>
    <div>
        <head title="测试"></head>
        <image @click="ok" style="width: 200px;height: 200px" src="root:img/load.gif"></image>
        <image @click="chat" style="width: 200px;height: 200px" src="root:img/load.gif"></image>

    </div>
</template>
<script>
  export default{
    props: {},
    data () {
      return {}
    },
    methods: {

      ok(){
         let im=weex.requireModule('netIm')
          im.login({account:'helloworld1',token:'7c2ad9cedfa2971efbbf58fbd1e67378'},(res)=>{
             this.alert(res)
          })
      },
      chat(){
        let im=weex.requireModule('netIm')
        im.openP2P({account:'helloworld1'})
      }
    },
    created () {
    }
  }
</script>
<style scoped>
</style>
