<template>
  <div class="wrapper">
    <m-header title="首页"></m-header>
    <div class="page-content">
      <scroller>
        <m-header title="电影" style="margin-top:20px;">
            <left-item v-on:handle="left" icon="http://www.mfancms.com/douban/images/ic_bar_back_white.png" slot='left'></left-item>
            <right-item v-on:handle="left" text="完成" slot='right'></right-item>
        </m-header>
        <m-header title="左侧文字" style="margin-top:20px;" >
            <left-item v-on:handle="left"  text="返回" slot='left'></left-item>
        </m-header>
        <m-header title="右侧文字" style="margin-top:20px;">
          <right-item v-on:handle="left" text="分享" slot='right'></right-item>
        </m-header>
        <m-header title="图标" style="margin-top:20px;">
            <left-item v-on:handle="left" icon="http://www.mfancms.com/douban/images/ic_bar_back_white.png" slot='left'></left-item>
            <right-item v-on:handle="left" icon="http://www.mfancms.com/douban/images/ic_action_overflow_white.png" text="完成" slot='right'></right-item>
        </m-header>
        <m-header title="多个按钮" style="margin-top:20px;">
            <left-item v-on:handle="left" icon="http://www.mfancms.com/douban/images/ic_bar_back_white.png" text="返回" slot='left'></left-item>
            <right-item v-on:handle="left" icon="http://www.mfancms.com/douban/images/ic_action_share.png" slot='right'></right-item>
            <right-item v-on:handle="left" icon="http://www.mfancms.com/douban/images/ic_status_action_all.png" slot='right'></right-item>
        </m-header>
        <m-header title="背景" bg="#f7f7f7" color="#333" style="margin-top:20px;">
            <left-item v-on:handle="left" color="#333" icon="http://www.mfancms.com/douban/images/ic_bar_back_green.png" text="返回" slot='left'></left-item>
            <right-item v-on:handle="left" icon="http://www.mfancms.com/douban/images/ic_action_share_green.png" slot='right'></right-item>
            <right-item v-on:handle="left" icon="http://www.mfancms.com/douban/images/ic_action_overflow_icon_green.png" slot='right'></right-item>
        </m-header>
        <input ref="input" style="margin-top:40px;" v-model="input" class="input" type="text" ></input>
        <input ref="input" style="margin-top:40px;" v-model="input" class="input" type="text" ></input>
        <input ref="input" style="margin-top:40px;" v-model="input" class="input" type="text" ></input>
        <input ref="input" style="margin-top:40px;" v-model="input" class="input" type="text" ></input>
        <input ref="input" style="margin-top:40px;" v-model="input" class="input" type="text" ></input>
        <input ref="input" style="margin-top:40px;" v-model="input" class="input" type="text" ></input>
        <input ref="input" style="margin-top:40px;" v-model="input" class="input" type="text" ></input>
        <input ref="input" style="margin-top:40px;" v-model="input" class="input" type="text" ></input>
        <input ref="input" style="margin-top:40px;" v-model="input" class="input" type="text" ></input>
        <input ref="input" style="margin-top:40px;" v-model="input" class="input" type="text" ></input>
        <input ref="input" style="margin-top:40px;" v-model="input" class="input" type="text" ></input>
        <input ref="input" style="margin-top:40px;" v-model="input" class="input" type="text" ></input>
        <input ref="input" style="margin-top:40px;" v-model="input" class="input" type="text" ></input>
      </scroller>
    </div>
  </div>
</template>



<script>
  
  var header =require('./components/header/heade.vue')
  var leftItem =require('./components/header/leftItem.vue')
  var rightItem =require('./components/header/rightItem.vue')
  const modal = weex.requireModule('modal')
  export default {
    data(){
      return{
        input:'123'
      }
      
    },
    components:{
      'mHeader':header,
      leftItem,
      rightItem
    },
    methods: {
      left(){
        modal.toast({ message: '点击了', duration: 1 })
      }
    }
  }
</script>
<style> 
body,html{
  margin: 0;
}
.page-content{
  flex: 1;
  background-color:#eee;
}
.input {
  font-size: 50px;
  width: 650px;
  margin-top: 50px;
  margin-left: 50px;
  padding-top: 20px;
  padding-bottom: 20px;
  padding-left: 20px;
  padding-right: 20px;
  color: #666666;
  border-width: 2px;
  border-style: solid;
  border-color: #41B883;
}


</style>