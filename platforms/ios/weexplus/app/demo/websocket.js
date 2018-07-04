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
/******/ 	return __webpack_require__(__webpack_require__.s = 449);
/******/ })
/************************************************************************/
/******/ ({

/***/ 449:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(450)
)

/* script */
__vue_exports__ = __webpack_require__(451)

/* template */
var __vue_template__ = __webpack_require__(452)
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
__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/weexplus/src/demo/websocket.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-8083f6aa"
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

/***/ 450:
/***/ (function(module, exports) {

module.exports = {
  "input": {
    "fontSize": "40",
    "height": "80",
    "width": "600"
  },
  "button": {
    "fontSize": "36",
    "width": "150",
    "color": "#41B883",
    "textAlign": "center",
    "paddingTop": "25",
    "paddingBottom": "25",
    "borderWidth": "2",
    "borderStyle": "solid",
    "marginRight": "20",
    "borderColor": "rgb(162,217,192)",
    "backgroundColor": "rgba(162,217,192,0.2)"
  }
}

/***/ }),

/***/ 451:
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
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

var websocket = weex.requireModule('webSocket');
exports.default = {
    data: function data() {
        return {
            connectinfo: '',
            sendinfo: '',
            onopeninfo: '',
            onmessage: '',
            oncloseinfo: '',
            onerrorinfo: '',
            closeinfo: '',
            txtInput: '',
            navBarHeight: 88,
            title: 'Navigator',
            dir: 'examples',
            baseURL: ''
        };
    },

    methods: {
        connect: function connect() {
            //                websocket.WebSocket('ws://10.23.6.64:9897','echo-protocol');
            websocket.onopen(function (e) {
                self.onopeninfo = 'websocket open';
            });
            websocket.WebSocket('ws://10.23.6.64:8088/page/entry', 'echo-protocol');
            var self = this;
            self.onopeninfo = 'connecting...';

            websocket.onmessage = function (e) {
                self.onmessage = e.data;
            };
            websocket.onerror = function (e) {
                self.onerrorinfo = e.data;
            };
            websocket.onclose = function (e) {
                self.onopeninfo = '';
                self.onerrorinfo = e.code;
            };
        },
        send: function send(e) {
            var input = this.$refs.input;
            input.blur();
            websocket.send(this.txtInput);
            this.sendinfo = this.txtInput;
        },
        oninput: function oninput(event) {
            this.txtInput = event.value;
        },
        close: function close(e) {
            websocket.close();
        }
    }
};

/***/ }),

/***/ 452:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('scroller', [_c('div', [_vm._m(0), _c('input', {
    ref: "input",
    staticClass: ["input"],
    attrs: {
      "type": "text",
      "placeholder": "please input message to send",
      "autofocus": "false",
      "value": ""
    },
    on: {
      "change": _vm.onchange,
      "input": _vm.oninput
    }
  }), _c('div', {
    staticStyle: {
      flexDirection: "row",
      justifyContent: "center"
    }
  }, [_c('text', {
    staticClass: ["button"],
    on: {
      "click": _vm.connect
    }
  }, [_vm._v("connect")]), _c('text', {
    staticClass: ["button"],
    on: {
      "click": _vm.send
    }
  }, [_vm._v("send")]), _c('text', {
    staticClass: ["button"],
    on: {
      "click": _vm.close
    }
  }, [_vm._v("close")])]), _vm._m(1), _c('text', {
    staticStyle: {
      color: "black",
      height: "80px"
    }
  }, [_vm._v(_vm._s(_vm.sendinfo))]), _vm._m(2), _c('text', {
    staticStyle: {
      color: "black",
      height: "80px"
    }
  }, [_vm._v(_vm._s(_vm.onopeninfo))]), _vm._m(3), _c('text', {
    staticStyle: {
      color: "black",
      height: "400px"
    }
  }, [_vm._v(_vm._s(_vm.onmessage))]), _vm._m(4), _c('text', {
    staticStyle: {
      color: "black",
      height: "80px"
    }
  }, [_vm._v(_vm._s(_vm.oncloseinfo))]), _vm._m(5), _c('text', {
    staticStyle: {
      color: "black",
      height: "80px"
    }
  }, [_vm._v(_vm._s(_vm.onerrorinfo))]), _vm._m(6), _c('text', {
    staticStyle: {
      color: "black",
      height: "80px"
    }
  }, [_vm._v(_vm._s(_vm.closeinfo))])])])
},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      backgroundColor: "#286090"
    }
  }, [_c('text', {
    staticClass: ["title"],
    staticStyle: {
      height: "80px",
      padding: "20px",
      color: "white"
    }
  }, [_vm._v("websocket")])])
},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      backgroundColor: "lightgray"
    }
  }, [_c('text', {
    staticClass: ["title"],
    staticStyle: {
      height: "80px",
      padding: "20px",
      color: "black"
    }
  }, [_vm._v("method = send")])])
},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      backgroundColor: "lightgray"
    }
  }, [_c('text', {
    staticClass: ["title"],
    staticStyle: {
      height: "80px",
      padding: "20px",
      color: "black"
    }
  }, [_vm._v("method = onopen")])])
},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      backgroundColor: "lightgray"
    }
  }, [_c('text', {
    staticClass: ["title"],
    staticStyle: {
      height: "80px",
      padding: "20px",
      color: "black"
    }
  }, [_vm._v("method = onmessage")])])
},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      backgroundColor: "lightgray"
    }
  }, [_c('text', {
    staticClass: ["title"],
    staticStyle: {
      height: "80px",
      padding: "20px",
      color: "black"
    }
  }, [_vm._v("method = onclose")])])
},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      backgroundColor: "lightgray"
    }
  }, [_c('text', {
    staticClass: ["title"],
    staticStyle: {
      height: "80px",
      padding: "20px",
      color: "black"
    }
  }, [_vm._v("method = onerror")])])
},function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticStyle: {
      backgroundColor: "lightgray"
    }
  }, [_c('text', {
    staticClass: ["title"],
    staticStyle: {
      height: "80px",
      padding: "20px",
      color: "black"
    }
  }, [_vm._v("method = close")])])
}]}
module.exports.render._withStripped = true

/***/ })

/******/ });