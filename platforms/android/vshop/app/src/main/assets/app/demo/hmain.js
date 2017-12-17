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
	__vue_styles__.push(__webpack_require__(237)
	)

	/* script */
	__vue_exports__ = __webpack_require__(238)

	/* template */
	var __vue_template__ = __webpack_require__(240)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/hmain.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-9e5ee44c"
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

/***/ 226:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(227)
	)

	/* script */
	__vue_exports__ = __webpack_require__(228)

	/* template */
	var __vue_template__ = __webpack_require__(229)
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

/***/ 227:
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

/***/ 228:
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

/***/ 229:
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

/***/ 234:
/***/ (function(module, exports) {

	module.exports = {
	  "header": {
	    "backgroundColor": "#66CCCC",
	    "alignItems": "center",
	    "justifyContent": "center",
	    "flexDirection": "row"
	  },
	  "leftview": {
	    "width": 120,
	    "height": 135,
	    "justifyContent": "center",
	    "position": "absolute",
	    "left": 0
	  },
	  "rightview": {
	    "width": 120,
	    "height": 135,
	    "justifyContent": "center",
	    "position": "absolute",
	    "right": 0
	  },
	  "wrapper": {
	    "height": 135
	  },
	  "titleback": {
	    "flex": 1,
	    "alignItems": "center"
	  },
	  "title": {
	    "color": "#FFFFFF",
	    "marginTop": 40,
	    "fontWeight": "bold"
	  },
	  "leftimage": {
	    "width": 30,
	    "height": 45,
	    "marginLeft": 20,
	    "marginTop": 40
	  },
	  "rightimage": {
	    "width": 45,
	    "height": 45,
	    "marginTop": 30,
	    "marginLeft": 45
	  },
	  "bottomline": {
	    "height": 1,
	    "backgroundColor": "#000000",
	    "position": "absolute",
	    "bottom": 0,
	    "left": 0,
	    "right": 0,
	    "flex": 1
	  }
	}

/***/ }),

/***/ 235:
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


	exports.default = {
	  props: {
	    title: {
	      type: String
	    },
	    leftClick: {}

	  },
	  data: function data() {
	    return {
	      title: ''
	    };
	  },

	  methods: {
	    leftclick: function leftclick() {
	      var nav = weex.requireModule('navigation');
	      nav.pop();
	    },
	    rightClick: function rightClick() {

	      this.$parent.$emit('rightClick', this.id);
	    }
	  },
	  created: function created() {}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 236:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["wrapper", "header"]
	  }, [_c('div', {
	    staticClass: ["leftview"],
	    on: {
	      "click": _vm.leftclick
	    }
	  }, [_c('image', {
	    staticClass: ["leftimage"],
	    attrs: {
	      "resize": "contain",
	      "src": "img/backarrow.png"
	    }
	  })]), _c('text', {
	    staticClass: ["title"]
	  }, [_vm._v(_vm._s(_vm.title))]), _c('div', {
	    staticClass: ["rightview"],
	    on: {
	      "click": _vm.rightClick
	    }
	  }, [_c('image', {
	    staticClass: ["rightimage"],
	    attrs: {
	      "resize": "contain",
	      "src": "img/scan.png"
	    }
	  })])])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ }),

/***/ 237:
/***/ (function(module, exports) {

	module.exports = {
	  "indicator": {
	    "height": 40,
	    "width": 40
	  },
	  "entercell": {
	    "flex": 1,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "backgroundColor:active": "#eeeeee"
	  },
	  "entertext": {
	    "fontSize": 30,
	    "marginTop": 15,
	    "color": "#7e7e7e"
	  },
	  "enterimg": {
	    "width": 100,
	    "height": 100,
	    "marginTop": -10
	  },
	  "hd": {
	    "height": 200,
	    "top": 0,
	    "left": 0,
	    "width": 200,
	    "backgroundColor": "#0000FF",
	    "position": "fixed"
	  },
	  "panel": {
	    "height": 150,
	    "position": "relative",
	    "backgroundColor:active": "#eeeeee"
	  },
	  "cell_title": {
	    "left": 36,
	    "top": 25,
	    "fontSize": 35
	  },
	  "cell_money": {
	    "right": 43,
	    "top": 25,
	    "position": "absolute",
	    "fontSize": 30,
	    "color": "#ffbb35"
	  },
	  "cell_location": {
	    "position": "absolute",
	    "left": 72,
	    "bottom": 15,
	    "fontSize": 30,
	    "color": "#e1e1e1"
	  },
	  "bottom_line": {
	    "height": 2,
	    "position": "absolute",
	    "width": 750,
	    "backgroundColor": "#f0f0f0",
	    "bottom": 0,
	    "left": 36
	  },
	  "text": {
	    "fontSize": 50,
	    "textAlign": "center",
	    "color": "#41B883"
	  }
	}

/***/ }),

/***/ 238:
/***/ (function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _head = __webpack_require__(239);

	var _head2 = _interopRequireDefault(_head);

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
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//

	var pull = __webpack_require__(226);

	var modal = weex.requireModule('modal');
	var LOADMORE_COUNT = 10;

	exports.default = {
	    components: { pull: pull },
	    data: function data() {
	        return {
	            lists: [],
	            pageNo: 1,
	            isloading: false

	        };
	    },

	    methods: {
	        fetch: function fetch(event) {

	            this.pageNo++;
	            this.load();
	        },
	        itemClick: function itemClick(p) {
	            var mp = weex.requireModule('mainpage');
	            mp.gotoDetail(p);
	        },
	        near: function near() {

	            var mp = weex.requireModule('mainpage');
	            mp.goNearBy();
	        },
	        goRecord: function goRecord() {

	            var mp = weex.requireModule('mainpage');
	            mp.goRecord();
	        },
	        goCollection: function goCollection() {

	            var mp = weex.requireModule('mainpage');
	            mp.goCollection();
	        },
	        goResume: function goResume() {

	            var mp = weex.requireModule('mainpage');
	            mp.goResume();
	        },
	        refresh: function refresh() {
	            this.lists.length = 0;
	            this.pageNo = 1;
	            this.isloading = false;
	            this.load();
	        },
	        load: function load() {
	            if (this.isloading) return;
	            this.isloading = true;
	            //                modal.alert({message:'ssss'})
	            var self = this;
	            var net = weex.requireModule('net');
	            net.post('http://hrmap.renturbo.com/position/search', { "distance": "10", "pageNo": this.pageNo }, {}, function () {
	                //start
	            }, function (e) {
	                //success

	                self.lists = self.lists.concat(e.res.list);
	            }, function (e) {
	                //exception

	            }, function () {
	                //compelete
	                var p = self.$refs.refresh;
	                p.end();
	                self.isloading = false;
	            });
	        }
	    },

	    created: function created() {

	        var globalEvent = weex.requireModule('globalEvent');
	        //
	        var self = this;
	        //
	        globalEvent.addEventListener("onPageInit", function (e) {
	            //                modal.alert({message:'xxxx'})

	            self.load();
	        });
	    }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 239:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(234)
	)

	/* script */
	__vue_exports__ = __webpack_require__(235)

	/* template */
	var __vue_template__ = __webpack_require__(236)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/head.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-034916b7"
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

/***/ 240:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticStyle: {
	      flex: "1"
	    }
	  }, [_c('list', {
	    staticClass: ["list"],
	    attrs: {
	      "layout": "2"
	    },
	    on: {
	      "loadmore": _vm.fetch
	    }
	  }, [_c('pull', {
	    ref: "refresh",
	    on: {
	      "refresh": _vm.refresh
	    }
	  }), _c('cell', {
	    appendAsTree: true,
	    attrs: {
	      "append": "tree"
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "750",
	      height: "230"
	    },
	    attrs: {
	      "src": "img/banner.jpg"
	    }
	  }), _c('div', {
	    staticStyle: {
	      width: "750",
	      height: "220",
	      flexDirection: "row"
	    }
	  }, [_c('div', {
	    staticClass: ["entercell"],
	    on: {
	      "click": _vm.near
	    }
	  }, [_c('image', {
	    staticClass: ["enterimg"],
	    attrs: {
	      "src": "img/home_ico_01.png"
	    }
	  }), _c('text', {
	    staticClass: ["entertext"]
	  }, [_vm._v("附近职位")])]), _c('div', {
	    staticClass: ["entercell"]
	  }, [_c('image', {
	    staticClass: ["enterimg"],
	    attrs: {
	      "src": "img/home_ico_02.png"
	    },
	    on: {
	      "click": _vm.goRecord
	    }
	  }), _c('text', {
	    staticClass: ["entertext"]
	  }, [_vm._v("申请记录")])]), _c('div', {
	    staticClass: ["entercell"]
	  }, [_c('image', {
	    staticClass: ["enterimg"],
	    attrs: {
	      "src": "img/home_ico_03.png"
	    },
	    on: {
	      "click": _vm.goCollection
	    }
	  }), _c('text', {
	    staticClass: ["entertext"]
	  }, [_vm._v("我的收藏")])]), _c('div', {
	    staticClass: ["entercell"]
	  }, [_c('image', {
	    staticClass: ["enterimg"],
	    attrs: {
	      "src": "img/home_ico_04.png"
	    },
	    on: {
	      "click": _vm.goResume
	    }
	  }), _c('text', {
	    staticClass: ["entertext"]
	  }, [_vm._v("简历中心")])])]), _c('div', {
	    staticStyle: {
	      backgroundColor: "#f0f0f0",
	      height: "2",
	      width: "750"
	    }
	  })]), _vm._l((_vm.lists), function(p) {
	    return _c('cell', {
	      staticClass: ["cell"],
	      appendAsTree: true,
	      attrs: {
	        "append": "tree"
	      }
	    }, [_c('div', {
	      staticClass: ["panel"],
	      on: {
	        "click": function($event) {
	          _vm.itemClick(p)
	        }
	      }
	    }, [_c('text', {
	      staticClass: ["cell_title"]
	    }, [_vm._v(_vm._s(p.name))]), _c('text', {
	      staticClass: ["cell_money"]
	    }, [_vm._v(_vm._s(p.salarBottom) + "-" + _vm._s(p.salarTop) + " 元/月")]), _c('image', {
	      staticStyle: {
	        width: "25",
	        height: "32",
	        position: "absolute",
	        left: "36",
	        bottom: "20"
	      },
	      attrs: {
	        "src": "img/location.png"
	      }
	    }), _c('image', {
	      staticStyle: {
	        width: "20",
	        height: "32",
	        position: "absolute",
	        right: "45",
	        top: "75"
	      },
	      attrs: {
	        "src": "img/arrow.png"
	      }
	    }), _c('text', {
	      staticClass: ["cell_location"]
	    }, [_vm._v(_vm._s(p.workAddress))]), _c('div', {
	      staticClass: ["bottom_line"]
	    })])])
	  }), _c('loading', [_c('div', {
	    staticStyle: {
	      width: "750",
	      height: "100",
	      justifyContent: "center",
	      alignItems: "center",
	      flexDirection: "row"
	    }
	  }, [_c('floading', {
	    staticClass: ["indicator"],
	    attrs: {
	      "color": "#000000",
	      "loadingStyle": "small",
	      "display": "show"
	    }
	  }), _c('text', {
	    staticStyle: {
	      marginLeft: "10"
	    }
	  }, [_vm._v("加载中...")])], 1)])], 2)])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });