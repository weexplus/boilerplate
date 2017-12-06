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
	__vue_styles__.push(__webpack_require__(389)
	)
	__vue_styles__.push(__webpack_require__(390)
	)

	/* script */
	__vue_exports__ = __webpack_require__(391)

	/* template */
	var __vue_template__ = __webpack_require__(392)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/wechat.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-c05ba586"
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

/***/ 389:
/***/ (function(module, exports) {

	module.exports = {
	  "layout": {
	    "backgroundColor": "#efeff4"
	  },
	  "line": {
	    "backgroundColor": "#dddddd",
	    "height": 1.5,
	    "width": 750
	  },
	  "block": {
	    "backgroundColor": "#ffffff",
	    "marginTop": 40
	  },
	  "leftline": {
	    "backgroundColor": "#dddddd",
	    "height": 1,
	    "marginLeft": 35
	  },
	  "t": {
	    "color": "#000000",
	    "marginLeft": 30,
	    "fontSize": 35,
	    "flex": 1
	  },
	  "cell": {
	    "width": 750,
	    "height": 90,
	    "flexDirection": "row",
	    "alignItems": "center",
	    "backgroundColor": "#ffffff",
	    "backgroundColor:active": "#dddddd"
	  },
	  "left_img": {
	    "width": 47,
	    "height": 47,
	    "marginLeft": 30
	  },
	  "right_img": {
	    "width": 20,
	    "height": 30,
	    "marginRight": 30
	  }
	}

/***/ }),

/***/ 390:
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

/***/ 391:
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
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//


	var globalEvent = weex.requireModule('globalEvent');
	globalEvent.addEventListener("onPageInit", function (e) {
	    var nav = weex.requireModule('navbar');
	    nav.setTitle('发现');
	    nav.setBack(true);
	    nav.setBackgroundColor('#353439');
	    nav.setRightImage('img/scan.png', function (res) {

	        var modal = weex.requireModule('modal');
	        modal.alert({ message: "ok" });
	    });
	});

	exports.default = {
	    data: function data() {
	        return {};
	    },

	    methods: {
	        jk: function jk() {

	            var modal = weex.requireModule('modal');
	            modal.toast({ message: 'native toast' });
	        }
	    },
	    created: function created() {}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 392:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _vm._m(0)
	},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('scroller', {
	    staticClass: ["layout"],
	    attrs: {
	      "showScrollbar": "false"
	    }
	  }, [_c('div', {
	    staticClass: ["line"],
	    staticStyle: {
	      marginTop: "30"
	    }
	  }), _c('div', {
	    staticClass: ["cell"]
	  }, [_c('image', {
	    staticClass: ["left_img"],
	    attrs: {
	      "src": "img/friend.png"
	    }
	  }), _c('text', {
	    staticClass: ["t"]
	  }, [_vm._v("朋友圈")]), _c('image', {
	    staticClass: ["right_img"],
	    attrs: {
	      "src": "img/arrow.png"
	    }
	  })]), _c('div', {
	    staticClass: ["line"]
	  }), _c('div', {
	    staticClass: ["block"]
	  }, [_c('div', {
	    staticClass: ["line"]
	  }), _c('div', {
	    staticClass: ["cell"]
	  }, [_c('image', {
	    staticClass: ["left_img"],
	    staticStyle: {
	      width: "42",
	      height: "40"
	    },
	    attrs: {
	      "src": "img/we_scan.png"
	    }
	  }), _c('text', {
	    staticClass: ["t"]
	  }, [_vm._v("扫一扫")]), _c('image', {
	    staticClass: ["right_img"],
	    attrs: {
	      "src": "img/arrow.png"
	    }
	  })]), _c('div', {
	    staticClass: ["leftline"]
	  }), _c('div', {
	    staticClass: ["cell"]
	  }, [_c('image', {
	    staticClass: ["left_img"],
	    staticStyle: {
	      width: "44",
	      height: "44"
	    },
	    attrs: {
	      "src": "img/shake.png"
	    }
	  }), _c('text', {
	    staticClass: ["t"]
	  }, [_vm._v("摇一摇")]), _c('image', {
	    staticClass: ["right_img"],
	    attrs: {
	      "src": "img/arrow.png"
	    }
	  })]), _c('div', {
	    staticClass: ["leftline"]
	  }), _c('div', {
	    staticClass: ["cell"]
	  }, [_c('image', {
	    staticClass: ["left_img"],
	    staticStyle: {
	      width: "40",
	      height: "40",
	      marginLeft: "40"
	    },
	    attrs: {
	      "src": "img/search.png"
	    }
	  }), _c('text', {
	    staticClass: ["t"]
	  }, [_vm._v("搜一搜")]), _c('image', {
	    staticClass: ["right_img"],
	    attrs: {
	      "src": "img/arrow.png"
	    }
	  })]), _c('div', {
	    staticClass: ["leftline"]
	  }), _c('div', {
	    staticClass: ["cell"]
	  }, [_c('image', {
	    staticClass: ["left_img"],
	    staticStyle: {
	      width: "44",
	      height: "30"
	    },
	    attrs: {
	      "src": "img/look.png"
	    }
	  }), _c('text', {
	    staticClass: ["t"]
	  }, [_vm._v("看一看")]), _c('image', {
	    staticClass: ["right_img"],
	    attrs: {
	      "src": "img/arrow.png"
	    }
	  })]), _c('div', {
	    staticClass: ["line"]
	  })]), _c('div', {
	    staticClass: ["block"]
	  }, [_c('div', {
	    staticClass: ["line"]
	  }), _c('div', {
	    staticClass: ["cell"]
	  }, [_c('image', {
	    staticClass: ["left_img"],
	    staticStyle: {
	      width: "42",
	      height: "40"
	    },
	    attrs: {
	      "src": "img/nearby.png"
	    }
	  }), _c('text', {
	    staticClass: ["t"]
	  }, [_vm._v("附近的人")]), _c('image', {
	    staticClass: ["right_img"],
	    attrs: {
	      "src": "img/arrow.png"
	    }
	  })]), _c('div', {
	    staticClass: ["leftline"]
	  }), _c('div', {
	    staticClass: ["cell"]
	  }, [_c('image', {
	    staticClass: ["left_img"],
	    staticStyle: {
	      width: "44",
	      height: "44"
	    },
	    attrs: {
	      "src": "img/bot.png"
	    }
	  }), _c('text', {
	    staticClass: ["t"]
	  }, [_vm._v("漂流瓶")]), _c('image', {
	    staticClass: ["right_img"],
	    attrs: {
	      "src": "img/arrow.png"
	    }
	  })]), _c('div', {
	    staticClass: ["line"]
	  })]), _c('div', {
	    staticClass: ["block"]
	  }, [_c('div', {
	    staticClass: ["line"]
	  }), _c('div', {
	    staticClass: ["cell"]
	  }, [_c('image', {
	    staticClass: ["left_img"],
	    staticStyle: {
	      width: "36",
	      height: "40",
	      marginLeft: "38"
	    },
	    attrs: {
	      "src": "img/buy.png"
	    }
	  }), _c('text', {
	    staticClass: ["t"]
	  }, [_vm._v("购物")]), _c('image', {
	    staticClass: ["right_img"],
	    attrs: {
	      "src": "img/arrow.png"
	    }
	  })]), _c('div', {
	    staticClass: ["leftline"]
	  }), _c('div', {
	    staticClass: ["cell"]
	  }, [_c('image', {
	    staticClass: ["left_img"],
	    staticStyle: {
	      width: "44",
	      height: "44",
	      marginLeft: "31"
	    },
	    attrs: {
	      "src": "img/game.png"
	    }
	  }), _c('text', {
	    staticClass: ["t"]
	  }, [_vm._v("游戏")]), _c('image', {
	    staticClass: ["right_img"],
	    attrs: {
	      "src": "img/arrow.png"
	    }
	  })]), _c('div', {
	    staticClass: ["line"]
	  })]), _c('div', {
	    staticClass: ["line"],
	    staticStyle: {
	      marginTop: "50"
	    }
	  }), _c('div', {
	    staticClass: ["cell"]
	  }, [_c('image', {
	    staticClass: ["left_img"],
	    attrs: {
	      "src": "img/small_app.png"
	    }
	  }), _c('text', {
	    staticClass: ["t"]
	  }, [_vm._v("朋友圈")]), _c('image', {
	    staticClass: ["right_img"],
	    attrs: {
	      "src": "img/arrow.png"
	    }
	  })]), _c('div', {
	    staticClass: ["line"],
	    staticStyle: {
	      marginBottom: "100"
	    }
	  })])
	}]}
	module.exports.render._withStripped = true

/***/ })

/******/ });