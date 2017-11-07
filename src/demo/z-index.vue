<template>
   <div class="wraper">
   

  
     <scroller class="scroller">
        <div>
          <div style="height:200px;background-color:yellow">
        
          <div style="flex:1;background-color:#000000;justify-content:center;align-items:center"><text style="color:#ffffff">宽度自适应</text></div>
        </div>
        <div class="d1">
        </div>
        <div class="d2">
        </div>
        <div class="d2" style="background-color:blue;height:3000px">
        <text></text>
        </div>
      </div>
         
    </scroller>
    
      <div class="fix" >
     </div>
     <div   class="mask">
     
     </div>
   </div>
</template>
<script>
   
</script>
<style scoped>
.mask
{
  background-color:rgba(0, 0, 0, 0.7);
    visibility:hidden;
  position:fixed;
     left:0px;
      right:0px;
     top:0px;
      bottom:0px;
}
.d1
  {
     height:100px;
    
      background-color:green;
  }
  .d2
  {
     height:100px;
     
      background-color:yellow;
  }
  .wraper
  {
     
      position:absolute;
      left:0px;
      right:0px;
      top:0px;
      bottom:0px;
     background-color:'#eeeeee';

  }
  .fix
  {
     height:100px;
     position:fixed;
     left:0px;
      right:0px;
     height:100px;
      bottom:0px;
      background-color:green;
  }
  .panel {
    width: 600px;
    height: 250px;
    margin-left: 75px;
    margin-top: 35px;
    margin-bottom: 35px;
    flex-direction: column;
    justify-content: center;
    border-width: 2px;
    border-style: solid;
    border-color: rgb(162, 217, 192);
    background-color: rgba(162, 217, 192, 0.2);
    visibility:hidden;
  }
  .text {
    font-size: 50px;
    text-align: center;
    color: #41B883;
  }
</style>