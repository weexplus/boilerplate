<template>
  <div class="header" :class="{'is_fixed':isFixed}" :style="{backgroundColor:bg}">
    <div class="header__left">
      <slot name="left"></slot>
    </div>
    <div class="header__title"><text class="header__text" :style="{color:color}">{{title}}</text></div>
    <div class="header__right">
      <slot name="right"></slot>
    </div>
    
  </div>
</template>
<script>
  
  export default {
    name:'header',
    props:{
        title:String,
        isFixed:Boolean,
        bg:{
          type:String,
          default:'#007aff'
        },
        color:{
          type:String,
          default:'#fff'
        }
    },
    components:{
      
    },
    data(){
      return{
      }
    },
    methods: {
      
    }
  }
</script>
<style>
.header{
  height:88px;
  background-color:#007aff;
  flex-direction:row;
  align-items: stretch;
  box-sizing: border-box;
  padding-left:20px;
  padding-right:20px;
}
.header__title{
  flex:1;
  align-items: center;
  justify-content: center;

}
.header__left{
  flex-direction: row;
  flex: 0.5;
  align-items: center;
  justify-content: flex-start;
  box-sizing: border-box;
}
.header__right{
  flex-direction: row;
  flex: 0.5;
  align-items: center;
  justify-content: flex-end;
  box-sizing: border-box;
}
.header__rightbox{
  flex-direction: row;
  flex: 1;
  align-items: center;
  justify-content: flex-end;
  box-sizing: border-box;
}
.header__text{
    color:#fff;
    font-size: 34px;
    lines:1;
}
.is_fixed{
  /*position:fixed;
  left: 0;
  right: 0;
  top:0;*/

}
</style>