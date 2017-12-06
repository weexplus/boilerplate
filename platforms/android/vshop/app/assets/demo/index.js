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
	__vue_styles__.push(__webpack_require__(212)
	)
	__vue_styles__.push(__webpack_require__(213)
	)

	/* script */
	__vue_exports__ = __webpack_require__(214)

	/* template */
	var __vue_template__ = __webpack_require__(215)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/index.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-4562640b"
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

/***/ 212:
/***/ (function(module, exports) {

	module.exports = {
	  "cl": {
	    "flex": 1,
	    "alignItems": "center"
	  }
	}

/***/ }),

/***/ 213:
/***/ (function(module, exports) {

	module.exports = {
	  "header": {
	    "backgroundColor": "#FF0000",
	    "flex": 1,
	    "flexDirection": "row"
	  },
	  "tz": {
	    "color": "#FF0000"
	  },
	  "logo": {
	    "width": 300,
	    "height": 300,
	    "marginTop": 80
	  },
	  "k1": {
	    "alignItems": "center"
	  },
	  "titleback": {
	    "flex": 1,
	    "alignItems": "center"
	  },
	  "title": {
	    "color": "#FFFFFF",
	    "flex": 1,
	    "marginTop": 73,
	    "fontWeight": "bold"
	  },
	  "leftimage": {
	    "width": 30,
	    "height": 45,
	    "bottom": 25,
	    "left": 30,
	    "position": "absolute"
	  },
	  "rightimage": {
	    "width": 45,
	    "height": 45,
	    "bottom": 23,
	    "right": 32,
	    "position": "absolute"
	  },
	  "bottomline": {
	    "height": 1,
	    "backgroundColor": "#000000",
	    "position": "absolute",
	    "bottom": 0,
	    "left": 0,
	    "right": 0,
	    "flex": 1
	  },
	  "btn": {
	    "backgroundColor": "#0085ee",
	    "height": 100,
	    "width": 500,
	    "marginTop": 50,
	    "borderRadius": 20,
	    "alignItems": "center",
	    "justifyContent": "center",
	    "backgroundColor:active": "#006ce7"
	  }
	}

/***/ }),

/***/ 214:
/***/ (function(module, exports) {

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


	var globalEvent = weex.requireModule('globalEvent');
	globalEvent.addEventListener("onPageInit", function (e) {
	    var nav = weex.requireModule('navbar');
	    nav.setTitle('主页');
	    var navigator = weex.requireModule('navigator');
	    navigator.setPageId('index');
	    navigator.setRoot('index');
	});

	exports.default = {
	    data: function data() {
	        return {};
	    },

	    methods: {
	        jk: function jk() {

	            var modal = weex.requireModule('modal');
	            modal.toast({ message: 'native toast' });
	        }
	    },
	    created: function created() {

	        this.$on('rightClick', function (e) {
	            var modal = weex.requireModule('modal');
	            modal.toast({ message: '右边点击' });
	        });
	    }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 215:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('scroller', {
	    staticStyle: {
	      backgroundColor: "#efeff4"
	    },
	    attrs: {
	      "showScrollbar": "false"
	    }
	  }, [_c('div', {
	    staticStyle: {
	      flexDirection: "row"
	    }
	  }, [_c('div', {
	    staticClass: ["cl"]
	  }, [_c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "navigator.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("导航")])]), _c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "list.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("列表")])]), _c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "recycler.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("瀑布流")])]), _c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "recycler1.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("recycler")])]), _c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "switch.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("switch")])]), _c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "wechat.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("wechat")])]), _c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "viewpager.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("viewpager")])]), _c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "templatelistholder.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("templatelistholder")])])], 1), _c('div', {
	    staticClass: ["cl"]
	  }, [_c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "notify.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("无线通知")])]), _c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "photo.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("照相")])]), _c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "net.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("网络请求")])]), _c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "input.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("input")])]), _c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "refresh.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("refresh")])]), _c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "loading.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("loading")])]), _c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "tabpage.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("tab")])]), _c('a', {
	    staticClass: ["btn"],
	    attrs: {
	      "href": "pickerdemo.js"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("地址选择")])])], 1)])])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });