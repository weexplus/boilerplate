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
	__vue_styles__.push(__webpack_require__(229)
	)

	/* script */
	__vue_exports__ = __webpack_require__(230)

	/* template */
	var __vue_template__ = __webpack_require__(235)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/demo/fpickerdemo.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-2d98bc50"
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

/***/ 16:
/***/ (function(module, exports) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var host = 'http://59.110.169.246/movie/';
	// var host='http://192.168.1.101:8080/'


	exports.default = {

	    postShort: function postShort(weg, param, header, start, success, compelete) {
	        var modal = weex.requireModule("modal");
	        this.postFull(weg, param, header, start, success, function (res) {
	            //fail
	            modal.toast({ message: res.msg });
	        }, function () {
	            //exception
	            modal.toast({ message: '网络异常！' });
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
	        var token = st.getString('token');
	        if (token != undefined && token != '') {
	            header.token = token;
	        }
	        // param.token='95d594d7b18fd1c7db37e81dd5bae9c9'
	        net.post(url, param, header, function () {
	            //start
	            start();
	        }, function (e) {
	            //success
	            // modal.toast({message:e.res.err})
	            if (e.res.err == 0) {

	                success(e.res);
	            } else {
	                // modal.toast({message:e.res.msg})
	                if (token != undefined && token != '') {
	                    st.remove('token');
	                    return;
	                }
	                if (e.res.err == 1000) {
	                    // var nav=weex.requireModule("navigator")
	                    // nav.presentFull('root:busi/account/login.js',{},'transparent',true,function(){
	                    //     self.postFull(weg,param,header,start,success,fail,exception,compelete);

	                    // },true);
	                } else fail(e.res);
	            }
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

	    postSilent: function postSilent(weg, param, success) {

	        this.postFull(weg, param, {}, function () {}, success, function (res) {
	            //fail

	        }, function () {
	            //exception

	        }, function () {
	            //compelete


	        });
	    }

	};
	module.exports = exports['default'];

/***/ }),

/***/ 49:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(50)
	)

	/* script */
	__vue_exports__ = __webpack_require__(51)

	/* template */
	var __vue_template__ = __webpack_require__(56)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/component/flist.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-1f94a15d"
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

/***/ 50:
/***/ (function(module, exports) {

	module.exports = {
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
	  },
	  "exception": {
	    "marginTop": 50,
	    "borderRadius": 10,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "width": 300,
	    "height": 80,
	    "borderWidth": 2,
	    "borderColor": "#949494",
	    "backgroundColor": "#ffffff",
	    "backgroundColor:active": "#dddddd"
	  },
	  "emptytxt": {
	    "marginTop": 100,
	    "color": "#999999"
	  },
	  "empty": {
	    "height": 500,
	    "alignItems": "center"
	  },
	  "loading": {
	    "height": 90,
	    "justifyContent": "center",
	    "alignItems": "center",
	    "flexDirection": "row",
	    "borderRadius": 5,
	    "borderWidth": 1,
	    "borderColor": "#e6e6e6",
	    "backgroundColor": "#fdfdfd",
	    "margin": 15,
	    "marginLeft": 30,
	    "marginRight": 30
	  },
	  "c": {
	    "flex": 1
	  },
	  "page": {
	    "backgroundColor": "#ffffff",
	    "width": 750,
	    "flex": 1
	  }
	}

/***/ }),

/***/ 51:
/***/ (function(module, exports, __webpack_require__) {

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

	var pull = __webpack_require__(52);
	var net = __webpack_require__(16);

	exports.default = {
	    components: { pull: pull },
	    props: {
	        columnCount: {
	            default: 1
	        },
	        key: {
	            default: 'key1'
	        },
	        columnGap: {
	            default: 20
	        },
	        columnWidth: {
	            default: 'auto'
	        },
	        scrollable: {
	            default: true
	        },
	        showScrollbar: {
	            default: true
	        },
	        showheader: {
	            default: false
	        },
	        usePullRefresh: {
	            default: true
	        },
	        items: {
	            default: []
	        },
	        isEmpty: {
	            default: false
	        },
	        background: {
	            default: '#ffffff'
	        },
	        isException: {
	            default: false
	        },
	        pageSize: {
	            default: 15
	        },
	        isloading: {
	            default: false
	        },
	        loadOnInit: {
	            default: true
	        },
	        hasmore: {
	            default: true
	        },
	        emptyTxt: {
	            default: '您的订单为空~'
	        },
	        img_empty_src: {
	            default: 'root:img/ico_empty.png'
	        },

	        img_exception_src: {
	            default: 'root:img/ico_exception.png'
	        },
	        showweg: {
	            default: false
	        },
	        ispull: {
	            default: false
	        }

	    },
	    data: function data() {

	        return {
	            pullkey: this.key + "pull",
	            waterkey: this.key + "water",
	            _columnCount: this.getCount()

	        };
	    },
	    mounted: function mounted() {

	        if (this.loadOnInit) {
	            var self = this;
	            self.$emit('load');
	        }
	    },


	    methods: {

	        loadmore: function loadmore(e) {

	            if (this.hasmore) this.$emit('load');
	        },
	        getCount: function getCount() {
	            if (this.isEmpty || this.isException) {
	                return 1;
	            } else {
	                return this.columnCount;
	            }
	        },

	        clear: function clear() {

	            this.hasmore = true;
	            this.items.length = 0;
	        },
	        showEmpty: function showEmpty() {
	            this._columnCount = this.getCount();
	            this.isEmpty = true;
	            this.isException = false;
	        },
	        showException: function showException() {
	            this._columnCount = this.getCount();
	            this.isEmpty = false;
	            this.isException = true;
	        },
	        load: function load(url, param, items, callback) {
	            this.loadFull(url, param, items, callback, function () {}, function () {}, function () {}, function () {});
	        },
	        loadFull: function loadFull(url, param, items, callback, start, fail, exp, comp) {
	            //                var progress=weex.requireModule("progress")
	            if (this.isloading) return;
	            this.isException = false;
	            this.isEmpty = false;
	            this.isloading = true;

	            var self = this;
	            //                postFull:  function (weg,param,header,start,success,compelete)

	            var modal = weex.requireModule('modal');
	            net.postFull(url, param, {}, function () {
	                //start
	                start();
	            }, function (res) {
	                //success
	                if (res.list.length < 15) {
	                    self.hasmore = false;
	                }
	                if (res.list.length > 0) {
	                    items = items.concat(res.list);
	                    self.items = items;
	                }
	                if (items.length == 0) {
	                    self.showEmpty();
	                }
	                callback(items);
	                self._columnCount = self.getCount();
	            }, function (e) {
	                //fail
	                fail();
	                modal.toast({ message: e.res.error });
	            }, function () {
	                //exception
	                exp();
	                if (items.length == 0) {
	                    self.showException();
	                } else {
	                    modal.toast({ message: '网络异常' });
	                }
	            }, function (e) {
	                //compelete
	                //                    progress.dismiss();
	                self.isloading = false;
	                self.$refs.refresh.end();
	                comp();
	            });
	        },

	        hideRefresh: function hideRefresh() {
	            var p = this.$refs.refresh;
	            p.end();
	            this.ispull = false;
	        },
	        refresh: function refresh() {
	            this.isEmpty = false;
	            this.isException = false;
	            this.isloading = false;
	            this.ispull = true;
	            this.$emit('refresh');
	        },
	        reload: function reload() {
	            this.isEmpty = false;
	            this.isException = false;
	            this.isloading = false;
	            this.ispull = false;
	            this.$emit('refresh');
	        }

	    }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 52:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(53)
	)

	/* script */
	__vue_exports__ = __webpack_require__(54)

	/* template */
	var __vue_template__ = __webpack_require__(55)
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
	__vue_options__.__file = "/Users/zhengjiangrong/Documents/GitHub/farwolf.weex/src/component/pullrefresh.vue"
	__vue_options__.render = __vue_template__.render
	__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
	__vue_options__._scopeId = "data-v-66af9ae2"
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

/***/ 53:
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

/***/ 54:
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


	exports.default = {
	    data: function data() {
	        return {
	            rtext: '下拉以加载',
	            updatetime: '没有更新',
	            offset: 0,
	            deg: 20,
	            refreshing: false,
	            pulldistance: 135,
	            hasrotate: false,
	            key: "ky" + Math.random()
	        };
	    },

	    methods: {
	        animateArrow: function animateArrow(deg) {
	            var animation = weex.requireModule('animation');
	            var arrow = this.$refs.arrow;
	            //                var deg=this.hasrotate?180:0

	            animation.transition(arrow, {
	                styles: {
	                    transform: "rotate(" + deg + "deg" + ")"
	                },

	                duration: 150, //ms
	                timingFunction: 'ease',
	                delay: 0 //ms
	            }, function () {});
	        },
	        onrefresh: function onrefresh(event) {
	            if (this.offset >= this.pulldistance) {

	                this.refreshing = true;
	                this.rtext = "加载中";
	                this.$emit('onRefresh');
	                //                    setTimeout(() => {
	                //                        this.refreshing = false
	                //                    }, 2000)
	            }
	        },
	        end: function end() {
	            this.refreshing = false;
	            //                this.deg=0;
	            this.updatetime = this.getNowFormatDate();
	            //                this.rtext='下拉以加载'
	        },
	        onpullingdown: function onpullingdown(event) {

	            var dis = event.pullingDistance;
	            if (dis < 0) dis *= -1;
	            this.offset = dis;

	            if (this.refreshing == false) {

	                //                     var t=dis>this.pulldistance
	                //                    if(t!=this.hasrotate)
	                //                    {
	                //                        this.hasrotate=t;
	                //                        this.animateArrow();
	                //                    }
	                if (dis > this.pulldistance) {
	                    this.rtext = "松开刷新";
	                    this.deg = 180;
	                    this.hasrotate = false;
	                    this.animateArrow(180);
	                } else {
	                    var p = dis / this.pulldistance;
	                    if (p > 1) p == 1;
	                    this.deg = p * 180;
	                    this.animateArrow(0);
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
	            var min = date.getMinutes();
	            var secon = date.getSeconds();
	            if (month >= 1 && month <= 9) {
	                month = "0" + month;
	            }
	            if (strDate >= 0 && strDate <= 9) {
	                strDate = "0" + strDate;
	            }
	            if (min >= 0 && min <= 9) {
	                min = "0" + min;
	            }
	            if (secon >= 0 && secon <= 9) {
	                secon = "0" + secon;
	            }

	            var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate + " " + date.getHours() + seperator2 + min + seperator2 + secon;
	            return currentdate;
	        }
	    },

	    created: function created() {}
	};
	module.exports = exports['default'];

/***/ }),

/***/ 55:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('refresh', {
	    key: _vm.key,
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
	  }, [(_vm.refreshing) ? _c('floading', {
	    staticClass: ["indicator"],
	    attrs: {
	      "color": "#555555"
	    }
	  }) : _vm._e(), (!_vm.refreshing) ? _c('image', {
	    ref: "arrow",
	    staticClass: ["limg"],
	    attrs: {
	      "src": "root:img/pull_arrow.png"
	    }
	  }) : _vm._e(), _c('div', {
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
	  }, [_vm._v("上次更新:" + _vm._s(_vm.updatetime))])])], 1)])
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ }),

/***/ 56:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticClass: ["c"]
	  }, [_c('waterfall', {
	    key: _vm.waterkey,
	    staticClass: ["page"],
	    style: {
	      'background-color': _vm.background
	    },
	    attrs: {
	      "columnCount": _vm.getCount(),
	      "columnGap": _vm.columnGap,
	      "showScrollbar": _vm.showScrollbar,
	      "scrollable": _vm.scrollable,
	      "loadmoreoffset": "141"
	    },
	    on: {
	      "loadmore": _vm.loadmore
	    }
	  }, [(_vm.usePullRefresh) ? _c('pull', {
	    ref: "refresh",
	    on: {
	      "onRefresh": _vm.refresh
	    }
	  }) : _vm._e(), _c('header', [_c('div', [_vm._t("head")], 2)]), _vm._t("cell"), _c('cell', {
	    appendAsTree: true,
	    attrs: {
	      "append": "tree"
	    }
	  }, [_c('div', [_vm._t("foot")], 2)]), (_vm.isEmpty) ? _c('cell', {
	    appendAsTree: true,
	    attrs: {
	      "append": "tree"
	    }
	  }, [_c('div', {
	    staticClass: ["empty"]
	  }, [_c('image', {
	    staticStyle: {
	      width: "252",
	      height: "201",
	      marginTop: "100"
	    },
	    attrs: {
	      "src": _vm.img_empty_src
	    }
	  }), _c('text', {
	    staticClass: ["emptytxt"],
	    staticStyle: {
	      marginTop: "20"
	    }
	  }, [_vm._v(_vm._s(_vm.emptyTxt))])])]) : _vm._e(), (_vm.isException) ? _c('cell', {
	    appendAsTree: true,
	    attrs: {
	      "append": "tree"
	    }
	  }, [_c('div', {
	    staticClass: ["empty"]
	  }, [_c('image', {
	    staticStyle: {
	      width: "252",
	      height: "201",
	      marginTop: "100"
	    },
	    attrs: {
	      "src": _vm.img_exception_src
	    }
	  }), _c('text', {
	    staticStyle: {
	      color: "#000000",
	      marginTop: "20"
	    }
	  }, [_vm._v("网络请求失败")]), _c('div', {
	    staticClass: ["exception"],
	    on: {
	      "click": _vm.reload
	    }
	  }, [_c('text', {
	    staticStyle: {
	      color: "#000000",
	      fontSize: "28"
	    }
	  }, [_vm._v("重新加载")])])])]) : _vm._e(), (!_vm.ispull && _vm.isloading && _vm.items.length > 0) ? _c('cell', {
	    appendAsTree: true,
	    attrs: {
	      "append": "tree"
	    }
	  }, [_c('div', {
	    staticClass: ["loading"]
	  }, [_c('floading', {
	    staticStyle: {
	      height: "40",
	      width: "40"
	    },
	    attrs: {
	      "color": "#999999",
	      "loadingStyle": "small"
	    }
	  }), _c('text', {
	    staticStyle: {
	      marginLeft: "10",
	      color: "#999999",
	      fontSize: "28"
	    }
	  }, [_vm._v("正在载入")])], 1)]) : _vm._e()], 2), (!_vm.ispull && _vm.isloading && _vm.items.length == 0) ? _c('div', {
	    staticClass: ["progress"]
	  }, [_c('floading', {
	    staticStyle: {
	      height: "40",
	      width: "40",
	      marginTop: "20"
	    },
	    attrs: {
	      "color": "#ffffff",
	      "loadingStyle": "big"
	    }
	  }), _c('text', {
	    staticStyle: {
	      marginLeft: "0",
	      color: "#ffffff",
	      fontSize: "30",
	      marginTop: "30",
	      fontWeight: "bold"
	    }
	  }, [_vm._v("加载中...")])], 1) : _vm._e()], 1)
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ }),

/***/ 229:
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

/***/ 230:
/***/ (function(module, exports, __webpack_require__) {

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

	var pull = __webpack_require__(231);
	var flist = __webpack_require__(49);

	var modal = weex.requireModule('modal');
	var LOADMORE_COUNT = 10;

	exports.default = {
	    components: { pull: pull, flist: flist },
	    data: function data() {
	        return {
	            lists: [],
	            pageNo: 1

	        };
	    },

	    methods: {
	        fetch: function fetch(event) {

	            this.pageNo++;
	            this.load();
	        },
	        refresh: function refresh() {
	            this.lists.length = 0;
	            this.pageNo = 1;
	            this.isloading = false;
	            this.load();
	        },
	        load: function load() {
	            //                this.pageNo++;
	            //                const t= this.pageNo+"";
	            //                var self=this;
	            //                this.$refs.list.load('http://hrmap.renturbo.com/position/search',{"distance":"10","pageNo":t},this.lists,function(itms){
	            //                    self.lists=itms;
	            //                });
	            this.$refs.list.showEmpty();
	        }
	    },

	    created: function created() {

	        //            this.load();
	        //            this.$refs.list.load('http://hrmap.renturbo.com/position/search',{"distance":"10","pageNo":this.pageNo},this.lists);
	    }
	};
	module.exports = exports['default'];

/***/ }),

/***/ 231:
/***/ (function(module, exports, __webpack_require__) {

	var __vue_exports__, __vue_options__
	var __vue_styles__ = []

	/* styles */
	__vue_styles__.push(__webpack_require__(232)
	)

	/* script */
	__vue_exports__ = __webpack_require__(233)

	/* template */
	var __vue_template__ = __webpack_require__(234)
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

/***/ 232:
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

/***/ 233:
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

/***/ 234:
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

/***/ 235:
/***/ (function(module, exports) {

	module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
	  return _c('div', {
	    staticStyle: {
	      flex: "1"
	    }
	  }, [_c('flist', {
	    ref: "list",
	    staticClass: ["list"],
	    on: {
	      "load": _vm.load,
	      "refresh": _vm.refresh
	    }
	  }, _vm._l((_vm.lists), function(p) {
	    return _c('cell', {
	      staticClass: ["cell"],
	      appendAsTree: true,
	      attrs: {
	        "append": "tree"
	      },
	      slot: "cell"
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
	  }))], 1)
	},staticRenderFns: []}
	module.exports.render._withStripped = true

/***/ })

/******/ });