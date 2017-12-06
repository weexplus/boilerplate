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

	/* script */
	__vue_exports__ = __webpack_require__(243)

	/* template */
	var __vue_template__ = __webpack_require__(244)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/list.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-d4012b16"
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

/***/ 224:
/***/ (function(module, exports) {

	module.exports = {
	  "header": {
	    "backgroundColor": "#66CCCC",
	    "alignItems": "center",
	    "justifyContent": "center",
	    "flexDirection": "row"
	  },
	  "leftview": {
	    "width": 120,
	    "height": 135,
	    "justifyContent": "center",
	    "position": "absolute",
	    "left": 0
	  },
	  "rightview": {
	    "width": 120,
	    "height": 135,
	    "justifyContent": "center",
	    "position": "absolute",
	    "right": 0
	  },
	  "wrapper": {
	    "height": 135
	  },
	  "titleback": {
	    "flex": 1,
	    "alignItems": "center"
	  },
	  "title": {
	    "color": "#FFFFFF",
	    "marginTop": 40,
	    "fontWeight": "bold"
	  },
	  "leftimage": {
	    "width": 30,
	    "height": 45,
	    "marginLeft": 20,
	    "marginTop": 40
	  },
	  "rightimage": {
	    "width": 45,
	    "height": 45,
	    "marginTop": 30,
	    "marginLeft": 45
	  },
	  "bottomline": {
	    "height": 1,
	    "backgroundColor": "#000000",
	    "position": "absolute",
	    "bottom": 0,
	    "left": 0,
	    "right": 0,
	    "flex": 1
	  }
	}

/***/ }),

/***/ 225:
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
	//
	//


	exports.default = {
	  props: {
	    title: {
	      type: String
	    },
	    leftClick: {}

	  },
	  data: function data() {
	    return {
	      title: ''
	    };
	  },

	  methods: {
	    leftclick: function leftclick() {
	      var nav = weex.requireModule('navigation');
	      nav.pop();
	    },
	    rightClick: function rightClick() {

	      this.$parent.$emit('rightClick', this.id);
	    }
	  },
	  created: function created() {}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 226:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["wrapper", "header"]
	  }, [_c('div', {
	    staticClass: ["leftview"],
	    on: {
	      "click": _vm.leftclick
	    }
	  }, [_c('image', {
	    staticClass: ["leftimage"],
	    attrs: {
	      "resize": "contain",
	      "src": "img/backarrow.png"
	    }
	  })]), _c('text', {
	    staticClass: ["title"]
	  }, [_vm._v(_vm._s(_vm.title))]), _c('div', {
	    staticClass: ["rightview"],
	    on: {
	      "click": _vm.rightClick
	    }
	  }, [_c('image', {
	    staticClass: ["rightimage"],
	    attrs: {
	      "resize": "contain",
	      "src": "img/scan.png"
	    }
	  })])])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ }),

/***/ 229:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(224)
	)

	/* script */
	__vue_exports__ = __webpack_require__(225)

	/* template */
	var __vue_template__ = __webpack_require__(226)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/head.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-034916b7"
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

/***/ 242:
/***/ (function(module, exports) {

	module.exports = {
	  "hd": {
	    "height": 200,
	    "top": 0,
	    "left": 0,
	    "width": 200,
	    "backgroundColor": "#0000FF",
	    "position": "fixed"
	  },
	  "panel1": {
	    "height": 250,
	    "marginLeft": 15,
	    "marginRight": 15,
	    "marginTop": 35,
	    "marginBottom": 35,
	    "flexDirection": "column",
	    "justifyContent": "center",
	    "borderWidth": 2,
	    "borderStyle": "solid",
	    "borderColor": "#FF0000",
	    "backgroundColor": "rgba(162,217,192,0.2)"
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
	    "backgroundColor": "rgba(162,217,192,0.2)"
	  },
	  "text": {
	    "fontSize": 50,
	    "textAlign": "center",
	    "color": "#41B883"
	  }
	}

/***/ }),

/***/ 243:
/***/ (function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	  value: true
	});

	var _head = __webpack_require__(229);

	var _head2 = _interopRequireDefault(_head);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	var modal = weex.requireModule('modal'); //
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

	var LOADMORE_COUNT = 10;
	var globalEvent = weex.requireModule('globalEvent');

	globalEvent.addEventListener("onPageInit", function (e) {
	  var nav = weex.requireModule('navbar');
	  nav.setTitle('列表');
	  nav.setBack(true);
	  nav.setRightImage('img/scan.png', function (res) {

	    modal.alert({ message: "ok" });
	  });
	});
	exports.default = {
	  components: { head: _head2.default },
	  data: function data() {
	    return {
	      lists: [1, 2, 3, 4, 5, 8],
	      lists1: [1]
	    };
	  },

	  methods: {
	    fetch: function fetch(event) {
	      //        modal.toast({ message: 'loadmore', duration: 1 })

	      var length = this.lists.length;
	      for (var i = length; i < length + LOADMORE_COUNT; ++i) {
	        this.lists.push(i + 1);
	      }
	    }
	  },
	  created: function created() {}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 244:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticStyle: {
	      flex: "1"
	    }
	  }, [_c('list', {
	    staticClass: ["list"],
	    attrs: {
	      "layout": "2"
	    },
	    on: {
	      "loadmore": _vm.fetch
	    }
	  }, _vm._l((_vm.lists), function(num) {
	    return _c('cell', {
	      staticClass: ["cell"],
	      appendAsTree: true,
	      attrs: {
	        "append": "tree"
	      }
	    }, [_c('div', {
	      staticClass: ["panel"]
	    }, [_c('text', {
	      staticClass: ["text"]
	    }, [_vm._v(_vm._s(num))])])])
	  }))])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });