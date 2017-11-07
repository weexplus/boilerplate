<template>
  <div class="header__btn" @click="handle">
    <image class="header__icon"  v-if="icon" :src="icon"></image>
    <text  class="header__text--right" :style="{color:color}" v-else>{{text}}</text>
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

.header__btn{
  padding-left:20px;
  flex-direction: row;
  align-items: center;
  justify-content: flex-end;
  box-sizing: border-box;
}
.header__text--right{
    color:#fff;
    font-size:28px;
}
.header__icon{
    width:44px;
    height:44px;
}
</style>