<template>
  <div style="height: 90;">
    <div style="flex-direction: row;flex:1;align-items: center;justify-content: center;">
      <div v-for="(item,index) in items" @click="itemClick(index)" style="flex: 1;justify-content: center;">
        <div style="flex-direction: row;justify-content: center;">
          <!--<image v-if="item.src" style="width: 40;height: 40;margin-right: 10" :src="index==selectIndex?item.selectSrc:item.src"></image>-->
          <text  @click="itemClick(index)" v-if="item.src" style="margin-right: 3;font-family: erp;font-size: 45;margin-top: 0;" :style="{'color':index==selectIndex?selectedColor:textColor}" >{{item.src | getFontName}}</text>

          <text :ref="'text'+index" @click="itemClick(index)" style="font-size: 28;margin-top: 3"
              :style="{'color':index==selectIndex?selectedColor:textColor,'font-size':size}">{{item.text}}</text>

        </div>
      </div>
    </div>
    <div ref="line" :style="{'width':lineWidth,'margin-left':lineLeft,'background-color':selectedColor}"
        style="height: 5;"></div>
    <div style="height: 2;background-color: #E7E7E7"></div>
  </div>
</template>
<script>
    var he = require('he');
export default {
  props: {
    text: {
      type: String,

    },
    color: {
      type: String,

    },
    selectedColor: {
      default: '#007aff'
    },
      textColor: {
          default: '#8d8d8d'
      },
    disabled: {

      default: false
    },

    type: {
      type: String,
      default: 'text'
    },
    size: {
      default: 28
    },
    selectIndex: {
      default: 0
    },
    lineWidth: {
      default: 65
    },
    lineLeft: {
      default: 60
    },
    items: {
      default: []
    }

  },
  data() {
    return {

      visiable: true,

    }
  },
    filters:{
        getFontName: function(text) {
            return he.decode(text)
        }
    },
  methods: {

    move(index) {
      this.itemClick(index);
    },

    itemClick(index) {

      this.animateLine(this.selectIndex, index);
      this.selectIndex = index;
      this.$emit('change', this.selectIndex)
    },
    animateLine: function(fromindex, toindex) {
      if (fromindex == toindex) {
        return;
      }
      const animation = weex.requireModule('animation')

      var c = this.$refs.line;
      var delt = 750 / this.items.length;

      var p = c.style.transform;
      var from = (fromindex + 1) * delt;
      var to = (toindex) * delt;

//              modal.toast({ message: dis })

      animation.transition(c, {
        styles: {

          transform: "translateX( " + to + ")",
//                            transform: "translateX(150, 600)",
//                            transformOrigin:'x-axis:0'

        },

        duration: 100, //ms
        timingFunction: 'ease',
        delay: 0 //ms
      }, function() {

      })

    },

  },

  created: function() {

    this.visiable = !this.value == '';
      var font=weex.requireModule("font");
      font.addFont('erp','root:font/agriculture.ttf')

  },
  ready: function() {

  },
//        watch: {
//
//
//            selectIndex:{
//                immediate: true,
//                handler (val) {
//
//                }
//            }
//        }
}
</script>

<style scoped>



</style>
