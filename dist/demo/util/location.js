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

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var data = __webpack_require__(246);

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

/***/ 246:
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

/***/ })

/******/ });