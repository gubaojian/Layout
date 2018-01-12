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
/***/ function(module, exports, __webpack_require__) {

	"use strict";

	importApi("ui");
	importApi("nav");
	importClass("com.efurture.glue.ui.XmlViewLoadListener");
	importClass("com.efurture.gule.hybrid.adapter.RecycleAdapter");
	importClass("android.view.View.OnClickListener");

	var homeUrl = "./koubei.xml";
	var gridItemXml = __webpack_require__(1);
	var categoryBannerXml = __webpack_require__(11);
	var middleSeparatorLineXml = __webpack_require__(12);
	var recommendItemsXml = __webpack_require__(13);
	var oneImageBannerXml = __webpack_require__(14);
	var emptyStickyHeaderXml = __webpack_require__(15);
	var koubeiShopItemXml = __webpack_require__(16);

	var recycleView; //

	var page = {
	  bindTabEvent: function bindTabEvent(tabName) {

	    if (tabName != 'home') {
	      var homeTabIcon = ui.find("homeTabTextIcon");
	      homeTabIcon.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./alipay.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));

	      var homeTabText = ui.find("homeTabText");
	      homeTabText.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./alipay.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));
	    }

	    if (tabName != 'koubei') {
	      var koubeiTabIcon = ui.find("koubeiTabTextIcon");
	      koubeiTabIcon.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./koubei.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));

	      var koubeiTabText = ui.find("koubeiTabText");
	      koubeiTabText.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./koubei.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));
	    }

	    if (tabName != 'friends') {
	      var friendsTabIcon = ui.find("friendsTabTextIcon");
	      friendsTabIcon.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./friends.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));

	      var friendsTabText = ui.find("friendsTabText");
	      friendsTabText.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./friends.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));
	    }

	    if (tabName != 'wealth') {
	      var wealthTabIcon = ui.find("wealthTabTextIcon");
	      wealthTabIcon.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./wealth.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));

	      var wealthTabText = ui.find("wealthTabText");
	      wealthTabText.setOnClickListener(new OnClickListener(function () {
	        nav.toUrl('./wealth.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
	      }));
	    }
	  },
	  onLoadXml: function onLoadXml() {
	    var _self = this;

	    //初始化listview
	    var jsAdapter = {
	      getItemCount: function getItemCount() {
	        return 30;
	      },
	      onCreateView: function onCreateView(parent, viewType) {
	        var view = null;
	        if (viewType == 'CategoryBanner') {
	          view = ui.fromXml(categoryBannerXml, parent, false);
	        } else if (viewType == 'MiddleSeparatorLine') {
	          view = ui.fromXml(middleSeparatorLineXml, parent, false);
	        } else if (viewType == 'RecommendItems') {
	          view = ui.fromXml(recommendItemsXml, parent, false);
	        } else if (viewType == 'oneImageBanner') {
	          view = ui.fromXml(oneImageBannerXml, parent, false);
	        } else if (viewType == 'EmptyStickyHeader') {
	          view = ui.fromXml(emptyStickyHeaderXml, parent, false);
	        } else if (viewType == 'KoubeiShopItem') {
	          view = ui.fromXml(koubeiShopItemXml, parent, false);
	        } else {
	          view = ui.fromXml(gridItemXml, parent, false);
	        }
	        print("getView js " + viewType);
	        return view;
	      },
	      onBindView: function onBindView(view, position) {},
	      stickViewType: function stickViewType(position) {
	        return ['EmptyStickyHeader', "stickGridItem"];
	      },
	      getItemViewType: function getItemViewType(position) {
	        if (position == 0) {
	          return 'EmptyStickyHeader';
	        }
	        if (position == 1) {
	          return 'CategoryBanner';
	        }
	        if (position == 2 || position == 8) {
	          return 'MiddleSeparatorLine';
	        }
	        if (position == 3) {
	          return 'RecommendItems';
	        }
	        if (position >= 4 && position <= 7) {
	          return 'oneImageBanner';
	        }
	        return 'KoubeiShopItem';
	      }
	    };
	    recycleView = hybridView.findViewWithTag("recycleView");
	    recycleView.setAdapter(new RecycleAdapter(jsAdapter));

	    _self.bindTabEvent('koubei');
	  }
	};
	hybridView.onLoad = function (result) {
	  page.onLoadXml();
	};
	hybridView.loadUrl(homeUrl);

/***/ },
/* 1 */
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View background=\"#FFFFFF\">\n  <View\n    width=\"180\"\n    height=\"180\">\n    <ImageView\n      y=\"40\"\n      x=\"60\"\n      width=\"60\"\n      height=\"60\"\n      imageUrl=\"https://img.alicdn.com/tps/i3/T1OjaVFl4dXXa.JOZB-114-114.png\"/>\n    <TextView\n      y=\"100\"\n      width=\"180\"\n      textSize=\"32\"\n      textAlign=\"center\"\n      text=\"手机淘宝\"/>\n  </View>\n</View>\n"

/***/ },
/* 2 */,
/* 3 */,
/* 4 */,
/* 5 */,
/* 6 */,
/* 7 */,
/* 8 */,
/* 9 */,
/* 10 */,
/* 11 */
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View screenUnit=\"720\"\n  background=\"#FFFFFF\">\n  <ViewPager\n       width=\"720\"\n       height=\"352\"\n       tag=\"topViewPager\"\n       xmlUrls=\"./koubei/categoryBannerPagerItem.xml;./koubei/categoryBannerPagerItem.xml;./koubei/categoryBannerPagerItem.xml;./koubei/categoryBannerPagerItem.xml\"/>\n\n  <PagerIndicator\n               width=\"720\"\n               height=\"20\"\n               y=\"314\"\n               circleColor=\"#CDCECF\"\n               circleSelectColor=\"#7D7E7F\"\n               circleSize=\"12\"\n               viewPagerTag=\"topViewPager\"/>\n</View>\n"

/***/ },
/* 12 */
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  screenUnit=\"720\"\n  background=\"#F4F5F9\">\n\n  <View\n     width=\"720\"\n     height=\"1px\"\n     background=\"#ECEDF0\"/>\n    <View\n       y=\"18\"\n       width=\"720\"\n       height=\"1px\"\n       background=\"#ECEDF0\"/>\n</View>\n"

/***/ },
/* 13 */
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  screenUnit=\"720\"\n  background=\"#FFFFFF\"\n  width=\"720\">\n\n  <View x=\"30\"\n       y=\"30\"\n       height=\"40\"\n       width=\"8\"\n       background=\"#F96269\"/>\n  <TextView\n         x=\"50\"\n         y=\"30\"\n         height=\"40\"\n         text=\"为你推荐\"\n         textSize=\"36\"/>\n\n <IconTextView\n            height=\"40\"\n            width=\"40\"\n            x=\"550\"\n            y=\"30\"\n            text=\"0f49a\"\n            textSize=\"42\"\n            textColor=\"#F96269\"\n            textAlign=\"center\"/>\n  <TextView\n                x=\"600\"\n                y=\"30\"\n                width=\"120\"\n                height=\"40\"\n                text=\"换一批\"\n                textColor=\"#F96269\"\n                textSize=\"30\"/>\n\n  <View width=\"208\"\n          height=\"340\"\n          x=\"30\"\n          y=\"94\">\n     <ImageView\n       width=\"208\"\n       height=\"208\"\n       imageUrl=\"https://img.alicdn.com/tps/i4/TB1ZxttKpXXXXXkaXXXSutbFXXX.jpg\"/>\n       <TextView\n                     y=\"230\"\n                     width=\"208\"\n                     height=\"40\"\n                     text=\"牛魔王西安美食\"\n                     textSize=\"30\"/>\n       <TextView\n                     y=\"278\"\n                     width=\"208\"\n                     height=\"32\"\n                     text=\"常去的店\"\n                     textColor=\"#888888\"\n                     textSize=\"28\"/>\n\n  </View>\n\n  <View width=\"208\"\n          height=\"340\"\n          x=\"256\"\n          y=\"94\">\n     <ImageView\n       width=\"208\"\n       height=\"208\"\n       imageUrl=\"https://img.alicdn.com/tps/i4/TB1v8rcKpXXXXXmXVXXSutbFXXX.jpg_640x480q100.jpg\"/>\n       <TextView\n                     y=\"230\"\n                     width=\"208\"\n                     height=\"40\"\n                     text=\"牛魔王西安美食\"\n                     textSize=\"30\"/>\n       <TextView\n                     y=\"278\"\n                     width=\"208\"\n                     height=\"32\"\n                     text=\"常去的店\"\n                     textColor=\"#888888\"\n                     textSize=\"28\"/>\n\n  </View>\n\n  <View width=\"208\"\n          height=\"340\"\n          x=\"482\"\n          y=\"94\">\n     <ImageView\n       width=\"208\"\n       height=\"208\"\n       imageUrl=\"https://img.alicdn.com/tps/TB1RuAcKXXXXXXnXVXXXXXXXXXX-492-680.jpg\"/>\n       <TextView\n                     y=\"230\"\n                     width=\"208\"\n                     height=\"40\"\n                     text=\"牛魔王西安美食\"\n                     textSize=\"30\"/>\n       <TextView\n                     y=\"278\"\n                     width=\"208\"\n                     height=\"32\"\n                     text=\"常去的店\"\n                     textColor=\"#888888\"\n                     textSize=\"28\"/>\n\n  </View>\n</View>\n"

/***/ },
/* 14 */
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  screenUnit=\"720\"\n  background=\"#FFFFFF\"\n  width=\"720\">\n\n <View\n   height=\"380\"\n   width=\"720\">\n      <ImageView\n        width=\"660\"\n        height=\"360\"\n        x=\"30\"\n        imageUrl=\"https://img.alicdn.com/tps/i4/TB1pm6QMXXXXXaNaXXX6EYqTVXX-490-740.jpg_760x760q100.jpg\"/>\n   </View>\n</View>\n"

/***/ },
/* 15 */
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  screenUnit=\"720\"\n  background=\"#FFFFFF\"\n  height=\"0\">\n\n</View>\n"

/***/ },
/* 16 */
/***/ function(module, exports) {

	module.exports = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  background=\"#FFFFFF\"\n  screenUnit=\"720\"\n  width=\"720\">\n  <View\n    x=\"30\"\n    y=\"30\"\n    width=\"320\"\n    height=\"476\">\n    <ImageView\n      width=\"320\"\n      height=\"320\"\n      imageUrl=\"https://img.alicdn.com/tps/i4/TB1Hd.bKpXXXXb4XVXXSutbFXXX.jpg_1080x1800q75s0.jpg\"/>\n    <TextView\n      y=\"330\"\n      width=\"320\"\n      height=\"50\"\n      textSize=\"32\"\n      text=\"楼外楼(唐河店)\"/>\n    <TextView\n        y=\"380\"\n        width=\"320\"\n        height=\"40\"\n        textSize=\"28\"\n        textColor=\"#888888\"\n        text=\"熟食 619m 人均26元\"/>\n  <TextView\n          y=\"420\"\n          width=\"320\"\n          height=\"48\"\n          textSize=\"32\"\n          textColor=\"#F2626C\"\n          text=\"9.7折扣\"/>\n  </View>\n\n  <View\n    x=\"370\"\n    y=\"30\"\n    width=\"320\"\n    height=\"476\">\n    <ImageView\n      width=\"320\"\n      height=\"320\"\n      imageUrl=\"https://img.alicdn.com/tps/i4/TB1t7L6KpXXXXcUaXXXSutbFXXX.jpg_1080x1800q75s0.jpg\"/>\n    <TextView\n      y=\"330\"\n      width=\"320\"\n      height=\"50\"\n      textSize=\"32\"\n      text=\"楼外楼(唐河店)\"/>\n    <TextView\n        y=\"380\"\n        width=\"320\"\n        height=\"40\"\n        textSize=\"28\"\n        textColor=\"#888888\"\n        text=\"熟食 619m 人均26元\"/>\n  <TextView\n          y=\"420\"\n          width=\"320\"\n          height=\"48\"\n          textSize=\"32\"\n          textColor=\"#F2626C\"\n          text=\"9.7折扣\"/>\n  </View>\n</View>\n"

/***/ }
/******/ ]);