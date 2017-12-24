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
	__vue_styles__.push(__webpack_require__(310)
	)

	/* script */
	__vue_exports__ = __webpack_require__(311)

	/* template */
	var __vue_template__ = __webpack_require__(312)
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
	__vue_options__.__file = "/Users/wangzixiao/Desktop/netplatform/src/task/Task.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-7f0aba4c"
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

/***/ 20:
/***/ (function(module, exports) {

	module.exports = {
	  "pubtime": {
	    "borderWidth": 1,
	    "borderRadius": 10,
	    "width": 700
	  },
	  "top": {
	    "height": 50,
	    "width": 700,
	    "backgroundColor": "#f5f5f5",
	    "justifyContent": "center",
	    "flexDirection": "row",
	    "borderBottomStyle": "solid",
	    "borderBottomWidth": 1,
	    "borderBottomColor": "#dedede"
	  },
	  "detail": {
	    "paddingTop": 15,
	    "paddingBottom": 15,
	    "marginLeft": 24,
	    "marginRight": 10,
	    "borderBottomStyle": "solid",
	    "borderBottomWidth": 1,
	    "borderBottomColor": "#eeeeee"
	  },
	  "details": {
	    "height": 80,
	    "marginLeft": 24,
	    "justifyContent": "flex-start",
	    "flexDirection": "row",
	    "alignItems": "center"
	  },
	  "time_wrapper": {
	    "flexDirection": "row",
	    "alignItems": "center",
	    "position": "absolute",
	    "right": 10,
	    "top": 20
	  },
	  "time": {
	    "fontSize": 24,
	    "color": "#999999",
	    "textAlign": "center",
	    "lineHeight": 50
	  },
	  "titleC": {
	    "fontSize": 28,
	    "color": "#000000"
	  },
	  "character": {
	    "fontSize": 24,
	    "color": "#9d9d9d",
	    "marginTop": 24
	  },
	  "img": {
	    "height": 38,
	    "width": 38,
	    "marginRight": 5
	  },
	  "overtime": {
	    "fontSize": 26,
	    "marginLeft": 10,
	    "color": "#acacac"
	  },
	  "g_reen": {
	    "fontSize": 26,
	    "color": "#44cc00",
	    "position": "relative",
	    "marginRight": 10,
	    "marginLeft": 10
	  },
	  "o_range": {
	    "color": "#ff9900",
	    "position": "relative",
	    "marginRight": 10
	  },
	  "r_ed": {
	    "color": "#ff6666",
	    "position": "relative",
	    "marginRight": 10
	  },
	  "g_rey": {
	    "color": "#7e99ab",
	    "position": "relative",
	    "marginRight": 10
	  },
	  "money": {
	    "color": "#fc9c0c"
	  },
	  "days": {
	    "color": "#acacac"
	  },
	  "contentDetail": {
	    "backgroundColor": "#ffffff"
	  }
	}

/***/ }),

/***/ 21:
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
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
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
			list: {
				default: {}
			}
		},
		mounted: function mounted() {
			console.log(this.list);
		},

		computed: {
			stateText: function stateText() {
				if (this.list.taskState == '报名中') {
					return this.list.bmjz ? this.list.bmjz + '天截止' : this.list.xmsysj + '天截止';
				}
				if (this.list.taskState == '进行中') {
					return '距结束还有' + this.list.xmsysj + '天';
				}
				if (this.list.taskState == '已完成') {
					return '';
				}
				if (this.list.taskState == '评标中' || this.list.taskState == '验收中') {
					return '距结束还有' + this.list.xmsysj + '天';
				}
			}
		},
		methods: {
			TaskItem: function TaskItem(e) {
				this.$emit('taskitem', e);
			}
		}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 22:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["pubtime"],
	    on: {
	      "click": function($event) {
	        _vm.TaskItem(_vm.list.taskCode)
	      }
	    }
	  }, [_c('div', {
	    staticClass: ["top"]
	  }, [_c('text', {
	    staticClass: ["time"]
	  }, [_vm._v("发布时间：" + _vm._s(_vm.list.publishTime))])]), _c('div', {
	    staticClass: ["contentDetail"]
	  }, [_c('div', {
	    staticClass: ["detail"]
	  }, [_c('text', {
	    staticClass: ["titleC"]
	  }, [_vm._v(_vm._s(_vm.list.taskName))]), _c('text', {
	    staticClass: ["character"]
	  }, [_vm._v("项目类型：" + _vm._s(_vm.list.demandProTypeName) + " | 施工地点：河北廊坊")])]), _c('div', {
	    staticClass: ["details"]
	  }, [_c('div', {
	    staticStyle: {
	      justifyContent: "flex-start",
	      flexDirection: "row",
	      alignItems: "center",
	      marginRight: "20px"
	    }
	  }, [_c('image', {
	    staticClass: ["img"],
	    attrs: {
	      "src": "../images/amount.png"
	    }
	  }), _c('text', {
	    staticClass: ["money"]
	  }, [_vm._v(_vm._s(_vm.list.managePrice) + "万元")])]), _c('div', {
	    staticStyle: {
	      justifyContent: "flex-start",
	      flexDirection: "row",
	      alignItems: "center"
	    }
	  }, [_c('image', {
	    staticClass: ["img"],
	    attrs: {
	      "src": "../images/process.png"
	    }
	  }), _c('text', {
	    staticClass: ["days"]
	  }, [_vm._v(_vm._s(_vm.list.xmsysj) + "天")])]), _c('div', {
	    staticClass: ["time_wrapper"]
	  }, [_c('text', {
	    staticClass: ["overtime"]
	  }, [_vm._v(_vm._s(_vm.stateText))]), _c('text', {
	    staticClass: ["g_reen"]
	  }, [_vm._v(_vm._s(_vm.list.taskState))])])])])])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ }),

/***/ 112:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(20)
	)

	/* script */
	__vue_exports__ = __webpack_require__(21)

	/* template */
	var __vue_template__ = __webpack_require__(22)
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
	__vue_options__.__file = "/Users/wangzixiao/Desktop/netplatform/src/components/TaskContent.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-805a48e4"
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

/***/ 310:
/***/ (function(module, exports) {

	module.exports = {
	  "body": {
	    "backgroundColor": "#ffffff"
	  },
	  "CON_top": {
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
	  },
	  "top-bar": {
	    "position": "relative",
	    "height": 90,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "paddingLeft": 70,
	    "paddingRight": 70,
	    "backgroundColor": "#17acf6",
	    "borderBottomWidth": 1,
	    "borderBottomStyle": "solid",
	    "borderBottomColor": "#eeeeee"
	  },
	  "title": {
	    "height": 86,
	    "borderBottomWidth": 1,
	    "borderBottomStyle": "solid",
	    "borderBottomColor": "#d8d8d8"
	  },
	  "item": {
	    "height": 86,
	    "flex": 1,
	    "justifyContent": "flex-start",
	    "alignItems": "center"
	  },
	  "items": {
	    "height": 86,
	    "flex": 1,
	    "justifyContent": "flex-start",
	    "alignItems": "center"
	  },
	  "secTitle": {
	    "lineHeight": 86,
	    "height": 86,
	    "flexDirection": "row"
	  },
	  "row": {
	    "height": 86,
	    "flexDirection": "row"
	  },
	  "btnPro": {
	    "lineHeight": 86
	  },
	  "active": {
	    "height": 86,
	    "width": 182,
	    "justifyContent": "flex-start",
	    "alignItems": "center",
	    "borderBottomStyle": "solid",
	    "borderBottomWidth": 5,
	    "borderBottomColor": "#1caced"
	  },
	  "allEdu": {
	    "lineHeight": 86
	  },
	  "all": {
	    "borderBottomStyle": "solid",
	    "borderBottomWidth": 1,
	    "borderBottomColor": "#d8d8d8"
	  },
	  "content": {
	    "backgroundColor": "#eeeeee",
	    "height": 1000
	  },
	  "sort": {
	    "marginTop": 32,
	    "flexDirection": "row",
	    "marginBottom": 16
	  },
	  "sortbtn": {
	    "height": 58,
	    "width": 220,
	    "marginRight": 26,
	    "backgroundColor": "#ffffff",
	    "justifyContent": "center",
	    "alignItems": "center",
	    "borderRadius": 5,
	    "borderColor": "#d8d8d8"
	  }
	}

/***/ }),

/***/ 311:
/***/ (function(module, exports, __webpack_require__) {

	"use strict";

	var _TaskContent = __webpack_require__(112);

	var _TaskContent2 = _interopRequireDefault(_TaskContent);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	module.exports = {
	  components: {
	    taskcontent: _TaskContent2.default
	  },
	  data: function data() {
	    return {
	      lists: [{ "pubtime": "发布时间：03月19日", "protitle": "浙江液化天然气LNG加气站-施工图-设计经理", "protype": "中压管道", "workspace": "河北廊坊", "money": "60.2", "days": "120", "overtime": "3天5小时", "status": "报名中" }, { "pubtime": "发布时间：03月19日", "protitle": "浙江液化天然气LNG加气站-施工图-设计经理", "protype": "中压管道", "workspace": "河北廊坊", "money": "60.2", "days": "120", "overtime": "3天5小时", "status": "验收中" }, { "pubtime": "发布时间：03月19日", "protitle": "浙江液化天然气LNG加气站-施工图-设计经理", "protype": "中压管道", "workspace": "河北廊坊", "money": "60.2", "days": "120", "overtime": "3天5小时", "status": "已驳回" }],
	      imgList: [{ src: 'images/process.png' }, { src: 'images/process.png' }]
	    };
	  }
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
	//
	//
	//
	//

/***/ }),

/***/ 312:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["body"]
	  }, [_vm._m(0), _vm._m(1), _c('div', {
	    staticClass: ["all"]
	  }, [_vm._m(2), _c('div', {
	    staticClass: ["content"]
	  }, [_c('div', {
	    staticStyle: {
	      marginLeft: "26px",
	      marginRight: "26px"
	    }
	  }, [_vm._m(3), _vm._l((_vm.lists), function(item) {
	    return _c('div', [_c('taskcontent', {
	      attrs: {
	        "pubtime": item.pubtime,
	        "protitle": item.protitle,
	        "protype": item.protype,
	        "workspace": item.workspace,
	        "money": item.money,
	        "days": item.days,
	        "overtime": item.overtime,
	        "status": item.status
	      }
	    })], 1)
	  })], 2)])])])
	},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["CON_top"]
	  }, [_c('input', {
	    staticClass: ["input"],
	    attrs: {
	      "type": "text",
	      "placeholder": "请输入搜索的联系人",
	      "value": ""
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
	      "src": "../images/4.1.1/icon_Search.png"
	    }
	  })])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["title"]
	  }, [_c('div', {
	    staticClass: ["row"]
	  }, [_c('div', {
	    staticClass: ["item"]
	  }, [_c('div', {
	    staticClass: ["active"]
	  }, [_c('text', {
	    staticClass: ["btnPro"]
	  }, [_vm._v("全部任务")])])]), _c('div', {
	    staticClass: ["item"]
	  }, [_c('div', [_c('text', {
	    staticClass: ["btnPro"]
	  }, [_vm._v("适合我的任务")])])])])])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["row"]
	  }, [_c('div', {
	    staticClass: ["items"],
	    staticStyle: {
	      flex: "0.8",
	      marginLeft: "40px"
	    }
	  }, [_c('div', {
	    staticClass: ["secTitle"]
	  }, [_c('text', {
	    staticClass: ["allEdu"]
	  }, [_vm._v("所有行业")]), _c('image', {
	    staticStyle: {
	      height: "15px",
	      width: "15px",
	      marginTop: "35px",
	      marginLeft: "14px"
	    },
	    attrs: {
	      "src": "images/xiala.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      color: "#d8d8d8",
	      marginTop: "22px",
	      marginLeft: "22px"
	    }
	  }, [_vm._v("|")])])]), _c('div', {
	    staticClass: ["items"],
	    staticStyle: {
	      flex: "0.8"
	    }
	  }, [_c('div', {
	    staticClass: ["secTitle"]
	  }, [_c('text', {
	    staticClass: ["allEdu"]
	  }, [_vm._v("所有专业")]), _c('image', {
	    staticStyle: {
	      height: "15px",
	      width: "15px",
	      marginTop: "35px",
	      marginLeft: "14px"
	    },
	    attrs: {
	      "src": "images/xiala.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      color: "#d8d8d8",
	      marginTop: "20px",
	      marginLeft: "22px"
	    }
	  }, [_vm._v("|")])])]), _c('div', {
	    staticClass: ["items"],
	    staticStyle: {
	      flex: "1"
	    }
	  }, [_c('div', {
	    staticClass: ["secTitle"]
	  }, [_c('text', {
	    staticClass: ["allEdu"]
	  }, [_vm._v("所有任务类型")]), _c('image', {
	    staticStyle: {
	      height: "15px",
	      width: "15px",
	      marginTop: "35px",
	      marginLeft: "14px"
	    },
	    attrs: {
	      "src": "images/xiala.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      color: "#d8d8d8",
	      marginTop: "20px",
	      marginLeft: "22px"
	    }
	  }, [_vm._v("|")])])]), _c('div', {
	    staticClass: ["item"],
	    staticStyle: {
	      flex: "0.4"
	    }
	  }, [_c('div', {
	    staticClass: ["secTitle"]
	  }, [_c('text', {
	    staticClass: ["allEdu"]
	  }, [_vm._v("≶")])])])])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["sort"]
	  }, [_c('div', {
	    staticClass: ["sortbtn"]
	  }, [_c('text', [_vm._v("默认排序")])]), _c('div', {
	    staticClass: ["sortbtn"]
	  }, [_c('text', [_vm._v("发布时间")])]), _c('div', {
	    staticClass: ["sortbtn"]
	  }, [_c('text', [_vm._v("任务价格")])])])
	}]}
	module.exports.render._withStripped = true

/***/ })

/******/ });