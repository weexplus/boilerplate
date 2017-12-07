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
	__vue_styles__.push(__webpack_require__(419)
	)

	/* script */
	__vue_exports__ = __webpack_require__(420)

	/* template */
	var __vue_template__ = __webpack_require__(421)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/search.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-dc209ce6"
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

/***/ 419:
/***/ (function(module, exports) {

	module.exports = {
	  "search_wrapper": {
	    "width": 750,
	    "left": 0,
	    "right": 0,
	    "bottom": 0,
	    "backgroundColor": "#EEEEEE"
	  },
	  "head_wrapper": {
	    "width": 750,
	    "height": 170,
	    "backgroundColor": "#17ACF6",
	    "flexDirection": "row",
	    "alignItems": "center"
	  },
	  "hotSearch": {
	    "width": 750,
	    "paddingLeft": 20,
	    "paddingRight": 20,
	    "marginBottom": 15,
	    "flexDirection": "column",
	    "backgroundColor": "#FFFFFF"
	  },
	  "hotHead": {
	    "height": 90,
	    "flexDirection": "row",
	    "alignItems": "center",
	    "borderBottomStyle": "solid",
	    "borderBottomColor": "#DEDEDE",
	    "borderBottomWidth": 1
	  },
	  "hotImg": {
	    "width": 53,
	    "height": 53,
	    "marginLeft": 20
	  },
	  "hotText": {
	    "fontSize": 30,
	    "fontWeight": "bold"
	  },
	  "searchWord": {
	    "paddingBottom": 25,
	    "paddingTop": 25,
	    "flexDirection": "row",
	    "flexWrap": "wrap"
	  },
	  "word": {
	    "height": 45,
	    "marginRight": 15,
	    "marginBottom": 15,
	    "borderStyle": "solid",
	    "borderRadius": 5,
	    "borderWidth": 1,
	    "borderColor": "#7A7A7A",
	    "paddingLeft": 15,
	    "paddingRight": 15
	  },
	  "clearH": {
	    "fontSize": 25,
	    "fontWeight": "bold",
	    "color": "#17ACF6",
	    "position": "absolute",
	    "right": 0,
	    "lineHeight": 90
	  },
	  "searchHT": {
	    "height": 90,
	    "borderTopColor": "#DFDFDF",
	    "borderTopStyle": "solid",
	    "borderTopWidth": 1,
	    "flexDirection": "row",
	    "alignItems": "center"
	  },
	  "input": {
	    "width": 580,
	    "height": 60,
	    "backgroundColor": "#ffffff",
	    "borderWidth": 1,
	    "borderColor": "#ffffff",
	    "borderRadius": 10,
	    "paddingLeft": 70
	  }
	}

/***/ }),

/***/ 420:
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


	var nav = weex.requireModule('navigator');
	//    const net = require('../vue/busi/util/net.js')
	var storage = weex.requireModule('storage');
	var modal = weex.requireModule('modal');
	//    import taskcontent from '../components/TaskContent.vue';

	exports.default = {
	    data: function data() {

	        return {
	            isShow: true,
	            inputs: '',
	            lists: [],
	            title: '我的',
	            w: '570px',
	            //                words: ['工艺设计','视觉传达设计','纯艺术','工艺艺术','环境艺术设计','什么是环境艺术设计'],
	            ss: []

	        };
	    },


	    components: {

	        //            taskcontent

	    },

	    mounted: function mounted() {

	        this._gethuoqu();
	    },

	    methods: {
	        sousuo1: function sousuo1(item, index) {

	            this.inputs = item;
	            this.sousuo();
	        },
	        cunchu: function cunchu() {

	            var pref = weex.requireModule("pref");
	            var obj = {};
	            this.ss.push(this.inputs);
	            obj.xxx = this.ss;
	            //                obj.xxx.length = 10;
	            pref.setObj('objkey', obj);
	            var modal = weex.requireModule("modal");
	            modal.toast({ message: '存储成功' });
	            this.sousuo();
	            this.isShow = !this.isShow;
	        },
	        sousuo: function sousuo() {

	            if (this.inputs == '') {
	                var modal = weex.requireModule("modal");
	                modal.toast({ message: '您输入的搜索内容不能为空！' });
	            } else {}

	            var this_se = this;
	            var modal = weex.requireModule('modal');
	            net.post('taskHall/findMobileTasks', {
	                pageNum: 1,
	                pageSize: 10,
	                conditions: this_se.inputs
	            }, function (e) {
	                this_se.lists = e.data.list;

	                //                            modal.alert({message:e.data.list});

	            });
	        },
	        img_click: function img_click() {

	            nav.push('../task/Task.js');
	        },


	        //点击取消清空输入框里面的内容
	        quxiao: function quxiao() {

	            this.inputs = '';

	            //                this.$refs.search.clear();//点击取消的时候清空里面的内容
	        },


	        //删除每一行
	        removeTodo: function removeTodo(index) {

	            this.ss.splice(index, 1);
	        },


	        //删除全部
	        qingchulishi: function qingchulishi() {

	            //                this.ss.splice([])

	            var pref = weex.requireModule("pref");
	            pref.remove('objkey');
	            var modal = weex.requireModule("modal");
	            modal.toast({ message: 123 });
	            this._gethuoqu();
	        },


	        //自定义获取字符串
	        _gethuoqu: function _gethuoqu() {

	            var pref = weex.requireModule("pref");
	            var p = pref.getObj('objkey');
	            if (p.xxx == undefined) {
	                this.ss = [];
	                return;
	            }
	            this.ss = p.xxx;
	        }
	    }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 421:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["search_wrapper"]
	  }, [_c('div', {
	    staticClass: ["head_wrapper"]
	  }, [_c('div', {
	    staticStyle: {
	      height: "128px",
	      marginTop: "60px",
	      marginLeft: "20px",
	      flexDirection: "row",
	      alignItems: "center"
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "40",
	      height: "40",
	      marginRight: "10px"
	    },
	    attrs: {
	      "src": "../images/my/backWhite.png"
	    },
	    on: {
	      "click": _vm.img_click
	    }
	  }), _c('div', {
	    staticStyle: {
	      flexDirection: "row",
	      position: "relative"
	    }
	  }, [_c('input', {
	    staticClass: ["input"],
	    attrs: {
	      "type": "text",
	      "placeholder": "搜索",
	      "autofocus": true,
	      "value": "",
	      "value": (_vm.inputs)
	    },
	    on: {
	      "change": _vm.cunchu,
	      "input": function($event) {
	        _vm.inputs = $event.target.attr.value
	      }
	    }
	  }), _c('image', {
	    staticStyle: {
	      width: "33px",
	      height: "33px",
	      position: "absolute",
	      left: "30px",
	      top: "15px"
	    },
	    attrs: {
	      "src": "../images/my/iconSearch.png"
	    }
	  })]), _c('text', {
	    staticStyle: {
	      fontSize: "30px",
	      color: "#FFFFFF",
	      marginLeft: "20px",
	      fontWeight: "bold"
	    },
	    on: {
	      "click": _vm.quxiao
	    }
	  }, [_vm._v("取消")])])]), _c('scroller', [(_vm.isShow) ? _c('div', {
	    staticClass: ["hotSearch"]
	  }, [_c('div', {
	    staticClass: ["hotHead"]
	  }, [_c('text', {
	    staticClass: ["hotText"]
	  }, [_vm._v("搜索历史")]), _c('text', {
	    staticClass: ["clearH"],
	    on: {
	      "click": _vm.qingchulishi
	    }
	  }, [_vm._v("清除历史")])]), _vm._l((_vm.ss), function(item, index) {
	    return _c('div', {
	      staticClass: ["searchHT"],
	      on: {
	        "click": function($event) {
	          _vm.sousuo1(item, index)
	        }
	      }
	    }, [_c('text', {
	      staticStyle: {
	        color: "#000000",
	        fontSize: "26px",
	        fontWeight: "bold"
	      }
	    }, [_vm._v(_vm._s(item))]), _c('image', {
	      staticStyle: {
	        width: "35px",
	        height: "35px",
	        position: "absolute",
	        right: "0",
	        top: "27px"
	      },
	      attrs: {
	        "src": "../images/deleteBtn.png"
	      },
	      on: {
	        "click": function($event) {
	          _vm.removeTodo(index)
	        }
	      }
	    })])
	  })], 2) : _vm._e(), _vm._l((_vm.lists), function(item) {
	    return _c('div', {
	      staticStyle: {
	        marginLeft: "28px",
	        marginTop: "20px"
	      }
	    }, [_c('taskcontent', {
	      attrs: {
	        "list": item
	      }
	    })], 1)
	  })], 2)])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });