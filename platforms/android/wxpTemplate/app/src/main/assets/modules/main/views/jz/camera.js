// { "framework": "Vue"} 

!function(t){function e(n){if(r[n])return r[n].exports;var o=r[n]={i:n,l:!1,exports:{}};return t[n].call(o.exports,o,o.exports,e),o.l=!0,o.exports}var r={};e.m=t,e.c=r,e.d=function(t,r,n){e.o(t,r)||Object.defineProperty(t,r,{configurable:!1,enumerable:!0,get:n})},e.n=function(t){var r=t&&t.__esModule?function(){return t.default}:function(){return t};return e.d(r,"a",r),r},e.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},e.p="",e(e.s=513)}({248:function(t,e,r){"use strict";t.exports=function(t){return t.webpackPolyfill||(t.deprecate=function(){},t.paths=[],t.children||(t.children=[]),Object.defineProperty(t,"loaded",{enumerable:!0,get:function(){return t.l}}),Object.defineProperty(t,"id",{enumerable:!0,get:function(){return t.i}}),t.webpackPolyfill=1),t}},249:function(t,e,r){"use strict";t.exports=r(250)},250:function(t,e,r){"use strict";var n=function(){return this}()||Function("return this")(),o=n.regeneratorRuntime&&Object.getOwnPropertyNames(n).indexOf("regeneratorRuntime")>=0,i=o&&n.regeneratorRuntime;if(n.regeneratorRuntime=void 0,t.exports=r(251),o)n.regeneratorRuntime=i;else try{delete n.regeneratorRuntime}catch(t){n.regeneratorRuntime=void 0}},251:function(t,e,r){"use strict";(function(t){var e="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(t){return typeof t}:function(t){return t&&"function"==typeof Symbol&&t.constructor===Symbol&&t!==Symbol.prototype?"symbol":typeof t};!function(r){function n(t,e,r,n){var o=e&&e.prototype instanceof i?e:i,a=Object.create(o.prototype),c=new d(n||[]);return a._invoke=f(t,r,c),a}function o(t,e,r){try{return{type:"normal",arg:t.call(e,r)}}catch(t){return{type:"throw",arg:t}}}function i(){}function a(){}function c(){}function u(t){["next","throw","return"].forEach(function(e){t[e]=function(t){return this._invoke(e,t)}})}function s(t){function r(n,i,a,c){var u=o(t[n],t,i);if("throw"!==u.type){var s=u.arg,f=s.value;return f&&"object"===(void 0===f?"undefined":e(f))&&w.call(f,"__await")?Promise.resolve(f.__await).then(function(t){r("next",t,a,c)},function(t){r("throw",t,a,c)}):Promise.resolve(f).then(function(t){s.value=t,a(s)},c)}c(u.arg)}function n(t,e){function n(){return new Promise(function(n,o){r(t,e,n,o)})}return i=i?i.then(n,n):n()}var i;this._invoke=n}function f(t,e,r){var n=j;return function(i,a){if(n===S)throw new Error("Generator is already running");if(n===k){if("throw"===i)throw a;return v()}for(r.method=i,r.arg=a;;){var c=r.delegate;if(c){var u=l(c,r);if(u){if(u===F)continue;return u}}if("next"===r.method)r.sent=r._sent=r.arg;else if("throw"===r.method){if(n===j)throw n=k,r.arg;r.dispatchException(r.arg)}else"return"===r.method&&r.abrupt("return",r.arg);n=S;var s=o(t,e,r);if("normal"===s.type){if(n=r.done?k:P,s.arg===F)continue;return{value:s.arg,done:r.done}}"throw"===s.type&&(n=k,r.method="throw",r.arg=s.arg)}}}function l(t,e){var r=t.iterator[e.method];if(r===g){if(e.delegate=null,"throw"===e.method){if(t.iterator.return&&(e.method="return",e.arg=g,l(t,e),"throw"===e.method))return F;e.method="throw",e.arg=new TypeError("The iterator does not provide a 'throw' method")}return F}var n=o(r,t.iterator,e.arg);if("throw"===n.type)return e.method="throw",e.arg=n.arg,e.delegate=null,F;var i=n.arg;return i?i.done?(e[t.resultName]=i.value,e.next=t.nextLoc,"return"!==e.method&&(e.method="next",e.arg=g),e.delegate=null,F):i:(e.method="throw",e.arg=new TypeError("iterator result is not an object"),e.delegate=null,F)}function h(t){var e={tryLoc:t[0]};1 in t&&(e.catchLoc=t[1]),2 in t&&(e.finallyLoc=t[2],e.afterLoc=t[3]),this.tryEntries.push(e)}function p(t){var e=t.completion||{};e.type="normal",delete e.arg,t.completion=e}function d(t){this.tryEntries=[{tryLoc:"root"}],t.forEach(h,this),this.reset(!0)}function y(t){if(t){var e=t[b];if(e)return e.call(t);if("function"==typeof t.next)return t;if(!isNaN(t.length)){var r=-1,n=function e(){for(;++r<t.length;)if(w.call(t,r))return e.value=t[r],e.done=!1,e;return e.value=g,e.done=!0,e};return n.next=n}}return{next:v}}function v(){return{value:g,done:!0}}var g,m=Object.prototype,w=m.hasOwnProperty,x="function"==typeof Symbol?Symbol:{},b=x.iterator||"@@iterator",_=x.asyncIterator||"@@asyncIterator",L=x.toStringTag||"@@toStringTag",E="object"===e(t),O=r.regeneratorRuntime;if(O)return void(E&&(t.exports=O));O=r.regeneratorRuntime=E?t.exports:{},O.wrap=n;var j="suspendedStart",P="suspendedYield",S="executing",k="completed",F={},R={};R[b]=function(){return this};var N=Object.getPrototypeOf,G=N&&N(N(y([])));G&&G!==m&&w.call(G,b)&&(R=G);var I=c.prototype=i.prototype=Object.create(R);a.prototype=I.constructor=c,c.constructor=a,c[L]=a.displayName="GeneratorFunction",O.isGeneratorFunction=function(t){var e="function"==typeof t&&t.constructor;return!!e&&(e===a||"GeneratorFunction"===(e.displayName||e.name))},O.mark=function(t){return Object.setPrototypeOf?Object.setPrototypeOf(t,c):(t.__proto__=c,L in t||(t[L]="GeneratorFunction")),t.prototype=Object.create(I),t},O.awrap=function(t){return{__await:t}},u(s.prototype),s.prototype[_]=function(){return this},O.AsyncIterator=s,O.async=function(t,e,r,o){var i=new s(n(t,e,r,o));return O.isGeneratorFunction(e)?i:i.next().then(function(t){return t.done?t.value:i.next()})},u(I),I[L]="Generator",I[b]=function(){return this},I.toString=function(){return"[object Generator]"},O.keys=function(t){var e=[];for(var r in t)e.push(r);return e.reverse(),function r(){for(;e.length;){var n=e.pop();if(n in t)return r.value=n,r.done=!1,r}return r.done=!0,r}},O.values=y,d.prototype={constructor:d,reset:function(t){if(this.prev=0,this.next=0,this.sent=this._sent=g,this.done=!1,this.delegate=null,this.method="next",this.arg=g,this.tryEntries.forEach(p),!t)for(var e in this)"t"===e.charAt(0)&&w.call(this,e)&&!isNaN(+e.slice(1))&&(this[e]=g)},stop:function(){this.done=!0;var t=this.tryEntries[0],e=t.completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(t){function e(e,n){return i.type="throw",i.arg=t,r.next=e,n&&(r.method="next",r.arg=g),!!n}if(this.done)throw t;for(var r=this,n=this.tryEntries.length-1;n>=0;--n){var o=this.tryEntries[n],i=o.completion;if("root"===o.tryLoc)return e("end");if(o.tryLoc<=this.prev){var a=w.call(o,"catchLoc"),c=w.call(o,"finallyLoc");if(a&&c){if(this.prev<o.catchLoc)return e(o.catchLoc,!0);if(this.prev<o.finallyLoc)return e(o.finallyLoc)}else if(a){if(this.prev<o.catchLoc)return e(o.catchLoc,!0)}else{if(!c)throw new Error("try statement without catch or finally");if(this.prev<o.finallyLoc)return e(o.finallyLoc)}}}},abrupt:function(t,e){for(var r=this.tryEntries.length-1;r>=0;--r){var n=this.tryEntries[r];if(n.tryLoc<=this.prev&&w.call(n,"finallyLoc")&&this.prev<n.finallyLoc){var o=n;break}}o&&("break"===t||"continue"===t)&&o.tryLoc<=e&&e<=o.finallyLoc&&(o=null);var i=o?o.completion:{};return i.type=t,i.arg=e,o?(this.method="next",this.next=o.finallyLoc,F):this.complete(i)},complete:function(t,e){if("throw"===t.type)throw t.arg;return"break"===t.type||"continue"===t.type?this.next=t.arg:"return"===t.type?(this.rval=this.arg=t.arg,this.method="return",this.next="end"):"normal"===t.type&&e&&(this.next=e),F},finish:function(t){for(var e=this.tryEntries.length-1;e>=0;--e){var r=this.tryEntries[e];if(r.finallyLoc===t)return this.complete(r.completion,r.afterLoc),p(r),F}},catch:function(t){for(var e=this.tryEntries.length-1;e>=0;--e){var r=this.tryEntries[e];if(r.tryLoc===t){var n=r.completion;if("throw"===n.type){var o=n.arg;p(r)}return o}}throw new Error("illegal catch attempt")},delegateYield:function(t,e,r){return this.delegate={iterator:y(t),resultName:e,nextLoc:r},"next"===this.method&&(this.arg=g),F}}}(function(){return this}()||Function("return this")())}).call(e,r(248)(t))},443:function(t,e){t.exports={input:{width:"700",height:"100",borderWidth:"1",borderColor:"#00B4FF",marginBottom:"20",paddingLeft:"10"}}},444:function(t,e,r){"use strict";function n(t){return function(){var e=t.apply(this,arguments);return new Promise(function(t,r){function n(o,i){try{var a=e[o](i),c=a.value}catch(t){return void r(t)}if(!a.done)return Promise.resolve(c).then(function(t){n("next",t)},function(t){n("throw",t)});t(c)}return n("next")})}}Object.defineProperty(e,"__esModule",{value:!0});var o=r(249),i=function(t){return t&&t.__esModule?t:{default:t}}(o);e.default={props:{},data:function(){return{txt:"",src:"",list:[]}},methods:{onLoad:function(t){},onShow:function(){},onHide:function(){},onUnload:function(){},testOneway:function(){var t=this;this.doCall("obmbase.openOcrCamera",{type:0},function(e){console.log("success------"),t.log(e),e.data.res.forEach(function(){var e=n(i.default.mark(function e(r){return i.default.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:t.list.push(r.preImg);case 1:case"end":return e.stop()}},e,t)}));return function(t){return e.apply(this,arguments)}}())})}},created:function(){}}},445:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("page",{attrs:{title:"拍照"}},[r("div",{staticStyle:{flex:"1",alignItems:"center"}},[r("scroller",{staticStyle:{flex:"1",alignItems:"center",paddingTop:"20px"}},t._l(t.list,function(t){return r("image",{staticStyle:{width:"400px",height:"600px",backgroundColor:"#dddddd"},attrs:{src:t}})})),r("button",{staticStyle:{marginBottom:"20px"},attrs:{width:700,text:"开始"},on:{onClick:function(e){t.testOneway()}}})],1)])},staticRenderFns:[]},t.exports.render._withStripped=!0},513:function(t,e,r){var n,o,i=[];i.push(r(443)),n=r(444);var a=r(445);o=n=n||{},"object"!=typeof n.default&&"function"!=typeof n.default||(Object.keys(n).some(function(t){return"default"!==t&&"__esModule"!==t})&&console.error("named exports are not supported in *.vue files."),o=n=n.default),"function"==typeof o&&(o=o.options),o.__file="/Users/zhengjiangrong/Desktop/work/cornerstone/ztTest/src/views/jz/camera.vue",o.render=a.render,o.staticRenderFns=a.staticRenderFns,o._scopeId="data-v-0edecf09",o.style=o.style||{},i.forEach(function(t){for(var e in t)o.style[e]=t[e]}),"function"==typeof __register_static_styles__&&__register_static_styles__(o._scopeId,i),t.exports=n,t.exports.el="true",new Vue(t.exports)}});