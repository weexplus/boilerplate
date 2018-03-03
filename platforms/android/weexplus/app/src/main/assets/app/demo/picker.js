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
/******/ 	return __webpack_require__(__webpack_require__.s = 439);
/******/ })
/************************************************************************/
/******/ ({

/***/ 439:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(440)
)
__vue_styles__.push(__webpack_require__(441)
)

/* script */
__vue_exports__ = __webpack_require__(442)

/* template */
var __vue_template__ = __webpack_require__(443)
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
__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/weexplus/src/demo/picker.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-5b1f85da"
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

/***/ 440:
/***/ (function(module, exports) {

module.exports = {
  "cl": {
    "alignItems": "center"
  }
}

/***/ }),

/***/ 441:
/***/ (function(module, exports) {

module.exports = {
  "header": {
    "backgroundColor": "#FF0000",
    "flex": 1,
    "flexDirection": "row"
  },
  "tz": {
    "color": "#FF0000"
  },
  "logo": {
    "width": 300,
    "height": 300,
    "marginTop": 80
  },
  "k1": {
    "alignItems": "center"
  },
  "titleback": {
    "flex": 1,
    "alignItems": "center"
  },
  "title": {
    "color": "#FFFFFF",
    "flex": 1,
    "marginTop": 73,
    "fontWeight": "bold"
  },
  "leftimage": {
    "width": 30,
    "height": 45,
    "bottom": 25,
    "left": 30,
    "position": "absolute"
  },
  "rightimage": {
    "width": 45,
    "height": 45,
    "bottom": 23,
    "right": 32,
    "position": "absolute"
  },
  "bottomline": {
    "height": 1,
    "backgroundColor": "#000000",
    "position": "absolute",
    "bottom": 0,
    "left": 0,
    "right": 0,
    "flex": 1
  },
  "btn": {
    "backgroundColor": "#0085ee",
    "height": 100,
    "width": 200,
    "marginTop": 50,
    "borderRadius": 10,
    "alignItems": "center",
    "justifyContent": "center",
    "backgroundColor:active": "#006ce7"
  }
}

/***/ }),

/***/ 442:
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


var head = __webpack_require__(9);

exports.default = {
    components: { head: head },
    data: function data() {
        return {
            src: ""
        };
    },

    methods: {
        jk1: function jk1() {
            var nav = weex.requireModule("navigator");
            nav.push('net.js');
        },
        jk: function jk() {

            var picker = weex.requireModule("fpicker");
            picker.setCount(3);
            picker.setItems1(['猫猫猫猫', '狗', '鹦鹉']);
            picker.setItems2(['猫猫猫猫', '狗', '鹦鹉']);
            picker.setItems3(['猫猫猫猫猫', '狗', '鹦鹉']);
            //                picker.setItems2(this.toArray(l[0].children))
            picker.setTheme('#5261f7', '#ffffff');
            picker.show();
        }
    },
    created: function created() {

        var globalEvent = weex.requireModule('globalEvent');

        globalEvent.addEventListener("onPageInit", function (e) {
            var nav = weex.requireModule('navbar');
            nav.setTitle('照相');
            nav.setBack(true);
            nav.setRightImage('img/scan.png', function (res) {

                var modal = weex.requireModule('modal');
                modal.alert({ message: "ok" });
            });
        });
    }
};

/***/ }),

/***/ 443:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('head', {
    appendAsTree: true,
    attrs: {
      "title": "picker",
      "append": "tree"
    }
  }), _c('div', {
    staticStyle: {
      alignItems: "center"
    }
  }, [_c('div', {
    staticClass: ["btn"],
    on: {
      "click": function($event) {
        _vm.jk()
      }
    }
  }, [_c('text', {
    staticStyle: {
      color: "#ffffff",
      fontSize: "30"
    }
  }, [_vm._v("picker单列")])])]), _c('div', {
    staticStyle: {
      alignItems: "center"
    }
  }, [_c('div', {
    staticClass: ["btn"],
    on: {
      "click": function($event) {
        _vm.jk1()
      }
    }
  }, [_c('text', {
    staticStyle: {
      color: "#ffffff",
      fontSize: "30"
    }
  }, [_vm._v("跳转")])])])], 1)
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ }),

/***/ 5:
/***/ (function(module, exports) {

module.exports = {
  "header": {
    "backgroundColor": "#FF0000",
    "flex": 1,
    "flexDirection": "row"
  },
  "tz": {
    "color": "#FF0000"
  },
  "logo": {
    "width": 300,
    "height": 300,
    "marginTop": 80
  },
  "k1": {
    "alignItems": "center"
  },
  "titleback": {
    "flex": 1,
    "alignItems": "center"
  },
  "title": {
    "color": "#FFFFFF",
    "flex": 1,
    "marginTop": 73,
    "fontWeight": "bold"
  },
  "leftimage": {
    "width": 30,
    "height": 45,
    "bottom": 25,
    "left": 30,
    "position": "absolute"
  },
  "rightimage": {
    "width": 45,
    "height": 45,
    "bottom": 23,
    "right": 32,
    "position": "absolute"
  },
  "bottomline": {
    "height": 1,
    "backgroundColor": "#000000",
    "position": "absolute",
    "bottom": 0,
    "left": 0,
    "right": 0,
    "flex": 1
  },
  "btn": {
    "backgroundColor": "#0085ee",
    "height": 100,
    "width": 500,
    "marginTop": 50,
    "borderRadius": 20,
    "alignItems": "center",
    "justifyContent": "center",
    "backgroundColor:active": "#006ce7"
  }
}

/***/ }),

/***/ 6:
/***/ (function(module, exports) {

module.exports = {
  "layout": {
    "backgroundColor": "#333333",
    "height": 128,
    "flexDirection": "row",
    "alignItems": "center",
    "justifyContent": "center"
  }
}

/***/ }),

/***/ 7:
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


exports.default = {
    props: {
        title: {
            default: ''

        },
        back: {
            default: true
        },
        bgcolor: {
            default: '#222222'

        },
        isloading: {
            default: false
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
        },
        height: {
            default: 128
        },
        top: {
            default: 40
        },
        titletop: {
            default: 10
        }

    },
    data: function data() {
        return {};
    },

    methods: {
        titleClick: function titleClick() {
            this.$emit('titleClick');
        },
        rightclick: function rightclick() {
            this.$emit('rightClick');
        },
        backTo: function backTo() {
            var nav = weex.requireModule("navigator");
            nav.back();
            this.$emit('backClick');
        },
        onclick: function onclick() {
            if (!this.disabled) this.$emit('onclick');
        },
        adjust: function adjust() {
            if (weex.config.env.platform == 'android') {
                //                    if(weex.config.env.osVersion=)
                var p = weex.config.env.osVersion;
                p = p.replace(/\./g, '');
                if (p.length < 3) p = p + "0";
                if (p <= '440') {
                    this.height = 108;
                    this.top = 16;
                    this.titletop = 4;
                }
            }
        }
    },

    created: function created() {

        this.adjust();
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

/***/ }),

/***/ 8:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: ["layout"],
    style: {
      'background-color': _vm.bgcolor,
      'height': _vm.height
    }
  }, [_c('div', {
    staticStyle: {
      flexDirection: "row"
    },
    style: {
      'top': _vm.titletop
    }
  }, [(_vm.isloading) ? _c('div', {
    staticStyle: {
      height: "40",
      width: "40",
      marginRight: "10"
    }
  }) : _vm._e(), _c('text', {
    staticStyle: {
      flex: "1",
      color: "#ffffff",
      textAlign: "center",
      fontSize: "38"
    },
    on: {
      "click": _vm.titleClick
    }
  }, [_vm._v(_vm._s(_vm.title))]), (_vm.isloading) ? _c('floading', {
    staticStyle: {
      height: "40",
      width: "40",
      marginLeft: "10",
      marginTop: "5"
    },
    attrs: {
      "color": "#ffffff",
      "loadingStyle": "small"
    }
  }) : _vm._e()], 1), (_vm.back) ? _c('div', {
    staticStyle: {
      width: "200",
      top: "40",
      position: "absolute",
      left: "0"
    },
    style: {
      'height': _vm.height,
      'top': _vm.top
    },
    on: {
      "click": _vm.backTo
    }
  }, [_c('image', {
    staticStyle: {
      width: "80",
      height: "80"
    },
    attrs: {
      "src": "root:img/back.png"
    }
  })]) : _vm._e(), _c('div', {
    staticStyle: {
      width: "200",
      position: "absolute",
      right: "0",
      top: "0",
      alignItems: "center",
      justifyContent: "center"
    },
    style: {
      'height': _vm.height
    },
    on: {
      "click": _vm.rightclick
    }
  }, [_vm._t("right")], 2), _c('div', {
    staticStyle: {
      height: "1",
      backgroundColor: "#111111",
      position: "absolute",
      bottom: "0",
      left: "0",
      right: "0"
    }
  })])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ }),

/***/ 9:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(5)
)
__vue_styles__.push(__webpack_require__(6)
)

/* script */
__vue_exports__ = __webpack_require__(7)

/* template */
var __vue_template__ = __webpack_require__(8)
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
__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/weexplus/src/demo/header.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-25b44859"
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


/***/ })

/******/ });