// { "framework": "Vue"} 

!function(t){function e(r){if(n[r])return n[r].exports;var i=n[r]={i:r,l:!1,exports:{}};return t[r].call(i.exports,i,i.exports,e),i.l=!0,i.exports}var n={};e.m=t,e.c=n,e.d=function(t,n,r){e.o(t,n)||Object.defineProperty(t,n,{configurable:!1,enumerable:!0,get:r})},e.n=function(t){var n=t&&t.__esModule?function(){return t.default}:function(){return t};return e.d(n,"a",n),n},e.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},e.p="",e(e.s=556)}({349:function(t,e){t.exports={input:{width:"700",height:"100",borderWidth:"1",borderColor:"#00B4FF",marginBottom:"20",paddingLeft:"10"}}},350:function(t,e,n){"use strict";function r(t,e,n){return e in t?Object.defineProperty(t,e,{value:n,enumerable:!0,configurable:!0,writable:!0}):t[e]=n,t}Object.defineProperty(e,"__esModule",{value:!0}),e.default={props:{},data:function(){var t;return t={ip:"10.23.2.72"},r(t,"ip","shipintest-dev-cstest.fzzqft.com"),r(t,"ip","27.115.76.52"),r(t,"port",9610),r(t,"port",8906),r(t,"port",9906),r(t,"serverPass",""),r(t,"userPwd",""),r(t,"txt","1111"),r(t,"videoTimeLimit","3,10"),r(t,"stateContent","我是张三自愿申请开户"),t},methods:{onLoad:function(t){},onShow:function(){},onHide:function(){},onUnload:function(){},testOneway:function(){var t=this,e={};e.appId="",e.userName="1111",e.serverIp=this.ip,e.port=this.port,e.EmpId="-1",e.userPwd=this.userPwd,e.serverAuthPass=this.serverPass,e.roomId="",e.type=1,e.videoMode=1,e.stateContent=this.stateContent,e.videoTimeLimit=this.videoTimeLimit,this.doCall("obmbase.initVideo",e,function(e){t.txt+=JSON.stringify(e),t.log(e)})}},created:function(){}}},351:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("page",{attrs:{title:"单向视频"}},[n("div",{staticStyle:{flex:"1",alignItems:"center"}},[n("scroller",{staticStyle:{flex:"1",alignItems:"center",paddingTop:"20px"}},[n("input",{staticClass:["input"],attrs:{placeholder:"ip",value:t.ip},on:{input:function(e){t.ip=e.target.attr.value}}}),n("input",{staticClass:["input"],attrs:{placeholder:"port",value:t.port},on:{input:function(e){t.port=e.target.attr.value}}}),n("input",{staticClass:["input"],attrs:{placeholder:"连接密码",value:t.serverPass},on:{input:function(e){t.serverPass=e.target.attr.value}}}),n("input",{staticClass:["input"],attrs:{placeholder:"登录密码",value:t.userPwd},on:{input:function(e){t.userPwd=e.target.attr.value}}}),n("input",{staticClass:["input"],attrs:{placeholder:"客户朗读话术",value:t.stateContent},on:{input:function(e){t.stateContent=e.target.attr.value}}}),n("input",{staticClass:["input"],attrs:{placeholder:"时间限制",value:t.videoTimeLimit},on:{input:function(e){t.videoTimeLimit=e.target.attr.value}}}),n("text",[t._v(t._s(t.txt))])]),n("button",{staticStyle:{marginBottom:"20px"},attrs:{width:700,text:"提交"},on:{onClick:function(e){t.testOneway()}}})],1)])},staticRenderFns:[]},t.exports.render._withStripped=!0},556:function(t,e,n){var r,i,o=[];o.push(n(349)),r=n(350);var s=n(351);i=r=r||{},"object"!=typeof r.default&&"function"!=typeof r.default||(Object.keys(r).some(function(t){return"default"!==t&&"__esModule"!==t})&&console.error("named exports are not supported in *.vue files."),i=r=r.default),"function"==typeof i&&(i=i.options),i.__file="/Users/zhengjiangrong/Desktop/work/cornerstone/ztTest/src/views/video/oneway.vue",i.render=s.render,i.staticRenderFns=s.staticRenderFns,i._scopeId="data-v-456913b0",i.style=i.style||{},o.forEach(function(t){for(var e in t)i.style[e]=t[e]}),"function"==typeof __register_static_styles__&&__register_static_styles__(i._scopeId,o),t.exports=r,t.exports.el="true",new Vue(t.exports)}});