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
	__vue_styles__.push(__webpack_require__(190)
	)

	/* script */
	__vue_exports__ = __webpack_require__(!(function webpackMissingModule() { var e = new Error("Cannot find module \"!!weex-vue-loader/lib/script-loader!babel-loader!weex-vue-loader/lib/selector?type=script&index=0!./index.vue\""); e.code = 'MODULE_NOT_FOUND'; throw e; }()))

	/* template */
	var __vue_template__ = __webpack_require__(192)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/weexplus/src/demo/component/packages/wxc-lightbox/index.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-0e736c12"
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

/***/ 190:
/***/ (function(module, exports) {

	module.exports = {
	  "indicator": {
	    "position": "absolute",
	    "itemColor": "rgba(255, 195, 0, .5)",
	    "itemSelectedColor": "#ffc300",
	    "itemSize": 20,
	    "height": 20,
	    "bottom": 24
	  }
	}

/***/ }),

/***/ 192:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('wxc-mask', {
	    attrs: {
	      "width": _vm.width,
	      "height": _vm.height,
	      "ariaHidden": "true",
	      "maskBgColor": "transparent",
	      "overlayOpacity": "0.8",
	      "show": _vm.show,
	      "showClose": false
	    },
	    on: {
	      "wxcMaskSetHidden": _vm.maskOverlayClick
	    }
	  }, [(_vm.show) ? _c('slider', {
	    style: {
	      height: _vm.height + 'px'
	    },
	    attrs: {
	      "autoPlay": "false"
	    }
	  }, [_vm._l((_vm.imageList), function(v, index) {
	    return _c('div', {
	      key: index,
	      style: {
	        height: _vm.height + 'px'
	      }
	    }, [_c('image', {
	      style: {
	        height: _vm.height + 'px',
	        width: _vm.width + 'px'
	      },
	      attrs: {
	        "resize": "cover",
	        "src": v.src
	      }
	    })])
	  }), _c('indicator', {
	    staticClass: ["indicator"],
	    style: _vm.indicatorStyle
	  })], 2) : _vm._e()])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });