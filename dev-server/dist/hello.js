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
/***/ function(module, exports, __webpack_require__) {

	"use strict";

	importApi("ui");
	importClass("android.view.View.OnClickListener");
	importClass("android.widget.Toast");

	var helloXml = __webpack_require__(9);
	var page = {
	    onLoad: function onLoad() {

	        var imageView = hybridView.findViewWithTag("imageView");
	        imageView.setOnClickListener(new OnClickListener(function () {
	            Toast.makeText(activity, "原生API调用", Toast.LENGTH_SHORT).show();
	        }));

	        /**
	        ui.click("imageView", function(view){
	             ui.toast(view);
	             view.setOnClickListener(new OnClickListener(function(){
	                  Toast.makeText(activity, "原生API调用", Toast.LENGTH_SHORT).show();
	             }));
	        });*/
	    }
	};
	ui.alert(activity);
	hybridView.onLoad = function () {
	    page.onLoad();
	};
	hybridView.loadXml(helloXml);

/***/ },

/***/ 9:
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n    width=\"750\"\n    height=\"100%\"\n    screenUnit=\"750\">\n   <ImageView\n     width=\"750\"\n     height=\"300\"\n     imageUrl=\"https://zos.alipayobjects.com/rmsportal/VIZVslEXXguQJbi.jpg\"\n     scaleType=\"fitXY\"\n     tag=\"imageView\"/>\n  <TextView\n     y=\"300\"\n     width=\"750\"\n     textAlign=\"center\"\n     height=\"100\"\n     text=\"Hello World 网商银行\"\n     tag=\"helloTextField\"/>\n</View>\n"

/***/ }

/******/ });