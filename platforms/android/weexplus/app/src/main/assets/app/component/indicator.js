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
	__vue_styles__.push(__webpack_require__(62)
	)
	__vue_styles__.push(__webpack_require__(63)
	)

	/* script */
	__vue_exports__ = __webpack_require__(64)

	/* template */
	var __vue_template__ = __webpack_require__(65)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/component/indicator.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-35b3e930"
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

/***/ 62:
/***/ (function(module, exports) {

	module.exports = {
	  "bg": {
	    "backgroundColor": "#f5f5f5"
	  },
	  "cell": {
	    "height": 100,
	    "backgroundColor": "#ffffff",
	    "flexDirection": "row",
	    "alignItems": "center",
	    "borderRadius": 5
	  },
	  "arrow": {
	    "width": 16,
	    "height": 26
	  },
	  "font_normal": {
	    "fontSize": 30
	  },
	  "theme_color": {
	    "color": "#1296db"
	  },
	  "theme_bg": {
	    "color": "#1296db"
	  },
	  "mask": {
	    "backgroundColor": "#000000",
	    "opacity": 0.6,
	    "position": "absolute",
	    "left": 0,
	    "top": 0,
	    "bottom": 0,
	    "right": 0
	  }
	}

/***/ }),

/***/ 63:
/***/ (function(module, exports) {

	module.exports = {
	  "item": {
	    "flex": 1,
	    "alignItems": "center",
	    "justifyContent": "center",
	    "height": 55,
	    "borderRightWidth": 3,
	    "borderColor": "#1296db",
	    "fontSize": 30
	  },
	  "item_sel": {
	    "flex": 1,
	    "alignItems": "center",
	    "justifyContent": "center",
	    "height": 55,
	    "backgroundColor": "#1296db",
	    "fontSize": 30
	  }
	}

/***/ }),

/***/ 64:
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


	exports.default = {
	    props: {
	        theme: {
	            default: 'red'
	        },
	        items: {
	            default: ['剧集', '简介', '简介']

	        },
	        selectIndex: {
	            default: 0
	        }

	    },
	    data: function data() {
	        return {

	            visiable: true

	        };
	    },

	    methods: {
	        onitemclick: function onitemclick(index) {
	            this.selectIndex = index;
	            this.$emit('change', index);
	        },
	        getBorderColor: function getBorderColor(index) {
	            //                return this.theme
	            if (this.items.length - 1 != index) {
	                return this.theme;
	            } else {
	                return 'transparent';
	            }
	        },
	        getBg: function getBg(index) {
	            if (this.selectIndex == index) {
	                return this.theme;
	            } else {
	                return 'transparent';
	            }
	        }
	    },

	    created: function created() {

	        this.visiable = !this.value == '';
	    },
	    ready: function ready() {}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 65:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticStyle: {
	      borderRadius: "10",
	      borderWidth: "3",
	      flexDirection: "row"
	    },
	    style: {
	      'border-color': _vm.theme
	    }
	  }, _vm._l((_vm.items), function(item, index) {
	    return _c('div', {
	      class: [_vm.selectIndex == index ? 'item_sel' : 'item'],
	      style: {
	        'border-right-width': index == _vm.items.length - 1 ? 0 : 3,
	        'background-color': _vm.getBg(index),
	        'border-color': _vm.getBorderColor(index)
	      },
	      on: {
	        "click": function($event) {
	          _vm.onitemclick(index)
	        }
	      }
	    }, [_c('text', {
	      staticStyle: {
	        color: "#ffffff",
	        fontSize: "30"
	      }
	    }, [_vm._v(_vm._s(item))])])
	  }))
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });