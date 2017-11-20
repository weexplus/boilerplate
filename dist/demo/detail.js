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
	__vue_styles__.push(__webpack_require__(192)
	)

	/* script */
	__vue_exports__ = __webpack_require__(193)

	/* template */
	var __vue_template__ = __webpack_require__(194)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/detail.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-0185a630"
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

/***/ 192:
/***/ (function(module, exports) {

	module.exports = {
	  "scroller": {
	    "backgroundColor": "#efefef",
	    "marginBottom": 100
	  },
	  "image": {
	    "width": 750,
	    "height": 560
	  },
	  "slider": {
	    "width": 750,
	    "height": 560
	  },
	  "frame": {
	    "width": 750,
	    "height": 560,
	    "position": "relative"
	  },
	  "panel": {
	    "backgroundColor": "#ffffff",
	    "width": 750,
	    "paddingTop": 15,
	    "paddingBottom": 40,
	    "paddingLeft": 20,
	    "paddingRight": 20
	  },
	  "text": {
	    "color": "#333333",
	    "fontSize": 36
	  },
	  "price": {
	    "backgroundColor": "#ffffff",
	    "width": 750,
	    "paddingBottom": 15,
	    "paddingLeft": 20,
	    "paddingRight": 20,
	    "flexDirection": "row",
	    "justifyContent": "space-between",
	    "alignItems": "center"
	  },
	  "sort-color": {
	    "backgroundColor": "#ffffff",
	    "width": 750,
	    "paddingBottom": 25,
	    "paddingLeft": 20,
	    "paddingRight": 20,
	    "flexDirection": "row",
	    "justifyContent": "space-between",
	    "alignItems": "center",
	    "borderTopStyle": "solid",
	    "borderTopWidth": 1,
	    "borderTopColor": "#d1d1d1",
	    "paddingTop": 25,
	    "marginTop": 20,
	    "marginBottom": 20
	  },
	  "num-sel": {
	    "backgroundColor": "#ffffff",
	    "width": 750,
	    "paddingBottom": 25,
	    "paddingLeft": 20,
	    "paddingRight": 20,
	    "flexDirection": "row",
	    "justifyContent": "space-between",
	    "alignItems": "center",
	    "paddingTop": 25
	  },
	  "price-fx": {
	    "flexDirection": "row",
	    "alignItems": "center"
	  },
	  "countdown": {
	    "flexDirection": "row",
	    "alignItems": "center"
	  },
	  "num-inp": {
	    "flexDirection": "row",
	    "alignItems": "center"
	  },
	  "ico-time": {
	    "height": 30,
	    "width": 30,
	    "marginRight": 5
	  },
	  "text1": {
	    "color": "#333333",
	    "fontSize": 28
	  },
	  "text6": {
	    "color": "#333333",
	    "fontSize": 32
	  },
	  "text2": {
	    "color": "#ff6600"
	  },
	  "text3": {
	    "color": "#ff6600",
	    "fontSize": 50
	  },
	  "text4": {
	    "color": "#999999",
	    "fontSize": 28
	  },
	  "text5": {
	    "borderRadius": 5,
	    "backgroundColor": "#eb6100",
	    "color": "#ffffff",
	    "fontSize": 28
	  },
	  "ico-arrow-r": {
	    "height": 30,
	    "width": 18
	  },
	  "minus": {
	    "backgroundColor": "#fbfbfb",
	    "color": "#aaaaaa",
	    "fontSize": 40,
	    "width": 50,
	    "height": 50,
	    "textAlign": "center",
	    "marginRight": 5
	  },
	  "plus": {
	    "backgroundColor": "#fbfbfb",
	    "color": "#aaaaaa",
	    "fontSize": 40,
	    "width": 50,
	    "height": 50,
	    "textAlign": "center",
	    "marginLeft": 5
	  },
	  "num-input": {
	    "backgroundColor": "#f5f5f5",
	    "color": "#666666",
	    "height": 50,
	    "width": 50,
	    "textAlign": "center"
	  },
	  "drag": {
	    "justifyContent": "center",
	    "alignItems": "center",
	    "paddingTop": 25,
	    "paddingBottom": 25
	  },
	  "detail-bt": {
	    "position": "fixed",
	    "width": 750,
	    "paddingTop": 15,
	    "paddingBttom": 15,
	    "flexDirection": "row",
	    "alignItems": "center",
	    "justifyContent": "center",
	    "bottom": 0,
	    "right": 0,
	    "left": 0
	  },
	  "kf": {
	    "backgroundColor": "#f8f7f5",
	    "borderTopStyle": "solid",
	    "borderTopWidth": 1,
	    "borderTopColor": "#d1d1d1",
	    "borderRightStyle": "solid",
	    "borderRightWidth": 1,
	    "borderRightColor": "#d1d1d1",
	    "flex": 1,
	    "height": 100,
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "ico-kf": {
	    "height": 40,
	    "width": 40,
	    "marginBottom": 10
	  },
	  "kf-txt": {
	    "backgroundColor": "#f8f7f5",
	    "color": "#666666",
	    "fontSize": 28,
	    "textAlign": "center"
	  },
	  "fav": {
	    "backgroundColor": "#f8f7f5",
	    "borderTopStyle": "solid",
	    "borderTopWidth": 1,
	    "borderTopColor": "#d1d1d1",
	    "flex": 1,
	    "height": 100,
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "ico-fav": {
	    "height": 40,
	    "width": 40,
	    "marginBottom": 10
	  },
	  "fav-txt": {
	    "backgroundColor": "#f8f7f5",
	    "color": "#666666",
	    "fontSize": 28,
	    "textAlign": "center"
	  },
	  "cart-join": {
	    "backgroundColor": "#e4d085",
	    "flex": 2,
	    "height": 100,
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "cj-txt": {
	    "backgroundColor": "#e4d085",
	    "color": "#ffffff",
	    "fontSize": 32,
	    "textAlign": "center"
	  },
	  "order-now": {
	    "backgroundColor": "#cc9900",
	    "flex": 2,
	    "height": 100,
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "on-txt": {
	    "backgroundColor": "#cc9900",
	    "color": "#ffffff",
	    "fontSize": 32,
	    "textAlign": "center"
	  }
	}

/***/ }),

/***/ 193:
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

	exports.default = {
	  data: function data() {
	    return {
	      imageList: [{ src: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg' }, { src: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg' }, { src: 'https://gd3.alicdn.com/bao/uploaded/i3/TB1x6hYLXXXXXazXVXXXXXXXXXX_!!0-item_pic.jpg' }]
	    };
	  }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 194:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["wrapper"]
	  }, [_c('scroller', {
	    staticClass: ["scroller"]
	  }, [_c('slider', {
	    staticClass: ["slider"],
	    attrs: {
	      "interval": "3000"
	    }
	  }, _vm._l((_vm.imageList), function(img) {
	    return _c('div', {
	      staticClass: ["frame"]
	    }, [_c('image', {
	      staticClass: ["image"],
	      attrs: {
	        "resize": "cover",
	        "src": img.src
	      }
	    })])
	  })), _vm._m(0), _vm._m(1), _vm._m(2), _vm._m(3), _vm._m(4), _vm._m(5)]), _vm._m(6)])
	},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["panel"]
	  }, [_c('text', {
	    staticClass: ["text"]
	  }, [_vm._v("欧思曼双肩包女真皮2017新款韩版百搭女包牛皮包包女士双肩背包潮")])])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["price"]
	  }, [_c('div', {
	    staticClass: ["price-fx"]
	  }, [_c('text', {
	    staticClass: ["text1"]
	  }, [_vm._v("分销价：")]), _c('text', {
	    staticClass: ["text2"]
	  }, [_vm._v("￥")]), _c('text', {
	    staticClass: ["text3"]
	  }, [_vm._v("108")])]), _c('div', {
	    staticClass: ["countdown"]
	  }, [_c('image', {
	    staticClass: ["ico-time"],
	    attrs: {
	      "src": "./img/ico_time.png"
	    }
	  }), _c('text', {
	    staticClass: ["text1"]
	  }, [_vm._v("还剩：12时30分")])])])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["price"]
	  }, [_c('div', {
	    staticClass: ["price-fx"]
	  }, [_c('text', {
	    staticClass: ["text4"]
	  }, [_vm._v("快递：")]), _c('text', {
	    staticClass: ["text5"]
	  }, [_vm._v("包邮")])]), _c('div', [_c('text', {
	    staticClass: ["text4"]
	  }, [_vm._v("已售150件")])]), _c('div', [_c('text', {
	    staticClass: ["text4"]
	  }, [_vm._v("国内现货")])])])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["sort-color"]
	  }, [_c('div', [_c('text', {
	    staticClass: ["text6"]
	  }, [_vm._v("颜色分类：")])]), _c('div', [_c('image', {
	    staticClass: ["ico-arrow-r"],
	    attrs: {
	      "src": "./img/arrow_right_02.png"
	    }
	  })])])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["num-sel"]
	  }, [_c('div', [_c('text', {
	    staticClass: ["text6"]
	  }, [_vm._v("选择数量：")])]), _c('div', {
	    staticClass: ["num-inp"]
	  }, [_c('text', {
	    staticClass: ["minus"]
	  }, [_vm._v("-")]), _c('input', {
	    staticClass: ["num-input"],
	    attrs: {
	      "type": "text",
	      "value": "1"
	    }
	  }), _c('text', {
	    staticClass: ["plus"]
	  }, [_vm._v("+")])])])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["drag"]
	  }, [_c('div', [_c('text', {
	    staticClass: ["text1"]
	  }, [_vm._v("继续拖动，查看商品详情")])])])
	},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["detail-bt"]
	  }, [_c('div', {
	    staticClass: ["kf"]
	  }, [_c('image', {
	    staticClass: ["ico-kf"],
	    attrs: {
	      "src": "./img/ico_kefu.png"
	    }
	  }), _c('text', {
	    staticClass: ["kf-txt"]
	  }, [_vm._v("客服")])]), _c('div', {
	    staticClass: ["fav"]
	  }, [_c('image', {
	    staticClass: ["ico-fav"],
	    attrs: {
	      "src": "./img/ico_fav.png"
	    }
	  }), _c('text', {
	    staticClass: ["fav-txt"]
	  }, [_vm._v("关注")])]), _c('div', {
	    staticClass: ["cart-join"]
	  }, [_c('text', {
	    staticClass: ["cj-txt"]
	  }, [_vm._v("加入购物车")])]), _c('div', {
	    staticClass: ["order-now"]
	  }, [_c('text', {
	    staticClass: ["on-txt"]
	  }, [_vm._v("立即下单")])])])
	}]}
	module.exports.render._withStripped = true

/***/ })

/******/ });