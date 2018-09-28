<template>
   <!--<scroller style="">-->
       <!--<swap :key="index" v-for="(item,index) in items" style="margin-top: 10px;height: 200px;width: 750px" :height="200" @delete="actionClick(index)">-->
           <!--<div   slot="content" style="background-color: #0088fb;flex: 1;">-->
              <!--<text>{{item}}</text>-->
           <!--</div>-->
       <!--</swap>-->
   <!--</scroller>-->
    <div style="background-color: red;flex: 1">
      <image  src="img/default.png" style="width: 200px;height: 200px;background-color: blue"></image>
    </div>
</template>
<style>
    .btn {
        width: 200px;
        height: 100px;
        background-color: red;
        margin-top: 20px;
    }


</style>

<script>


  export default {
    components: {},
    data()
    {
      return {
        items: [1, 2, 3, 4, 5, 6],
        show: false,
        text: ''
      }
    },
    methods: {
      actionClick(index){
        this.items.splice(index, 1);
      },
      change(){
        this.show = !this.show
      },
      add()
      {
//             this.show=!this.show;
        var t = weex.requireModule('navbar')
        t.setStatusBarStyle('black')
      },
      remove(item)
      {
//                this.items.remove(0)


      }
    },
    mounted(){
      let k=window._processImgSrc
//      alert(window.router)
//      alert(this.path.getImagePath('root:jk/lk.js'))
    },
    created: function () {

      var globalEvent = weex.requireModule('globalEvent');
      globalEvent.addEventListener("onPageInit", function (e) {

        var t = weex.requireModule('modal')
//                t.toast({message:'111'})

      });

    },
  }
</script>