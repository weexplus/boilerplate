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
/******/ 	return __webpack_require__(__webpack_require__.s = 211);
/******/ })
/************************************************************************/
/******/ ({

/***/ 211:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(212)
)

/* script */
__vue_exports__ = __webpack_require__(213)

/* template */
var __vue_template__ = __webpack_require__(214)
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
__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/weexplus/src/demo/bindx.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-c41c2e82"
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

/***/ 212:
/***/ (function(module, exports) {

module.exports = {
  "head_bar": {
    "position": "absolute",
    "width": "750",
    "height": "120",
    "backgroundColor": "#6A1B9A",
    "opacity": 0,
    "justifyContent": "center",
    "alignItems": "center"
  },
  "title": {
    "color": "#ffffff",
    "fontSize": "32"
  },
  "circle": {
    "width": "250",
    "height": "250",
    "borderRadius": "125",
    "backgroundColor": "#ff0000",
    "marginLeft": "250",
    "marginTop": "205"
  },
  "header": {
    "width": "750",
    "height": "700",
    "position": "absolute"
  },
  "headerImg": {
    "height": "700",
    "width": "750",
    "position": "absolute"
  },
  "list": {
    "marginTop": "120",
    "width": "750"
  },
  "blankHeader": {
    "height": "580"
  },
  "item": {
    "backgroundColor": "#FFFFFF",
    "alignItems": "center"
  },
  "itemName": {
    "fontSize": 28,
    "color": "#333333",
    "lineHeight": 42,
    "textAlign": "left",
    "marginTop": 24
  },
  "itemPhoto": {
    "marginTop": 18,
    "width": 220,
    "height": 220,
    "marginBottom": 18
  },
  "itemDesc": {
    "fontSize": 24,
    "marginTop": 12,
    "marginRight": 12,
    "marginBottom": 12,
    "marginLeft": 12,
    "color": "#999999",
    "lineHeight": 36,
    "textAlign": "left"
  },
  "itemClickBehaviour": {
    "fontSize": 36,
    "color": "#00cc99",
    "lineHeight": 36,
    "textAlign": "center",
    "marginTop": 6,
    "marginLeft": 24,
    "marginRight": 24,
    "marginBottom": 30
  }
}

/***/ }),

/***/ 213:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

module.exports = {
    methods: {},
    created: function created() {},
    mounted: function mounted() {

        console.log("mounted!!!!!!");

        var binding = weex.requireModule('bindingx');
        var timer = weex.requireModule('timer');
        var self = this;
        timer.setTimeout(function () {
            var list = self.$refs.list.ref;
            var circle = self.$refs.circle.ref;
            var image = self.$refs.image.ref;
            var head_bar = self.$refs.head_bar.ref;

            var image_origin = "0-min(300,y/580*300)";
            var image_transformed = "{\"type\":\"-\",\"children\":[{\"type\":\"NumericLiteral\",\"value\":0},{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"min\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"NumericLiteral\",\"value\":300},{\"type\":\"*\",\"children\":[{\"type\":\"/\",\"children\":[{\"type\":\"Identifier\",\"value\":\"y\"},{\"type\":\"NumericLiteral\",\"value\":580}]},{\"type\":\"NumericLiteral\",\"value\":300}]}]}]}]}";

            var circle_scale_orign = "max(1-min(y,580)/580,0.4)"; //1--->0.4
            var circle_scale_transformed = "{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"max\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"-\",\"children\":[{\"type\":\"NumericLiteral\",\"value\":1},{\"type\":\"/\",\"children\":[{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"min\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"Identifier\",\"value\":\"y\"},{\"type\":\"NumericLiteral\",\"value\":580}]}]},{\"type\":\"NumericLiteral\",\"value\":580}]}]},{\"type\":\"NumericLiteral\",\"value\":0.4}]}]}";

            var circle_translate_x_origin = "(0-min(y,580)/580)*295"; //0---> -250    0 660  (0-y/660)*250
            var circle_translate_x_transformed = "{\"type\":\"*\",\"children\":[{\"type\":\"-\",\"children\":[{\"type\":\"NumericLiteral\",\"value\":0},{\"type\":\"/\",\"children\":[{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"min\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"Identifier\",\"value\":\"y\"},{\"type\":\"NumericLiteral\",\"value\":580}]}]},{\"type\":\"NumericLiteral\",\"value\":580}]}]},{\"type\":\"NumericLiteral\",\"value\":295}]}";

            var circle_translate_y_origin = "(0-min(y,580)/580)*268"; //0---> -205  0 660   (0-y/660)*205
            var circle_translate_y_transformed = "{\"type\":\"*\",\"children\":[{\"type\":\"-\",\"children\":[{\"type\":\"NumericLiteral\",\"value\":0},{\"type\":\"/\",\"children\":[{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"min\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"Identifier\",\"value\":\"y\"},{\"type\":\"NumericLiteral\",\"value\":580}]}]},{\"type\":\"NumericLiteral\",\"value\":580}]}]},{\"type\":\"NumericLiteral\",\"value\":268}]}";

            var head_opacity_origin = "min(y,580)/580"; //0.0-1.0  0 580
            var head_opacity_transformed = "{\"type\":\"/\",\"children\":[{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"min\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"Identifier\",\"value\":\"y\"},{\"type\":\"NumericLiteral\",\"value\":580}]}]},{\"type\":\"NumericLiteral\",\"value\":580}]}";

            var head_background_origin = "evaluateColor('#6A1B9A','#FF9800',(y<=580?0:min(500,y-580)/500))"; //#6A1B9A-->>  #CDDC39 580--> 1080
            var head_background_transformed = "{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"evaluateColor\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"StringLiteral\",\"value\":\"'#6A1B9A'\"},{\"type\":\"StringLiteral\",\"value\":\"'#FF9800'\"},{\"type\":\"?\",\"children\":[{\"type\":\"<=\",\"children\":[{\"type\":\"Identifier\",\"value\":\"y\"},{\"type\":\"NumericLiteral\",\"value\":580}]},{\"type\":\"NumericLiteral\",\"value\":0},{\"type\":\"/\",\"children\":[{\"type\":\"CallExpression\",\"children\":[{\"type\":\"Identifier\",\"value\":\"min\"},{\"type\":\"Arguments\",\"children\":[{\"type\":\"NumericLiteral\",\"value\":500},{\"type\":\"-\",\"children\":[{\"type\":\"Identifier\",\"value\":\"y\"},{\"type\":\"NumericLiteral\",\"value\":580}]}]}]},{\"type\":\"NumericLiteral\",\"value\":500}]}]}]}]}";

            var result = binding.bind({
                eventType: 'scroll',
                anchor: list,
                props: [{
                    element: image,
                    property: 'transform.translateY',
                    expression: {
                        origin: image_origin,
                        transformed: image_transformed
                    }
                }, {
                    element: circle,
                    property: 'transform.scaleX',
                    expression: {
                        origin: circle_scale_orign,
                        transformed: circle_scale_transformed
                    }
                }, {
                    element: circle,
                    property: 'transform.scaleY',
                    expression: {
                        origin: circle_scale_orign,
                        transformed: circle_scale_transformed
                    }
                },
                //circle: translate_x
                {
                    element: circle,
                    property: 'transform.translateX',
                    expression: {
                        origin: circle_translate_x_origin,
                        transformed: circle_translate_x_transformed
                    }
                },
                //circle: translate_y
                {
                    element: circle,
                    property: 'transform.translateY',
                    expression: {
                        origin: circle_translate_y_origin,
                        transformed: circle_translate_y_transformed
                    }
                },
                //head: opacity
                {
                    element: head_bar,
                    property: 'opacity',
                    expression: {
                        origin: head_opacity_origin,
                        transformed: head_opacity_transformed
                    }
                },
                //head: background
                {
                    element: head_bar,
                    property: 'background-color',
                    expression: {
                        origin: head_background_origin,
                        transformed: head_background_transformed
                    }
                }]
            }, function (e) {});
        }, 200);
    },
    data: function data() {
        var items = [{
            src: 'https://gw.alicdn.com/tps/TB1Jl1CPFXXXXcJXXXXXXXXXXXX-370-370.jpg',
            name: 'Thomas Carlyle',
            desc: 'Genius only means hard-working all one\'s life',
            behaviourName: '#### NO.1'
        }, {
            src: 'https://gw.alicdn.com/tps/TB1Hv1JPFXXXXa3XXXXXXXXXXXX-370-370.jpg',
            desc: 'The man who has made up his mind to win will never say "impossible "',
            behaviourName: '#### NO.2'
        }, {
            src: 'https://gw.alicdn.com/tps/TB1eNKuPFXXXXc_XpXXXXXXXXXX-370-370.jpg',
            desc: 'There is no such thing as a great talent without great will - power',
            behaviourName: '#### NO.3'
        }, {
            src: 'https://gw.alicdn.com/tps/TB1DCh8PFXXXXX7aXXXXXXXXXXX-370-370.jpg',
            name: 'Addison',
            desc: 'Cease to struggle and you cease to live',
            behaviourName: '#### NO.4'
        }, {
            src: 'https://gw.alicdn.com/tps/TB1ACygPFXXXXXwXVXXXXXXXXXX-370-370.jpg',
            desc: 'A strong man will struggle with the storms of fate',
            behaviourName: '#### NO.5'
        }, {
            src: 'https://gw.alicdn.com/tps/TB1IGShPFXXXXaqXVXXXXXXXXXX-370-370.jpg',
            name: 'Ruskin',
            desc: 'Living without an aim is like sailing without a compass',
            behaviourName: '#### NO.6'
        }, {
            src: 'https://gw.alicdn.com/tps/TB1xU93PFXXXXXHaXXXXXXXXXXX-240-240.jpg',
            behaviourName: '#### NO.7'
        }, {
            src: 'https://gw.alicdn.com/tps/TB19hu0PFXXXXaXaXXXXXXXXXXX-240-240.jpg',
            name: 'Balzac',
            desc: 'There is no such thing as a great talent without great will - power',
            behaviourName: '#### NO.8'
        }, {
            src: 'https://gw.alicdn.com/tps/TB1ux2vPFXXXXbkXXXXXXXXXXXX-240-240.jpg',
            behaviourName: '#### NO.9'
        }, {
            src: 'https://gw.alicdn.com/tps/TB1tCCWPFXXXXa7aXXXXXXXXXXX-240-240.jpg',
            behaviourName: '#### NO.10'
        }];

        var repeatItems = [];
        for (var i = 0; i < 3; i++) {
            repeatItems.push.apply(repeatItems, items);
        }

        return {

            items: repeatItems
        };
    }
};

/***/ }),

/***/ 214:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('div', {
    staticClass: ["header"]
  }, [_c('image', {
    ref: "image",
    staticClass: ["headerImg"],
    attrs: {
      "src": "https://img.alicdn.com/tfs/TB1SOyrSVXXXXb3XXXXXXXXXXXX-1000-667.jpg"
    }
  }), _c('div', {
    ref: "head_bar",
    staticClass: ["head_bar"]
  }, [_c('text', {
    staticClass: ["title"]
  }, [_vm._v("aweome binding")])]), _c('image', {
    ref: "circle",
    staticClass: ["circle"],
    attrs: {
      "src": "https://img.alicdn.com/tfs/TB1KXuHQVXXXXazXFXXXXXXXXXX-750-499.jpg"
    }
  })]), _c('list', {
    ref: "list",
    staticClass: ["list"]
  }, [_c('cell', {
    staticClass: ["blankHeader"],
    appendAsTree: true,
    attrs: {
      "append": "tree"
    }
  }), _vm._l((_vm.items), function(item, index) {
    return _c('cell', {
      key: item.src,
      ref: "index",
      refInFor: true,
      staticClass: ["cell"],
      appendAsTree: true,
      attrs: {
        "append": "tree"
      }
    }, [_c('div', {
      staticClass: ["item"]
    }, [(item.name) ? _c('text', {
      staticClass: ["itemName"]
    }, [_vm._v(_vm._s(item.name))]) : _vm._e(), _c('image', {
      staticClass: ["itemPhoto"],
      attrs: {
        "src": item.src
      }
    }), (item.desc) ? _c('text', {
      staticClass: ["itemDesc"]
    }, [_vm._v(_vm._s(item.desc))]) : _vm._e(), (item.behaviourName) ? _c('text', {
      staticClass: ["itemClickBehaviour"]
    }, [_vm._v(" " + _vm._s(item.behaviourName))]) : _vm._e()])])
  })], 2)])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });