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
	__vue_styles__.push(__webpack_require__(112)
	)

	/* script */
	__vue_exports__ = __webpack_require__(113)

	/* template */
	var __vue_template__ = __webpack_require__(126)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/app.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-07b12bcc"
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

/***/ 112:
/***/ (function(module, exports) {

	module.exports = {
	  "page-content": {
	    "flex": 1,
	    "backgroundColor": "#eeeeee"
	  },
	  "input": {
	    "fontSize": 50,
	    "width": 650,
	    "marginTop": 50,
	    "marginLeft": 50,
	    "paddingTop": 20,
	    "paddingBottom": 20,
	    "paddingLeft": 20,
	    "paddingRight": 20,
	    "color": "#666666",
	    "borderWidth": 2,
	    "borderStyle": "solid",
	    "borderColor": "#41B883"
	  }
	}

/***/ }),

/***/ 113:
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


	var header = __webpack_require__(114);
	var leftItem = __webpack_require__(118);
	var rightItem = __webpack_require__(122);
	var modal = weex.requireModule('modal');
	exports.default = {
	  data: function data() {
	    return {
	      input: '123'
	    };
	  },

	  components: {
	    'mHeader': header,
	    leftItem: leftItem,
	    rightItem: rightItem
	  },
	  methods: {
	    left: function left() {
	      modal.toast({ message: '点击了', duration: 1 });
	    }
	  }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 114:
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

/***/ }),

/***/ 118:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(119)
	)

	/* script */
	__vue_exports__ = __webpack_require__(120)

	/* template */
	var __vue_template__ = __webpack_require__(121)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/components/header/leftItem.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-12c34a1a"
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

/***/ 119:
/***/ (function(module, exports) {

	module.exports = {
	  "header__left-btn": {
	    "flexDirection": "row",
	    "flex": 1,
	    "alignItems": "center",
	    "justifyContent": "flex-start",
	    "boxSizing": "border-box"
	  },
	  "header__text--left": {
	    "color": "#ffffff",
	    "fontSize": 28
	  },
	  "header__icon": {
	    "width": 44,
	    "height": 44
	  }
	}

/***/ }),

/***/ 120:
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


	exports.default = {
	  name: 'leftItem',
	  props: {
	    title: String,
	    icon: String,
	    text: {
	      type: String,
	      default: ''
	    },
	    color: {
	      type: String,
	      default: '#fff'
	    }
	  },
	  data: function data() {
	    return {};
	  },

	  methods: {
	    handle: function handle() {
	      this.$emit('handle');
	    }
	  }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 121:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["header__left-btn"],
	    on: {
	      "click": _vm.handle
	    }
	  }, [(_vm.icon) ? _c('image', {
	    staticClass: ["header__icon"],
	    attrs: {
	      "src": _vm.icon
	    }
	  }) : _vm._e(), _c('text', {
	    staticClass: ["header__text--left"],
	    style: {
	      color: _vm.color
	    }
	  }, [_vm._v(_vm._s(_vm.text))])])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ }),

/***/ 122:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(123)
	)

	/* script */
	__vue_exports__ = __webpack_require__(124)

	/* template */
	var __vue_template__ = __webpack_require__(125)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/components/header/rightItem.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-be6802c2"
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

/***/ 123:
/***/ (function(module, exports) {

	module.exports = {
	  "header__btn": {
	    "paddingLeft": 20,
	    "flexDirection": "row",
	    "alignItems": "center",
	    "justifyContent": "flex-end",
	    "boxSizing": "border-box"
	  },
	  "header__text--right": {
	    "color": "#ffffff",
	    "fontSize": 28
	  },
	  "header__icon": {
	    "width": 44,
	    "height": 44
	  }
	}

/***/ }),

/***/ 124:
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


	exports.default = {
	  name: 'leftItem',
	  props: {
	    title: String,
	    icon: String,
	    text: {
	      type: String,
	      default: ''
	    },
	    color: {
	      type: String,
	      default: '#fff'
	    }
	  },
	  data: function data() {
	    return {};
	  },

	  methods: {
	    handle: function handle() {
	      this.$emit('handle');
	    }
	  }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 125:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["header__btn"],
	    on: {
	      "click": _vm.handle
	    }
	  }, [(_vm.icon) ? _c('image', {
	    staticClass: ["header__icon"],
	    attrs: {
	      "src": _vm.icon
	    }
	  }) : _c('text', {
	    staticClass: ["header__text--right"],
	    style: {
	      color: _vm.color
	    }
	  }, [_vm._v(_vm._s(_vm.text))])])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ }),

/***/ 126:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["wrapper"]
	  }, [_c('m-header', {
	    attrs: {
	      "title": "首页"
	    }
	  }), _c('div', {
	    staticClass: ["page-content"]
	  }, [_c('scroller', [_c('m-header', {
	    staticStyle: {
	      marginTop: "20px"
	    },
	    attrs: {
	      "title": "电影"
	    }
	  }, [_c('left-item', {
	    attrs: {
	      "icon": "http://www.mfancms.com/douban/images/ic_bar_back_white.png"
	    },
	    on: {
	      "handle": _vm.left
	    },
	    slot: "left"
	  }), _c('right-item', {
	    attrs: {
	      "text": "完成"
	    },
	    on: {
	      "handle": _vm.left
	    },
	    slot: "right"
	  })], 1), _c('m-header', {
	    staticStyle: {
	      marginTop: "20px"
	    },
	    attrs: {
	      "title": "左侧文字"
	    }
	  }, [_c('left-item', {
	    attrs: {
	      "text": "返回"
	    },
	    on: {
	      "handle": _vm.left
	    },
	    slot: "left"
	  })], 1), _c('m-header', {
	    staticStyle: {
	      marginTop: "20px"
	    },
	    attrs: {
	      "title": "右侧文字"
	    }
	  }, [_c('right-item', {
	    attrs: {
	      "text": "分享"
	    },
	    on: {
	      "handle": _vm.left
	    },
	    slot: "right"
	  })], 1), _c('m-header', {
	    staticStyle: {
	      marginTop: "20px"
	    },
	    attrs: {
	      "title": "图标"
	    }
	  }, [_c('left-item', {
	    attrs: {
	      "icon": "http://www.mfancms.com/douban/images/ic_bar_back_white.png"
	    },
	    on: {
	      "handle": _vm.left
	    },
	    slot: "left"
	  }), _c('right-item', {
	    attrs: {
	      "icon": "http://www.mfancms.com/douban/images/ic_action_overflow_white.png",
	      "text": "完成"
	    },
	    on: {
	      "handle": _vm.left
	    },
	    slot: "right"
	  })], 1), _c('m-header', {
	    staticStyle: {
	      marginTop: "20px"
	    },
	    attrs: {
	      "title": "多个按钮"
	    }
	  }, [_c('left-item', {
	    attrs: {
	      "icon": "http://www.mfancms.com/douban/images/ic_bar_back_white.png",
	      "text": "返回"
	    },
	    on: {
	      "handle": _vm.left
	    },
	    slot: "left"
	  }), _c('right-item', {
	    attrs: {
	      "icon": "http://www.mfancms.com/douban/images/ic_action_share.png"
	    },
	    on: {
	      "handle": _vm.left
	    },
	    slot: "right"
	  }), _c('right-item', {
	    attrs: {
	      "icon": "http://www.mfancms.com/douban/images/ic_status_action_all.png"
	    },
	    on: {
	      "handle": _vm.left
	    },
	    slot: "right"
	  })], 1), _c('m-header', {
	    staticStyle: {
	      marginTop: "20px"
	    },
	    attrs: {
	      "title": "背景",
	      "bg": "#f7f7f7",
	      "color": "#333"
	    }
	  }, [_c('left-item', {
	    attrs: {
	      "color": "#333",
	      "icon": "http://www.mfancms.com/douban/images/ic_bar_back_green.png",
	      "text": "返回"
	    },
	    on: {
	      "handle": _vm.left
	    },
	    slot: "left"
	  }), _c('right-item', {
	    attrs: {
	      "icon": "http://www.mfancms.com/douban/images/ic_action_share_green.png"
	    },
	    on: {
	      "handle": _vm.left
	    },
	    slot: "right"
	  }), _c('right-item', {
	    attrs: {
	      "icon": "http://www.mfancms.com/douban/images/ic_action_overflow_icon_green.png"
	    },
	    on: {
	      "handle": _vm.left
	    },
	    slot: "right"
	  })], 1), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      marginTop: "40px"
	    },
	    attrs: {
	      "type": "text",
	      "value": (_vm.input)
	    },
	    on: {
	      "input": function($event) {
	        _vm.input = $event.target.attr.value
	      }
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      marginTop: "40px"
	    },
	    attrs: {
	      "type": "text",
	      "value": (_vm.input)
	    },
	    on: {
	      "input": function($event) {
	        _vm.input = $event.target.attr.value
	      }
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      marginTop: "40px"
	    },
	    attrs: {
	      "type": "text",
	      "value": (_vm.input)
	    },
	    on: {
	      "input": function($event) {
	        _vm.input = $event.target.attr.value
	      }
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      marginTop: "40px"
	    },
	    attrs: {
	      "type": "text",
	      "value": (_vm.input)
	    },
	    on: {
	      "input": function($event) {
	        _vm.input = $event.target.attr.value
	      }
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      marginTop: "40px"
	    },
	    attrs: {
	      "type": "text",
	      "value": (_vm.input)
	    },
	    on: {
	      "input": function($event) {
	        _vm.input = $event.target.attr.value
	      }
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      marginTop: "40px"
	    },
	    attrs: {
	      "type": "text",
	      "value": (_vm.input)
	    },
	    on: {
	      "input": function($event) {
	        _vm.input = $event.target.attr.value
	      }
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      marginTop: "40px"
	    },
	    attrs: {
	      "type": "text",
	      "value": (_vm.input)
	    },
	    on: {
	      "input": function($event) {
	        _vm.input = $event.target.attr.value
	      }
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      marginTop: "40px"
	    },
	    attrs: {
	      "type": "text",
	      "value": (_vm.input)
	    },
	    on: {
	      "input": function($event) {
	        _vm.input = $event.target.attr.value
	      }
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      marginTop: "40px"
	    },
	    attrs: {
	      "type": "text",
	      "value": (_vm.input)
	    },
	    on: {
	      "input": function($event) {
	        _vm.input = $event.target.attr.value
	      }
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      marginTop: "40px"
	    },
	    attrs: {
	      "type": "text",
	      "value": (_vm.input)
	    },
	    on: {
	      "input": function($event) {
	        _vm.input = $event.target.attr.value
	      }
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      marginTop: "40px"
	    },
	    attrs: {
	      "type": "text",
	      "value": (_vm.input)
	    },
	    on: {
	      "input": function($event) {
	        _vm.input = $event.target.attr.value
	      }
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      marginTop: "40px"
	    },
	    attrs: {
	      "type": "text",
	      "value": (_vm.input)
	    },
	    on: {
	      "input": function($event) {
	        _vm.input = $event.target.attr.value
	      }
	    }
	  }), _c('input', {
	    ref: "input",
	    staticClass: ["input"],
	    staticStyle: {
	      marginTop: "40px"
	    },
	    attrs: {
	      "type": "text",
	      "value": (_vm.input)
	    },
	    on: {
	      "input": function($event) {
	        _vm.input = $event.target.attr.value
	      }
	    }
	  })], 1)])], 1)
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });