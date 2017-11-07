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
	__vue_styles__.push(__webpack_require__(232)
	)

	/* script */
	__vue_exports__ = __webpack_require__(233)

	/* template */
	var __vue_template__ = __webpack_require__(234)
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
	__vue_options__.__file = "/Users/wangzixiao/Desktop/netplatform/src/my/my.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-ab161468"
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

/***/ 56:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(57)
	)
	__vue_styles__.push(__webpack_require__(58)
	)

	/* script */
	__vue_exports__ = __webpack_require__(59)

	/* template */
	var __vue_template__ = __webpack_require__(60)
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
	__vue_options__.__file = "/Users/wangzixiao/Desktop/netplatform/src/vue/component/head.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-b29bd264"
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

/***/ 57:
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
	    "color": "#ff6e15"
	  },
	  "theme_bg": {
	    "color": "#ff6e15"
	  },
	  "mask": {
	    "backgroundColor": "#000000",
	    "opacity": 0.6,
	    "position": "absolute",
	    "left": 0,
	    "top": 0,
	    "bottom": 0,
	    "right": 0
	  },
	  "progress": {
	    "width": 220,
	    "height": 220,
	    "opacity": 0.8,
	    "backgroundColor": "#000000",
	    "borderRadius": 30,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "position": "absolute",
	    "left": 265,
	    "top": 300
	  }
	}

/***/ }),

/***/ 58:
/***/ (function(module, exports) {

	module.exports = {
	  "layout": {
	    "backgroundColor": "#333333",
	    "height": 128,
	    "width": 750,
	    "flexDirection": "row",
	    "alignItems": "center",
	    "justifyContent": "center",
	    "position": "relative"
	  },
	  "back": {
	    "height": 80,
	    "width": 80,
	    "top": 42,
	    "position": "absolute",
	    "left": 0
	  },
	  "add": {
	    "height": 33,
	    "width": 33,
	    "top": 62,
	    "position": "absolute",
	    "right": 30
	  },
	  "search": {
	    "height": 33,
	    "width": 34,
	    "top": 62,
	    "position": "absolute",
	    "right": 30
	  }
	}

/***/ }),

/***/ 59:
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


	exports.default = {
	    props: {
	        title: {
	            default: ''

	        },
	        back: {
	            default: true
	        },
	        headAdd: {
	            default: false
	        },
	        headSearch: {
	            default: false
	        },
	        bgcolor: {
	            default: '#17ACF6'

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
	        }

	    },
	    data: function data() {
	        return {

	            visiable: true

	        };
	    },

	    methods: {
	        backTo: function backTo() {
	            var nav = weex.requireModule("navigator");
	            nav.back();
	        },
	        add: function add() {
	            var nav = weex.requireModule("navigator");
	            //              nav.add();
	        },
	        search: function search() {
	            var nav = weex.requireModule("navigator");
	            nav.push('root:my/search.js');
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

/***/ 60:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["layout"],
	    style: {
	      'background-color': _vm.bgcolor
	    }
	  }, [(_vm.back) ? _c('div', {
	    staticClass: ["back"],
	    on: {
	      "click": _vm.backTo
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "80px",
	      height: "80px"
	    },
	    attrs: {
	      "src": "../images/back.png"
	    }
	  })]) : _vm._e(), _c('text', {
	    staticStyle: {
	      flex: "1",
	      color: "#ffffff",
	      textAlign: "center",
	      marginTop: "30",
	      fontSize: "35",
	      fontWeight: "bold"
	    }
	  }, [_vm._v(_vm._s(_vm.title))]), (_vm.headAdd) ? _c('div', {
	    staticClass: ["add"],
	    on: {
	      "click": _vm.add
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "33px",
	      height: "33px"
	    },
	    attrs: {
	      "src": "root:/6.3.1/iconadd.png"
	    }
	  })]) : _vm._e(), (_vm.headSearch) ? _c('div', {
	    staticClass: ["search"],
	    on: {
	      "click": _vm.search
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "34px",
	      height: "33px"
	    },
	    attrs: {
	      "src": "../images/my/head_search.png"
	    }
	  })]) : _vm._e()])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ }),

/***/ 232:
/***/ (function(module, exports) {

	module.exports = {
	  "me-container": {
	    "backgroundRepeat": "no-repeat",
	    "backgroundImage": "url(\"images/7.1.0-me/bg.png\")",
	    "width": 750,
	    "fontSize": 30
	  },
	  "title": {
	    "marginTop": 30,
	    "fontSize": 36,
	    "color": "#FFFFFF"
	  },
	  "shezhi_img": {
	    "width": 34,
	    "height": 36,
	    "position": "absolute",
	    "marginLeft": 275,
	    "marginTop": 20
	  },
	  "top_bar": {
	    "flexDirection": "row",
	    "justifyContent": "center",
	    "alignItems": "center",
	    "backgroundColor": "#17acf6",
	    "width": 750,
	    "height": 100
	  },
	  "my_data": {
	    "backgroundRepeat": "no-repeat",
	    "backgroundImage": "url(\"/images/7.1.0-me/bg1.png\")",
	    "width": 710,
	    "marginTop": 38
	  },
	  "head_name": {
	    "height": 230,
	    "margin": 0,
	    "padding": 0
	  },
	  "head_area": {
	    "justifyContent": "center",
	    "flexDirection": "row"
	  },
	  "head_img_image": {
	    "borderRadius": 120,
	    "height": 120,
	    "width": 120,
	    "marginTop": 5
	  },
	  "shiming_image": {
	    "height": 28,
	    "width": 28,
	    "position": "absolute",
	    "marginLeft": -35,
	    "marginTop": 106
	  },
	  "name_area": {
	    "flexDirection": "row",
	    "justifyContent": "center",
	    "marginTop": 10
	  },
	  "renzheng_image": {
	    "height": 28,
	    "width": 28,
	    "marginLeft": 8
	  },
	  "star_area": {
	    "marginLeft": 28
	  },
	  "leval": {
	    "flexDirection": "row",
	    "justifyContent": "flex-start",
	    "margin": 0
	  },
	  "credit": {
	    "flexDirection": "row",
	    "justifyContent": "flex-start",
	    "margin": 0,
	    "marginTop": 33
	  },
	  "star": {
	    "flexDirection": "row",
	    "justifyContent": "flex-start",
	    "marginLeft": 13
	  },
	  "star_image": {
	    "width": 33,
	    "height": 33,
	    "marginLeft": 20
	  },
	  "data_area": {
	    "marginTop": 72,
	    "height": 100,
	    "flexDirection": "row",
	    "justifyContent": "center"
	  },
	  "data": {
	    "height": 100,
	    "paddingLeft": 33,
	    "paddingRight": 33,
	    "borderRightStyle": "solid",
	    "borderRightColor": "#84b5ff",
	    "borderRightWidth": 2
	  },
	  "data_4": {
	    "height": 100,
	    "paddingLeft": 33,
	    "paddingRight": 33
	  },
	  "text_number": {
	    "color": "#ffa529",
	    "fontSize": 30,
	    "textAlign": "center"
	  },
	  "text_dataname": {
	    "marginTop": 18,
	    "color": "#17acf6",
	    "fontSize": 24
	  },
	  "my_task": {
	    "marginTop": 40,
	    "height": 228,
	    "backgroundColor": "#ffffff",
	    "flexDirection": "row",
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "task_1": {
	    "width": 228,
	    "height": 228,
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "task": {
	    "width": 231,
	    "height": 228,
	    "marginLeft": 10,
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "text_task": {
	    "fontSize": 25
	  },
	  "task_inner": {
	    "width": 231,
	    "height": 109,
	    "flexDirection": "row",
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "my_act": {
	    "height": 300,
	    "marginTop": 18
	  },
	  "act_area": {
	    "height": 150,
	    "flexDirection": "row"
	  },
	  "act": {
	    "height": 150,
	    "width": 187.5,
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "img_act": {
	    "height": 64,
	    "width": 64
	  },
	  "text_act": {
	    "marginTop": 14,
	    "fontSize": 25,
	    "textAlign": "center"
	  }
	}

/***/ }),

/***/ 233:
/***/ (function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _head = __webpack_require__(56);

	var _head2 = _interopRequireDefault(_head);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	exports.default = {
	    components: {
	        TopBar: _head2.default

	    },
	    data: {},
	    methods: {
	        turnToIntegral: function turnToIntegral() {
	            var nav = weex.requireModule("navigator");
	            nav.push('/integralAdministration.js');
	        }
	    }
	    /*  create(){
	        /!*  var nav=weex.requireModule('navigator');
	          nav.setPageId('my');*!/
	    //            nav.pushParam('nav1.js',{a:'这是传过来的值'});
	      }*/
	}; //
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

	module.exports = exports['default'];

/***/ }),

/***/ 234:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('scroller', [_c('div', {
	    staticClass: ["me-container"],
	    staticStyle: {
	      backgroundColor: "#fff"
	    }
	  }, [_c('TopBar', {
	    attrs: {
	      "title": "我的",
	      "back": false
	    }
	  }), _c('div', {
	    staticStyle: {
	      width: "750px",
	      justifyContent: "center",
	      flexDirection: "row",
	      backgroundColor: "#fff"
	    }
	  }, [_c('div', {
	    staticClass: ["my_data"]
	  }, [_c('div', {
	    staticClass: ["head_name"]
	  }, [_c('a', {
	    attrs: {
	      "href": "/myInfo.js"
	    }
	  }, [_c('div', {
	    staticClass: ["head_area"]
	  }, [_c('image', {
	    staticClass: ["head_img_image"],
	    attrs: {
	      "src": "../images/7.1.0-me/user_img.png"
	    }
	  }), _c('image', {
	    staticClass: ["shiming_image"],
	    attrs: {
	      "src": "../images/7.1.0-me/shiming.png"
	    }
	  })])]), _vm._m(0)], 1), _vm._m(1), _vm._m(2)])]), _c('div', {
	    staticClass: ["my_task"]
	  }, [_vm._m(3), _c('div', {
	    staticClass: ["task"]
	  }, [_c('a', {
	    attrs: {
	      "href": "../task/Task_Published.js"
	    }
	  }, [_c('div', {
	    staticClass: ["task_inner"]
	  }, [_c('image', {
	    staticClass: ["img_taskinner"],
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/faburenwu.png"
	    }
	  }), _c('text', {
	    staticClass: ["text_task"]
	  }, [_vm._v("发布的任务")])])]), _c('a', {
	    attrs: {
	      "href": "../task/Task_Participant.js"
	    }
	  }, [_c('div', {
	    staticClass: ["task_inner"],
	    staticStyle: {
	      marginTop: "10px"
	    }
	  }, [_c('image', {
	    staticClass: ["img_taskinner"],
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/canyude.png"
	    }
	  }), _c('text', {
	    staticClass: ["text_task"]
	  }, [_vm._v("参与的任务")])])])], 1), _c('div', {
	    staticClass: ["task"]
	  }, [_c('a', {
	    attrs: {
	      "href": "../task/Check.js"
	    }
	  }, [_c('div', {
	    staticClass: ["task_inner"]
	  }, [_c('image', {
	    staticClass: ["img_taskinner"],
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/shenherenwu.png"
	    }
	  }), _c('text', {
	    staticClass: ["text_task"]
	  }, [_vm._v("审核的任务")])])]), _vm._m(4)], 1)]), _c('div', {
	    staticClass: ["my_act"]
	  }, [_c('div', {
	    staticClass: ["act_area"]
	  }, [_c('a', {
	    attrs: {
	      "href": "/realName.js"
	    }
	  }, [_c('div', {
	    staticClass: ["act"]
	  }, [_c('image', {
	    staticClass: ["img_act"],
	    attrs: {
	      "src": "../images/7.1.0-me/shimingg.png"
	    }
	  }), _c('text', {
	    staticClass: ["text_act"]
	  }, [_vm._v("实名认证")])])]), _c('a', {
	    attrs: {
	      "href": ""
	    }
	  }, [_c('div', {
	    staticClass: ["act"]
	  }, [_c('image', {
	    staticClass: ["img_act"],
	    attrs: {
	      "src": "../images/7.1.0-me/zijin.png"
	    }
	  }), _c('text', {
	    staticClass: ["text_act"]
	  }, [_vm._v("资金管理")])])]), _c('a', {
	    attrs: {
	      "href": "AfterEditEligibleApplyFor.js"
	    }
	  }, [_c('div', {
	    staticClass: ["act"]
	  }, [_c('image', {
	    staticClass: ["img_act"],
	    attrs: {
	      "src": "../images/7.1.0-me/jineng.png"
	    }
	  }), _c('text', {
	    staticClass: ["text_act"]
	  }, [_vm._v("技能管理")])])]), _c('a', {
	    attrs: {
	      "href": "/storeGuanli.js"
	    }
	  }, [_c('div', {
	    staticClass: ["act"]
	  }, [_c('image', {
	    staticClass: ["img_act"],
	    attrs: {
	      "src": "../images/7.1.0-me/dianu.png"
	    }
	  }), _c('text', {
	    staticClass: ["text_act"]
	  }, [_vm._v("商铺管理")])])])], 1), _c('div', {
	    staticClass: ["act_area"],
	    staticStyle: {
	      marginBottom: "150"
	    }
	  }, [_c('a', {
	    attrs: {
	      "href": "/set.js"
	    }
	  }, [_c('div', {
	    staticClass: ["act"]
	  }, [_c('image', {
	    staticClass: ["img_act"],
	    attrs: {
	      "src": "../images/7.1.0-me/shequ.png"
	    }
	  }), _c('text', {
	    staticClass: ["text_act"]
	  }, [_vm._v("社区")])])]), _c('a', {
	    attrs: {
	      "href": "/subscriptionManage.js"
	    }
	  }, [_c('div', {
	    staticClass: ["act"]
	  }, [_c('image', {
	    staticClass: ["img_act"],
	    attrs: {
	      "src": "../images/7.1.0-me/tuisong.png"
	    }
	  }), _c('text', {
	    staticClass: ["text_act"]
	  }, [_vm._v("推送订阅")])])]), _c('div', {
	    staticClass: ["act"],
	    on: {
	      "click": _vm.turnToIntegral
	    }
	  }, [_c('image', {
	    staticClass: ["img_act"],
	    attrs: {
	      "src": "../images/7.1.0-me/jifen.png"
	    }
	  }), _c('text', {
	    staticClass: ["text_act"]
	  }, [_vm._v("积分管理")])]), _c('a', {
	    attrs: {
	      "href": "/myEvaluate.js"
	    }
	  }, [_c('div', {
	    staticClass: ["act"]
	  }, [_c('image', {
	    staticClass: ["img_act"],
	    attrs: {
	      "src": "../images/7.1.0-me/shenherenwu.png"
	    }
	  }), _c('text', {
	    staticClass: ["text_act"]
	  }, [_vm._v("评价")])])])], 1)])], 1)])
	},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["name_area"],
	    staticStyle: {
	      backgroundColor: "#fff"
	    }
	  }, [_c('text', {}, [_vm._v("赵四")]), _c('image', {
	    staticClass: ["renzheng_image"],
	    attrs: {
	      "src": "/images/7.1.0-me/renzheng.png"
	    }
	  })])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["star_area"]
	  }, [_c('div', {
	    staticClass: ["leval"],
	    staticStyle: {
	      backgroundColor: "#fff"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "30px"
	    }
	  }, [_vm._v("等级：")]), _c('div', {
	    staticClass: ["star"]
	  }, [_c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": "../images/7.1.0-me/xinyong.png"
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": "../images/7.1.0-me/xinyong.png"
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": "../images/7.1.0-me/xinyong.png"
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": "../images/7.1.0-me/xinyong.png"
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": "../images/7.1.0-me/weixinyong.png"
	    }
	  })])]), _c('div', {
	    staticClass: ["credit"],
	    staticStyle: {
	      backgroundColor: "#fff"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "30px"
	    }
	  }, [_vm._v("信用：")]), _c('div', {
	    staticClass: ["star"]
	  }, [_c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": "../images/7.1.0-me/zuanshse.png"
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": "../images/7.1.0-me/zuanshse.png"
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": "../images/7.1.0-me/zuanshse.png"
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": "../images/7.1.0-me/zuanshse.png"
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": "../images/7.1.0-me/zuanshi.png"
	    }
	  })])])])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["data_area"],
	    staticStyle: {
	      width: "750px"
	    }
	  }, [_c('div', {
	    staticClass: ["data"]
	  }, [_c('div', {
	    staticClass: ["number"]
	  }, [_c('text', {
	    staticClass: ["text_number"],
	    staticStyle: {
	      margin: "0px"
	    }
	  }, [_vm._v("150")])]), _c('div', {
	    staticClass: ["dataname"]
	  }, [_c('text', {
	    staticClass: ["text_dataname"],
	    staticStyle: {
	      margin: "0px"
	    }
	  }, [_vm._v("发布任务数")])])]), _c('div', {
	    staticClass: ["data"]
	  }, [_c('div', {
	    staticClass: ["number"]
	  }, [_c('text', {
	    staticClass: ["text_number"],
	    staticStyle: {
	      margin: "0px"
	    }
	  }, [_vm._v("150")])]), _c('div', {
	    staticClass: ["dataname"]
	  }, [_c('text', {
	    staticClass: ["text_dataname"],
	    staticStyle: {
	      margin: "0px"
	    }
	  }, [_vm._v("参与任务数")])])]), _c('div', {
	    staticClass: ["data"]
	  }, [_c('div', {
	    staticClass: ["number"]
	  }, [_c('text', {
	    staticClass: ["text_number"],
	    staticStyle: {
	      margin: "0px"
	    }
	  }, [_vm._v("300")])]), _c('div', {
	    staticClass: ["dataname"]
	  }, [_c('text', {
	    staticClass: ["text_dataname"],
	    staticStyle: {
	      margin: "0px"
	    }
	  }, [_vm._v("任务支出额")])])]), _c('div', {
	    staticClass: ["data_4"]
	  }, [_c('div', {
	    staticClass: ["number"]
	  }, [_c('text', {
	    staticClass: ["text_number"],
	    staticStyle: {
	      margin: "0px"
	    }
	  }, [_vm._v("300")])]), _c('div', {
	    staticClass: ["dataname"]
	  }, [_c('text', {
	    staticClass: ["text_dataname"],
	    staticStyle: {
	      margin: "0px"
	    }
	  }, [_vm._v("任务收入额")])])])])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["task_1"]
	  }, [_c('image', {
	    staticClass: ["img_task"],
	    staticStyle: {
	      width: "64px",
	      height: "64px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/fabuxuqiu.png"
	    }
	  }), _c('text', {
	    staticClass: ["text_task"]
	  }, [_vm._v("发布的需求")])])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["task_inner"],
	    staticStyle: {
	      marginTop: "10px"
	    }
	  }, [_c('image', {
	    staticClass: ["img_taskinner"],
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/guanzhude.png"
	    }
	  }), _c('text', {
	    staticClass: ["text_task"]
	  }, [_vm._v("关注的任务")])])
	}]}
	module.exports.render._withStripped = true

/***/ })

/******/ });