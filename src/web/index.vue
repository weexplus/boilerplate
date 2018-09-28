<template>
  <navigation>
  </navigation>
</template>
<script>
export default {
  components:{},
  name: 'App',
  data () {
    return {
      logo: 'https://gw.alicdn.com/tfs/TB1yopEdgoQMeJjy1XaXXcSsFXa-640-302.png',
    }
  },
  mounted(){
    this.$router.push('./index.js',{})
  }
}
</script>

<style scoped>
  .wrapper {
    justify-content: center;
    align-items: center;
    width: 750px;
    flex: 1;
  }
  .logo {
    width: 424px;
    height: 200px;
  }
  .greeting {
    text-align: center;
    margin-top: 70px;
    font-size: 50px;
    color: #41B883;
  }
  .message {
    margin: 30px;
    font-size: 32px;
    color: #727272;
  }
</style>
