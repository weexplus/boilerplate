// { "framework": "Vue"} 

!function(t){function e(r){if(n[r])return n[r].exports;var o=n[r]={i:r,l:!1,exports:{}};return t[r].call(o.exports,o,o.exports,e),o.l=!0,o.exports}var n={};e.m=t,e.c=n,e.d=function(t,n,r){e.o(t,n)||Object.defineProperty(t,n,{configurable:!1,enumerable:!0,get:r})},e.n=function(t){var n=t&&t.__esModule?function(){return t.default}:function(){return t};return e.d(n,"a",n),n},e.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},e.p="",e(e.s=534)}({412:function(t,e){t.exports={input:{width:"700",height:"100",borderWidth:"1",borderColor:"#00B4FF",marginBottom:"20",paddingLeft:"10"}}},413:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={props:{},data:function(){return{url:"http://10.20.39.79/h5obh/",user:"",pwd:"",business:"",phone:""}},methods:{test:function(){var t={};t.account=this.user,t.pwd=this.pwd,t.business=this.business,t.mobile_tel=this.phone;var e={};e.user=t,e.url=this.url,this.doCall("obmbase.testSdk",e,function(t){})},onLoad:function(t){},onShow:function(){},onHide:function(){},onUnload:function(){}},created:function(){}}},414:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("page",{attrs:{title:"sdk接入模拟"}},[n("scroller",{staticStyle:{flex:"1",alignItems:"center",paddingTop:"20px"}},[n("input",{staticClass:["input"],attrs:{placeholder:"地址",value:t.url},on:{input:function(e){t.url=e.target.attr.value}}}),n("input",{staticClass:["input"],attrs:{placeholder:"用户名",value:t.user},on:{input:function(e){t.user=e.target.attr.value}}}),n("input",{staticClass:["input"],attrs:{placeholder:"密码",value:t.pwd},on:{input:function(e){t.pwd=e.target.attr.value}}}),n("input",{staticClass:["input"],attrs:{placeholder:"业务类别",value:t.business},on:{input:function(e){t.business=e.target.attr.value}}}),n("input",{staticClass:["input"],attrs:{placeholder:"手机号码",value:t.phone},on:{input:function(e){t.phone=e.target.attr.value}}}),n("text",[t._v(t._s(t.txt))])]),n("button",{staticStyle:{marginBottom:"20px"},attrs:{width:700,text:"提交"},on:{onClick:function(e){t.test()}}})],1)},staticRenderFns:[]},t.exports.render._withStripped=!0},534:function(t,e,n){var r,o,s=[];s.push(n(412)),r=n(413);var u=n(414);o=r=r||{},"object"!=typeof r.default&&"function"!=typeof r.default||(Object.keys(r).some(function(t){return"default"!==t&&"__esModule"!==t})&&console.error("named exports are not supported in *.vue files."),o=r=r.default),"function"==typeof o&&(o=o.options),o.__file="/Users/zhengjiangrong/Desktop/work/cornerstone/ztTest/src/views/normal/sdk.vue",o.render=u.render,o.staticRenderFns=u.staticRenderFns,o._scopeId="data-v-ae8c0ec6",o.style=o.style||{},s.forEach(function(t){for(var e in t)o.style[e]=t[e]}),"function"==typeof __register_static_styles__&&__register_static_styles__(o._scopeId,s),t.exports=r,t.exports.el="true",new Vue(t.exports)}});