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
	__vue_styles__.push(__webpack_require__(261)
	)

	/* script */
	__vue_exports__ = __webpack_require__(262)

	/* template */
	var __vue_template__ = __webpack_require__(264)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/pickerdemo.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-31c83c88"
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

/***/ 106:
/***/ (function(module, exports) {

	"use strict";

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});
	exports.default = {

	    getClassData: function getClassData() {

	        var pref = weex.requireModule("pref");
	        var s = pref.get('serial_class');
	        var modal = weex.requireModule("modal");
	        // modal.alert({message:"l"+s+"l"})
	        if (s != undefined && s != '') {
	            return s;
	        }

	        var l = { "msg": "成功", "err": 0, "list": [{ "name": "魔幻", "id": 5 }, { "name": "青春", "id": 6 }, { "name": "都市", "id": 7 }, { "name": "迷你剧", "id": 8 }, { "name": "谍战", "id": 9 }, { "name": "记录", "id": 10 }, { "name": "西部", "id": 11 }, { "name": "血腥", "id": 12 }, { "name": "罪案", "id": 13 }, { "name": "综艺", "id": 14 }, { "name": "科幻", "id": 15 }, { "name": "真人秀", "id": 16 }, { "name": "爱情", "id": 17 }, { "name": "歌舞", "id": 18 }, { "name": "暴力", "id": 19 }, { "name": "政治", "id": 20 }, { "name": "战争", "id": 21 }, { "name": "惊悚", "id": 22 }, { "name": "悬疑", "id": 23 }, { "name": "律政", "id": 24 }, { "name": "家庭", "id": 25 }, { "name": "奇幻", "id": 26 }, { "name": "喜剧", "id": 27 }, { "name": "吸血鬼", "id": 28 }, { "name": "同性", "id": 29 }, { "name": "史诗", "id": 30 }, { "name": "古装", "id": 31 }, { "name": "历史", "id": 32 }, { "name": "医务", "id": 33 }, { "name": "动画", "id": 34 }, { "name": "动作", "id": 35 }, { "name": "剧情", "id": 36 }, { "name": "冒险", "id": 37 }, { "name": "传记", "id": 38 }, { "name": "丧尸", "id": 39 }, { "name": "情景喜剧", "id": 40 }] };
	        return l.list;
	    },
	    getMovieClassData: function getMovieClassData() {

	        var pref = weex.requireModule("pref");
	        var s = pref.get('movie_class');
	        var modal = weex.requireModule("modal");
	        // modal.alert({message:"l"+s+"l"})
	        if (s != undefined && s != '') {
	            return s;
	        }

	        var l = { "msg": "成功", "err": 0, "list": [{ "name": "动作", "id": 41 }, { "name": "剧情", "id": 42 }, { "name": "悬疑", "id": 43 }, { "name": "喜剧", "id": 44 }, { "name": "爱情", "id": 45 }, { "name": "战争", "id": 46 }, { "name": "科幻", "id": 47 }, { "name": "灾难", "id": 48 }, { "name": "恐怖", "id": 49 }, { "name": "犯罪", "id": 50 }, { "name": "动漫", "id": 51 }, { "name": "惊悚", "id": 52 }, { "name": "奇幻", "id": 53 }, { "name": "冒险", "id": 54 }, { "name": "动作", "id": 41 }, { "name": "剧情", "id": 42 }, { "name": "悬疑", "id": 43 }, { "name": "喜剧", "id": 44 }, { "name": "爱情", "id": 45 }, { "name": "战争", "id": 46 }, { "name": "科幻", "id": 47 }, { "name": "灾难", "id": 48 }, { "name": "恐怖", "id": 49 }, { "name": "犯罪", "id": 50 }, { "name": "动漫", "id": 51 }, { "name": "惊悚", "id": 52 }, { "name": "奇幻", "id": 53 }, { "name": "冒险", "id": 54 }] };

	        return l.list;
	    }

	};
	module.exports = exports["default"];

/***/ }),

/***/ 261:
/***/ (function(module, exports) {

	module.exports = {}

/***/ }),

/***/ 262:
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

	var location = __webpack_require__(263);

	var picker;
	exports.default = {
	  data: function data() {
	    return {
	      imageList: [{ src: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg' }, { src: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg' }, { src: 'https://gd3.alicdn.com/bao/uploaded/i3/TB1x6hYLXXXXXazXVXXXXXXXXXX_!!0-item_pic.jpg' }]
	    };
	  },

	  methods: {
	    show: function show() {
	      if (picker == undefined) {
	        picker = location.show(function (e1, e2, e3) {});
	      }
	      picker.show();
	    }
	  }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 263:
/***/ (function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var data = __webpack_require__(106);

	exports.default = {
	    get: function get(res) {
	        var self = this;
	        var picker = weex.requireModule("fpicker");
	        var d = data.getAddressData();
	        var l = d.list;
	        //务必调用此方法，此方法有初始化的功能，最多3列
	        picker.setCount(3);
	        picker.setItems1(this.toArray(l));
	        picker.setItems2(this.toArray(l[0].children));
	        picker.setTheme('#f9f9f9', '#4c4c4c');

	        var index1 = 0;x;
	        var index2 = 0;
	        var index3 = 0;
	        //每个滚轮的change事件
	        picker.setChange(function (e) {

	            index1 = e.index;
	            picker.setItems2(self.toArray(l[index1].children));
	            picker.select(2, 0);
	            if (l[index1].children.length > 0) {

	                picker.setItems3(self.toArray(l[index1].children[0].children));

	                picker.select(3, 0);
	            } else {
	                picker.setItems3([]);
	            }
	        }, function (e) {
	            index2 = e.index;
	            if (l[index1].children[index2].children.length > 0) {
	                picker.setItems3(self.toArray(l[index1].children[index2].children));
	                picker.select(3, 0);
	            } else {
	                picker.setItems3([]);
	            }
	        }, function (e) {
	            index3 = e.index;
	        });
	        //点击完成的事件
	        picker.setDone(function (e) {
	            var p1 = l[e.index1];
	            var p2 = l[e.index1].children[e.index2];
	            var p3 = l[e.index1].children[e.index2].children[e.index3];
	            res(p1, p2, p3);
	        });
	        // picker.show()
	        return picker;
	    },
	    toArray: function toArray(list) {
	        var p = [];
	        for (var i = 0; i < list.length; i++) {
	            p.push(list[i].name);
	        }
	        return p;
	    }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 264:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _vm._m(0)
	},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticStyle: {
	      justifyContent: "center",
	      alignItems: "center"
	    }
	  }, [_c('div', {
	    staticStyle: {
	      alignItems: "center",
	      justifyContent: "center",
	      backgroundColor: "red",
	      width: "200",
	      height: "100"
	    }
	  }, [_c('text', [_vm._v("显示")])])])
	}]}
	module.exports.render._withStripped = true

/***/ })

/******/ });