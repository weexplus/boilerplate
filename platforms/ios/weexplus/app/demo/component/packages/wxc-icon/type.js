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
/***/ (function(module, exports) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	  value: true
	});
	exports.default = {
	  less: '\uE6A5',
	  'more_unfold': '\uE6A6',
	  back: '\uE697',
	  more: '\uE6A7',
	  add: '\uE6B9',
	  subtract: '\uE6FE',
	  close: '\uE69A',
	  cry: '\uE69C',
	  delete: '\uE69D',
	  help: '\uE6A3',
	  refresh: '\uE6AA',
	  search: '\uE6AC',
	  success: '\uE6B1',
	  warning: '\uE6B6',
	  wrong: '\uE6B7',
	  clock: '\uE6BB',
	  scanning: '\uE6EC',
	  filter: '\uE6F1',
	  map: '\uE715',
	  play: '\uE719'
	};
	module.exports = exports['default'];

/***/ })
/******/ ]);