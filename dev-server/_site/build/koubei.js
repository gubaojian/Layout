(function(modules) {
  var installedModules = {};
  function __webpack_require__(moduleId) {
    if (installedModules[moduleId]) 
    return installedModules[moduleId].exports;
    var module = installedModules[moduleId] = {exports: {}, id: moduleId, loaded: false};
    modules[moduleId].__c("call", module.__g("exports"), module, module.__g("exports"), __webpack_require__);
    module.__s("loaded", true);
    return module.exports;
  }
  __webpack_require__.__s("m", modules);
  __webpack_require__.__s("c", installedModules);
  __webpack_require__.__s("p", "");
  return __webpack_require__(0);
})([function(module, exports, __webpack_require__) {
  "use strict";
  importApi("ui");
  importApi("nav");
  importClass("com.efurture.glue.ui.XmlViewLoadListener");
  importClass("com.efurture.gule.hybrid.adapter.RecycleAdapter");
  importClass("android.view.View.OnClickListener");
  var homeUrl = "./koubei.xml";
  var gridItemXml = __webpack_require__(1);
  var categoryBannerXml = __webpack_require__(10);
  var middleSeparatorLineXml = __webpack_require__(11);
  var recommendItemsXml = __webpack_require__(12);
  var oneImageBannerXml = __webpack_require__(13);
  var emptyStickyHeaderXml = __webpack_require__(14);
  var koubeiShopItemXml = __webpack_require__(15);
  var recycleView;
  var page = {bindTabEvent: function bindTabEvent(tabName) {
  if (tabName != 'home') 
  {
    var homeTabIcon = ui.__c("find", "homeTabTextIcon");
    homeTabIcon.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './alipay.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
    var homeTabText = ui.__c("find", "homeTabText");
    homeTabText.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './alipay.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
  }
  if (tabName != 'koubei') 
  {
    var koubeiTabIcon = ui.__c("find", "koubeiTabTextIcon");
    koubeiTabIcon.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './koubei.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
    var koubeiTabText = ui.__c("find", "koubeiTabText");
    koubeiTabText.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './koubei.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
  }
  if (tabName != 'friends') 
  {
    var friendsTabIcon = ui.__c("find", "friendsTabTextIcon");
    friendsTabIcon.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './friends.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
    var friendsTabText = ui.__c("find", "friendsTabText");
    friendsTabText.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './friends.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
  }
  if (tabName != 'wealth') 
  {
    var wealthTabIcon = ui.__c("find", "wealthTabTextIcon");
    wealthTabIcon.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './wealth.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
    var wealthTabText = ui.__c("find", "wealthTabText");
    wealthTabText.__c("setOnClickListener", new OnClickListener(function() {
  nav.__c("toUrl", './wealth.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
}));
  }
}, onLoadXml: function onLoadXml() {
  var _self = this;
  var jsAdapter = {getItemCount: function getItemCount() {
  return 30;
}, onCreateView: function onCreateView(parent, viewType) {
  var view = null;
  if (viewType == 'CategoryBanner') 
  {
    view = ui.__c("fromXml", categoryBannerXml, parent, false);
  } else if (viewType == 'MiddleSeparatorLine') 
  {
    view = ui.__c("fromXml", middleSeparatorLineXml, parent, false);
  } else if (viewType == 'RecommendItems') 
  {
    view = ui.__c("fromXml", recommendItemsXml, parent, false);
  } else if (viewType == 'oneImageBanner') 
  {
    view = ui.__c("fromXml", oneImageBannerXml, parent, false);
  } else if (viewType == 'EmptyStickyHeader') 
  {
    view = ui.__c("fromXml", emptyStickyHeaderXml, parent, false);
  } else if (viewType == 'KoubeiShopItem') 
  {
    view = ui.__c("fromXml", koubeiShopItemXml, parent, false);
  } else {
    view = ui.__c("fromXml", gridItemXml, parent, false);
  }
  print("getView js " + viewType);
  return view;
}, onBindView: function onBindView(view, position) {
}, stickViewType: function stickViewType(position) {
  return ['EmptyStickyHeader', "stickGridItem"];
}, getItemViewType: function getItemViewType(position) {
  if (position == 0) 
  {
    return 'EmptyStickyHeader';
  }
  if (position == 1) 
  {
    return 'CategoryBanner';
  }
  if (position == 2 || position == 8) 
  {
    return 'MiddleSeparatorLine';
  }
  if (position == 3) 
  {
    return 'RecommendItems';
  }
  if (position >= 4 && position <= 7) 
  {
    return 'oneImageBanner';
  }
  return 'KoubeiShopItem';
}};
  recycleView = hybridView.__c("findViewWithTag", "recycleView");
  recycleView.__c("setAdapter", new RecycleAdapter(jsAdapter));
  _self.__c("bindTabEvent", 'koubei');
}};
  hybridView.__s("onLoad", function(result) {
  page.__c("onLoadXml");
});
  hybridView.__c("loadUrl", homeUrl);
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View background=\"#FFFFFF\">\n  <View\n    width=\"180\"\n    height=\"180\">\n    <ImageView\n      y=\"40\"\n      x=\"60\"\n      width=\"60\"\n      height=\"60\"\n      imageUrl=\"https://img.alicdn.com/tps/i3/T1OjaVFl4dXXa.JOZB-114-114.png\"/>\n    <TextView\n      y=\"100\"\n      width=\"180\"\n      textSize=\"32\"\n      textAlign=\"center\"\n      text=\"\u7f51\u5546\u94f6\u884c\"/>\n  </View>\n</View>\n");
}, , , , , , , , , function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View screenUnit=\"720\"\n  background=\"#FFFFFF\">\n  <ViewPager\n       width=\"720\"\n       height=\"352\"\n       tag=\"topViewPager\"\n       xmlUrls=\"./koubei/categoryBannerPagerItem.xml;./koubei/categoryBannerPagerItem.xml;./koubei/categoryBannerPagerItem.xml;./koubei/categoryBannerPagerItem.xml\"/>\n\n  <PagerIndicator\n               width=\"720\"\n               height=\"20\"\n               y=\"314\"\n               circleColor=\"#CDCECF\"\n               circleSelectColor=\"#7D7E7F\"\n               circleSize=\"12\"\n               viewPagerTag=\"topViewPager\"/>\n</View>\n");
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  screenUnit=\"720\"\n  background=\"#F4F5F9\">\n\n  <View\n     width=\"720\"\n     height=\"1px\"\n     background=\"#ECEDF0\"/>\n    <View\n       y=\"18\"\n       width=\"720\"\n       height=\"1px\"\n       background=\"#ECEDF0\"/>\n</View>\n");
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  screenUnit=\"720\"\n  background=\"#FFFFFF\"\n  width=\"720\">\n\n  <View x=\"30\"\n       y=\"30\"\n       height=\"40\"\n       width=\"8\"\n       background=\"#F96269\"/>\n  <TextView\n         x=\"50\"\n         y=\"30\"\n         height=\"40\"\n         text=\"\u4e3a\u4f60\u63a8\u8350\"\n         textSize=\"36\"/>\n\n <IconTextView\n            height=\"40\"\n            width=\"40\"\n            x=\"550\"\n            y=\"30\"\n            text=\"0f49a\"\n            textSize=\"42\"\n            textColor=\"#F96269\"\n            textAlign=\"center\"/>\n  <TextView\n                x=\"600\"\n                y=\"30\"\n                width=\"120\"\n                height=\"40\"\n                text=\"\u6362\u4e00\u6279\"\n                textColor=\"#F96269\"\n                textSize=\"30\"/>\n\n  <View width=\"208\"\n          height=\"340\"\n          x=\"30\"\n          y=\"94\">\n     <ImageView\n       width=\"208\"\n       height=\"208\"\n       imageUrl=\"https://img.alicdn.com/tps/i4/TB1ZxttKpXXXXXkaXXXSutbFXXX.jpg\"/>\n       <TextView\n                     y=\"230\"\n                     width=\"208\"\n                     height=\"40\"\n                     text=\"\u725b\u9b54\u738b\u897f\u5b89\u7f8e\u98df\"\n                     textSize=\"30\"/>\n       <TextView\n                     y=\"278\"\n                     width=\"208\"\n                     height=\"32\"\n                     text=\"\u5e38\u53bb\u7684\u5e97\"\n                     textColor=\"#888888\"\n                     textSize=\"28\"/>\n\n  </View>\n\n  <View width=\"208\"\n          height=\"340\"\n          x=\"256\"\n          y=\"94\">\n     <ImageView\n       width=\"208\"\n       height=\"208\"\n       imageUrl=\"https://img.alicdn.com/tps/i4/TB1v8rcKpXXXXXmXVXXSutbFXXX.jpg_640x480q100.jpg\"/>\n       <TextView\n                     y=\"230\"\n                     width=\"208\"\n                     height=\"40\"\n                     text=\"\u725b\u9b54\u738b\u897f\u5b89\u7f8e\u98df\"\n                     textSize=\"30\"/>\n       <TextView\n                     y=\"278\"\n                     width=\"208\"\n                     height=\"32\"\n                     text=\"\u5e38\u53bb\u7684\u5e97\"\n                     textColor=\"#888888\"\n                     textSize=\"28\"/>\n\n  </View>\n\n  <View width=\"208\"\n          height=\"340\"\n          x=\"482\"\n          y=\"94\">\n     <ImageView\n       width=\"208\"\n       height=\"208\"\n       imageUrl=\"https://img.alicdn.com/tps/TB1RuAcKXXXXXXnXVXXXXXXXXXX-492-680.jpg\"/>\n       <TextView\n                     y=\"230\"\n                     width=\"208\"\n                     height=\"40\"\n                     text=\"\u725b\u9b54\u738b\u897f\u5b89\u7f8e\u98df\"\n                     textSize=\"30\"/>\n       <TextView\n                     y=\"278\"\n                     width=\"208\"\n                     height=\"32\"\n                     text=\"\u5e38\u53bb\u7684\u5e97\"\n                     textColor=\"#888888\"\n                     textSize=\"28\"/>\n\n  </View>\n</View>\n");
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  screenUnit=\"720\"\n  background=\"#FFFFFF\"\n  width=\"720\">\n\n <View\n   height=\"380\"\n   width=\"720\">\n      <ImageView\n        width=\"660\"\n        height=\"360\"\n        x=\"30\"\n        imageUrl=\"https://img.alicdn.com/tps/i4/TB1pm6QMXXXXXaNaXXX6EYqTVXX-490-740.jpg_760x760q100.jpg\"/>\n   </View>\n</View>\n");
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  screenUnit=\"720\"\n  background=\"#FFFFFF\"\n  height=\"0\">\n\n</View>\n");
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  background=\"#FFFFFF\"\n  screenUnit=\"720\"\n  width=\"720\">\n  <View\n    x=\"30\"\n    y=\"30\"\n    width=\"320\"\n    height=\"476\">\n    <ImageView\n      width=\"320\"\n      height=\"320\"\n      imageUrl=\"https://img.alicdn.com/tps/i4/TB1Hd.bKpXXXXb4XVXXSutbFXXX.jpg_1080x1800q75s0.jpg\"/>\n    <TextView\n      y=\"330\"\n      width=\"320\"\n      height=\"50\"\n      textSize=\"32\"\n      text=\"\u697c\u5916\u697c(\u5510\u6cb3\u5e97)\"/>\n    <TextView\n        y=\"380\"\n        width=\"320\"\n        height=\"40\"\n        textSize=\"28\"\n        textColor=\"#888888\"\n        text=\"\u719f\u98df 619m \u4eba\u574726\u5143\"/>\n  <TextView\n          y=\"420\"\n          width=\"320\"\n          height=\"48\"\n          textSize=\"32\"\n          textColor=\"#F2626C\"\n          text=\"9.7\u6298\u6263\"/>\n  </View>\n\n  <View\n    x=\"370\"\n    y=\"30\"\n    width=\"320\"\n    height=\"476\">\n    <ImageView\n      width=\"320\"\n      height=\"320\"\n      imageUrl=\"https://img.alicdn.com/tps/i4/TB1t7L6KpXXXXcUaXXXSutbFXXX.jpg_1080x1800q75s0.jpg\"/>\n    <TextView\n      y=\"330\"\n      width=\"320\"\n      height=\"50\"\n      textSize=\"32\"\n      text=\"\u697c\u5916\u697c(\u5510\u6cb3\u5e97)\"/>\n    <TextView\n        y=\"380\"\n        width=\"320\"\n        height=\"40\"\n        textSize=\"28\"\n        textColor=\"#888888\"\n        text=\"\u719f\u98df 619m \u4eba\u574726\u5143\"/>\n  <TextView\n          y=\"420\"\n          width=\"320\"\n          height=\"48\"\n          textSize=\"32\"\n          textColor=\"#F2626C\"\n          text=\"9.7\u6298\u6263\"/>\n  </View>\n</View>\n");
}]);
