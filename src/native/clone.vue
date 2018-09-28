<template>

    <div style="flex: 1;width: 750px;justify-content: center;align-items: center;background-color: red">
        <text style="color:#000000">clone1</text>
        <div class="btn" @click="actionClick">
            <text class="btn-text">index1</text>
        </div>
        <div class="btn" @click="back">
            <text class="btn-text">返回</text>
        </div>
    </div>
</template>
<style>
    .btn {
        width: 200px;
        height: 100px;
        background-color: blue;
        margin-top: 20px;
        justify-content: center;
        align-items: center;
    }
    .btn-text{
        color: #ffffff;
    }

</style>

<script>

  import swap from './swapCell.vue'
  export default {
    components: {swap},
    data()
    {
      return {
        items: [1, 2, 3, 4, 5, 6],
        show: false,
        text: ''
      }
    },
    methods: {
      back(){
        var nav=weex.requireModule('navigator')
        nav.back()
      },
      actionClick(index){
        var nav=weex.requireModule('navigator')
        nav.push('index1.js')
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

    },
    created: function () {

      var globalEvent = weex.requireModule('globalEvent');
      globalEvent.addEventListener("onPageInit", function (e) {

//         alert(window._processImgSrc)

      });

    },
  }
</script>