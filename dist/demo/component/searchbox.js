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
	__vue_styles__.push(__webpack_require__(158)
	)
	__vue_styles__.push(__webpack_require__(159)
	)

	/* script */
	__vue_exports__ = __webpack_require__(160)

	/* template */
	var __vue_template__ = __webpack_require__(162)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/component/searchbox.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-00c9368a"
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

/***/ 155:
/***/ (function(module, exports) {

	module.exports = {
	  "text": {
	    "fontSize": 50,
	    "textAlign": "center",
	    "color": "#41B883"
	  }
	}

/***/ }),

/***/ 156:
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

/***/ 157:
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

/***/ 158:
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

/***/ 159:
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

/***/ 160:
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

	var finput = __webpack_require__(161);
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

/***/ 161:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(155)
	)

	/* script */
	__vue_exports__ = __webpack_require__(156)

	/* template */
	var __vue_template__ = __webpack_require__(157)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/component/input.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-39d3e99e"
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

/***/ 162:
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