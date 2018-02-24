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
	__vue_styles__.push(__webpack_require__(162)
	)

	/* script */
	__vue_exports__ = __webpack_require__(!(function webpackMissingModule() { var e = new Error("Cannot find module \"!!weex-vue-loader/lib/script-loader!babel-loader!weex-vue-loader/lib/selector?type=script&index=0!./index.vue\""); e.code = 'MODULE_NOT_FOUND'; throw e; }()))

	/* template */
	var __vue_template__ = __webpack_require__(164)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/weexplus/src/demo/component/packages/wxc-countdown/index.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-12e9d0c5"
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

/***/ 162:
/***/ (function(module, exports) {

	module.exports = {
	  "time-dot-wrap": {
	    "flexDirection": "row",
	    "alignItems": "center"
	  }
	}

/***/ }),

/***/ 164:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    style: _vm.mrTimeWrapStyle
	  }, [_c('div', {
	    staticClass: ["time-dot-wrap"]
	  }, [(_vm.tplIndexOfDays !== -1) ? _c('div', {
	    style: _vm.mrTimeBoxStyle,
	    attrs: {
	      "accessible": true,
	      "ariaLabel": ((_vm.countDownData.day) + "天")
	    }
	  }, [_c('text', {
	    style: _vm.mrTimeTextStyle
	  }, [_vm._v(_vm._s(_vm.countDownData.day))])]) : _vm._e(), (_vm.tplIndexOfDays !== -1) ? _c('div', {
	    style: _vm.mrDotBoxStyle,
	    attrs: {
	      "ariaHidden": true
	    }
	  }, [_c('text', {
	    style: _vm.mrDotTextStyle
	  }, [_vm._v(_vm._s(_vm.getDot(_vm.tplIndexOfDays, _vm.tplIndexOfHours)))])]) : _vm._e(), (_vm.tplIndexOfHours !== -1) ? _c('div', {
	    style: _vm.mrTimeBoxStyle,
	    attrs: {
	      "accessible": true,
	      "ariaLabel": ((_vm.countDownData.hour) + "时")
	    }
	  }, [_c('text', {
	    style: _vm.mrTimeTextStyle
	  }, [_vm._v(_vm._s(_vm.countDownData.hour))])]) : _vm._e(), (_vm.tplIndexOfHours !== -1) ? _c('div', {
	    style: _vm.mrDotBoxStyle,
	    attrs: {
	      "ariaHidden": true
	    }
	  }, [_c('text', {
	    style: _vm.mrDotTextStyle
	  }, [_vm._v(_vm._s(_vm.getDot(_vm.tplIndexOfHours, _vm.tplIndexOfMinutes)))])]) : _vm._e(), (_vm.tplIndexOfMinutes !== -1) ? _c('div', {
	    style: _vm.mrTimeBoxStyle,
	    attrs: {
	      "accessible": true,
	      "ariaLabel": ((_vm.countDownData.minute) + "分")
	    }
	  }, [_c('text', {
	    style: _vm.mrTimeTextStyle
	  }, [_vm._v(_vm._s(_vm.countDownData.minute))])]) : _vm._e(), (_vm.tplIndexOfMinutes !== -1) ? _c('div', {
	    style: _vm.mrDotBoxStyle,
	    attrs: {
	      "ariaHidden": true
	    }
	  }, [_c('text', {
	    style: _vm.mrDotTextStyle
	  }, [_vm._v(_vm._s(_vm.getDot(_vm.tplIndexOfMinutes, _vm.tplIndexOfSeconds)))])]) : _vm._e(), (_vm.tplIndexOfSeconds !== -1) ? _c('div', {
	    style: _vm.mrTimeBoxStyle,
	    attrs: {
	      "accessible": true,
	      "ariaLabel": ((_vm.countDownData.second) + "秒")
	    }
	  }, [_c('text', {
	    style: _vm.mrTimeTextStyle
	  }, [_vm._v(_vm._s(_vm.countDownData.second))])]) : _vm._e(), (_vm.tplIndexOfSeconds !== -1) ? _c('div', {
	    style: _vm.mrDotBoxStyle,
	    attrs: {
	      "ariaHidden": true
	    }
	  }, [_c('text', {
	    style: _vm.mrDotTextStyle
	  }, [_vm._v(_vm._s(_vm.getDot(_vm.tplIndexOfSeconds, -1)))])]) : _vm._e()])])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });