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
	__vue_styles__.push(__webpack_require__(145)
	)

	/* script */
	__vue_exports__ = __webpack_require__(146)

	/* template */
	var __vue_template__ = __webpack_require__(150)
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
	__vue_options__.__file = "/Users/wangzixiao/Desktop/netplatform/src/message/Message.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-6786687c"
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

/***/ 32:
/***/ (function(module, exports) {

	module.exports = {
	  "layout": {
	    "backgroundColor": "#333333",
	    "height": 128,
	    "width": 750,
	    "flexDirection": "row",
	    "alignItems": "center",
	    "justifyContent": "center"
	  }
	}

/***/ }),

/***/ 33:
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


	exports.default = {
	    props: {
	        title: {
	            default: ''

	        },
	        back: {
	            default: true
	        },
	        bgcolor: {
	            default: '#16ADF6'

	        },
	        isloading: {
	            default: false
	        },
	        disabled: {

	            default: false
	        },

	        type: {
	            type: String,
	            default: 'text'
	        },
	        font_size: {
	            default: 20
	        },
	        height: {
	            default: 128
	        },
	        top: {
	            default: 40
	        },
	        titletop: {
	            default: 10
	        }

	    },
	    data: function data() {
	        return {};
	    },

	    methods: {
	        titleClick: function titleClick() {
	            this.$emit('titleClick');
	        },
	        rightclick: function rightclick() {
	            this.$emit('rightClick');
	        },
	        backTo: function backTo() {
	            var nav = weex.requireModule("navigator");
	            nav.back();
	        },
	        onclick: function onclick() {
	            if (!this.disabled) this.$emit('onclick');
	        },
	        adjust: function adjust() {
	            if (weex.config.env.platform == 'android') {
	                //                    if(weex.config.env.osVersion=)
	                var p = weex.config.env.osVersion;
	                p = p.replace(/\./g, '');
	                if (p.length < 3) p = p + "0";
	                if (p <= '440') {
	                    this.height = 108;
	                    this.top = 16;
	                    this.titletop = 4;
	                }
	            }
	        }
	    },

	    created: function created() {

	        this.adjust();
	    },
	    ready: function ready() {}
	    //        watch: {
	    //
	    //
	    //            disabled:{
	    //                immediate: true,
	    //                handler (val) {
	    //
	    //                }
	    //            }
	    //        }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 34:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["layout"],
	    style: {
	      'background-color': _vm.bgcolor,
	      'height': _vm.height
	    }
	  }, [_c('div', {
	    staticStyle: {
	      flexDirection: "row"
	    },
	    style: {
	      'top': _vm.titletop
	    }
	  }, [(_vm.isloading) ? _c('div', {
	    staticStyle: {
	      height: "40",
	      width: "40",
	      marginRight: "10"
	    }
	  }) : _vm._e(), _c('text', {
	    staticStyle: {
	      flex: "1",
	      color: "#ffffff",
	      textAlign: "center",
	      fontSize: "35"
	    },
	    on: {
	      "click": _vm.titleClick
	    }
	  }, [_vm._v(_vm._s(_vm.title))]), (_vm.isloading) ? _c('floading', {
	    staticStyle: {
	      height: "40",
	      width: "40",
	      marginLeft: "10",
	      marginTop: "5"
	    },
	    attrs: {
	      "color": "#ffffff",
	      "loadingStyle": "small"
	    }
	  }) : _vm._e()], 1), (_vm.back) ? _c('div', {
	    staticStyle: {
	      width: "200",
	      top: "40",
	      position: "absolute",
	      left: "0"
	    },
	    style: {
	      'height': _vm.height,
	      'top': _vm.top
	    },
	    on: {
	      "click": _vm.backTo
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "80",
	      height: "80"
	    },
	    attrs: {
	      "src": "root:images/back.png"
	    }
	  })]) : _vm._e(), _c('div', {
	    staticStyle: {
	      width: "200",
	      position: "absolute",
	      right: "0",
	      top: "0",
	      alignItems: "center",
	      justifyContent: "center"
	    },
	    style: {
	      'height': _vm.height
	    },
	    on: {
	      "click": _vm.rightclick
	    }
	  }, [_vm._t("right")], 2)])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ }),

/***/ 120:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(32)
	)

	/* script */
	__vue_exports__ = __webpack_require__(33)

	/* template */
	var __vue_template__ = __webpack_require__(34)
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
	__vue_options__.__file = "/Users/wangzixiao/Desktop/netplatform/src/components/header.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-0f8984fa"
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

/***/ 145:
/***/ (function(module, exports) {

	module.exports = {
	  "message-container": {
	    "width": 750,
	    "height": 1400,
	    "backgroundColor": "#EEEEEE"
	  },
	  "MES_top": {
	    "position": "relative",
	    "flexDirection": "row",
	    "height": 90,
	    "borderBottomWidth": 1,
	    "borderColor": "#cbcbcb",
	    "backgroundColor": "#FFFFFF",
	    "justifyContent": "center",
	    "alignItems": "center"
	  },
	  "input": {
	    "fontSize": 28,
	    "color": "#cbcbcb",
	    "height": 62,
	    "width": 710,
	    "borderWidth": 1,
	    "borderColor": "#cbcbcb",
	    "borderRadius": 30,
	    "paddingLeft": 30
	  }
	}

/***/ }),

/***/ 146:
/***/ (function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	  value: true
	});

	var _ContentGe = __webpack_require__(147);

	var _ContentGe2 = _interopRequireDefault(_ContentGe);

	var _header = __webpack_require__(120);

	var _header2 = _interopRequireDefault(_header);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

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

	//  import TopBar from './TopBar.vue'
	exports.default = {
	  components: {
	    TopBar: _header2.default,
	    ContentGe: _ContentGe2.default
	  }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 147:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(148)
	)

	/* template */
	var __vue_template__ = __webpack_require__(149)
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
	__vue_options__.__file = "/Users/wangzixiao/Desktop/netplatform/src/message/ContentGe.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-1283706c"
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

/***/ 148:
/***/ (function(module, exports) {

	module.exports = {}

/***/ }),

/***/ 149:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _vm._m(0)
	},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["article"],
	    staticStyle: {
	      backgroundColor: "#FFFFFF",
	      width: "750px"
	    }
	  }, [_c('div', {
	    staticClass: ["content_gz"],
	    staticStyle: {
	      flexDirection: "row",
	      height: "140px"
	    }
	  }, [_c('div', {
	    staticStyle: {
	      flex: "0.2",
	      justifyContent: "center",
	      alignItems: "center"
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "90px",
	      height: "90px",
	      marginLeft: "24px"
	    },
	    attrs: {
	      "src": "images/4.1.1/icontongzhi.png"
	    }
	  })]), _c('div', {
	    staticStyle: {
	      flex: "0.6",
	      justifyContent: "center",
	      marginLeft: "30px",
	      borderBottomWidth: "1px",
	      borderColor: "#cbcbcb"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "32px",
	      color: "#333"
	    }
	  }, [_vm._v("通知")]), _c('text', {
	    staticStyle: {
	      fontSize: "24px",
	      marginTop: "20px",
	      color: "#999"
	    }
	  }, [_vm._v("恭喜您成为承接人")])]), _c('div', {
	    staticStyle: {
	      flex: "0.2",
	      borderBottomWidth: "1px",
	      borderColor: "#cbcbcb"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "20px",
	      color: "#999",
	      lineHeight: "90px",
	      marginLeft: "10px"
	    }
	  }, [_vm._v("10:48:03")])])]), _c('div', {
	    staticClass: ["content_gz"],
	    staticStyle: {
	      flexDirection: "row",
	      height: "140px"
	    }
	  }, [_c('div', {
	    staticStyle: {
	      flex: "0.2",
	      justifyContent: "center",
	      alignItems: "center"
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "90px",
	      height: "90px",
	      marginLeft: "24px"
	    },
	    attrs: {
	      "src": "images/4.1.1/iconkejie.png"
	    }
	  })]), _c('div', {
	    staticStyle: {
	      flex: "0.6",
	      justifyContent: "center",
	      marginLeft: "30px",
	      borderBottomWidth: "1px",
	      borderColor: "#cbcbcb"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "32px",
	      color: "#333"
	    }
	  }, [_vm._v("可接任务")]), _c('text', {
	    staticStyle: {
	      fontSize: "24px",
	      marginTop: "20px",
	      color: "#999"
	    }
	  }, [_vm._v("刘莉莉发布了设计任务")])]), _c('div', {
	    staticStyle: {
	      flex: "0.2",
	      borderBottomWidth: "1px",
	      borderColor: "#cbcbcb"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "20px",
	      color: "#999",
	      lineHeight: "90px",
	      marginLeft: "10px"
	    }
	  }, [_vm._v("10:48:03")])])]), _c('div', {
	    staticClass: ["content_gz"],
	    staticStyle: {
	      flexDirection: "row",
	      height: "140px"
	    }
	  }, [_c('div', {
	    staticStyle: {
	      flex: "0.2",
	      justifyContent: "center",
	      alignItems: "center"
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "90px",
	      height: "90px",
	      marginLeft: "24px"
	    },
	    attrs: {
	      "src": "images/4.1.1/icondaiban.png"
	    }
	  })]), _c('div', {
	    staticStyle: {
	      flex: "0.6",
	      justifyContent: "center",
	      marginLeft: "30px",
	      borderBottomWidth: "1px",
	      borderColor: "#cbcbcb"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "32px",
	      color: "#333"
	    }
	  }, [_vm._v("待办")]), _c('text', {
	    staticStyle: {
	      fontSize: "24px",
	      marginTop: "20px",
	      color: "#999"
	    }
	  }, [_vm._v("您好，我的货物什么时候过来取呢？")])]), _c('div', {
	    staticStyle: {
	      flex: "0.2",
	      borderBottomWidth: "1px",
	      borderColor: "#cbcbcb"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "20px",
	      color: "#999",
	      lineHeight: "90px",
	      marginLeft: "20px"
	    }
	  }, [_vm._v("10:48:03")])])]), _c('div', {
	    staticClass: ["content_gz"],
	    staticStyle: {
	      flexDirection: "row",
	      height: "140px"
	    }
	  }, [_c('div', {
	    staticStyle: {
	      flex: "0.2",
	      justifyContent: "center",
	      alignItems: "center"
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "90px",
	      height: "90px",
	      marginLeft: "24px"
	    },
	    attrs: {
	      "src": "images/4.1.1/iconpingtai.png"
	    }
	  })]), _c('div', {
	    staticStyle: {
	      flex: "0.6",
	      justifyContent: "center",
	      marginLeft: "30px",
	      borderBottomWidth: "1px",
	      borderColor: "#cbcbcb"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "32px",
	      color: "#333"
	    }
	  }, [_vm._v("平台服务")]), _c('text', {
	    staticStyle: {
	      fontSize: "24px",
	      marginTop: "20px",
	      color: "#999"
	    }
	  }, [_vm._v("您好，我的货物什么时候过来取呢？")])]), _c('div', {
	    staticStyle: {
	      flex: "0.2",
	      borderBottomWidth: "1px",
	      borderColor: "#cbcbcb"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "20px",
	      color: "#999",
	      lineHeight: "90px",
	      marginLeft: "20px"
	    }
	  }, [_vm._v("10:48:03")])])]), _c('div', {
	    staticClass: ["content_gz"],
	    staticStyle: {
	      flexDirection: "row",
	      height: "140px"
	    }
	  }, [_c('div', {
	    staticStyle: {
	      flex: "0.2",
	      justifyContent: "center",
	      alignItems: "center"
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "90px",
	      height: "90px",
	      marginLeft: "24px"
	    },
	    attrs: {
	      "src": "images/4.1.1/icontongzhi.png"
	    }
	  })]), _c('div', {
	    staticStyle: {
	      flex: "0.6",
	      justifyContent: "center",
	      marginLeft: "30px",
	      borderBottomWidth: "1px",
	      borderColor: "#cbcbcb"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "32px",
	      color: "#333"
	    }
	  }, [_vm._v("张三")]), _c('text', {
	    staticStyle: {
	      fontSize: "24px",
	      marginTop: "20px",
	      color: "#999"
	    }
	  }, [_vm._v("上次那个文档再发一次")])]), _c('div', {
	    staticStyle: {
	      flex: "0.2",
	      borderBottomWidth: "1px",
	      borderColor: "#cbcbcb"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "20px",
	      color: "#999",
	      lineHeight: "90px",
	      marginLeft: "20px"
	    }
	  }, [_vm._v("10:48:03")])])])])
	}]}
	module.exports.render._withStripped = true

/***/ }),

/***/ 150:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["message-container"]
	  }, [_c('top-bar', {
	    attrs: {
	      "title": "消息",
	      "back": false
	    }
	  }), _vm._m(0), _c('content-ge')], 1)
	},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["MES_top"]
	  }, [_c('input', {
	    staticClass: ["input"],
	    attrs: {
	      "type": "text",
	      "placeholder": "请输入搜索的联系人",
	      "value": "请输入搜索的联系人"
	    }
	  }), _c('text', {
	    staticStyle: {
	      color: "#17ACF6",
	      height: "28px",
	      position: "absolute",
	      right: "120px",
	      top: "28px"
	    }
	  }, [_vm._v("|")]), _c('image', {
	    staticStyle: {
	      width: "37px",
	      height: "37px",
	      position: "absolute",
	      right: "56px",
	      top: "28px"
	    },
	    attrs: {
	      "src": "images/4.1.1/icon_Search.png"
	    }
	  })])
	}]}
	module.exports.render._withStripped = true

/***/ })

/******/ });