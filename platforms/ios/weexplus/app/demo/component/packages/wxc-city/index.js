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
	__vue_styles__.push(__webpack_require__(156)
	)

	/* script */
	__vue_exports__ = __webpack_require__(!(function webpackMissingModule() { var e = new Error("Cannot find module \"!!weex-vue-loader/lib/script-loader!babel-loader!weex-vue-loader/lib/selector?type=script&index=0!./index.vue\""); e.code = 'MODULE_NOT_FOUND'; throw e; }()))

	/* template */
	var __vue_template__ = __webpack_require__(158)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/weexplus/src/demo/component/packages/wxc-city/index.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-8db96b26"
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

/***/ 156:
/***/ (function(module, exports) {

	module.exports = {
	  "wxc-city": {
	    "position": "fixed",
	    "width": 750,
	    "top": 0,
	    "left": 750,
	    "right": 0,
	    "bottom": 0,
	    "backgroundColor": "#F2F3F4"
	  }
	}

/***/ }),

/***/ 158:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    ref: "city",
	    staticClass: ["wxc-city"]
	  }, [_c('wxc-searchbar', _vm._b({
	    ref: "wxc-searchbar",
	    on: {
	      "wxcSearchbarInputOnInput": _vm.onInput,
	      "wxcSearchbarInputReturned": _vm.onSubmit,
	      "wxcSearchbarCancelClicked": _vm.onCancel
	    }
	  }, 'wxc-searchbar', _vm.mergeInputConfig)), (_vm.showTab) ? _c('wxc-tab', {
	    ref: "wxc-tab",
	    on: {
	      "wxcTabSwitch": _vm.onTabSwitch
	    }
	  }) : _vm._e(), _c('wxc-indexlist', {
	    attrs: {
	      "normalList": _vm.normalList,
	      "hotListConfig": _vm.hotListConfig,
	      "navStyle": {
	        top: '24px'
	      },
	      "height": _vm.listHeight,
	      "showIndex": _vm.showIndex,
	      "onlyShowList": _vm.onlyShowList,
	      "cityLocationConfig": _vm.currentCityLocationConfig
	    },
	    on: {
	      "wxcIndexlistItemClicked": _vm.onItemClick
	    }
	  }), (_vm.showError) ? _c('wxc-result', {
	    attrs: {
	      "type": "noGoods",
	      "wrapStyle": {
	        top: '84px'
	      },
	      "show": true,
	      "customSet": _vm.result
	    }
	  }) : _vm._e()], 1)
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });