<template>
  <div style="flex-direction: column;background-color: red">
    <head ></head>
    <tab ref="tab" :tabItems="tabItems" @tabBarOnClick="tabBarOnClick"></tab>
  </div>
</template>

<script>

var tab =require('./tabbar.vue')
var head =require('./head.vue')
    export default{
        components:{tab,head},
    data () {
      return {

        tabItems: [
          {
            index: 0,
            title: 'tab1',
            titleColor: '#000000',
            icon: '',
            image: 'img/tab1_un.png',
            selectedImage: 'img/tab1.png',
            src: 'index.js',
            visibility: 'visible',
          },
          {
            index: 1,
            title: 'tab2',
            titleColor: '#000000',
            icon: '',
            image: 'img/tab2_un.png',
            selectedImage: 'img/tab2.png',
            src: 'wechat.js',
            visibility: 'hidden',
          },
          {
            index: 2,
            title: 'tab3',
            titleColor: '#000000',
            icon: '',
              image: 'img/tab3_un.png',
              selectedImage: 'img/tab3.png',
            src: 'recycler.js',
            visibility: 'hidden',
          }
        ],
      }
    },

    created: function() {



      var baseURL = "http://192.168.2.124:9898/";
      for(var i = 0; i < this.tabItems.length; i++) {
        var tabItem = this.tabItems[i];
        tabItem.src = baseURL + tabItem.src;
      }

        var self=this;
        var globalEvent = weex.requireModule('globalEvent') ;
        globalEvent.addEventListener("onPageInit", function (e) {
            const nav = weex.requireModule('navbar');
            nav.setTitle('tab');
            var navigator = weex.requireModule('navigator') ;
            var t=  self.$refs.tab;
            t.load(1);
//            t.load(2);
        });
    },
    activated:function()
    {

    },
    methods: {
      tabBarOnClick: function (e) {
        console.log('tabBarOnClick', e.index)
      }
    }
  }
</script>
