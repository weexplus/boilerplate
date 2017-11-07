
<template>
  <div class="wrapper header">
     <div class="leftview" @click="leftclick">
      <image  resize="contain"  class="leftimage" src="img/backarrow.png" ></image>
     </div>
      <text class="title">{{title}}</text>
      <div class="rightview" @click="rightClick">
      <image class="rightimage"  resize="contain"  src="img/scan.png"  ></image>

     </div>
 </div>
</template>
 
 <style  type="stylus">
 .header{
    background-color:#66CCCC;
    align-items:center;
    justify-content:center;
    flex-direction:row;

  }
  .leftview
  {
       width:120;
       height:135;
       
         justify-content:center;
       
       position:absolute;
       left:0;
  }
  .rightview
  {
       width:120;
       height:135;
       justify-content:center;
      
       position:absolute;
       right:0;
  }
  .wrapper{
    
  height:135;
  }
  .titleback
  {
     flex:1;
     align-items:center;
     
  }
  .title
  {
    color:white;
    
    margin-top:40;
    font-weight:bold;

  }
  .leftimage
  {
     width:30;
     height:45;
     margin-left:20;
      margin-top:40;
      
  }
  .rightimage
  {
     width:45;
     height:45;
      
     margin-top:30;
       margin-left:45;
    
  }
  .bottomline
  {
    height:1;
    background-color:#000000;
    position:absolute;
    bottom:0;
    left:0;
    right:0;
    flex:1;

  }
 </style>
<script>
     

   export default {
     props:{
       title:
       {
          type:String,
       },
       leftClick:{}
       
     },
    data () {
      return {
        title:'',
      }
    },
    methods: {
       leftclick()
       {
          const nav = weex.requireModule('navigation');
          nav.pop();
            
       },
       rightClick()
       {            
          
             this.$parent.$emit('rightClick', this.id);

       
       }
    },
    created:function(){
     
    }
   }
</script>