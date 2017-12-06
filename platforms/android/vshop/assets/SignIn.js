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
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(7)
	)

	/* script */
	__vue_exports__ = __webpack_require__(8)

	/* template */
	var __vue_template__ = __webpack_require__(9)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/SignIn.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-6d101272"
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
/* 1 */,
/* 2 */,
/* 3 */,
/* 4 */,
/* 5 */,
/* 6 */,
/* 7 */
/***/ (function(module, exports) {

	module.exports = {
	  "main": {
	    "width": 750,
	    "backgroundColor": "#ffffff",
	    "position": "relative"
	  },
	  "touxiang": {
	    "alignItems": "center",
	    "marginTop": 120
	  },
	  "input1": {
	    "flexDirection": "row",
	    "alignItems": "center",
	    "width": 684,
	    "height": 98,
	    "borderBottomWidth": 1,
	    "borderColor": "#17acf6",
	    "marginLeft": 33,
	    "paddingBottom": 20,
	    "paddingLeft": 10,
	    "marginTop": 60
	  },
	  "sjdtmmdl": {
	    "flexDirection": "row",
	    "justifyContent": "space-between",
	    "marginLeft": 35,
	    "marginRight": 35,
	    "marginTop": 42,
	    "marginBottom": 68
	  },
	  "zc_container": {
	    "width": 630,
	    "flexDirection": "row",
	    "justifyContent": "space-between",
	    "marginLeft": 35,
	    "marginRight": 35,
	    "marginTop": 40
	  },
	  "texts": {
	    "fontSize": 24,
	    "color": "#00AAEE"
	  },
	  "checkTextYes": {
	    "fontSize": 24,
	    "color": "#00AAEE"
	  },
	  "checkTextNo": {
	    "fontSize": 24,
	    "color": "#999999"
	  },
	  "textxx": {
	    "color": "#17acf6",
	    "fontSize": 28
	  },
	  "active": {
	    "backgroundColor": "#17acf6"
	  },
	  "wenzi": {
	    "fontSize": 32,
	    "color": "#ffffff",
	    "fontWeight": "bold"
	  },
	  "SignIn": {
	    "borderBottomLeftRadius": 50,
	    "borderBottomRightRadius": 50,
	    "borderTopLeftRadius": 50,
	    "borderTopRightRadius": 50,
	    "borderRadius": 50,
	    "borderWidth": 1,
	    "borderColor": "#17acf6",
	    "width": 700,
	    "height": 76,
	    "backgroundColor": "#17acf6",
	    "justifyContent": "center",
	    "alignItems": "center",
	    "marginLeft": 25
	  },
	  "register": {
	    "width": 700,
	    "height": 76,
	    "borderWidth": 1,
	    "borderColor": "#17acf6",
	    "justifyContent": "center",
	    "alignItems": "center",
	    "marginTop": 50,
	    "marginLeft": 25,
	    "borderRadius": 50
	  },
	  "wq": {
	    "flexDirection": "row",
	    "marginTop": 60,
	    "justifyContent": "space-between",
	    "marginLeft": 220,
	    "marginRight": 220
	  },
	  "wqtext": {
	    "flexDirection": "row",
	    "marginTop": 20,
	    "justifyContent": "space-between",
	    "marginLeft": 210,
	    "marginRight": 210
	  },
	  "tet": {
	    "fontSize": 24,
	    "color": "#999999"
	  },
	  "input": {
	    "width": 479,
	    "height": 63,
	    "fontSize": 28
	  },
	  "container": {
	    "height": 128,
	    "width": 750,
	    "flexDirection": "row",
	    "alignItems": "center",
	    "justifyContent": "center",
	    "position": "relative"
	  },
	  "X": {
	    "height": 80,
	    "width": 150,
	    "position": "absolute",
	    "left": 0,
	    "marginTop": 5
	  },
	  "title": {
	    "color": "#363636",
	    "textAlign": "center",
	    "marginTop": 30,
	    "fontSize": 35,
	    "fontWeight": "bold"
	  },
	  "jzmm": {
	    "flexDirection": "row",
	    "alignItems": "center"
	  },
	  "checkImgStyle": {
	    "width": 20,
	    "height": 20,
	    "marginLeft": 5,
	    "marginRight": 5
	  },
	  "zc_boss": {
	    "width": 750,
	    "justifyContent": "center",
	    "alignItems": "center"
	  }
	}

/***/ }),
/* 8 */
/***/ (function(module, exports) {

	'use strict';

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


	var modal = weex.requireModule('modal');
	var nav = weex.requireModule('navigator');

/***/ }),
/* 9 */
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["main"]
	  }, [_c('scroller', [_c('div', {
	    staticClass: ["container"],
	    on: {
	      "click": _vm.chadiao
	    }
	  }, [_vm._m(0), _c('text', {
	    staticClass: ["title"]
	  }, [_vm._v("登录")])]), _c('div', {
	    staticClass: ["touxiang"]
	  }, [_c('div', {
	    staticStyle: {
	      width: "156px",
	      height: "156px",
	      borderRadius: "156px"
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "156px",
	      height: "156px",
	      borderRadius: "156px"
	    },
	    attrs: {
	      "src": _vm.avatar
	    }
	  })])]), _c('div', {
	    staticClass: ["input1"]
	  }, [_c('input', {
	    staticClass: ["input"],
	    attrs: {
	      "type": "text",
	      "placeholder": "用户名",
	      "maxlength": "16",
	      "max": "16",
	      "value": (_vm.username)
	    },
	    on: {
	      "input": function($event) {
	        _vm.username = $event.target.attr.value
	      }
	    }
	  }), _c('div', {
	    staticStyle: {
	      position: "absolute",
	      right: "0px",
	      width: "90px",
	      height: "90px",
	      alignItems: "center",
	      justifyContent: "center"
	    },
	    on: {
	      "click": _vm.clearUser
	    }
	  }, [(_vm.delUser) ? _c('image', {
	    staticStyle: {
	      width: "23px",
	      height: "23px"
	    },
	    attrs: {
	      "src": "../images/xx.png"
	    }
	  }) : _vm._e()])]), _c('div', {
	    staticClass: ["input1"],
	    staticStyle: {
	      position: "relative"
	    }
	  }, [_c('input', {
	    staticClass: ["input"],
	    attrs: {
	      "type": "password",
	      "placeholder": "请输入密码",
	      "value": _vm.isPass,
	      "value": (_vm.password)
	    },
	    on: {
	      "input": function($event) {
	        _vm.password = $event.target.attr.value
	      }
	    }
	  }), _c('div', {
	    staticStyle: {
	      position: "absolute",
	      right: "0px",
	      width: "90px",
	      height: "90px",
	      alignItems: "center",
	      justifyContent: "center"
	    },
	    on: {
	      "click": _vm.clearPass
	    }
	  }, [(_vm.delPass) ? _c('image', {
	    staticStyle: {
	      width: "23px",
	      height: "23px"
	    },
	    attrs: {
	      "src": "../images/xx.png"
	    }
	  }) : _vm._e()])]), _c('div', {
	    staticClass: ["sjdtmmdl"]
	  }, [_c('div', {
	    staticClass: ["jzmm"],
	    on: {
	      "click": _vm.checkChange
	    }
	  }, [_c('image', {
	    staticClass: ["checkImgStyle"],
	    attrs: {
	      "src": _vm.checkImg
	    }
	  }), _c('text', {
	    class: [_vm.checkText]
	  }, [_vm._v("记住密码")])]), _c('text', {
	    staticStyle: {
	      fontSize: "24px",
	      color: "#999999"
	    },
	    on: {
	      "click": _vm.wjmm
	    }
	  }, [_vm._v("忘记密码？")])]), _c('div', {
	    staticStyle: {
	      alignItems: "center"
	    }
	  }, [_c('div', {
	    staticStyle: {
	      backgroundColor: "#17ACF6",
	      width: "710",
	      height: "72",
	      justifyContent: "center",
	      alignItems: "center",
	      borderRadius: "50"
	    },
	    on: {
	      "click": _vm.login
	    }
	  }, [_c('text', {
	    staticClass: ["wenzi"]
	  }, [_vm._v("登录")])])]), _c('div', {
	    staticClass: ["zc_boss"]
	  }, [_c('div', {
	    staticClass: ["zc_container"]
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "24px",
	      color: "#999999"
	    },
	    on: {
	      "click": _vm.dtdl
	    }
	  }, [_vm._v("手机动态密码登录")]), _c('text', {
	    staticStyle: {
	      fontSize: "24px",
	      color: "#999999"
	    },
	    on: {
	      "click": _vm.register
	    }
	  }, [_vm._v("立即注册")])])])])])
	},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["X"]
	  }, [_c('image', {
	    staticStyle: {
	      marginLeft: "30px",
	      marginTop: "50px",
	      width: "30px",
	      height: "30px"
	    },
	    attrs: {
	      "src": "../images/xx.png"
	    }
	  })])
	}]}
	module.exports.render._withStripped = true

/***/ })
/******/ ]);