<template>
  <div class="header__left-btn" @click="handle">
    <image class="header__icon" v-if="icon" :src="icon"></image>
    <text class="header__text--left" :style="{color:color}" >{{text}}</text>
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
  flex-direction: row;
  flex: 1;
  align-items: center;
  justify-content: flex-start;
  box-sizing: border-box;
}
.header__text--left{
    color:#fff;
    font-size:28px;
}
.header__icon{
    width:44px;
    height:44px;
}
</style>