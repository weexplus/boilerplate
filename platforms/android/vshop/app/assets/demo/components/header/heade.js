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
	__vue_styles__.push(__webpack_require__(115)
	)

	/* script */
	__vue_exports__ = __webpack_require__(116)

	/* template */
	var __vue_template__ = __webpack_require__(117)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/components/header/heade.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-10ee0a56"
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

/***/ 115:
/***/ (function(module, exports) {

	module.exports = {
	  "header": {
	    "height": 88,
	    "backgroundColor": "#007aff",
	    "flexDirection": "row",
	    "alignItems": "stretch",
	    "boxSizing": "border-box",
	    "paddingLeft": 20,
	    "paddingRight": 20
	  },
	  "header__title": {
	    "flex": 1,
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "header__left": {
	    "flexDirection": "row",
	    "flex": 0.5,
	    "alignItems": "center",
	    "justifyContent": "flex-start",
	    "boxSizing": "border-box"
	  },
	  "header__right": {
	    "flexDirection": "row",
	    "flex": 0.5,
	    "alignItems": "center",
	    "justifyContent": "flex-end",
	    "boxSizing": "border-box"
	  },
	  "header__rightbox": {
	    "flexDirection": "row",
	    "flex": 1,
	    "alignItems": "center",
	    "justifyContent": "flex-end",
	    "boxSizing": "border-box"
	  },
	  "header__text": {
	    "color": "#ffffff",
	    "fontSize": 34,
	    "lines": 1
	  }
	}

/***/ }),

/***/ 116:
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


	exports.default = {
	  name: 'header',
	  props: {
	    title: String,
	    isFixed: Boolean,
	    bg: {
	      type: String,
	      default: '#007aff'
	    },
	    color: {
	      type: String,
	      default: '#fff'
	    }
	  },
	  components: {},
	  data: function data() {
	    return {};
	  },

	  methods: {}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 117:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["header"],
	    class: {
	      'is_fixed': _vm.isFixed
	    },
	    style: {
	      backgroundColor: _vm.bg
	    }
	  }, [_c('div', {
	    staticClass: ["header__left"]
	  }, [_vm._t("left")], 2), _c('div', {
	    staticClass: ["header__title"]
	  }, [_c('text', {
	    staticClass: ["header__text"],
	    style: {
	      color: _vm.color
	    }
	  }, [_vm._v(_vm._s(_vm.title))])]), _c('div', {
	    staticClass: ["header__right"]
	  }, [_vm._t("right")], 2)])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });