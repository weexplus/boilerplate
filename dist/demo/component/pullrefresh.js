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
	__vue_styles__.push(__webpack_require__(142)
	)

	/* script */
	__vue_exports__ = __webpack_require__(143)

	/* template */
	var __vue_template__ = __webpack_require__(144)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/component/pullrefresh.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-0219a31d"
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

/***/ 142:
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

/***/ 143:
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


	exports.default = {
	    data: function data() {
	        return {
	            rtext: '下拉以加载',
	            updatetime: '没有更新',
	            offset: 0,
	            deg: 20,
	            refreshing: false,
	            pulldistance: 135,
	            hasrotate: false,
	            key: "ky" + Math.random()
	        };
	    },

	    methods: {
	        animateArrow: function animateArrow(deg) {
	            var animation = weex.requireModule('animation');
	            var arrow = this.$refs.arrow;
	            //                var deg=this.hasrotate?180:0

	            animation.transition(arrow, {
	                styles: {
	                    transform: "rotate(" + deg + "deg" + ")"
	                },

	                duration: 150, //ms
	                timingFunction: 'ease',
	                delay: 0 //ms
	            }, function () {});
	        },
	        onrefresh: function onrefresh(event) {
	            if (this.offset >= this.pulldistance) {

	                this.refreshing = true;
	                this.rtext = "加载中";
	                this.$emit('onRefresh');
	                //                    setTimeout(() => {
	                //                        this.refreshing = false
	                //                    }, 2000)
	            }
	        },
	        end: function end() {
	            this.refreshing = false;
	            //                this.deg=0;
	            this.updatetime = this.getNowFormatDate();
	            //                this.rtext='下拉以加载'
	        },
	        onpullingdown: function onpullingdown(event) {

	            var dis = event.pullingDistance;
	            if (dis < 0) dis *= -1;
	            this.offset = dis;

	            if (this.refreshing == false) {

	                //                     var t=dis>this.pulldistance
	                //                    if(t!=this.hasrotate)
	                //                    {
	                //                        this.hasrotate=t;
	                //                        this.animateArrow();
	                //                    }
	                if (dis > this.pulldistance) {
	                    this.rtext = "松开刷新";
	                    this.deg = 180;
	                    this.hasrotate = false;
	                    this.animateArrow(180);
	                } else {
	                    var p = dis / this.pulldistance;
	                    if (p > 1) p == 1;
	                    this.deg = p * 180;
	                    this.animateArrow(0);
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
	            var min = date.getMinutes();
	            var secon = date.getSeconds();
	            if (month >= 1 && month <= 9) {
	                month = "0" + month;
	            }
	            if (strDate >= 0 && strDate <= 9) {
	                strDate = "0" + strDate;
	            }
	            if (min >= 0 && min <= 9) {
	                min = "0" + min;
	            }
	            if (secon >= 0 && secon <= 9) {
	                secon = "0" + secon;
	            }

	            var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate + " " + date.getHours() + seperator2 + min + seperator2 + secon;
	            return currentdate;
	        }
	    },

	    created: function created() {}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 144:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('refresh', {
	    key: _vm.key,
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
	  }, [(_vm.refreshing) ? _c('floading', {
	    staticClass: ["indicator"],
	    attrs: {
	      "color": "#555555"
	    }
	  }) : _vm._e(), (!_vm.refreshing) ? _c('image', {
	    ref: "arrow",
	    staticClass: ["limg"],
	    attrs: {
	      "src": "root:img/pull_arrow.png"
	    }
	  }) : _vm._e(), _c('div', {
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
	  }, [_vm._v("上次更新:" + _vm._s(_vm.updatetime))])])], 1)])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });