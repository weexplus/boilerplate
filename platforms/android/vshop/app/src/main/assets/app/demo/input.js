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
	__vue_styles__.push(__webpack_require__(242)
	)
	__vue_styles__.push(__webpack_require__(243)
	)

	/* script */
	__vue_exports__ = __webpack_require__(244)

	/* template */
	var __vue_template__ = __webpack_require__(245)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/input.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-497b51fa"
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

/***/ 242:
/***/ (function(module, exports) {

	module.exports = {
	  "input": {
	    "fontSize": 40,
	    "height": 100,
	    "borderRadius": 10,
	    "marginTop": 50,
	    "paddingLeft": 10,
	    "color": "#666666",
	    "borderWidth": 2,
	    "borderStyle": "solid",
	    "borderColor": "#41B883"
	  }
	}

/***/ }),

/***/ 243:
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

/***/ 244:
/***/ (function(module, exports) {

	"use strict";

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
	//


	exports.default = {
	    data: function data() {
	        return {
	            name: "",
	            display: ""
	        };
	    },

	    methods: {
	        oninput: function oninput(event) {

	            //                this.display=event.value;
	            //                event.value=""
	        },
	        onchange: function onchange(event) {
	            //                this.display=event.value;
	            //                this.name="xxx"
	        },
	        submit: function submit() {

	            var self = this;
	            var model = weex.requireModule('modal');
	            model.toast({ message: this.name });

	            //                const modal = weex.requireModule('modal');
	            //                modal.toast({ message: 'native toast' });
	        }
	    },
	    created: function created() {

	        var globalEvent = weex.requireModule('globalEvent');

	        globalEvent.addEventListener("onPageInit", function (e) {
	            var nav = weex.requireModule('navbar');
	            nav.setTitle('input');
	            nav.setBack(true);
	            nav.setRightImage('img/scan.png', function (res) {

	                var modal = weex.requireModule('modal');
	                modal.alert({ message: "ok" });
	            });
	        });
	    }
	};
	module.exports = exports["default"];

/***/ }),

/***/ 245:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('scroller', {
	    staticStyle: {
	      flex: "1"
	    }
	  }, [_c('div', {
	    staticStyle: {
	      alignItems: "center"
	    }
	  }, [_c('div', {
	    staticStyle: {
	      marginLeft: "0",
	      marginRight: "0",
	      width: "650"
	    }
	  }, [_c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      marginTop: "100"
	    },
	    attrs: {
	      "placeholder": "普通的text",
	      "autofocus": "false",
	      "type": "text",
	      "returnKeyType": "next",
	      "value": (_vm.name)
	    },
	    on: {
	      "change": _vm.onchange,
	      "input": [function($event) {
	        _vm.name = $event.target.attr.value
	      }, _vm.oninput]
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    attrs: {
	      "placeholder": "password",
	      "type": "password",
	      "maxlength": "10",
	      "returnKeyType": "done"
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    attrs: {
	      "placeholder": "return-key done,next,search,go",
	      "type": "password",
	      "maxlength": "10",
	      "returnKeyType": "done"
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    attrs: {
	      "placeholder": "type text，password，url，email，tel",
	      "type": "tel",
	      "maxlength": "10",
	      "returnKeyType": "done"
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    attrs: {
	      "placeholder": "disabled",
	      "type": "tel",
	      "disabled": "true",
	      "maxlength": "10",
	      "returnKeyType": "done"
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      fontWeight: "bold"
	    },
	    attrs: {
	      "placeholder": "type text，password，url，email，tel",
	      "type": "tel",
	      "maxlength": "10",
	      "returnKeyType": "done"
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      fontWeight: "bold"
	    },
	    attrs: {
	      "placeholder": "type text，password，url，email，tel",
	      "type": "number",
	      "maxlength": "10",
	      "returnKeyType": "done"
	    }
	  })]), _c('text', {
	    staticStyle: {
	      position: "fixed",
	      top: "5"
	    }
	  }, [_vm._v(_vm._s(_vm.display))]), _c('div', {
	    staticClass: ["btn"],
	    on: {
	      "click": function($event) {
	        _vm.submit()
	      }
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#ffffff"
	    }
	  }, [_vm._v("提交")])])])])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });