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
	__vue_styles__.push(__webpack_require__(315)
	)

	/* script */
	__vue_exports__ = __webpack_require__(316)

	/* template */
	var __vue_template__ = __webpack_require__(317)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/refresh.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-1289bd54"
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

/***/ 231:
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

/***/ 232:
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

/***/ 233:
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

/***/ 234:
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

/***/ 315:
/***/ (function(module, exports) {

	module.exports = {
	  "scroller": {
	    "backgroundColor": "#ffffff"
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
	    "fontWeight": "bold"
	  },
	  "indicator": {
	    "color": "#888888",
	    "height": 40,
	    "width": 40,
	    "marginRight": 30
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

/***/ 316:
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

	var pull = __webpack_require__(231);
	var modal = weex.requireModule('modal');
	exports.default = {
	    data: function data() {
	        return {
	            refreshing: false,
	            lists: [1, 2, 3, 4, 5],
	            rtext: '下拉以加载'
	        };
	    },

	    components: { pull: pull },
	    methods: {
	        refresh: function refresh() {
	            var _this = this;

	            this.refreshing = true;
	            this.rtext = "加载中";
	            setTimeout(function () {
	                _this.refreshing = false;
	                _this.lists = [1, 2, 3, 4, 5];
	                var p = _this.$refs.refresh;
	                p.end();
	            }, 2000);
	        }
	    },
	    created: function created() {

	        var globalEvent = weex.requireModule('globalEvent');

	        globalEvent.addEventListener("onPageInit", function (e) {
	            var nav = weex.requireModule('navbar');
	            nav.setTitle('refresh');
	            nav.setBack(true);
	            nav.setRightImage('img/scan.png', function (res) {

	                var modal = weex.requireModule('modal');
	                modal.alert({ message: "ok" });
	            });
	        });
	    }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 317:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('scroller', {
	    staticClass: ["scroller"]
	  }, [_c('pull', {
	    ref: "refresh",
	    on: {
	      "refresh": _vm.refresh
	    }
	  }), _vm._l((_vm.lists), function(num) {
	    return _c('div', {
	      staticClass: ["cell"]
	    }, [_c('div', {
	      staticClass: ["panel"]
	    }, [_c('text', {
	      staticClass: ["text"]
	    }, [_vm._v(_vm._s(num))])])])
	  })], 2)
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });