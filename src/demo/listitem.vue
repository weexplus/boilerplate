<template>
  <div class="header__left-btn" @click="handle">

    <text class="header__text--left"   >{{title}}</text>
  </div>
</template>
<script>

  export default {
    name:'leftItem',
    props:{
        title:String,
        icon:String,
        text:{
          type:String,
          default:''
        },
        color:{
          type:String,
          default:'#fff'
        }
    },
    data(){
      return{

      }
      
    },
    methods: {
      handle(){
        this.$emit('handle')
      }
    }
  }
</script>
<style>

.header__left-btn{
height: 100;
  width: 750;
}
.header__text--left{
    color:#000;
    font-size:28px;
}
.header__icon{
    width:44px;
    height:44px;
}
</style>