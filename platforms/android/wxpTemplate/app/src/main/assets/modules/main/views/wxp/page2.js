// { "framework": "Vue"} 

!function(t){function e(o){if(n[o])return n[o].exports;var r=n[o]={i:o,l:!1,exports:{}};return t[o].call(r.exports,r,r.exports,e),r.l=!0,r.exports}var n={};e.m=t,e.c=n,e.d=function(t,n,o){e.o(t,n)||Object.defineProperty(t,n,{configurable:!1,enumerable:!0,get:o})},e.n=function(t){var n=t&&t.__esModule?function(){return t.default}:function(){return t};return e.d(n,"a",n),n},e.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},e.p="",e(e.s=563)}({465:function(t,e){t.exports={}},466:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={props:{},data:function(){return{txt:""}},methods:{goNext:function(){var t=this;this.$navigator.push({url:"/page3",param:{name:1,age:2}},function(e){t.txt=e})},goback:function(){this.$navigator.back({param:{xx:11,x2:222}})},onLoad:function(t){this.txt=t},onShow:function(){},onHide:function(){},onUnload:function(){}},created:function(){}}},467:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("page",{attrs:{title:"page2"}},[n("div",{staticStyle:{flex:"1"}},[n("div",{staticStyle:{flex:"1"}},[n("text",[t._v(t._s(t.txt))])]),n("h-center",[n("button",{attrs:{text:"跳转"},on:{onClick:function(e){t.goNext()}}}),n("button",{attrs:{text:"带参数返回"},on:{onClick:function(e){t.goback()}}})],1)],1)])},staticRenderFns:[]},t.exports.render._withStripped=!0},563:function(t,e,n){var o,r,s=[];s.push(n(465)),o=n(466);var i=n(467);r=o=o||{},"object"!=typeof o.default&&"function"!=typeof o.default||(Object.keys(o).some(function(t){return"default"!==t&&"__esModule"!==t})&&console.error("named exports are not supported in *.vue files."),r=o=o.default),"function"==typeof r&&(r=r.options),r.__file="/Users/zhengjiangrong/Desktop/work/cornerstone/ztTest/src/views/wxp/page2.vue",r.render=i.render,r.staticRenderFns=i.staticRenderFns,r._scopeId="data-v-6dc4cde8",r.style=r.style||{},s.forEach(function(t){for(var e in t)r.style[e]=t[e]}),"function"==typeof __register_static_styles__&&__register_static_styles__(r._scopeId,s),t.exports=o,t.exports.el="true",new Vue(t.exports)}});