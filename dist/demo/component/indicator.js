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
	__vue_styles__.push(__webpack_require__(151)
	)
	__vue_styles__.push(__webpack_require__(152)
	)

	/* script */
	__vue_exports__ = __webpack_require__(153)

	/* template */
	var __vue_template__ = __webpack_require__(154)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/component/indicator.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-04937114"
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

/***/ 151:
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

/***/ 152:
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

/***/ 153:
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

/***/ 154:
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