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
	__vue_styles__.push(__webpack_require__(84)
	)
	__vue_styles__.push(__webpack_require__(85)
	)

	/* script */
	__vue_exports__ = __webpack_require__(86)

	/* template */
	var __vue_template__ = __webpack_require__(87)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/component/searchbox.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-2f8e0b08"
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

/***/ 22:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(23)
	)

	/* script */
	__vue_exports__ = __webpack_require__(24)

	/* template */
	var __vue_template__ = __webpack_require__(25)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/component/input.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-21d99823"
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


/***/ }),

/***/ 23:
/***/ (function(module, exports) {

	module.exports = {
	  "text": {
	    "fontSize": 50,
	    "textAlign": "center",
	    "color": "#41B883"
	  }
	}

/***/ }),

/***/ 24:
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


	exports.default = {
	    props: {
	        placeholder: {
	            default: ''

	        },
	        placeholder_color: {
	            default: '#ffffff'

	        },
	        color: {
	            default: '#000000'

	        },
	        value: {
	            default: ''
	        },

	        type: {
	            type: String,
	            default: 'text'
	        },
	        font_size: {
	            default: 20
	        },
	        autofocus: {
	            default: false
	        },
	        return_key_type: {
	            default: 'defalut'
	        }

	    },
	    data: function data() {
	        return {

	            pulldistance: 180,
	            visiable: true

	        };
	    },

	    methods: {
	        onchange: function onchange(event) {
	            this.visiable = !event.value == '';
	            //                this.$emit('onchange',event.value);
	            this.value = event.value;
	            //                this.name="xxx"
	        },
	        onfocus: function onfocus() {
	            this.$emit('focus');
	        },
	        focus: function focus() {
	            this.$refs.input.focus();
	        },
	        blur: function blur() {
	            this.$refs.input.blur();
	            this.$emit('blur');
	        },
	        oninput: function oninput(e) {

	            //                this.$emit('oninput');
	            this.value = e.value;
	            this.visiable = e.value != '';
	            this.$emit('onchange', e.value);
	        },
	        onreturn: function onreturn(e) {
	            this.$emit('return', e);
	        },
	        onclose: function onclose() {
	            this.value = '';
	            this.visiable = false;
	            this.$emit('onchange', '');
	        }
	    },

	    created: function created() {
	        var globalEvent = weex.requireModule('globalEvent');
	        globalEvent.addEventListener("onPageInit", function (e) {});

	        this.visiable = !this.value == '';
	    },
	    ready: function ready() {}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 25:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticStyle: {
	      flexDirection: "row",
	      height: "100",
	      alignItems: "center"
	    }
	  }, [_c('input', {
	    ref: "input",
	    staticStyle: {
	      flex: "1",
	      paddingLeft: "20",
	      height: "100"
	    },
	    style: {
	      'color': _vm.color,
	      'placeholder-color': _vm.placeholder_color
	    },
	    attrs: {
	      "returnKeyType": _vm.return_key_type,
	      "autofocus": _vm.autofocus,
	      "placeholder": _vm.placeholder,
	      "type": _vm.type,
	      "value": (_vm.value)
	    },
	    on: {
	      "return": _vm.onreturn,
	      "focus": _vm.onfocus,
	      "change": _vm.onchange,
	      "input": [function($event) {
	        _vm.value = $event.target.attr.value
	      }, _vm.oninput]
	    }
	  }), (_vm.visiable) ? _c('div', {
	    staticStyle: {
	      width: "50px",
	      height: "100px",
	      marginRight: "10",
	      alignItems: "center",
	      justifyContent: "center"
	    },
	    on: {
	      "click": function($event) {
	        _vm.onclose()
	      }
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "30px",
	      height: "30px"
	    },
	    attrs: {
	      "src": "root:img/delete.png"
	    }
	  })]) : _vm._e()])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ }),

/***/ 84:
/***/ (function(module, exports) {

	module.exports = {
	  "cancel": {
	    "marginLeft": 20,
	    "color": "#444444",
	    "fontSize": 30,
	    "fontWeight": "bold",
	    "color:active": "#000000"
	  }
	}

/***/ }),

/***/ 85:
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

/***/ 86:
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

	var finput = __webpack_require__(22);
	exports.default = {
	    components: { finput: finput },
	    props: {
	        text: {
	            type: String

	        },
	        radius: {
	            default: 5
	        },
	        color: {
	            type: String

	        },
	        value: {
	            default: ''
	        },
	        placeholder: {
	            default: ''

	        },
	        disabled: {

	            default: false
	        },
	        autofocus: {
	            default: true
	        },
	        needCancel: {
	            default: true
	        },
	        type: {
	            type: String,
	            default: 'text'
	        },
	        font_size: {
	            default: 20
	        },
	        placeholder_color: {
	            default: '#8d909a'
	        }

	    },
	    data: function data() {
	        return {

	            visiable: true

	        };
	    },

	    methods: {
	        cancel: function cancel() {
	            this.$refs.input.blur();
	            var nav = weex.requireModule('navigator');
	            nav.backFull({}, false);
	        },
	        change: function change(res) {
	            this.$emit('change', res);
	        },
	        onreturn: function onreturn(e) {
	            this.$emit('return', e);
	        },
	        onfocus: function onfocus() {
	            this.$emit('focus');
	        },
	        focus: function focus() {
	            this.$refs.input.focus();
	        },
	        onblur: function onblur() {
	            this.$emit('blur');
	        },
	        blur: function blur() {
	            this.$refs.input.blur();
	        },
	        oninput: function oninput(e) {

	            //                this.$emit('oninput');
	            this.$emit('oninput', e);
	            this.visiable = e.value != '';
	        },
	        onclick: function onclick() {
	            if (!this.disabled) this.$emit('onclick');
	        },
	        panstart: function panstart() {
	            if (!this.disabled) this.bgcolor = '#ff1b08';
	        },
	        panend: function panend() {
	            if (!this.disabled) this.bgcolor = '#ff4800';
	        },
	        setenable: function setenable() {},
	        onclose: function onclose() {
	            this.value = '';
	        }
	    },

	    created: function created() {

	        this.visiable = !this.value == '';
	    },
	    ready: function ready() {}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 87:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', [_c('div', {
	    staticStyle: {
	      flexDirection: "row",
	      alignItems: "center"
	    }
	  }, [_c('div', {
	    staticStyle: {
	      flex: "1",
	      backgroundColor: "#5c616f",
	      height: "65",
	      borderRadius: "5",
	      justifyContent: "center",
	      alignItems: "center",
	      flexDirection: "row"
	    },
	    style: {
	      'border-radius': _vm.radius
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "40",
	      height: "40",
	      marginLeft: "20"
	    },
	    attrs: {
	      "src": "root:img/search.png"
	    }
	  }), _c('finput', {
	    ref: "input",
	    staticStyle: {
	      flex: "1",
	      height: "65",
	      paddingLeft: "1",
	      marginTop: "5"
	    },
	    attrs: {
	      "color": "#8d909a",
	      "value": _vm.value,
	      "return_key_type": "search",
	      "placeholder_color": _vm.placeholder_color,
	      "autofocus": _vm.autofocus,
	      "placeholder": _vm.placeholder
	    },
	    on: {
	      "return": _vm.onreturn,
	      "onchange": _vm.change,
	      "focus": _vm.onfocus,
	      "blur": _vm.onblur
	    }
	  })], 1), (_vm.needCancel) ? _c('text', {
	    staticClass: ["cancel"],
	    on: {
	      "click": _vm.cancel
	    }
	  }, [_vm._v("取消")]) : _vm._e()])])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });