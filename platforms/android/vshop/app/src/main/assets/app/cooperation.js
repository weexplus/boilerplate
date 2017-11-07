// { "framework": "Vue" }
/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ({

/***/ 0:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(287)
	)

	/* script */
	__vue_exports__ = __webpack_require__(288)

	/* template */
	var __vue_template__ = __webpack_require__(289)
	__vue_options__ = __vue_exports__ = __vue_exports__ || {}
	if (
	  typeof __vue_exports__.default === "object" ||
	  typeof __vue_exports__.default === "function"
	) {
	if (Object.keys(__vue_exports__).some(function (key) { return key !== "default" && key !== "__esModule" })) {console.error("named exports are not supported in *.vue files.")}
	__vue_options__ = __vue_exports__ = __vue_exports__.default
	}
	if (typeof __vue_options__ === "function") {
	  __vue_options__ = __vue_options__.options
	}
	__vue_options__.__file = "/Users/zhengjiangrong/work/corenerstone/netplatform/src/home/cooperation.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-f22957ba"
	__vue_options__.style = __vue_options__.style || {}
	__vue_styles__.forEach(function (module) {
	  for (var name in module) {
	    __vue_options__.style[name] = module[name]
	  }
	})
	if (typeof __register_static_styles__ === "function") {
	  __register_static_styles__(__vue_options__._scopeId, __vue_styles__)
	}

	module.exports = __vue_exports__
	module.exports.el = 'true'
	new Vue(module.exports)


/***/ }),

/***/ 99:
/***/ (function(module, exports) {

	"use strict";

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});
	//var host='http://10.39.1.237:8081/cdtp/';
	//var host='http://10.39.1.237:8081/cdtp/';
	var host = 'http://10.39.1.72:8081/cdtp/';
	//var token= "8c0ecee9ba760a1820f8006e0cfb7413";
	//var refreshToken="68a24a0722b6fc0aed1006b2a28f4091";
	//var Header = {
	//	token: token,
	//	refreshToken: refreshToken
	//}

	exports.default = {
	    //	userId: '85935039157a4740aac07aa0e015a7e8',
	    postShort: function postShort(weg, param, header, start, success, compelete) {
	        var modal = weex.requireModule("modal");
	        this.postFull(weg, param, header, start, success, function (res) {
	            //fail
	            //          modal.toast({message:res.msg})
	        }, function () {
	            //exception
	            //          modal.toast({message:'网络异常！'})
	        }, function () {
	            //compelete
	            compelete();
	        });
	    },

	    postFull: function postFull(weg, param, header, start, success, fail, exception, compelete) {
	        var net = weex.requireModule("net");
	        var modal = weex.requireModule("modal");
	        var self = this;
	        var url = host + weg;
	        var st = weex.requireModule('static');
	        var objkey = st.get('user');
	        if (objkey != undefined && objkey != '') {
	            header.token = objkey.userInfo.token;
	            header.refreshToken = objkey.userInfo.refreshToken;
	        }
	        // param.token='95d594d7b18fd1c7db37e81dd5bae9c9'
	        net.post(url, param, header, function () {
	            //start
	            start();
	        }, function (e) {
	            //success
	            // modal.toast({message:e.res.err})
	            //          if(e.res.err==0)
	            //          {
	            //
	            success(e.res);
	            /*token=e.data.token
	            refreshToken=e.data.refreshToken*/
	            //          }
	            //          else
	            //          {
	            //              // modal.toast({message:e.res.msg})
	            //              if(e.res.err==1000)
	            //              {
	            //                  var nav=weex.requireModule("navigator")
	            //                  nav.presentFull('root:busi/account/login.js',{},'transparent',true,function(){
	            //                      self.postFull(weg,param,header,start,success,fail,exception,compelete);
	            //                  },true);
	            //              }
	            //              else
	            //              fail(e.res);
	            //          }
	        }, function (e) {
	            //compelete


	            compelete();
	        }, function (e) {
	            // exception
	            exception();
	        });
	    },
	    post: function post(weg, param, success) {
	        var progress = weex.requireModule("progress");
	        this.postShort(weg, param, {}, function () {
	            progress.show();
	        }, success, function () {
	            progress.dismiss();
	        });
	    },
	    postNoHeader: function postNoHeader(weg, param, success) {
	        var progress = weex.requireModule("progress");
	        this.postShort(weg, param, {}, function () {
	            progress.show();
	        }, success, function () {
	            progress.dismiss();
	        });
	    },

	    postSlient: function postSlient(weg, param, success) {

	        this.postFull(weg, param, {}, function () {}, success, function (res) {
	            //fail

	        }, function () {
	            //exception

	        }, function () {
	            //compelete


	        });
	    },

	    //     GET
	    getShort: function getShort(weg, param, header, start, success, compelete) {
	        var modal = weex.requireModule("modal");
	        this.getFull(weg, param, header, start, success, function (res) {
	            //fail
	            //          modal.toast({message:res.msg})
	        }, function () {
	            //exception
	            //          modal.toast({message:'网络异常！'})
	        }, function () {
	            //compelete

	            compelete();
	        });
	    },

	    getFull: function getFull(weg, param, header, start, success, fail, exception, compelete) {
	        var net = weex.requireModule("net");
	        var modal = weex.requireModule("modal");
	        var self = this;
	        var url = host + weg;
	        var st = weex.requireModule('static');
	        var objkey = st.get('objkey');
	        if (objkey != undefined && objkey != '') {
	            header.token = objkey.userInfo.token;
	            header.refreshToken = objkey.userInfo.refreshToken;
	        }
	        // param.token='95d594d7b18fd1c7db37e81dd5bae9c9'
	        net.get(url, param, header, function () {
	            //start
	            start();
	        }, function (e) {
	            //success
	            // modal.toast({message:e.res.err})
	            //          if(e.res.err==0)
	            //          {
	            //
	            success(e.res);
	            /*token=e.data.token
	            refreshToken=e.data.refreshToken*/
	            //          }
	            //          else
	            //          {
	            //              // modal.toast({message:e.res.msg})
	            //              if(e.res.err==1000)
	            //              {
	            //                  var nav=weex.requireModule("navigator")
	            //                  nav.presentFull('root:busi/account/login.js',{},'transparent',true,function(){
	            //                      self.postFull(weg,param,header,start,success,fail,exception,compelete);
	            //                  },true);
	            //              }
	            //              else
	            //              fail(e.res);
	            //          }
	        }, function (e) {
	            //compelete


	            compelete();
	        }, function (e) {
	            // exception
	            exception();
	        });
	    },
	    get: function get(weg, param, success) {
	        var progress = weex.requireModule("progress");
	        this.getShort(weg, param, {}, function () {
	            progress.show();
	        }, success, function () {
	            progress.dismiss();
	        });
	    },
	    getNoHeader: function getNoHeader(weg, param, success) {
	        var progress = weex.requireModule("progress");
	        this.getShort(weg, param, {}, function () {
	            progress.show();
	        }, success, function () {
	            progress.dismiss();
	        });
	    },

	    getSlient: function getSlient(weg, param, success) {

	        this.getFull(weg, param, {}, function () {}, success, function (res) {
	            //fail

	        }, function () {
	            //exception

	        }, function () {
	            //compelete


	        });
	    }
	};
	module.exports = exports["default"];

/***/ }),

/***/ 287:
/***/ (function(module, exports) {

	module.exports = {
	  "title1": {
	    "fontSize": 28,
	    "color": "#333333",
	    "borderBottomColor": "#333333",
	    "borderBottomStyle": "solid",
	    "borderBottomWidth": 1,
	    "lineHeight": 48
	  },
	  "text_box": {
	    "padding": 44
	  },
	  "btn": {
	    "position": "absolute",
	    "top": 544,
	    "left": 45,
	    "width": 226,
	    "height": 88
	  }
	}

/***/ }),

/***/ 288:
/***/ (function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//

	var net = __webpack_require__(99);
	exports.default = {
		methods: {
			backTo: function backTo() {
				var nav = weex.requireModule("navigator");
				nav.backTo('index');
			},
			jump: function jump() {
				//				net.post('/user/getStatus',{},       //获取用户等级（即一般客户和普通客户信息）内容
				//	          		function (e) {
				////	          			var modal = weex.requireModule('modal')
				////        				modal.alert({message:e.status});
				//
				//	          			if(e.status == 0){
				//	          				var nav = weex.requireModule('navigator');
				//          					nav.push('Demand.js')
				//	          			  }
				//	          			else if(e.status == 1)
				//	          			  {
				//	          				var nav = weex.requireModule('navigator');
				//          					nav.push('PublishDemand.js')
				//	          			  }
				//	          			else if(e.status == 2)
				//	          			  {
				//	          				var nav = weex.requireModule('navigator');
				//          					nav.push('PublishDemandGroup.js')
				//	          			  }
				//				    }
				//    			);
			}
		}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 289:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('scroller', [_c('div', {
	    staticClass: ["container"]
	  }, [_c('image', {
	    staticStyle: {
	      height: "750px",
	      width: "750px"
	    },
	    attrs: {
	      "src": "../images/home/banner2.png"
	    }
	  }), _c('image', {
	    staticStyle: {
	      width: "80px",
	      height: "80px",
	      position: "absolute",
	      left: "30px",
	      top: "45px"
	    },
	    attrs: {
	      "src": "../images/back.png"
	    },
	    on: {
	      "click": _vm.backTo
	    }
	  }), _c('div', {
	    staticStyle: {
	      width: "100",
	      height: "100",
	      backgroundColor: "#0088fb"
	    },
	    on: {
	      "click": _vm.backTo
	    }
	  }), _c('image', {
	    staticStyle: {
	      height: "50px",
	      width: "395px",
	      position: "absolute",
	      top: "62px",
	      left: "145px"
	    },
	    attrs: {
	      "src": "../images/home/logo1.png"
	    }
	  }), _c('image', {
	    staticStyle: {
	      height: "103px",
	      width: "380px",
	      position: "absolute",
	      top: "280px",
	      left: "45px"
	    },
	    attrs: {
	      "src": "../images/home/zi.png"
	    }
	  }), _c('div', {
	    staticClass: ["btn"],
	    on: {
	      "click": _vm.jump
	    }
	  }, [_c('image', {
	    staticStyle: {
	      height: "88px",
	      width: "226px",
	      position: "absolute",
	      top: "0",
	      left: "0"
	    },
	    attrs: {
	      "src": "../images/home/btn.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      position: "absolute",
	      left: "50px",
	      fontSize: "30px",
	      lineHeight: "84px",
	      color: "#fff"
	    }
	  }, [_vm._v("发布需求")])]), _vm._m(0), _vm._m(1)])])
	},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["text_box"]
	  }, [_c('div', {
	    staticClass: ["title"],
	    staticStyle: {
	      flexDirection: "row",
	      marginBottom: "20px"
	    }
	  }, [_c('text', {
	    staticClass: ["title1"]
	  }, [_vm._v("我们的实力")]), _c('text', {
	    staticStyle: {
	      fontSize: "18px",
	      color: "#bababa",
	      marginLeft: "18px",
	      lineHeight: "48px"
	    }
	  }, [_vm._v("OUR STRENGTH")])]), _c('text', {
	    staticStyle: {
	      lineHeight: "48px",
	      color: "#999",
	      fontSize: "24px"
	    }
	  }, [_vm._v("公司拥有市政公用工程咨询甲级，市政行业设计甲级，化工石化医药行业设计甲级、市政公用工程施工总承包壹级、化工石油工程施工总承包壹级等资质。下设九家分(子)公司，业务遍及25个省100多个城市。")])])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["text_box"],
	    staticStyle: {
	      marginTop: "-44px"
	    }
	  }, [_c('div', {
	    staticClass: ["title"],
	    staticStyle: {
	      flexDirection: "row",
	      marginBottom: "20px"
	    }
	  }, [_c('text', {
	    staticClass: ["title1"]
	  }, [_vm._v("联系我们")]), _c('text', {
	    staticStyle: {
	      fontSize: "18px",
	      color: "#bababa",
	      marginLeft: "18px",
	      lineHeight: "48px"
	    }
	  }, [_vm._v("CONTACTUS")])]), _c('text', {
	    staticStyle: {
	      lineHeight: "48px",
	      color: "#999",
	      fontSize: "24px"
	    }
	  }, [_vm._v("如果您拥有市场信息，可以与我们联系，新地拥有专业的市场开发、技术研发、生产制造、设计咨询、项目管理等服务，共同实现市场开发和双方的共赢，助您实现市场信息向市场收益的转变。")])])
	}]}
	module.exports.render._withStripped = true

/***/ })

/******/ });