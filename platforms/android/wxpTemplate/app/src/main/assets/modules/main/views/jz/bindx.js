// { "framework": "Vue"} 

!function(e){function t(r){if(n[r])return n[r].exports;var o=n[r]={i:r,l:!1,exports:{}};return e[r].call(o.exports,o,o.exports,t),o.l=!0,o.exports}var n={};t.m=e,t.c=n,t.d=function(e,n,r){t.o(e,n)||Object.defineProperty(e,n,{configurable:!1,enumerable:!0,get:r})},t.n=function(e){var n=e&&e.__esModule?function(){return e.default}:function(){return e};return t.d(n,"a",n),n},t.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},t.p="",t(t.s=512)}({446:function(e,t){e.exports={dot:{width:"200",height:"200",borderRadius:"100",backgroundColor:"#FF0000"}}},447:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=weex.requireModule("bindingx");t.default={props:{},data:function(){return{x:0,y:0}},methods:{ontouchstart:function(e){this.log("sss");var t=this;if(!0===this.isInAnimation)return console.log("we are in animation, drop pan gesture..."),void(0!=this.gesToken&&(r.unbind({eventType:"pan",token:t.gesToken}),this.gesToken=0));var n=this.$refs.my.ref,o="x+"+this.x,i='{"type":"+","children":[{"type":"Identifier","value":"x"},{"type":"NumericLiteral","value":'+this.x+"}]}",s="y+"+this.y,a='{"type":"+","children":[{"type":"Identifier","value":"y"},{"type":"NumericLiteral","value":'+this.y+"}]}";r.bind({anchor:n,eventType:"pan",props:[{element:n,property:"transform.translateX",expression:{transformed:i,origin:o}},{element:n,property:"transform.translateY",expression:{transformed:a,origin:s}}]},function(e){"end"===e.state&&(t.x+=e.deltaX,t.y+=e.deltaY)})},onLoad:function(e){},onShow:function(){},onHide:function(){},onUnload:function(){}},created:function(){}}},448:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("page",{attrs:{title:"bindingX"}},[n("div",{staticStyle:{flex:"1",flexDirection:"row",flexWrap:"wrap"}},[e._l([1,1,1,1,1,1],function(t){return n("div",{staticClass:["dot"],on:{touchstart:e.ontouchstart}})}),n("div",{ref:"my",staticClass:["dot"],staticStyle:{backgroundColor:"#00B4FF"},on:{touchstart:e.ontouchstart}})],2)])},staticRenderFns:[]},e.exports.render._withStripped=!0},512:function(e,t,n){var r,o,i=[];i.push(n(446)),r=n(447);var s=n(448);o=r=r||{},"object"!=typeof r.default&&"function"!=typeof r.default||(Object.keys(r).some(function(e){return"default"!==e&&"__esModule"!==e})&&console.error("named exports are not supported in *.vue files."),o=r=r.default),"function"==typeof o&&(o=o.options),o.__file="/Users/zhengjiangrong/Desktop/work/cornerstone/ztTest/src/views/jz/bindx.vue",o.render=s.render,o.staticRenderFns=s.staticRenderFns,o._scopeId="data-v-42a0d8b2",o.style=o.style||{},i.forEach(function(e){for(var t in e)o.style[t]=e[t]}),"function"==typeof __register_static_styles__&&__register_static_styles__(o._scopeId,i),e.exports=r,e.exports.el="true",new Vue(e.exports)}});