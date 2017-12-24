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
	__vue_styles__.push(__webpack_require__(487)
	)

	/* script */
	__vue_exports__ = __webpack_require__(488)

	/* template */
	var __vue_template__ = __webpack_require__(489)
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
	__vue_options__.__file = "/Users/zhengjiangrong/work/corenerstone/netplatform/src/my/my.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-97ca8b72"
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

/***/ 99:
/***/ (function(module, exports) {

	"use strict";

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});
	//var host='http://10.39.1.237:8081/cdtp/';
	//var host='http://10.2.115.90:8081/cdtp/';
	var host = 'http://10.39.1.72:8081/cdtp/';
	//var host='http://nginx-app-xdgc-idesign-dev.ipaas.enncloud.cn/CDTPWebGateway/';
	//var host='http://10.39.1.72:8081/cdtp/';
	//var token= "1d594af1295976f9a2e0371afe83fbd2";
	//var refreshToken="04907e223c16463e8d55b8d4446504fc";
	//var Header = {
	//	token: token,
	//	refreshToken: refreshToken
	//}

	exports.default = {
	    //	userId: '85935039157a4740aac07aa0e015a7e8',
	    postShort: function postShort(weg, param, header, start, success, compelete) {
	        var modal = weex.requireModule("modal");
	        this.postFull(weg, param, header, start, success, function (res) {
	            //fail
	            //          modal.toast({message:res.msg})
	        }, function () {
	            //exception
	            //          modal.toast({message:'网络异常！'})
	        }, function () {
	            //compelete
	            compelete();
	        });
	    },

	    postFull: function postFull(weg, param, header, start, success, fail, exception, compelete) {
	        var net = weex.requireModule("net");
	        var modal = weex.requireModule("modal");
	        var self = this;
	        var url = host + weg;
	        var st = weex.requireModule('static');
	        var objkey = st.get('user');
	        if (objkey != undefined && objkey != '') {
	            header.token = objkey.userInfo.token;
	            header.refreshToken = objkey.userInfo.refreshToken;
	        }
	        // param.token='95d594d7b18fd1c7db37e81dd5bae9c9'
	        net.post(url, param, header, function () {
	            //start
	            start();
	        }, function (e) {
	            //success
	            // modal.toast({message:e.res.err})
	            //          if(e.res.err==0)
	            //          {
	            //
	            success(e.res);
	            /*token=e.data.token
	            refreshToken=e.data.refreshToken*/
	            //          }
	            //          else
	            //          {
	            //              // modal.toast({message:e.res.msg})
	            //              if(e.res.err==1000)
	            //              {
	            //                  var nav=weex.requireModule("navigator")
	            //                  nav.presentFull('root:busi/account/login.js',{},'transparent',true,function(){
	            //                      self.postFull(weg,param,header,start,success,fail,exception,compelete);
	            //                  },true);
	            //              }
	            //              else
	            //              fail(e.res);
	            //          }
	        }, function (e) {
	            //compelete


	            compelete();
	        }, function (e) {
	            // exception
	            exception();
	        });
	    },
	    post: function post(weg, param, success) {
	        var progress = weex.requireModule("progress");
	        this.postShort(weg, param, {}, function () {
	            progress.show();
	        }, success, function () {
	            progress.dismiss();
	        });
	    },
	    postNoHeader: function postNoHeader(weg, param, success) {
	        var progress = weex.requireModule("progress");
	        this.postShort(weg, param, {}, function () {
	            progress.show();
	        }, success, function () {
	            progress.dismiss();
	        });
	    },

	    postSlient: function postSlient(weg, param, success) {

	        this.postFull(weg, param, {}, function () {}, success, function (res) {
	            //fail

	        }, function () {
	            //exception

	        }, function () {
	            //compelete


	        });
	    },

	    //     GET
	    getShort: function getShort(weg, param, header, start, success, compelete) {
	        var modal = weex.requireModule("modal");
	        this.getFull(weg, param, header, start, success, function (res) {
	            //fail
	            //          modal.toast({message:res.msg})
	        }, function () {
	            //exception
	            //          modal.toast({message:'网络异常！'})
	        }, function () {
	            //compelete

	            compelete();
	        });
	    },

	    getFull: function getFull(weg, param, header, start, success, fail, exception, compelete) {
	        var net = weex.requireModule("net");
	        var modal = weex.requireModule("modal");
	        var self = this;
	        var url = host + weg;
	        var st = weex.requireModule('static');
	        var objkey = st.get('user');
	        if (objkey != undefined && objkey != '') {
	            header.token = objkey.userInfo.token;
	            header.refreshToken = objkey.userInfo.refreshToken;
	        }
	        // param.token='95d594d7b18fd1c7db37e81dd5bae9c9'
	        net.get(url, param, header, function () {
	            //start
	            start();
	        }, function (e) {
	            //success
	            // modal.toast({message:e.res.err})
	            //          if(e.res.err==0)
	            //          {
	            //
	            success(e.res);
	            /*token=e.data.token
	            refreshToken=e.data.refreshToken*/
	            //          }
	            //          else
	            //          {
	            //              // modal.toast({message:e.res.msg})
	            //              if(e.res.err==1000)
	            //              {
	            //                  var nav=weex.requireModule("navigator")
	            //                  nav.presentFull('root:busi/account/login.js',{},'transparent',true,function(){
	            //                      self.postFull(weg,param,header,start,success,fail,exception,compelete);
	            //                  },true);
	            //              }
	            //              else
	            //              fail(e.res);
	            //          }
	        }, function (e) {
	            //compelete


	            compelete();
	        }, function (e) {
	            // exception
	            exception();
	        });
	    },
	    get: function get(weg, param, success) {
	        var progress = weex.requireModule("progress");
	        this.getShort(weg, param, {}, function () {
	            progress.show();
	        }, success, function () {
	            progress.dismiss();
	        });
	    },
	    getNoHeader: function getNoHeader(weg, param, success) {
	        var progress = weex.requireModule("progress");
	        this.getShort(weg, param, {}, function () {
	            progress.show();
	        }, success, function () {
	            progress.dismiss();
	        });
	    },

	    getSlient: function getSlient(weg, param, success) {

	        this.getFull(weg, param, {}, function () {}, success, function (res) {
	            //fail

	        }, function () {
	            //exception

	        }, function () {
	            //compelete


	        });
	    }
	};
	module.exports = exports["default"];

/***/ }),

/***/ 134:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(135)
	)
	__vue_styles__.push(__webpack_require__(136)
	)

	/* script */
	__vue_exports__ = __webpack_require__(137)

	/* template */
	var __vue_template__ = __webpack_require__(138)
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
	__vue_options__.__file = "/Users/zhengjiangrong/work/corenerstone/netplatform/src/vue/component/head.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-f5ca9b9a"
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

/***/ 135:
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

/***/ 136:
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
	    "width": 150,
	    "position": "absolute",
	    "left": 0,
	    "marginTop": 40
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
	  },
	  "headTitle": {
	    "color": "#ffffff",
	    "textAlign": "center",
	    "marginTop": 30,
	    "fontSize": 35,
	    "fontWeight": "bold"
	  }
	}

/***/ }),

/***/ 137:
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
	            this.$emit('add');
	            //              nav.add();
	        },
	        search: function search() {
	            //                var nav=weex.requireModule("navigator");
	            //                nav.push('root:my/search.js');
	            this.$emit('clicksearch');
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

/***/ 138:
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
	    staticClass: ["headTitle"]
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
	      "src": "../images/6.3.1/iconadd.png"
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

/***/ 487:
/***/ (function(module, exports) {

	module.exports = {
	  "img1": {
	    "marginTop": -36
	  },
	  "img2": {
	    "position": "relative",
	    "top": -245,
	    "left": 20
	  },
	  "pep": {
	    "flexDirection": "column",
	    "justifyContent": "flex-start",
	    "alignItems": "center",
	    "width": 750,
	    "height": 174
	  },
	  "dengji_box": {
	    "width": 750,
	    "height": 158,
	    "paddingLeft": 50,
	    "alignItems": "flex-start",
	    "justifyContent": "flex-start"
	  },
	  "imgbox": {
	    "width": 120,
	    "height": 120,
	    "borderRadius": 60
	  },
	  "star_image": {
	    "width": 33,
	    "height": 33,
	    "marginLeft": 20
	  },
	  "list1": {
	    "flexDirection": "row",
	    "justifyContent": "flex-start"
	  },
	  "count_box": {
	    "flexDirection": "row",
	    "width": 710,
	    "paddingTop": 20,
	    "marginLeft": 24
	  },
	  "count_list": {
	    "borderRightStyle": "solid",
	    "borderRightWidth": 2,
	    "borderRightColor": "#84b5ff"
	  },
	  "data_area": {
	    "width": 710,
	    "height": 230,
	    "flexDirection": "row",
	    "marginTop": 44
	  },
	  "data_l": {
	    "flex": 1,
	    "backgroundColor": "#ffffff",
	    "borderRadius": 6,
	    "justifyContent": "center",
	    "alignItems": "center"
	  },
	  "data_r1": {
	    "flexDirection": "row",
	    "flexWrap": "wrap"
	  },
	  "data_list": {
	    "width": 231,
	    "height": 110,
	    "backgroundColor": "#ffffff",
	    "borderRadius": 6,
	    "flexDirection": "row",
	    "justifyContent": "center",
	    "alignItems": "center"
	  },
	  "list_box": {
	    "width": 710,
	    "height": 300,
	    "backgroundColor": "#ffffff",
	    "borderRadius": 6
	  },
	  "item_list": {
	    "width": 100,
	    "height": 94,
	    "justifyContent": "center",
	    "alignItems": "center"
	  },
	  "list_box1": {
	    "height": 150,
	    "flexDirection": "row",
	    "justifyContent": "space-between",
	    "alignItems": "center"
	  },
	  "list_box2": {
	    "height": 150,
	    "flexDirection": "row",
	    "justifyContent": "space-between",
	    "alignItems": "center"
	  },
	  "text_jump": {
	    "color": "#17ACF6"
	  }
	}

/***/ }),

/***/ 488:
/***/ (function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _head = __webpack_require__(134);

	var _head2 = _interopRequireDefault(_head);

	var _net = __webpack_require__(99);

	var _net2 = _interopRequireDefault(_net);

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
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
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
	    components: {
	        topBar: _head2.default
	    },
	    data: {
	        state1: true,
	        state2: true,
	        state3: true,
	        state4: true,
	        state5: true,
	        //      count1:150,
	        //      count2:150,
	        //      count3:150,
	        //      count4:150,
	        publishCount: 0,
	        acceptCount: 0, //参与任务数
	        name: "",
	        src: "", //../images/7.1.0-me/user_img.png
	        num1: null, //等级管理的数据
	        num2: null, //信用管理的数据
	        title: "我的",
	        expenditure: 0,
	        income: 0,
	        isshow1: true,
	        isshow2: false,
	        logined: true, //判断是否登录
	        images: null,
	        startimg: ""

	    },
	    computed: {
	        img1: function img1() {

	            if (this.num1 >= 1) {
	                return this.startimg;
	            }
	            return '';
	        },
	        img2: function img2() {
	            if (this.num1 >= 2) {
	                return this.startimg; //"../images/my/hg.png"
	            }
	            return ''; //../images/7.1.0-me/weixinyong.png
	        },
	        img3: function img3() {
	            if (this.num1 >= 3) {
	                return this.startimg; //"../images/my/hg.png"
	            }
	            return '';
	        },
	        img4: function img4() {
	            if (this.num1 >= 4) {
	                return this.startimg; // "../images/my/hg.pngg"
	            }
	            return '';
	        },
	        img5: function img5() {
	            if (this.num1 >= 5) {
	                return this.startimg; //"../images/my/hg.png"
	            }
	            return '';
	        },
	        img6: function img6() {
	            if (this.num2 >= 1) {
	                return "../images/my/xx.png";
	            }
	            return '';
	        },
	        img7: function img7() {
	            if (this.num2 >= 2) {
	                return "../images/my/xx.png";
	            }
	            return '';
	        },
	        img8: function img8() {
	            if (this.num2 >= 3) {
	                return "../images/my/xx.png";
	            }
	            return '';
	        },
	        img9: function img9() {
	            if (this.num2 >= 4) {
	                return "../images/my/xx.png";
	            }
	            return '';
	        },
	        img10: function img10() {
	            if (this.num2 >= 5) {
	                return "../images/my/xx.png";
	            }
	            return '';
	        }
	    },
	    methods: {
	        turnToIntegral: function turnToIntegral() {
	            var nav = weex.requireModule("navigator");
	            nav.push('integralAdministration.js');
	        },

	        jump1: function jump1() {
	            //跳转到实名认证
	            var self = this;
	            if (self.logined == false) {
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '请您登录！', duration: 0.3 });
	            } else {
	                var nav = weex.requireModule('navigator');
	                nav.push('realName.js');
	            }
	        },
	        jump2: function jump2() {
	            //跳转到资金管理
	            var self = this;
	            if (self.logined == false) {
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '请您登录！', duration: 0.3 });
	            } else {
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '功能正在开发中！', duration: 0.3 });
	            }
	        },
	        jump3: function jump3() {
	            //跳转到技能管理
	            var self = this;
	            if (self.logined == false) {
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '请您登录！', duration: 0.3 });
	            } else {
	                var nav = weex.requireModule('navigator');
	                nav.push('EligibleApplyFor.js');
	            }
	        },
	        jump4: function jump4() {
	            //跳转到商铺管理
	            var self = this;
	            if (self.logined == false) {
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '请您登录！', duration: 0.3 });
	            } else {
	                var nav = weex.requireModule('navigator');
	                nav.push('storeGuanli.js');
	            }
	        },
	        jump5: function jump5() {
	            //跳转到社区
	            var self = this;
	            if (self.logined == false) {
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '请您登录！', duration: 0.3 });
	            } else {
	                var nav = weex.requireModule('navigator');
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '功能正在开发中！', duration: 0.3 });
	            }
	        },
	        jump6: function jump6() {
	            //跳转到推送订阅
	            var self = this;
	            if (self.logined == false) {
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '请您登录！', duration: 0.3 });
	            } else {
	                var nav = weex.requireModule('navigator');
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '功能正在开发中！', duration: 0.3 });
	            }
	        },
	        jump7: function jump7() {
	            //跳转到积分管理
	            var self = this;
	            if (self.logined == false) {
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '请您登录！', duration: 0.3 });
	            } else {
	                var nav = weex.requireModule('navigator');
	                nav.push('integralAdministration.js');
	            }
	        },
	        jump8: function jump8() {
	            //跳转到评价,评价页面有问题
	            var self = this;
	            if (self.logined == false) {
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '请您登录！', duration: 0.3 });
	            } else {
	                var nav = weex.requireModule('navigator');
	                nav.push('myEvaluate.js');
	            }
	        },
	        jump9: function jump9() {
	            //跳转到发布的需求
	            var self = this;
	            if (self.logined == false) {
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '请您登录！', duration: 0.3 });
	            } else {
	                var nav = weex.requireModule('navigator');
	                nav.push('myDemand.js');
	            }
	        },
	        jump10: function jump10() {
	            //跳转到发布的任务
	            var self = this;
	            if (self.logined == false) {
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '请您登录！', duration: 0.3 });
	            } else {
	                var nav = weex.requireModule('navigator');
	                nav.push('../task/Task_Published.js');
	            }
	        },
	        jump11: function jump11() {
	            //跳转到参与的任务
	            var self = this;
	            if (self.logined == false) {
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '请您登录！', duration: 0.3 });
	            } else {
	                var nav = weex.requireModule('navigator');
	                nav.push('../task/wcydrw.js');
	            }
	        },
	        jump12: function jump12() {
	            //跳转到审核的任务
	            var self = this;
	            if (self.logined == false) {
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '请您登录！', duration: 0.3 });
	            } else {
	                var nav = weex.requireModule('navigator');
	                nav.push('../task/Check.js');
	            }
	        },
	        jump13: function jump13() {
	            //跳转到关注的任务
	            var self = this;
	            if (self.logined == false) {
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '请您登录！', duration: 0.3 });
	            } else {
	                var nav = weex.requireModule('navigator');
	                nav.push('focusTasks.js');
	            }
	        },
	        setpage: function setpage() {
	            //跳转到设置
	            var self = this;
	            if (self.logined == false) {
	                var modal = weex.requireModule('modal');
	                modal.toast({ message: '请您登录！', duration: 0.3 });
	            } else {
	                var nav = weex.requireModule('navigator');
	                nav.push('set.js');
	            }
	        },
	        jump_login: function jump_login() {
	            //默认的文字点击         myInfo
	            var nav = weex.requireModule('navigator');
	            nav.push('../login/SignIn.js');
	        },
	        jump_img: function jump_img() {
	            //点击头像跳转
	            var self = this;
	            if (self.logined == false) {
	                var nav = weex.requireModule('navigator');
	                nav.push('../login/SignIn.js');
	            } else {
	                var nav = weex.requireModule('navigator');
	                nav.push('myInfo.js');
	            }
	        },
	        _usered: function _usered() {
	            //用户头像和名字定义函数
	            var self = this;
	            _net2.default.post('center/findAllInformation', {}, //用户基本信息（头像、姓名）
	            function (e) {
	                if (e != null && e.status == 1) {
	                    //e!=null&&
	                    //                    var modal = weex.requireModule('modal');
	                    //                    modal.toast({message:111111111,duration: 0.3});
	                    self.images = e.data.userInfo.headImage;
	                    if (e.data.userInfo.nickName != "" && e.data.userInfo.nickName != null) {
	                        //名称
	                        self.name = e.data.userInfo.nickName;
	                    } else if (e.data.userInfo.nickName == "" || e.data.userInfo.nickName == null) {
	                        self.name = e.data.userInfo.mobile;
	                    };
	                    if (e.data.userInfo.headImage == "" || e.data.userInfo.headImage == null) {
	                        //头像
	                        self.src = "../images/7.1.0-me/default.png";
	                    } else {
	                        self.src = "http://10.39.1.72:8081/cdtp/file/show/" + e.data.userInfo.headImage;
	                    };
	                    //                       var modal = weex.requireModule('modal');
	                    //                       modal.alert({message:self.src,duration: 0.3});
	                }
	            });
	        },
	        _task_count: function _task_count() {
	            //发布任务数、参与任务数定义函数
	            var self = this;
	            var modal = weex.requireModule('modal');
	            _net2.default.post('taskHall/statisticsOrderCount', {}, //发布任务数、参与任务数
	            function (e) {
	                if (e.data != null && e.status == 1) {
	                    if (e.data.publishCount != '' && e.data.publishCount != null) {
	                        //                           modal.alert({message:self.publishCount})
	                        self.publishCount = e.data.publishCount;
	                    }
	                    if (e.data.acceptCount != '' && e.data.acceptCount != null) {
	                        self.acceptCount = e.data.acceptCount; //参与任务数
	                    }
	                }
	            });
	        },
	        _grade: function _grade() {
	            //用户等级
	            _net2.default.post('integral/findAllIntegral', {}, //用户等级
	            function (e) {
	                if (e.status == 1) {
	                    if (e.data.stars) {
	                        self.num1 = e.data.stars;
	                        if (e.data.stars == 0) {
	                            self.num1 = 1;
	                        }
	                        self.startimg = "../images/my/xx.png";
	                    } else if (e.data.diamond) {
	                        self.num1 = e.data.diamond;
	                        self.startimg = "../images/my/zs.png";
	                    } else if (e.data.crown) {
	                        self.num1 = e.data.crown;
	                        self.startimg = "../images/my/hg.png";
	                    } else if (e.data.allmessage == null) {
	                        self.num1 = 1;
	                        self.startimg = "../images/my/xx.png";
	                    }
	                }
	            });
	        }
	    },
	    created: function created() {
	        var self = this;
	        var globalEvent = weex.requireModule('globalEvent'); //首页设置进入其他页面后返回首页代码

	        globalEvent.addEventListener("onPageInit", function (e) {
	            var notify = weex.requireModule('notify');
	            notify.regist("lxrRefresh", function (res) {
	                if (res.data == 1) {
	                    //根据用户登录情况显示名称
	                    self.isshow1 = true;
	                    self.isshow2 = false;
	                    self.num1 = 1;
	                    self.num2 = 5;
	                    self.logined = true;
	                    self._usered(); //用户姓名和头像信息
	                    self._task_count(); //任务数
	                    self._grade(); //等级
	                } else {
	                    //没有登录情况
	                    self.isshow1 = false;
	                    self.isshow2 = true;
	                    self.num1 = 0;
	                    self.num2 = 0;
	                    self.logined = false;
	                    self.src = "../images/7.1.0-me/default.png";
	                }
	            });
	            notify.regist("refreshHeadImg", function (res) {
	                if (res.data == '1') {
	                    _net2.default.post('center/findAllInformation', {}, //用户基本信息（头像、姓名）
	                    function (e) {
	                        if (e != null && e.status == 1) {
	                            self.src = "http://10.39.1.72:8081/cdtp/file/show/" + e.data.userInfo.headImage;
	                        }
	                    });
	                }
	            });

	            var nav = weex.requireModule('navigator');
	            nav.setPageId('qualification');
	            nav.setRoot('qualification');
	        });
	        //        net.post('user/getStatus',{},function(e){    //根据用户登录情况显示名称
	        //            if(e.status == 0){          				//未登录情况
	        //                self.isshow1 = false;
	        //                self.isshow2 = true;
	        //                self.num1 = 0;
	        //                self.num2 = 0;
	        //                self.logined = false;
	        //                self.src = "../images/7.1.0-me/default.png";
	        ////
	        //            }else{                     //登录情况
	        //                self.isshow1 = true;
	        //                self.isshow2 = false;
	        //                self.logined = true;
	        //            }
	        //        });

	        //  	net.post('taskHall/recommendTask',
	        //  			{pageNum:1,pageSize:5},
	        //        		function (e) {
	        ////					console.log(e.data)
	        //        			self.lists=e.data.list;
	        ////        			modal.alert({message:self.lists});
	        //  	});
	    }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 489:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('scroller', [_c('div', {
	    staticClass: ["me-container"],
	    staticStyle: {
	      backgroundColor: "#eee",
	      height: "1350px"
	    }
	  }, [_c('topBar', {
	    attrs: {
	      "title": _vm.title,
	      "back": false
	    }
	  }), _c('image', {
	    staticStyle: {
	      height: "36px",
	      width: "36px",
	      position: "relative",
	      top: "-63px",
	      left: "684px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/shezhi.png"
	    },
	    on: {
	      "click": _vm.setpage
	    }
	  }), _c('image', {
	    staticClass: ["img1"],
	    staticStyle: {
	      height: "280px",
	      width: "750px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/bg.png"
	    }
	  }), _c('image', {
	    staticClass: ["img2"],
	    staticStyle: {
	      height: "488px",
	      width: "710px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/bg1.png"
	    }
	  }), _c('div', {
	    staticStyle: {
	      position: "relative",
	      top: "-710px",
	      left: "0px",
	      width: "750px",
	      height: "auto"
	    }
	  }, [_c('div', {
	    staticClass: ["pep"]
	  }, [_c('div', {
	    staticClass: ["imgbox"],
	    on: {
	      "click": _vm.jump_img
	    }
	  }, [_c('image', {
	    staticClass: ["head_img_image"],
	    staticStyle: {
	      height: "120px",
	      width: "120px",
	      borderRadius: "60px",
	      position: "absolute",
	      top: "0",
	      bottom: "0",
	      left: "0",
	      right: "0"
	    },
	    attrs: {
	      "src": _vm.src
	    }
	  })]), (_vm.isshow1) ? _c('text', {
	    staticStyle: {
	      marginTop: "10px",
	      fontSize: "28px",
	      width: "320px",
	      textAlign: "center"
	    }
	  }, [_vm._v(_vm._s(_vm.name))]) : _vm._e(), (_vm.isshow2) ? _c('text', {
	    staticClass: ["text_jump"],
	    staticStyle: {
	      marginTop: "10px",
	      fontSize: "28px",
	      width: "320px",
	      textAlign: "center",
	      color: "#17ACF6"
	    },
	    on: {
	      "click": _vm.jump_login
	    }
	  }, [_vm._v("点击登录")]) : _vm._e()]), _c('div', {
	    staticClass: ["dengji_box"]
	  }, [_c('div', {
	    staticClass: ["list1"],
	    staticStyle: {
	      marginTop: "36px"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "26px"
	    }
	  }, [_vm._v("等级：")]), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": _vm.img1
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": _vm.img2
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": _vm.img3
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": _vm.img4
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": _vm.img5
	    }
	  })]), _c('div', {
	    staticClass: ["list1"],
	    staticStyle: {
	      marginTop: "22px"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "26px"
	    }
	  }, [_vm._v("信用：")]), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": _vm.img6
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": _vm.img7
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": _vm.img8
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": _vm.img9
	    }
	  }), _c('image', {
	    staticClass: ["star_image"],
	    attrs: {
	      "src": _vm.img10
	    }
	  })])]), _c('div', {
	    staticStyle: {
	      flexDirection: "row",
	      justifyContent: "center",
	      alignItems: "center"
	    }
	  }, [_c('div', {
	    staticClass: ["count_box"]
	  }, [_c('div', {
	    staticClass: ["count_list"],
	    staticStyle: {
	      width: "150px",
	      justifyContent: "center",
	      alignItems: "center"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#FFA529",
	      fontSize: "30px",
	      marginBottom: "18px"
	    }
	  }, [_vm._v(_vm._s(_vm.publishCount))]), _c('text', {
	    staticStyle: {
	      color: "#17ACF6",
	      fontSize: "24px"
	    }
	  }, [_vm._v("发布任务数")])]), _c('div', {
	    staticClass: ["count_list"],
	    staticStyle: {
	      width: "195px",
	      justifyContent: "center",
	      alignItems: "center"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#FFA529",
	      fontSize: "30px",
	      marginBottom: "18px"
	    }
	  }, [_vm._v(_vm._s(_vm.acceptCount))]), _c('text', {
	    staticStyle: {
	      color: "#17ACF6",
	      fontSize: "24px"
	    }
	  }, [_vm._v("参与任务数")])]), _c('div', {
	    staticClass: ["count_list"],
	    staticStyle: {
	      width: "195px",
	      justifyContent: "center",
	      alignItems: "center"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#FFA529",
	      fontSize: "30px",
	      marginBottom: "18px"
	    }
	  }, [_vm._v(_vm._s(_vm.expenditure))]), _c('text', {
	    staticStyle: {
	      color: "#17ACF6",
	      fontSize: "24px"
	    }
	  }, [_vm._v("任务支出额")])]), _c('div', {
	    staticClass: ["count_list"],
	    staticStyle: {
	      width: "160px",
	      borderRightColor: "#eee",
	      justifyContent: "center",
	      alignItems: "center"
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#FFA529",
	      fontSize: "30px",
	      marginBottom: "18px"
	    }
	  }, [_vm._v(_vm._s(_vm.income))]), _c('text', {
	    staticStyle: {
	      color: "#17ACF6",
	      fontSize: "24px"
	    }
	  }, [_vm._v("任务收入额")])])])]), _c('div', {
	    staticStyle: {
	      flexDirection: "row",
	      justifyContent: "center",
	      marginTop: "5px"
	    }
	  }, [_c('div', {
	    staticClass: ["data_area"]
	  }, [_c('div', {
	    staticClass: ["data_l"],
	    on: {
	      "click": _vm.jump9
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "64px",
	      height: "64px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/fabuxuqiu.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      fontSize: "24px"
	    }
	  }, [_vm._v("发布的需求")])]), _c('div', {
	    staticStyle: {
	      flexDirection: "column"
	    }
	  }, [_c('div', {
	    staticClass: ["data_list"],
	    staticStyle: {
	      marginLeft: "10px",
	      marginBottom: "10px"
	    },
	    on: {
	      "click": _vm.jump10
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/fabuxuqiu.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      fontSize: "24px"
	    }
	  }, [_vm._v("发布的任务")])]), _c('div', {
	    staticClass: ["data_list"],
	    staticStyle: {
	      marginLeft: "10px",
	      marginBottom: "10px"
	    },
	    on: {
	      "click": _vm.jump11
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/faburenwu.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      fontSize: "24px"
	    }
	  }, [_vm._v("参与的任务")])])]), _c('div', {
	    staticStyle: {
	      flexDirection: "column"
	    }
	  }, [_c('div', {
	    staticClass: ["data_list"],
	    staticStyle: {
	      marginLeft: "10px",
	      marginBottom: "10px"
	    },
	    on: {
	      "click": _vm.jump12
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/shenherenwu.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      fontSize: "24px"
	    }
	  }, [_vm._v("审核的任务")])]), _c('div', {
	    staticClass: ["data_list"],
	    staticStyle: {
	      marginLeft: "10px"
	    },
	    on: {
	      "click": _vm.jump13
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/guanzhude.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      fontSize: "24px"
	    }
	  }, [_vm._v("关注的任务")])])])])]), _c('div', {
	    staticStyle: {
	      flexDirection: "row",
	      justifyContent: "center",
	      marginTop: "18px",
	      marginBottom: "20px"
	    }
	  }, [_c('div', {
	    staticClass: ["list_box", "listbox"]
	  }, [_c('div', {
	    staticClass: ["list_box1"]
	  }, [_c('div', {
	    staticClass: ["item_list"],
	    staticStyle: {
	      marginLeft: "40px"
	    },
	    on: {
	      "click": _vm.jump1
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/shimingg.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      marginTop: "20px",
	      fontSize: "24px"
	    }
	  }, [_vm._v("实名认证")])]), _c('div', {
	    staticClass: ["item_list"],
	    on: {
	      "click": _vm.jump2
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/zijin.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      marginTop: "20px",
	      fontSize: "24px"
	    }
	  }, [_vm._v("资金管理")])]), _c('div', {
	    staticClass: ["item_list"],
	    on: {
	      "click": _vm.jump3
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/jineng.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      marginTop: "20px",
	      fontSize: "24px"
	    }
	  }, [_vm._v("技能管理")])]), _c('div', {
	    staticClass: ["item_list"],
	    staticStyle: {
	      marginRight: "40px"
	    },
	    on: {
	      "click": _vm.jump4
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/dianu.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      marginTop: "20px",
	      fontSize: "24px"
	    }
	  }, [_vm._v("商铺管理")])])]), _c('div', {
	    staticClass: ["list_box2"]
	  }, [_c('div', {
	    staticClass: ["item_list"],
	    staticStyle: {
	      marginLeft: "40px"
	    },
	    on: {
	      "click": _vm.jump5
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/shequ.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      marginTop: "20px",
	      fontSize: "24px"
	    }
	  }, [_vm._v("社区")])]), _c('div', {
	    staticClass: ["item_list"],
	    on: {
	      "click": _vm.jump6
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/tuisong.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      marginTop: "20px",
	      fontSize: "24px"
	    }
	  }, [_vm._v("推送订阅")])]), _c('div', {
	    staticClass: ["item_list"],
	    on: {
	      "click": _vm.jump7
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/jifen.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      marginTop: "20px",
	      fontSize: "24px"
	    }
	  }, [_vm._v("积分管理")])]), _c('div', {
	    staticClass: ["item_list"],
	    staticStyle: {
	      marginRight: "40px"
	    },
	    on: {
	      "click": _vm.jump8
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "48px",
	      height: "48px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/pingjia.png"
	    }
	  }), _c('text', {
	    staticStyle: {
	      marginTop: "20px",
	      fontSize: "24px"
	    }
	  }, [_vm._v("评价")])])])])])])], 1)])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });