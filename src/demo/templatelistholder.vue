<template>
    <div style="flex:1;">

    <tlist style="height: 800;width: 750">
        <cell class="cell" v-for="num in lists"  slot='left'>

            <div class="panel">

                <text class="text">{{num}}</text>
            </div>
        </cell>
    </tlist>
    </div>
  
</template>
<script>

var head =require('./head.vue')
var tlist =require('./vlist.vue')
var leftItem =require('./listitem.vue')

var globalEvent = weex.requireModule('globalEvent') ;
globalEvent.addEventListener("onPageInit", function (e) {
    const nav = weex.requireModule('navbar');
    nav.setTitle('模块列表');
    nav.setBack(true);
    nav.setRightImage('img/scan.png',function(res){

        modal.alert({message:"ok"})
    });
});
  export default {
     components:{head,tlist,leftItem},
    data () {
      return {
        lists: [1, 2, 3, 4, 5,8],

      }
    },
    methods: {

    },     
    created:function(){
     
    }
  }
</script>
<style scoped>

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
 