// { "framework": "Vue"} 

!function(t){function e(n){if(r[n])return r[n].exports;var o=r[n]={i:n,l:!1,exports:{}};return t[n].call(o.exports,o,o.exports,e),o.l=!0,o.exports}var r={};e.m=t,e.c=r,e.d=function(t,r,n){e.o(t,r)||Object.defineProperty(t,r,{configurable:!1,enumerable:!0,get:n})},e.n=function(t){var r=t&&t.__esModule?function(){return t.default}:function(){return t};return e.d(r,"a",r),r},e.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},e.p="",e(e.s=553)}({248:function(t,e,r){"use strict";t.exports=function(t){return t.webpackPolyfill||(t.deprecate=function(){},t.paths=[],t.children||(t.children=[]),Object.defineProperty(t,"loaded",{enumerable:!0,get:function(){return t.l}}),Object.defineProperty(t,"id",{enumerable:!0,get:function(){return t.i}}),t.webpackPolyfill=1),t}},249:function(t,e,r){"use strict";t.exports=r(250)},250:function(t,e,r){"use strict";var n=function(){return this}()||Function("return this")(),o=n.regeneratorRuntime&&Object.getOwnPropertyNames(n).indexOf("regeneratorRuntime")>=0,a=o&&n.regeneratorRuntime;if(n.regeneratorRuntime=void 0,t.exports=r(251),o)n.regeneratorRuntime=a;else try{delete n.regeneratorRuntime}catch(t){n.regeneratorRuntime=void 0}},251:function(t,e,r){"use strict";(function(t){var e="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(t){return typeof t}:function(t){return t&&"function"==typeof Symbol&&t.constructor===Symbol&&t!==Symbol.prototype?"symbol":typeof t};!function(r){function n(t,e,r,n){var o=e&&e.prototype instanceof a?e:a,i=Object.create(o.prototype),c=new h(n||[]);return i._invoke=l(t,r,c),i}function o(t,e,r){try{return{type:"normal",arg:t.call(e,r)}}catch(t){return{type:"throw",arg:t}}}function a(){}function i(){}function c(){}function s(t){["next","throw","return"].forEach(function(e){t[e]=function(t){return this._invoke(e,t)}})}function u(t){function r(n,a,i,c){var s=o(t[n],t,a);if("throw"!==s.type){var u=s.arg,l=u.value;return l&&"object"===(void 0===l?"undefined":e(l))&&g.call(l,"__await")?Promise.resolve(l.__await).then(function(t){r("next",t,i,c)},function(t){r("throw",t,i,c)}):Promise.resolve(l).then(function(t){u.value=t,i(u)},c)}c(s.arg)}function n(t,e){function n(){return new Promise(function(n,o){r(t,e,n,o)})}return a=a?a.then(n,n):n()}var a;this._invoke=n}function l(t,e,r){var n=P;return function(a,i){if(n===E)throw new Error("Generator is already running");if(n===S){if("throw"===a)throw i;return y()}for(r.method=a,r.arg=i;;){var c=r.delegate;if(c){var s=p(c,r);if(s){if(s===j)continue;return s}}if("next"===r.method)r.sent=r._sent=r.arg;else if("throw"===r.method){if(n===P)throw n=S,r.arg;r.dispatchException(r.arg)}else"return"===r.method&&r.abrupt("return",r.arg);n=E;var u=o(t,e,r);if("normal"===u.type){if(n=r.done?S:O,u.arg===j)continue;return{value:u.arg,done:r.done}}"throw"===u.type&&(n=S,r.method="throw",r.arg=u.arg)}}}function p(t,e){var r=t.iterator[e.method];if(r===m){if(e.delegate=null,"throw"===e.method){if(t.iterator.return&&(e.method="return",e.arg=m,p(t,e),"throw"===e.method))return j;e.method="throw",e.arg=new TypeError("The iterator does not provide a 'throw' method")}return j}var n=o(r,t.iterator,e.arg);if("throw"===n.type)return e.method="throw",e.arg=n.arg,e.delegate=null,j;var a=n.arg;return a?a.done?(e[t.resultName]=a.value,e.next=t.nextLoc,"return"!==e.method&&(e.method="next",e.arg=m),e.delegate=null,j):a:(e.method="throw",e.arg=new TypeError("iterator result is not an object"),e.delegate=null,j)}function f(t){var e={tryLoc:t[0]};1 in t&&(e.catchLoc=t[1]),2 in t&&(e.finallyLoc=t[2],e.afterLoc=t[3]),this.tryEntries.push(e)}function d(t){var e=t.completion||{};e.type="normal",delete e.arg,t.completion=e}function h(t){this.tryEntries=[{tryLoc:"root"}],t.forEach(f,this),this.reset(!0)}function v(t){if(t){var e=t[b];if(e)return e.call(t);if("function"==typeof t.next)return t;if(!isNaN(t.length)){var r=-1,n=function e(){for(;++r<t.length;)if(g.call(t,r))return e.value=t[r],e.done=!1,e;return e.value=m,e.done=!0,e};return n.next=n}}return{next:y}}function y(){return{value:m,done:!0}}var m,_=Object.prototype,g=_.hasOwnProperty,w="function"==typeof Symbol?Symbol:{},b=w.iterator||"@@iterator",x=w.asyncIterator||"@@asyncIterator",k=w.toStringTag||"@@toStringTag",C="object"===e(t),L=r.regeneratorRuntime;if(L)return void(C&&(t.exports=L));L=r.regeneratorRuntime=C?t.exports:{},L.wrap=n;var P="suspendedStart",O="suspendedYield",E="executing",S="completed",j={},F={};F[b]=function(){return this};var N=Object.getPrototypeOf,T=N&&N(N(v([])));T&&T!==_&&g.call(T,b)&&(F=T);var I=c.prototype=a.prototype=Object.create(F);i.prototype=I.constructor=c,c.constructor=i,c[k]=i.displayName="GeneratorFunction",L.isGeneratorFunction=function(t){var e="function"==typeof t&&t.constructor;return!!e&&(e===i||"GeneratorFunction"===(e.displayName||e.name))},L.mark=function(t){return Object.setPrototypeOf?Object.setPrototypeOf(t,c):(t.__proto__=c,k in t||(t[k]="GeneratorFunction")),t.prototype=Object.create(I),t},L.awrap=function(t){return{__await:t}},s(u.prototype),u.prototype[x]=function(){return this},L.AsyncIterator=u,L.async=function(t,e,r,o){var a=new u(n(t,e,r,o));return L.isGeneratorFunction(e)?a:a.next().then(function(t){return t.done?t.value:a.next()})},s(I),I[k]="Generator",I[b]=function(){return this},I.toString=function(){return"[object Generator]"},L.keys=function(t){var e=[];for(var r in t)e.push(r);return e.reverse(),function r(){for(;e.length;){var n=e.pop();if(n in t)return r.value=n,r.done=!1,r}return r.done=!0,r}},L.values=v,h.prototype={constructor:h,reset:function(t){if(this.prev=0,this.next=0,this.sent=this._sent=m,this.done=!1,this.delegate=null,this.method="next",this.arg=m,this.tryEntries.forEach(d),!t)for(var e in this)"t"===e.charAt(0)&&g.call(this,e)&&!isNaN(+e.slice(1))&&(this[e]=m)},stop:function(){this.done=!0;var t=this.tryEntries[0],e=t.completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(t){function e(e,n){return a.type="throw",a.arg=t,r.next=e,n&&(r.method="next",r.arg=m),!!n}if(this.done)throw t;for(var r=this,n=this.tryEntries.length-1;n>=0;--n){var o=this.tryEntries[n],a=o.completion;if("root"===o.tryLoc)return e("end");if(o.tryLoc<=this.prev){var i=g.call(o,"catchLoc"),c=g.call(o,"finallyLoc");if(i&&c){if(this.prev<o.catchLoc)return e(o.catchLoc,!0);if(this.prev<o.finallyLoc)return e(o.finallyLoc)}else if(i){if(this.prev<o.catchLoc)return e(o.catchLoc,!0)}else{if(!c)throw new Error("try statement without catch or finally");if(this.prev<o.finallyLoc)return e(o.finallyLoc)}}}},abrupt:function(t,e){for(var r=this.tryEntries.length-1;r>=0;--r){var n=this.tryEntries[r];if(n.tryLoc<=this.prev&&g.call(n,"finallyLoc")&&this.prev<n.finallyLoc){var o=n;break}}o&&("break"===t||"continue"===t)&&o.tryLoc<=e&&e<=o.finallyLoc&&(o=null);var a=o?o.completion:{};return a.type=t,a.arg=e,o?(this.method="next",this.next=o.finallyLoc,j):this.complete(a)},complete:function(t,e){if("throw"===t.type)throw t.arg;return"break"===t.type||"continue"===t.type?this.next=t.arg:"return"===t.type?(this.rval=this.arg=t.arg,this.method="return",this.next="end"):"normal"===t.type&&e&&(this.next=e),j},finish:function(t){for(var e=this.tryEntries.length-1;e>=0;--e){var r=this.tryEntries[e];if(r.finallyLoc===t)return this.complete(r.completion,r.afterLoc),d(r),j}},catch:function(t){for(var e=this.tryEntries.length-1;e>=0;--e){var r=this.tryEntries[e];if(r.tryLoc===t){var n=r.completion;if("throw"===n.type){var o=n.arg;d(r)}return o}}throw new Error("illegal catch attempt")},delegateYield:function(t,e,r){return this.delegate={iterator:v(t),resultName:e,nextLoc:r},"next"===this.method&&(this.arg=m),j}}}(function(){return this}()||Function("return this")())}).call(e,r(248)(t))},376:function(t,e){t.exports={input:{width:"700",height:"100",borderWidth:"1",borderColor:"#00B4FF",marginBottom:"20",paddingLeft:"10"}}},377:function(t,e,r){"use strict";function n(t){return function(){var e=t.apply(this,arguments);return new Promise(function(t,r){function n(o,a){try{var i=e[o](a),c=i.value}catch(t){return void r(t)}if(!i.done)return Promise.resolve(c).then(function(t){n("next",t)},function(t){n("throw",t)});t(c)}return n("next")})}}function o(t,e,r){return e in t?Object.defineProperty(t,e,{value:r,enumerable:!0,configurable:!0,writable:!0}):t[e]=r,t}Object.defineProperty(e,"__esModule",{value:!0});var a=r(249),i=function(t){return t&&t.__esModule?t:{default:t}}(a);e.default={props:{},data:function(){var t;return t={ip:"tglmcs.ghzq.com.cn"},o(t,"ip","cfztobm.hundsun.com:9806"),o(t,"ip","27.115.76.52"),o(t,"port",8908),o(t,"port",9806),o(t,"port",9906),o(t,"serverPass",""),o(t,"auth_type","0"),o(t,"userPwd",""),o(t,"txt",""),o(t,"type","7"),o(t,"companyNo","ss"),o(t,"useAliAsr",!1),o(t,"usevalidcode",!0),o(t,"maxTry",100),o(t,"asr_company",6),o(t,"skipFaceCheck",!1),o(t,"needUploadVideo",!1),o(t,"recordTime",3),o(t,"asr_sample_rate",16e3),o(t,"aliUrl",""),o(t,"src",""),o(t,"asr_app_id",""),o(t,"asr_token_id",""),o(t,"osv","http://10.23.2.198/obm-osv/v2/"),t},methods:{change:function(t){this.skipFaceCheck=t.value},onLoad:function(t){},onShow:function(){},onHide:function(){},onUnload:function(){},testOneway:function(){var t=this;return n(i.default.mark(function e(){var r;return i.default.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:r={hsosv_server_url:t.osv,hsobm_server_url:"https://app-dev-cstest.fzzqxf.com/hspalmhall/obm-web/services//hsobm/v2/",upload_server_params:{userName:"HSBOP1020000121"},client_id:"20407907",acpt_id:"2020120200000121",upload_server_ip:t.ip,upload_server_port:t.port,upload_mode:"1",max_errors:t.maxTry,key:"0063-20407907-002-002006",init_date:20201202,user_token:"f7c27fec-0618-482f-b953-3bcfd8518b43",front_acpt_id:"2020112600000006",obm_business_kind:"0063",screenshot_time:"1",reply_timespan:"5",module_no:71,my_session_id:"H5-OBH(03a9dd258ffe429681f6d4fab93dcae3)",op_entrust_way:"8",company_code:"BC",op_station:"MA;IIP=61.183.152.196;IPORT=NA;LIP=192.168.137.151;MAC=3847BCE82A5D;IMEI=867676044672177;RMPN=15509092308;UMPN=NA;ICCID=89860318250275713575;OSV=android10;IMSI=460035891642064@HSOBM;V1.0.0",channel_code:"002",pageid:"example",company_no:t.companyNo,skipFaceCheck:t.skipFaceCheck},r.asr_company=t.asr_company,r.asr_app_id=t.asr_app_id,r.asr_token_id=t.asr_token_id,r.asr_sample_rate=t.asr_sample_rate,r.asr_server=t.aliUrl,r.need_upload_video=t.needUploadVideo,r.reply_timespan=t.recordTime,r.left_btn_name="退出",r.type=t.type,r.validcode=t.usevalidcode?1:0,t.doCall("obmbase.setVolume",{percent:.6},function(){t.doCall("obmbase.validateMobileCode",r,function(e){t.src=e.data.image_data,delete e.data.image_data,t.txt=e,t.log(e)})});case 12:case"end":return e.stop()}},e,t)}))()},openQr:function(){this.doCall("native.scanCode",{},function(){})},getAliToken:function(){var t=this;return n(i.default.mark(function e(){return i.default.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.abrupt("return",new Promise(function(e,r){var n=t.osv+"/getOSVConfig.json";t.$http.fetch({url:n},function(r){r=r.res,t.log(r),t.asr_app_id=r.asr_app_id,t.asr_token_id=r.asr_token_id,t.asr_sample_rate=r.asr_sample_rate,e()})}));case 1:case"end":return e.stop()}},e,t)}))()}},created:function(){this.getAliToken()}}},378:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("page",{attrs:{title:"视频验证码"}},[r("div",{staticStyle:{flex:"1",alignItems:"center"}},[r("scroller",{staticStyle:{flex:"1",alignItems:"center",paddingTop:"20px"}},[r("input",{staticClass:["input"],attrs:{placeholder:"osv地址",value:t.osv},on:{input:function(e){t.osv=e.target.attr.value}}}),r("input",{staticClass:["input"],attrs:{placeholder:"ip",value:t.ip},on:{input:function(e){t.ip=e.target.attr.value}}}),r("input",{staticClass:["input"],attrs:{placeholder:"port",value:t.port},on:{input:function(e){t.port=e.target.attr.value}}}),r("input",{staticClass:["input"],attrs:{placeholder:"最大错误次数",value:t.maxTry},on:{input:function(e){t.maxTry=e.target.attr.value}}}),r("input",{staticClass:["input"],attrs:{placeholder:"连接密码",value:t.serverPass},on:{input:function(e){t.serverPass=e.target.attr.value}}}),r("input",{staticClass:["input"],attrs:{placeholder:"登录密码",value:t.userPwd},on:{input:function(e){t.userPwd=e.target.attr.value}}}),r("input",{staticClass:["input"],attrs:{placeholder:"公司编号",value:t.companyNo},on:{input:function(e){t.companyNo=e.target.attr.value}}}),r("input",{staticClass:["input"],attrs:{placeholder:"录音时间",value:t.recordTime},on:{input:function(e){t.recordTime=e.target.attr.value}}}),r("input",{staticClass:["input"],attrs:{placeholder:"语音厂商：6:阿里时时;7:普强;8:阿里非时时",value:t.asr_company},on:{input:function(e){t.asr_company=e.target.attr.value}}}),r("input",{staticClass:["input"],attrs:{placeholder:"视频上传：0，1:anychat;7:视通",value:t.type},on:{input:function(e){t.type=e.target.attr.value}}}),r("itemSwitch",{attrs:{title:"是否跳过检测人脸"},model:{value:t.skipFaceCheck,callback:function(e){t.skipFaceCheck=e},expression:"skipFaceCheck"}}),r("itemSwitch",{attrs:{title:"是否用验证码"},model:{value:t.usevalidcode,callback:function(e){t.usevalidcode=e},expression:"usevalidcode"}}),r("image",{staticStyle:{width:"400px",height:"600px",backgroundColor:"#dddddd"},attrs:{src:t.src}}),r("text",[t._v(t._s(t.txt))])],1),r("button",{staticStyle:{marginBottom:"20px"},attrs:{width:700,text:"提交"},on:{onClick:function(e){t.testOneway()}}})],1)])},staticRenderFns:[]},t.exports.render._withStripped=!0},553:function(t,e,r){var n,o,a=[];a.push(r(376)),n=r(377);var i=r(378);o=n=n||{},"object"!=typeof n.default&&"function"!=typeof n.default||(Object.keys(n).some(function(t){return"default"!==t&&"__esModule"!==t})&&console.error("named exports are not supported in *.vue files."),o=n=n.default),"function"==typeof o&&(o=o.options),o.__file="/Users/zhengjiangrong/Desktop/work/cornerstone/ztTest/src/views/video/cap.vue",o.render=i.render,o.staticRenderFns=i.staticRenderFns,o._scopeId="data-v-39467cfa",o.style=o.style||{},a.forEach(function(t){for(var e in t)o.style[e]=t[e]}),"function"==typeof __register_static_styles__&&__register_static_styles__(o._scopeId,a),t.exports=n,t.exports.el="true",new Vue(t.exports)}});