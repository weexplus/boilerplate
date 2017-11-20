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
	__vue_styles__.push(__webpack_require__(313)
	)

	/* script */
	__vue_exports__ = __webpack_require__(314)

	/* template */
	var __vue_template__ = __webpack_require__(315)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/vlist.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-1d44504d"
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

/***/ 197:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(198)
	)

	/* script */
	__vue_exports__ = __webpack_require__(199)

	/* template */
	var __vue_template__ = __webpack_require__(200)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/pullrefresh.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-017e496f"
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

/***/ 198:
/***/ (function(module, exports) {

	module.exports = {
	  "limg": {
	    "width": 32,
	    "height": 46
	  },
	  "refresh": {
	    "height": 128,
	    "width": 750,
	    "flexDirection": "row",
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "refreshText": {
	    "color": "#888888",
	    "fontSize": 30
	  },
	  "indicator": {
	    "color": "#888888",
	    "height": 40,
	    "width": 40,
	    "marginRight": 10
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
	    "borderColor": "#DDDDDD",
	    "backgroundColor": "#F5F5F5"
	  },
	  "text": {
	    "fontSize": 50,
	    "textAlign": "center",
	    "color": "#41B883"
	  }
	}

/***/ }),

/***/ 199:
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


	exports.default = {
	    data: function data() {
	        return {
	            refreshing: false,
	            rtext: '下拉以加载',
	            updatetime: '没有更新',
	            offset: 0,
	            deg: 20,
	            pulldistance: 180

	        };
	    },

	    methods: {
	        onrefresh: function onrefresh(event) {
	            if (this.offset >= this.pulldistance) {
	                this.refreshing = true;
	                this.rtext = "加载中";
	                this.$emit('refresh');
	            }
	        },
	        end: function end() {
	            this.refreshing = false;
	            this.deg = 0;
	            this.updatetime = this.getNowFormatDate();
	            //                this.rtext='下拉以加载'
	        },
	        onpullingdown: function onpullingdown(event) {

	            var dis = event.pullingDistance * -1;
	            this.offset = dis;

	            if (this.refreshing == false) {

	                if (dis > this.pulldistance) {
	                    this.rtext = "松开刷新";
	                    this.deg = 180;
	                } else {
	                    this.deg = dis / this.pulldistance * 180;
	                    this.rtext = '下拉以加载';
	                }
	            }
	        },
	        getNowFormatDate: function getNowFormatDate() {
	            var date = new Date();
	            var seperator1 = "-";
	            var seperator2 = ":";
	            var month = date.getMonth() + 1;
	            var strDate = date.getDate();
	            if (month >= 1 && month <= 9) {
	                month = "0" + month;
	            }
	            if (strDate >= 0 && strDate <= 9) {
	                strDate = "0" + strDate;
	            }
	            var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate + " " + date.getHours() + seperator2 + date.getMinutes() + seperator2 + date.getSeconds();
	            return currentdate;
	        }
	    },

	    created: function created() {}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 200:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('refresh', {
	    staticClass: ["refresh"],
	    attrs: {
	      "id": "rex",
	      "display": _vm.refreshing ? 'show' : 'hide'
	    },
	    on: {
	      "refresh": _vm.onrefresh,
	      "pullingdown": _vm.onpullingdown
	    }
	  }, [_c('div', {
	    staticStyle: {
	      flexDirection: "row"
	    }
	  }, [_c('div', {
	    staticStyle: {
	      width: "50",
	      height: "50",
	      position: "absolute"
	    },
	    style: {
	      visibility: _vm.refreshing ? 'show' : 'hidden'
	    }
	  }, [_c('floading', {
	    staticClass: ["indicator"]
	  })], 1), _c('image', {
	    ref: "ll",
	    staticClass: ["limg"],
	    style: {
	      transform: 'rotate(' + _vm.deg + 'deg)',
	      visibility: _vm.refreshing ? 'hidden' : 'show'
	    },
	    attrs: {
	      "src": "img/pull_arrow.png"
	    }
	  }), _c('div', {
	    staticStyle: {
	      alignItems: "center"
	    }
	  }, [_c('text', {
	    staticClass: ["refreshText"]
	  }, [_vm._v(_vm._s(_vm.rtext))]), _c('text', {
	    staticStyle: {
	      fontSize: "25",
	      color: "#888888"
	    }
	  }, [_vm._v("上次更新:" + _vm._s(_vm.updatetime))])])])])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ }),

/***/ 313:
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

/***/ 314:
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


	var pull = __webpack_require__(197);
	var modal = weex.requireModule('modal');
	var LOADMORE_COUNT = 10;

	exports.default = {
	  components: { pull: pull },
	  data: function data() {
	    return {

	      showEmpty: false,
	      showExpetion: false
	    };
	  },

	  methods: {
	    tk: function tk() {
	      this.showEmpty = !this.showEmpty;
	    },
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

/***/ 315:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticStyle: {
	      flex: "1"
	    }
	  }, [_c('list', {
	    staticClass: ["list"],
	    on: {
	      "loadmore": _vm.fetch
	    }
	  }, [_c('pull', {
	    ref: "refresh",
	    on: {
	      "refresh": _vm.refresh
	    }
	  }), (_vm.showEmpty) ? _c('cell', {
	    appendAsTree: true,
	    attrs: {
	      "append": "tree"
	    }
	  }, [_vm._m(0)]) : _vm._e(), (_vm.showExpetion) ? _c('cell', {
	    appendAsTree: true,
	    attrs: {
	      "append": "tree"
	    }
	  }, [_vm._m(1)]) : _vm._e(), _c('cell', {
	    appendAsTree: true,
	    attrs: {
	      "append": "tree"
	    }
	  }, [_c('div', {
	    staticStyle: {
	      width: "750",
	      height: "150",
	      justifyContent: "center",
	      alignItems: "center",
	      backgroundColor: "red"
	    },
	    on: {
	      "click": function($event) {
	        _vm.tk()
	      }
	    }
	  })]), _vm._t("left")], 2)])
	},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticStyle: {
	      width: "750",
	      height: "750",
	      justifyContent: "center",
	      alignItems: "center",
	      backgroundColor: "#eeeeee"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      textAlign: "center",
	      flex: "1",
	      marginTop: "450"
	    }
	  }, [_vm._v("暂无内容")])])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticStyle: {
	      width: "750",
	      height: "750",
	      justifyContent: "center",
	      alignItems: "center",
	      backgroundColor: "#eeeeee"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      textAlign: "center",
	      flex: "1",
	      marginTop: "450"
	    }
	  }, [_vm._v("网络异常")])])
	}]}
	module.exports.render._withStripped = true

/***/ })

/******/ });