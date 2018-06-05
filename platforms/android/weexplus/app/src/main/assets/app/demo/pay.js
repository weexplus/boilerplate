// { "framework": "Vue"} 

/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 383);
/******/ })
/************************************************************************/
/******/ ({

/***/ 383:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(384)
)

/* script */
__vue_exports__ = __webpack_require__(385)

/* template */
var __vue_template__ = __webpack_require__(386)
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
__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/weexplus/src/demo/pay.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-c1b8ebe8"
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

/***/ 384:
/***/ (function(module, exports) {

module.exports = {
  "add": {
    "width": "250",
    "height": "250",
    "backgroundColor": "#0088fb",
    "alignItems": "center",
    "justifyContent": "center"
  },
  "pic": {
    "width": "250",
    "height": "250",
    "backgroundColor": "#FF0000",
    "borderWidth": 1,
    "borderColor": "#ffffff"
  }
}

/***/ }),

/***/ 385:
/***/ (function(module, exports, __webpack_require__) {

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

exports.default = {
    data: function data() {
        return {
            text: {}
        };
    },

    methods: {
        pay: function pay() {
            var _this = this;

            var progress = weex.requireModule('progress');
            var alipay = weex.requireModule('alipay');
            progress.show();
            alipay.open('alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018051060110243&biz_content=%7B%22body%22%3A%22%E6%94%AF%E4%BB%98%E5%8F%91%E5%B8%83%E8%AF%A2%E4%BB%B7%22%2C%22out_trade_no%22%3A%2220180531152017697388%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E8%AF%A2%E4%BB%B7%E8%AE%A2%E5%8D%95%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%225.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&sign=JjY1Bq0UyUl77n5KH7H2nqwolaaqQ10Mt4lWCpRsSgqzPILlYlWeyzOdvsvBlgnkQY1VAhsaewJqkBBNEqs1MShePBcpdJlr1et06IXDjlghSsAZvNxr5vX5NPjgH1fUrk5xF11VTea%2F8ZDwo1XRxJZMY5JSGvGHTH8Rn%2B5YdRYekPHuYRvW7sFHDPMOkrJNxmRqoXxB5173t7k50yjwuMGSoDvmr9qsjhwrPdjmf0PrJRbYqaDwMJnA6P4ZT3T3ifWzTcDcM%2F54eudFzbELpdAAqLLHknpCTVZNZYjl5k6HwRVqJdf7VLMOXN3HA0xVc5mbeibED51fakCoSa53lw%3D%3D&sign_type=RSA2&timestamp=2018-05-31+15%3A21%3A49&version=1.0', function (res) {
                _this.text = res;
                progress.dismiss();
            });
        }
    },
    created: function created() {

        var globalEvent = weex.requireModule('globalEvent');
        globalEvent.addEventListener("onPageInit", function (e) {

            var t = weex.requireModule('modal');
            //                t.toast({message:'111'})
        });
    }
};

/***/ }),

/***/ 386:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('text', [_vm._v(_vm._s(_vm.text))]), _c('image', {
    staticStyle: {
      width: "300px",
      height: "300px",
      marginTop: "100px",
      marginLeft: "20px"
    },
    attrs: {
      "src": "root:/img/cat.png"
    },
    on: {
      "click": _vm.pay
    }
  })])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });