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
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(1)
	)

	/* script */
	__vue_exports__ = __webpack_require__(2)

	/* template */
	var __vue_template__ = __webpack_require__(3)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/Login.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-45a84b18"
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
/* 1 */
/***/ (function(module, exports) {

	module.exports = {
	  "top-bar": {
	    "position": "relative",
	    "height": 90,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "paddingLeft": 70,
	    "paddingRight": 70,
	    "backgroundColor": "#16ADF6",
	    "borderBottomWidth": 1,
	    "borderBottomStyle": "solid",
	    "borderBottomColor": "#eeeeee"
	  },
	  "back": {
	    "position": "absolute",
	    "left": 0,
	    "top": 0,
	    "width": 108,
	    "height": 90,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "color": "#ffffff"
	  },
	  "back-text": {
	    "color": "#999999",
	    "fontSize": 24
	  },
	  "title-name": {
	    "color": "#ffffff",
	    "fontSize": 36
	  },
	  "home-container": {
	    "width": 750,
	    "fontSize": 30,
	    "textAlign": "center",
	    "backgroundColor": "#16ADF6"
	  },
	  "content": {
	    "marginTop": 60,
	    "height": 800,
	    "marginLeft": 70,
	    "marginRight": 70,
	    "backgroundColor": "#ffffff",
	    "borderRadius": 30
	  },
	  "imageContent": {
	    "marginLeft": 90,
	    "marginRight": 90,
	    "marginTop": 30
	  },
	  "img": {
	    "width": 750,
	    "height": 110
	  },
	  "inputcon": {
	    "flex": 1,
	    "paddingLeft": 30,
	    "paddingRight": 30,
	    "height": 48
	  },
	  "inputGroup": {
	    "marginTop": 60,
	    "marginLeft": 70,
	    "marginRight": 70
	  },
	  "user": {
	    "height": 120,
	    "justifyContent": "flex-start",
	    "flexDirection": "row",
	    "alignItems": "center",
	    "borderBottomStyle": "solid",
	    "borderBottomColor": "#EDEDED",
	    "borderBottomWidth": 1
	  },
	  "username": {
	    "color": "#17acf6",
	    "fontSize": 34
	  },
	  "btn": {
	    "marginTop": 78,
	    "marginLeft": 116,
	    "marginRight": 116,
	    "height": 70,
	    "backgroundColor": "#18ACF4",
	    "borderRadius": 100,
	    "justifyContent": "flex-start",
	    "alignItems": "center"
	  },
	  "group": {
	    "marginTop": 90,
	    "justifyContent": "space-between",
	    "flexDirection": "row",
	    "alignItems": "center",
	    "marginLeft": 40,
	    "marginRight": 40
	  },
	  "parent": {
	    "width": 750,
	    "justifyContent": "flex-start",
	    "alignItems": "center"
	  },
	  "wechat": {
	    "marginTop": 50,
	    "width": 73,
	    "height": 73,
	    "backgroundColor": "#ffffff",
	    "borderRadius": 100
	  }
	}

/***/ }),
/* 2 */
/***/ (function(module, exports) {

	"use strict";

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

	var modal = weex.requireModule('modal');
	//    const net = require('../vue/busi/util/net.js');
	//    import TopBar from '../components/headerlogin.vue'
	exports.default = {
	    components: {
	        //            TopBar
	    },
	    data: function data() {
	        return {
	            username: "",
	            password: ""
	        };
	    },
	    methods: {
	        ready: function ready() {
	            var self = this;
	            setTimeout(function () {
	                self.autofocus = true;
	            }, 50000);
	        },
	        onchange: function onchange(event) {
	            this.txtChange = event.value;
	        },

	        oninput: function oninput(event) {
	            this.txtInput = event.value;
	        },
	        login: function login() {
	            //               if (this.check()) {
	            //                  net.post('user/userLoginWithoutVerifyCode',{
	            //                    userName: this.username,
	            //                    password: this.password,
	            //                    clientCode:'app',
	            //                    platformCode:'jypt'
	            ////                  platformCode:'gzt'
	            //                  },function(res){
	            //                    if(res.status == 1){
	            //						var modal=weex.requireModule("modal");
	            //                        modal.toast({
	            //                           message: "登录成功",
	            //                           duration:1
	            //                       })
	            //                        var pref=weex.requireModule("static");
	            //                        var obj={};
	            //                        obj.userInfo = res.data;
	            //                        pref.set('objkey',obj);
	            //                        modal.toast({message:'存储成功'});
	            //
	            //              			pref.setString('user','');
	            //
	            //                        var nav=weex.requireModule("navigator")
	            //                        nav.dismiss();
	            //                        var notify=weex.requireModule("notify")
	            //                        notify.sendNative('tabselect',{name:'我的'})
	            //                         //nav.push('../my/my.js')
	            //                     }else{
	            //                        var modal=weex.requireModule('modal');
	            //                        modal.toast({
	            //                       message: '您输入的用户名或密码不正确，请重新输入！',
	            //                     })
	            //                     }
	            //                  });

	            var nav = weex.requireModule("navigator");
	            nav.dismiss();
	            var notify = weex.requireModule("notify");
	            notify.sendNative('tabselect', { name: '我的' });
	            //               }

	            //导航控制器
	            // var nav=weex.requireModule("navigator")
	            // nav.dismiss();
	            // modal.toast({message:'登录成功!'})
	        },
	        check: function check() {
	            if (this.username == '') {
	                modal.toast({
	                    message: "请输入用户名！",
	                    duration: 0.3
	                });
	                return false;
	            }
	            if (this.password == '') {
	                modal.toast({
	                    message: "请填写密码！",
	                    duration: 0.3
	                });
	                return false;
	            }
	            return true;
	        },
	        register: function register() {
	            //                this.$router.push("register")
	            var nav = weex.requireModule("navigator");
	            nav.push('./Register.js');
	        },
	        telLogin: function telLogin() {
	            //                this.$router.push("mobileLogin")
	            var nav = weex.requireModule("navigator");
	            nav.push('./MobileLogin.js');
	        },
	        wexinLogin: function wexinLogin() {
	            //                this.$router.push("weixinLogin")
	            var nav = weex.requireModule("navigator");
	            nav.push('./WeixinLogin.js');
	            //                nav.pushParam('WeixinLogin.js',{});//带参数传递
	        },
	        forgetpass: function forgetpass() {
	            //                this.$router.push("weixinLogin")
	            var nav = weex.requireModule("navigator");
	            nav.push('./forgetPassword.js');
	            //                nav.pushParam('WeixinLogin.js',{});//带参数传递
	        }

	    }
	};
	module.exports = exports["default"];

/***/ }),
/* 3 */
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["home-container"]
	  }, [_c('div', {
	    staticClass: ["content"]
	  }, [_vm._m(0), _c('div', {
	    staticClass: ["inputGroup"]
	  }, [_c('div', {
	    staticClass: ["user"]
	  }, [_c('image', {
	    staticStyle: {
	      height: "40px",
	      width: "36px"
	    },
	    attrs: {
	      "src": "images/user.png"
	    }
	  }), _c('text', {
	    staticClass: ["username"]
	  }, [_vm._v(" 用户名 ")]), _c('input', {
	    staticClass: ["inputcon"],
	    attrs: {
	      "type": "text",
	      "value": _vm.username,
	      "name": "username",
	      "placeholder": "请输入用户名",
	      "maxlength": "15",
	      "value": (_vm.username)
	    },
	    on: {
	      "change": _vm.onchange,
	      "input": [function($event) {
	        _vm.username = $event.target.attr.value
	      }, _vm.oninput]
	    }
	  })]), _c('div', {
	    staticClass: ["user"]
	  }, [_c('image', {
	    staticStyle: {
	      height: "40px",
	      width: "36px"
	    },
	    attrs: {
	      "src": "images/password.png"
	    }
	  }), _c('text', {
	    staticClass: ["username"]
	  }, [_vm._v(" 密  码 ")]), _c('input', {
	    staticClass: ["inputcon"],
	    attrs: {
	      "type": "password",
	      "value": _vm.password,
	      "name": "password",
	      "placeholder": "请输入密码",
	      "maxlength": "16",
	      "value": (_vm.password)
	    },
	    on: {
	      "change": _vm.onchange,
	      "input": [function($event) {
	        _vm.password = $event.target.attr.value
	      }, _vm.oninput]
	    }
	  })])]), _c('div', {
	    staticClass: ["btn"],
	    on: {
	      "click": function($event) {
	        _vm.login()
	      }
	    }
	  }, [_c('text', {
	    staticStyle: {
	      fontSize: "40px",
	      color: "#fff",
	      lineHeight: "70px"
	    }
	  }, [_vm._v("登  陆")])]), _c('div', {
	    staticClass: ["group"]
	  }, [_c('text', {
	    staticStyle: {
	      color: "#17acf6",
	      fontSize: "24px"
	    },
	    on: {
	      "click": _vm.forgetpass
	    }
	  }, [_vm._v("忘记密码? ")]), _c('text', {
	    staticStyle: {
	      color: "#AFAFAF",
	      textAlign: "center",
	      fontSize: "24px"
	    },
	    on: {
	      "click": _vm.telLogin
	    }
	  }, [_vm._v("手机动态密码登陆 ")]), _c('text', {
	    staticStyle: {
	      color: "#17acf6",
	      fontSize: "24px"
	    },
	    on: {
	      "click": _vm.register
	    }
	  }, [_vm._v("注册>")])])]), _c('div', {
	    staticClass: ["parent"]
	  }, [_c('div', {
	    staticClass: ["wechat"],
	    on: {
	      "click": _vm.wexinLogin
	    }
	  }, [_c('image', {
	    staticStyle: {
	      height: "75px",
	      width: "75px"
	    },
	    attrs: {
	      "src": "../images/wechat.png"
	    }
	  })]), _c('text', {
	    staticStyle: {
	      color: "#fff",
	      fontWeight: "700",
	      marginTop: "20px",
	      fontSize: "24px"
	    }
	  }, [_vm._v("微信登陆")])])])
	},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["imageContent"]
	  }, [_c('image', {
	    staticClass: ["img"],
	    staticStyle: {
	      width: "325px",
	      height: "77px",
	      marginLeft: "40px"
	    },
	    attrs: {
	      "src": "images/u19.png"
	    }
	  })])
	}]}
	module.exports.render._withStripped = true

/***/ })
/******/ ]);