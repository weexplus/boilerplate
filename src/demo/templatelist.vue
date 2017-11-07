<template>
    <div style="flex:1">

    <list class="list" layout="2" @loadmore="fetch" >
        <slot name="left"></slot>
  </list>
    </div>
  
</template>
<script>

const modal = weex.requireModule('modal')
const LOADMORE_COUNT = 10

  export default {
     components:{},
    data () {
      return {
        lists: [1, 2, 3, 4, 5,8],
         lists1: [1]
      }
    },
    methods: {
      fetch (event) {
//        modal.toast({ message: 'loadmore', duration: 1 })

          const length = this.lists.length
          for (let i = length; i < length + LOADMORE_COUNT; ++i) {
            this.lists.push(i + 1)
          }

      }
    },     
    created:function(){
     
    }
  }
</script>
<style scoped>
  .hd
  {
    height:200;
    top:0;
    left:0;
    width:200;
    background-color:blue;
    position:fixed;
  }
  .panel1 {
    
    height: 250px;
    margin-left: 15px;
     margin-right:15px;
    margin-top: 35px;
    
    margin-bottom: 35px;
    flex-direction: column;
    justify-content: center;
    border-width: 2px;
    border-style: solid;
    border-color: red;
    background-color: rgba(162, 217, 192, 0.2);
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
  }
  .text {
    font-size: 50px;
    text-align: center;
    color: #41B883;
  }
</style>
 