// { "framework": "Vue"} 

!function(t){function e(n){if(r[n])return r[n].exports;var o=r[n]={i:n,l:!1,exports:{}};return t[n].call(o.exports,o,o.exports,e),o.l=!0,o.exports}var r={};e.m=t,e.c=r,e.d=function(t,r,n){e.o(t,r)||Object.defineProperty(t,r,{configurable:!1,enumerable:!0,get:n})},e.n=function(t){var r=t&&t.__esModule?function(){return t.default}:function(){return t};return e.d(r,"a",r),r},e.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},e.p="",e(e.s=551)}({340:function(t,e){t.exports={}},341:function(t,e,r){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={data:function(){return{items:[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]}},methods:{startLocal:function(){var t=this,e={};e.serverIp="121.41.140.236",e.anychat_user_name="8888",e.anychat_app_id="",e.port="8906",e.is_aiaudit=0,e.word_list=[{is_read:1,state_content:"我是张三自愿申请开户"}],this.doCall("obmbase.startLocalVideo",e,function(e){t.log(e)})},imageLoad:function(t){this.log(t)},onLoad:function(t){}}}},342:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("page",{attrs:{title:"test"}},[r("image",{staticStyle:{width:"500px",height:"500px"},attrs:{src:"https://ns-strategy.cdn.bcebos.com/ns-strategy/upload/fc_big_pic/part-00295-835.jpg"},on:{load:t.imageLoad}})])},staticRenderFns:[]},t.exports.render._withStripped=!0},551:function(t,e,r){var n,o,s=[];s.push(r(340)),n=r(341);var a=r(342);o=n=n||{},"object"!=typeof n.default&&"function"!=typeof n.default||(Object.keys(n).some(function(t){return"default"!==t&&"__esModule"!==t})&&console.error("named exports are not supported in *.vue files."),o=n=n.default),"function"==typeof o&&(o=o.options),o.__file="/Users/zhengjiangrong/Desktop/work/cornerstone/ztTest/src/views/test.vue",o.render=a.render,o.staticRenderFns=a.staticRenderFns,o._scopeId="data-v-3321b746",o.style=o.style||{},s.forEach(function(t){for(var e in t)o.style[e]=t[e]}),"function"==typeof __register_static_styles__&&__register_static_styles__(o._scopeId,s),t.exports=n,t.exports.el="true",new Vue(t.exports)}});