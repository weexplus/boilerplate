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
	__vue_styles__.push(__webpack_require__(568)
	)

	/* script */
	__vue_exports__ = __webpack_require__(569)

	/* template */
	var __vue_template__ = __webpack_require__(570)
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
	__vue_options__.__file = "D:\\workproject\\NetworkDesign1\\netplatform\\src\\my\\myInfo.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-3e79d08b"
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

/***/ 102:
/***/ (function(module, exports) {

	"use strict";

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});
	//var host='http://10.39.1.237:8081/cdtp/';
	// var host='http://10.39.1.72:8081/cdtp/';
	//var host='http://10.2.115.90:8081/cdtp/';
	// var host='http://10.39.1.72:8081/cdtp/'
	// var host='http://123.58.243.114:10081/CDTPWebGateway/'
	// var host='http://172.23.203.1:8081/cdtp/';
	//var token= "9191385a2655505203de264eb6b1daa6";
	//var refreshToken="242ba17b567daf0fa1fe5290da3bbe43";
	//var Header = {
	//	token: token,
	//	refreshToken: refreshToken
	//var host='http://10.39.1.72:8081/cdtp/';
	var host = 'http://10.39.1.72:8081/cdtp/';
	var token = "56d1907e7068a0e6436c16cbf452bf82";
	var refreshToken = "2ba2e86d66a8f92ef6ea319a5013d9de";
	var Header = {
	    token: token,
	    refreshToken: refreshToken
	};

	exports.default = {
	    getHost: function getHost() {
	        return host;
	    },

	    //	userId: '85935039157a4740aac07aa0e015a7e8',x
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
	        } else {
	            header.token = Header.token;
	            header.refreshToken = Header.refreshToken;
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

/***/ 137:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(138)
	)
	__vue_styles__.push(__webpack_require__(139)
	)

	/* script */
	__vue_exports__ = __webpack_require__(140)

	/* template */
	var __vue_template__ = __webpack_require__(141)
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
	__vue_options__.__file = "D:\\workproject\\NetworkDesign1\\netplatform\\src\\vue\\component\\head.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-65d60664"
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

/***/ 138:
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

/***/ 139:
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

/***/ 140:
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

/***/ 141:
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

/***/ 568:
/***/ (function(module, exports) {

	module.exports = {
	  "zhezhao": {
	    "position": "absolute",
	    "left": 0,
	    "top": 0,
	    "width": 750,
	    "height": 2000,
	    "backgroundColor": "rgba(1,1,1,0.5)",
	    "alignItems": "center"
	  },
	  "form": {
	    "alignItems": "center",
	    "width": 602,
	    "height": 329,
	    "backgroundColor": "#ffffff",
	    "borderRadius": 20,
	    "marginTop": 400
	  },
	  "addtitle": {
	    "fontSize": 32,
	    "color": "#333333",
	    "marginTop": 36,
	    "marginBottom": 48
	  },
	  "textTitle": {
	    "marginLeft": 30,
	    "fontSize": 28,
	    "color": "#363636"
	  },
	  "icon": {
	    "width": 35,
	    "height": 35,
	    "marginLeft": 30
	  },
	  "cancle": {
	    "height": 100,
	    "width": 301,
	    "backgroundColor": "#ffffff",
	    "borderBottomLeftRadius": 20,
	    "flexDirection": "row",
	    "borderTopColor": "#e8e8e8",
	    "borderTopWidth": 1,
	    "borderTopStyle": "solid",
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "confirm": {
	    "height": 100,
	    "width": 301,
	    "backgroundColor": "#17acf6",
	    "borderBottomRightRadius": 20,
	    "flexDirection": "row",
	    "borderColor": "#17acf6",
	    "borderRightWidth": 1,
	    "borderRightStyle": "solid",
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "can_text": {
	    "fontSize": 32,
	    "color": "#999999"
	  },
	  "con_text": {
	    "fontSize": 32,
	    "color": "#ffffff"
	  },
	  "aaa": {
	    "width": 750,
	    "height": 1200,
	    "backgroundColor": "#808080",
	    "position": "absolute",
	    "top": 0,
	    "left": 0
	  },
	  "popArea": {
	    "width": 300,
	    "height": 160,
	    "backgroundColor": "#ffffff",
	    "position": "absolute",
	    "left": 200,
	    "top": 500
	  },
	  "photo": {
	    "width": 300,
	    "height": 80,
	    "alignItems": "center",
	    "justifyContent": "center",
	    "borderBottomStyle": "solid",
	    "borderBottomColor": "#808080",
	    "borderBottomWidth": 1
	  },
	  "photo_text": {
	    "fontSize": 30,
	    "color": "#808080"
	  },
	  "myInfo": {
	    "width": 750,
	    "position": "relative",
	    "left": 0,
	    "right": 0,
	    "bottom": 0,
	    "backgroundColor": "#EEEEEE",
	    "flexDirection": "column"
	  },
	  "title": {
	    "width": 750,
	    "height": 138,
	    "backgroundColor": "#FFFFFF",
	    "flexDirection": "row",
	    "alignItems": "center",
	    "paddingLeft": 40
	  },
	  "titletext": {
	    "fontSize": 26,
	    "color": "#363636",
	    "marginLeft": 36,
	    "fontWeight": "bold"
	  },
	  "titleimg": {
	    "position": "absolute",
	    "right": 46,
	    "top": 55,
	    "width": 16,
	    "height": 30
	  },
	  "listitem": {
	    "width": 750,
	    "height": 90,
	    "marginTop": 15,
	    "backgroundColor": "#FFFFFF",
	    "flexDirection": "row",
	    "alignItems": "center",
	    "paddingLeft": 40
	  },
	  "list_item_text": {
	    "fontSize": 30,
	    "fontWeight": "500",
	    "marginLeft": 20
	  },
	  "right_wrapper": {
	    "position": "absolute",
	    "top": 30,
	    "right": 46,
	    "flexDirection": "row",
	    "alignItems": "center",
	    "justifyContent": "center"
	  },
	  "list_item_img": {
	    "width": 16,
	    "height": 30,
	    "marginLeft": 20
	  },
	  "info": {
	    "fontSize": 25,
	    "color": "#999999"
	  }
	}

/***/ }),

/***/ 569:
/***/ (function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _head = __webpack_require__(137);

	var _head2 = _interopRequireDefault(_head);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	var net = __webpack_require__(102); //
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//

	var modal = weex.requireModule('modal');
	exports.default = {
	    data: function data() {
	        return {
	            title: '个人信息',
	            myinfo: {
	                'name': '',
	                'sex': '',
	                'headImg': '',
	                'acount': '',
	                'phoneNumber': '',
	                'weiXin': '',
	                'email': ''
	            },
	            back: '',
	            src: '',
	            sex: 0,
	            changeSex: false,
	            imageHead: 'http://10.39.1.72:8081/cdtp/file/show/'
	        };
	    },

	    computed: {
	        imageUrl: function imageUrl() {
	            var url = this.imageHead + this.myinfo.headImg;
	            return url;
	        },
	        imageSrc1: function imageSrc1() {
	            if (this.sex == 1) {
	                return "../images/6.3.1/unsel.png";
	            } else {
	                return "../images/6.3.1/sel.png";
	            }
	        },
	        imageSrc2: function imageSrc2() {
	            if (this.sex == 1) {
	                return "../images/6.3.1/sel.png";
	            } else {
	                return "../images/6.3.1/unsel.png";
	            }
	        }
	    },
	    components: {
	        Head: _head2.default
	    },
	    methods: {
	        pre: function pre() {},
	        click1: function click1() {
	            this.sex = 0;
	        },
	        click2: function click2() {
	            this.sex = 1;
	        },
	        confirmed: function confirmed() {
	            var self = this;
	            if (this.sex == 0) {

	                net.post('/personal/updateSex', { 'sex': '男' }, function (e) {
	                    if (e != null && e.status == 1) {
	                        modal.toast({ message: '修改成功' });
	                        self.myinfo.sex = '男';
	                    } else {
	                        modal.toast({ message: '修改失败' });
	                    }
	                });
	            } else {

	                net.post('/personal/updateSex', { 'sex': '女' }, function (e) {
	                    if (e != null && e.status == 1) {
	                        modal.toast({ message: '修改成功' });
	                        self.myinfo.sex = '女';
	                    } else {
	                        modal.toast({ message: '修改失败' });
	                    }
	                });
	            }
	            this.changeSex = false;
	        },
	        cancled: function cancled() {
	            this.changeSex = false;
	        },
	        post: function post() {
	            var self = this;
	            net.post('center/findAllInformation', {}, function (e) {
	                if (e != null && e.status == 1) {
	                    var a = e.data.userInfo;
	                    self.myinfo.name = a.nickName;
	                    self.myinfo.headImg = a.headImage;
	                    self.imageUrl = self.imageHead + self.myinfo.headImg;
	                    self.myinfo.sex = a.gender;
	                    if (self.myinfo.sex == "男") {
	                        self.sex = 0;
	                    } else {
	                        self.sex = 1;
	                    }
	                    self.myinfo.acount = a.loginName;
	                    self.myinfo.phoneNumber = e.data.userInfo.mobile;
	                    self.myinfo.email = e.data.userInfo.email;
	                }
	            });
	        },


	        changeImage: function changeImage() {
	            var src = '';
	            var self = this;
	            var photo = weex.requireModule('photo');

	            var host = net.host;
	            /*modal.alert({message:header});*/
	            photo.open(500, 500, '#000000', '#ffffff', '#ffffff', function (e) {
	                //打开相机
	                self.src = e.path;
	                var net1 = weex.requireModule("net");
	                net1.postFile('http://10.39.1.72:8081/cdtp/file/uploadReturnName', {//param

	                }, {
	                    //header
	                }, { file: e.path }, function () {
	                    //start
	                }, function (e) {
	                    /* modal.alert({message:e});*/
	                    //succcess
	                    if (typeof e == 'string') {
	                        var obj = JSON.parse(e);
	                        self.myinfo.headImg = obj.data.filePath;
	                    } else {
	                        var path = e.res.data.filePath;
	                        self.myinfo.headImg = path;
	                    }
	                    net.post('personal/setPhoto', { photoUrl: self.myinfo.headImg }, function (e) {
	                        if (e != null && e.status == 1) {
	                            modal.alert({ message: '更改成功' });
	                        }
	                    });
	                }, function () {
	                    //compelete

	                }, function () {
	                    //exception
	                    var modal = weex.requireModule("modal");
	                    modal.toast({ message: '上传异常！' });
	                });
	            });
	            /*var net1 = weex.requireModule("net");
	                               var modal = weex.requireModule("modal");
	                               net1.postFile('http://10.39.1.72:8081/cdtp/file/upload', {}, header, {file: e.path}, () => {
	                               }, (m) => {
	                                   
	                                   modal.toast({message: '上传成功！'});
	                                  
	                                   var a=m.split('"');
	                                   self.myinfo.headImg=a[1];
	                                   net.post('personal/setPhoto', {photoUrl: self.myinfo.headImg}, function (e) {
	                                    modal.alert({message: e});
	                                   });
	                               }, (e) => {
	                                  
	                               }, (e) => {
	                                  
	                                   modal.alert({message:'上传失败'})
	                               });*/
	        },
	        clickSex: function clickSex() {
	            this.changeSex = true;
	        },
	        //
	        changeAcount: function changeAcount() {
	            var self = this;
	            var arry = self.myinfo.acount.split("");
	            if (arry[0] == 'm' || self.myinfo.acount == '' || self.myinfo.acount == null) {
	                modal.prompt({
	                    message: '修改你的账号',
	                    duration: 0.3,
	                    okTitle: '确认',
	                    cancelTitle: '取消'
	                }, function (value) {
	                    if (value.data != '') {
	                        var re = /[a-zA-Z0-9]*/;
	                        if (re.test(value.data)) {
	                            self.myinfo.acount = value.data;
	                            net.post('/personal/setLoginName', { 'loginName': self.myinfo.acount }, function (e) {
	                                modal.alert({ message: '修改成功' });
	                            });
	                        } else {
	                            modal.alert({ message: '账号格式只能是字母开头，只能包含数字和字母' });
	                        }
	                    }
	                });
	            } else {
	                modal.alert({ message: '账号只能修改一次，您已经修改过账号了' });
	            }
	        },
	        changeNickName: function changeNickName() {
	            var self = this;
	            if (self.myinfo.name == '' || self.myinfo.name == null) {
	                modal.prompt({
	                    message: '修改你的昵称',
	                    duration: 0.3,
	                    okTitle: '确认',
	                    cancelTitle: '取消'
	                }, function (value) {
	                    if (value.data != '') {
	                        self.myinfo.name = value.data;
	                        net.post('/personal/setNickName', { 'nickName': self.myinfo.name }, function (e) {});
	                    } else {}
	                });
	            } else {
	                modal.alert({ message: '您已经修改过一次昵称，不能再次修改' });
	            }
	        },
	        changePhoneNumber: function changePhoneNumber() {
	            var self = this;
	            var nav = weex.requireModule('navigator');
	            nav.setPageId('myinfo');
	            nav.pushParam('../my/changePhoneNumber.js', { phoneNumber: self.myinfo.phoneNumber });
	        },
	        changeEmail: function changeEmail() {
	            var self = this;
	            modal.prompt({
	                message: '修改你的邮箱',
	                duration: 0.3,
	                okTitle: '确认',
	                cancelTitle: '取消'
	            }, function (value) {
	                if (value.data != '') {
	                    var re = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;

	                    if (re.test(value.data)) {
	                        self.myinfo.email = value.data;
	                        net.post('personal/updateEmail', { 'email': self.myinfo.email }, function (e) {
	                            if (e.status == 1) {
	                                modal.toast({ message: '邮箱修改成功' });
	                            }
	                        });
	                    } else {
	                        modal.toast({ message: '邮箱格式不对' });
	                    }
	                }
	            });
	        }

	    },

	    created: function created() {
	        this.post();
	        var globalEvent = weex.requireModule('globalEvent');
	        globalEvent.addEventListener("onPageInit", function (e) {
	            var navigator = weex.requireModule('navigator');
	            navigator.addBackGestureSelfControl();
	        });
	    }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 570:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["myInfo"]
	  }, [_c('Head', {
	    attrs: {
	      "title": _vm.title
	    }
	  }), _c('div', {
	    staticClass: ["title"],
	    on: {
	      "click": _vm.changeImage
	    }
	  }, [_c('div', {
	    staticStyle: {
	      width: "86px",
	      height: "86px",
	      borderRadius: "86px",
	      overflow: "hidden"
	    }
	  }, [(_vm.myinfo.headImg != null) ? _c('image', {
	    staticStyle: {
	      width: "86px",
	      height: "86px"
	    },
	    attrs: {
	      "src": _vm.imageUrl
	    }
	  }) : _c('image', {
	    staticStyle: {
	      width: "86px",
	      height: "86px"
	    },
	    attrs: {
	      "src": "../images/7.1.0-me/1.png"
	    }
	  })]), _c('text', {
	    staticClass: ["titletext"]
	  }, [_vm._v(_vm._s(_vm.myinfo.name))]), _c('image', {
	    staticClass: ["titleimg"],
	    attrs: {
	      "src": "../images/my/icon1.png"
	    }
	  })]), _vm._m(0), _c('div', {
	    staticClass: ["listitem"],
	    on: {
	      "click": _vm.clickSex
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "53px",
	      height: "53px"
	    },
	    attrs: {
	      "src": "../images/my/xingbie.png"
	    }
	  }), _c('text', {
	    staticClass: ["list_item_text"]
	  }, [_vm._v("性别")]), _c('div', {
	    staticClass: ["right_wrapper"]
	  }, [_c('text', {
	    staticClass: ["info"]
	  }, [_vm._v(_vm._s(_vm.myinfo.sex))]), _c('image', {
	    staticClass: ["list_item_img"],
	    attrs: {
	      "src": "../images/my/icon1.png"
	    }
	  })])]), _c('div', {
	    staticClass: ["listitem"],
	    on: {
	      "click": _vm.changeAcount
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "53px",
	      height: "53px"
	    },
	    attrs: {
	      "src": "../images/my/zhnghao.png"
	    }
	  }), _c('text', {
	    staticClass: ["list_item_text"]
	  }, [_vm._v("账号")]), _c('div', {
	    staticClass: ["right_wrapper"]
	  }, [_c('text', {
	    staticClass: ["info"]
	  }, [_vm._v(_vm._s(_vm.myinfo.acount))]), _c('image', {
	    staticClass: ["list_item_img"],
	    attrs: {
	      "src": "../images/my/icon1.png"
	    }
	  })])]), _c('div', {
	    staticClass: ["listitem"],
	    on: {
	      "click": _vm.changeNickName
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "53px",
	      height: "53px"
	    },
	    attrs: {
	      "src": "../images/my/nicheng.png"
	    }
	  }), _c('text', {
	    staticClass: ["list_item_text"]
	  }, [_vm._v("昵称")]), _c('div', {
	    staticClass: ["right_wrapper"]
	  }, [_c('text', {
	    staticClass: ["info"]
	  }, [_vm._v(_vm._s(_vm.myinfo.name))]), _c('image', {
	    staticClass: ["list_item_img"],
	    attrs: {
	      "src": "../images/my/icon1.png"
	    }
	  })])]), _c('div', {
	    staticClass: ["listitem"],
	    on: {
	      "click": _vm.changePhoneNumber
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "53px",
	      height: "53px"
	    },
	    attrs: {
	      "src": "../images/my/shouji.png"
	    }
	  }), _c('text', {
	    staticClass: ["list_item_text"]
	  }, [_vm._v("手机号")]), _c('div', {
	    staticClass: ["right_wrapper"]
	  }, [_c('text', {
	    staticClass: ["info"]
	  }, [_vm._v(_vm._s(_vm.myinfo.phoneNumber))]), _c('image', {
	    staticClass: ["list_item_img"],
	    attrs: {
	      "src": "../images/my/icon1.png"
	    }
	  })])]), _c('div', {
	    staticClass: ["listitem"],
	    on: {
	      "click": _vm.changeEmail
	    }
	  }, [_c('image', {
	    staticStyle: {
	      width: "53px",
	      height: "53px"
	    },
	    attrs: {
	      "src": "../images/my/youxiang.png"
	    }
	  }), _c('text', {
	    staticClass: ["list_item_text"]
	  }, [_vm._v("邮箱")]), _c('div', {
	    staticClass: ["right_wrapper"]
	  }, [_c('text', {
	    staticClass: ["info"]
	  }, [_vm._v(_vm._s(_vm.myinfo.email))]), _c('image', {
	    staticClass: ["list_item_img"],
	    attrs: {
	      "src": "../images/my/icon1.png"
	    }
	  })])]), (_vm.changeSex) ? _c('div', {
	    staticClass: ["zhezhao"],
	    on: {
	      "click": _vm.pre
	    }
	  }, [_c('div', {
	    staticClass: ["form"]
	  }, [_c('text', {
	    staticClass: ["addtitle"]
	  }, [_vm._v("选择性别")]), _c('div', {
	    staticStyle: {
	      width: "602px",
	      flexDirection: "row",
	      alignItems: "center",
	      justifyContent: "center"
	    }
	  }, [_c('image', {
	    staticClass: ["icon"],
	    attrs: {
	      "src": _vm.imageSrc1
	    },
	    on: {
	      "click": _vm.click1
	    }
	  }), _c('text', {
	    staticClass: ["textTitle"]
	  }, [_vm._v("男")]), _c('image', {
	    staticClass: ["icon"],
	    attrs: {
	      "src": _vm.imageSrc2
	    },
	    on: {
	      "click": _vm.click2
	    }
	  }), _c('text', {
	    staticClass: ["textTitle"]
	  }, [_vm._v("女")])]), _c('div', {
	    staticStyle: {
	      flexDirection: "row"
	    }
	  }, [_c('div', {
	    staticClass: ["cancle"],
	    staticStyle: {
	      marginTop: "73px"
	    },
	    on: {
	      "click": _vm.cancled
	    }
	  }, [_c('text', {
	    staticClass: ["can_text"]
	  }, [_vm._v("取消")])]), _c('div', {
	    staticClass: ["confirm"],
	    staticStyle: {
	      marginTop: "73px"
	    },
	    on: {
	      "click": _vm.confirmed
	    }
	  }, [_c('text', {
	    staticClass: ["con_text"]
	  }, [_vm._v("确定")])])])])]) : _vm._e()], 1)
	},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["listitem"]
	  }, [_c('image', {
	    staticStyle: {
	      width: "53px",
	      height: "53px"
	    },
	    attrs: {
	      "src": "../images/my/erweima.png"
	    }
	  }), _c('text', {
	    staticClass: ["list_item_text"]
	  }, [_vm._v("二维码名片")]), _c('div', {
	    staticClass: ["right_wrapper"]
	  }, [_c('text', {
	    staticClass: ["info"]
	  }, [_vm._v("我")]), _c('image', {
	    staticClass: ["list_item_img"],
	    attrs: {
	      "src": "../images/my/icon1.png"
	    }
	  })])])
	}]}
	module.exports.render._withStripped = true

/***/ })

/******/ });