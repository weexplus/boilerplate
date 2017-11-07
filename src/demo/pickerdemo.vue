<template>
  <div style="justify-content: center;align-items: center">
      <div style="align-items: center;justify-content: center;background-color: red;width: 200;height: 100">
         <text>显示</text>
	  </div>
  </div>

</template>

<style>

</style>

<script>
    const location =require('./../busi/util/location.js')

    var picker;
  export default {
    data () {
      return {
        imageList: [
          { src: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg'},
          { src: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg'},
          { src: 'https://gd3.alicdn.com/bao/uploaded/i3/TB1x6hYLXXXXXazXVXXXXXXXXXX_!!0-item_pic.jpg'}
        ]
      }
    },
      methods: {
          show(){
              if(picker==undefined)
              {
                  picker= location.show(function(e1,e2,e3){

                  })
              }
              picker.show();
          }
      }
  }
</script>