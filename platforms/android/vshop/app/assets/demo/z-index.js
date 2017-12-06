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
	__vue_styles__.push(__webpack_require__(390)
	)

	/* script */
	__vue_exports__ = __webpack_require__(391)

	/* template */
	var __vue_template__ = __webpack_require__(392)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/z-index.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-1afe9238"
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

/***/ 390:
/***/ (function(module, exports) {

	module.exports = {
	  "mask": {
	    "backgroundColor": "rgba(0,0,0,0.7)",
	    "visibility": "hidden",
	    "position": "fixed",
	    "left": 0,
	    "right": 0,
	    "top": 0,
	    "bottom": 0
	  },
	  "d1": {
	    "height": 100,
	    "backgroundColor": "#008000"
	  },
	  "d2": {
	    "height": 100,
	    "backgroundColor": "#FFFF00"
	  },
	  "wraper": {
	    "position": "absolute",
	    "left": 0,
	    "right": 0,
	    "top": 0,
	    "bottom": 0
	  },
	  "fix": {
	    "height": 100,
	    "position": "fixed",
	    "left": 0,
	    "right": 0,
	    "bottom": 0,
	    "backgroundColor": "#008000"
	  },
	  "panel": {
	    "width": 600,
	    "height": 250,
	    "marginLeft": 75,
	    "marginTop": 35,
	    "marginBottom": 35,
	    "flexDirection": "column",
	    "justifyContent": "center",
	    "borderWidth": 2,
	    "borderStyle": "solid",
	    "borderColor": "rgb(162,217,192)",
	    "backgroundColor": "rgba(162,217,192,0.2)",
	    "visibility": "hidden"
	  },
	  "text": {
	    "fontSize": 50,
	    "textAlign": "center",
	    "color": "#41B883"
	  }
	}

/***/ }),

/***/ 391:
/***/ (function(module, exports) {

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
	"use strict";

/***/ }),

/***/ 392:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _vm._m(0)
	},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["wraper"]
	  }, [_c('scroller', {
	    staticClass: ["scroller"]
	  }, [_c('div', [_c('div', {
	    staticStyle: {
	      height: "200px",
	      backgroundColor: "yellow"
	    }
	  }, [_c('div', {
	    staticStyle: {
	      flex: "1",
	      backgroundColor: "#000000",
	      justifyContent: "center",
	      alignItems: "center"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("宽度自适应")])])]), _c('div', {
	    staticClass: ["d1"]
	  }), _c('div', {
	    staticClass: ["d2"]
	  }), _c('div', {
	    staticClass: ["d2"],
	    staticStyle: {
	      backgroundColor: "blue",
	      height: "3000px"
	    }
	  }, [_c('text')])])]), _c('div', {
	    staticClass: ["fix"]
	  }), _c('div', {
	    staticClass: ["mask"]
	  })])
	}]}
	module.exports.render._withStripped = true

/***/ })

/******/ });