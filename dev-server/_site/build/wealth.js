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
  var homeUrl = "./wealth.xml";
  var userHeaderItemXml = __webpack_require__(16);
  var middleLineXml = __webpack_require__(17);
  var assetAmountItemXml = __webpack_require__(18);
  var topGridItemXml = __webpack_require__(19);
  var bottomGridItemXml = __webpack_require__(20);
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
  return 13;
}, onCreateView: function onCreateView(parent, viewType) {
  var view = null;
  if (viewType == 'UserHeaderItem') 
  {
    view = ui.__c("fromXml", userHeaderItemXml, parent, false);
  } else if (viewType == 'MiddleLine') 
  {
    view = ui.__c("fromXml", middleLineXml, parent, false);
  } else if (viewType == 'AssetAmountItem') 
  {
    view = ui.__c("fromXml", assetAmountItemXml, parent, false);
  } else if (viewType == 'TopGridItem') 
  {
    view = ui.__c("fromXml", topGridItemXml, parent, false);
  } else if (viewType == 'BottomGridItem') 
  {
    view = ui.__c("fromXml", bottomGridItemXml, parent, false);
  } else {
    view = ui.__c("fromXml", userHeaderItemXml, parent, false);
  }
  print("getView js " + viewType);
  return view;
}, onBindView: function onBindView(view, position) {
}, getItemViewType: function getItemViewType(position) {
  if (position == 0) 
  {
    return 'UserHeaderItem';
  }
  if (position == 1 || position == 5 || position == 8 || position == 12) 
  {
    return 'MiddleLine';
  }
  if (position == 2) 
  {
    return 'AssetAmountItem';
  }
  if (position == 3 || position == 6 || position == 9) 
  {
    return 'TopGridItem';
  }
  if (position == 4 || position == 7 || position == 10 || position == 11) 
  {
    return 'BottomGridItem';
  }
  return 'UserHeaderItem';
}};
  recycleView = hybridView.__c("findViewWithTag", "recycleView");
  recycleView.__c("setAdapter", new RecycleAdapter(jsAdapter));
  _self.__c("bindTabEvent", 'wealth');
}};
  hybridView.__s("onLoad", function(result) {
  page.__c("onLoadXml");
});
  hybridView.__c("loadUrl", homeUrl);
}, , , , , , , , , , , , , , , , function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  width=\"720\"\n  screenUnit=\"720\"\n  background=\"#FFFFFF\">\n\n  <ImageView\n    x=\"30\"\n    y=\"30\"\n    width=\"120\"\n    height=\"120\"\n    imageUrl=\"https://img.alicdn.com/tps/i3/T1OjaVFl4dXXa.JOZB-114-114.png\"/>\n <TextView\n      x=\"170\"\n      y=\"30\"\n      width=\"500\"\n      height=\"40\"\n      textSize=\"34\"\n      text=\"\u5251\u767d\"/>\n  <TextView\n     x=\"170\"\n     y=\"70\"\n     height=\"40\"\n     textSize=\"26\"\n     textColor=\"#888888\"\n     text=\"jianbai.gbj@alibaba-inc.com\"/>\n\n  <LinearLayout\n    x=\"170\"\n    y=\"110\"\n    height=\"40\"\n    gravity=\"left|centerVertical\">\n\n    <IconTextView\n      width=\"40\"\n      height=\"40\"\n      text=\"0f481\"\n      textSize=\"36\"\n       textColor=\"#2d78f4\"\n       textAlign=\"center\"/>\n     <TextView\n        x=\"5\"\n        height=\"40\"\n        textSize=\"26\"\n        textColor=\"#888888\"\n        text=\"\u76f8\u518c\"/>\n      <View\n           x=\"20\"\n           width=\"2\"\n           height=\"35\"\n           background=\"#ECEDF0\"/>\n\n     <IconTextView\n        x=\"20\"\n        width=\"40\"\n        height=\"40\"\n        text=\"0f442\"\n        textSize=\"36\"\n        textColor=\"#2d78f4\"\n        textAlign=\"center\"/>\n      <TextView\n         x=\"5\"\n         height=\"40\"\n         textSize=\"26\"\n         textColor=\"#888888\"\n         text=\"\u6536\u85cf\"/>\n       <View\n            x=\"20\"\n            width=\"2\"\n            height=\"35\"\n            background=\"#ECEDF0\"/>\n\n      <IconTextView\n         x=\"20\"\n         width=\"40\"\n         height=\"40\"\n         text=\"0f3c6\"\n         textSize=\"36\"\n         textColor=\"#2d78f4\"\n         textAlign=\"center\"/>\n       <TextView\n          x=\"5\"\n          height=\"40\"\n          textSize=\"26\"\n          textColor=\"#888888\"\n          text=\"\u4f1a\u5458\u4e2d\u5fc3\"/>\n\n </LinearLayout>\n\n\n<View\n     y=\"180\"\n     width=\"720\"\n     height=\"1px\"\n     background=\"#ECEDF0\"/>\n</View>\n");
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  screenUnit=\"720\"\n  background=\"#F4F5F9\">\n  <View\n     height=\"30\"/>\n</View>\n");
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  width=\"720\"\n  screenUnit=\"720\"\n  background=\"#FFFFFF\">\n\n <WrapLayout\n   height=\"118\"\n   gravity=\"left|centerVertical\">\n   <TextView\n        x=\"30\"\n        height=\"118\"\n        textSize=\"50\"\n        text=\"1,500.00\"/>\n    <IconTextView\n       x=\"10\"\n       width=\"60\"\n       height=\"60\"\n       text=\"0f424\"\n       textSize=\"50\"\n       textColor=\"#888888\"\n       textAlign=\"center\"/>\n </WrapLayout>\n <TextView\n    x=\"360\"\n    width=\"280\"\n    height=\"118\"\n    text=\"\u5f00\u542f\u8d26\u6237\u5b89\u5168\u9669\u4eab100\u5b8c\u4fdd\u969c\u4fdd\u969c\u4fdd\u969c\"\n    textSize=\"28\"\n    textColor=\"#888888\"\n    lineBreakMode=\"tail\"\n    textAlign=\"center\"/>\n\n  <IconTextView\n     x=\"640\"\n     width=\"60\"\n     height=\"118\"\n     text=\"0f125\"\n     textSize=\"34\"\n     textColor=\"#888888\"\n     textAlign=\"center\"/>\n\n\n</View>\n");
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  background=\"#FFFFFF\"\n  screenUnit=\"720\"\n  width=\"720\">\n  <View\n     height=\"1px\"\n     background=\"#ECEDF0\"/>\n  <View\n    width=\"360\"\n    height=\"126\">\n    <ImageView\n      y=\"25\"\n      x=\"30\"\n      width=\"48\"\n      height=\"48\"\n      imageUrl=\"https://img.alicdn.com/tps/i3/T1OjaVFl4dXXa.JOZB-114-114.png\"/>\n    <TextView\n      x=\"100\"\n      y=\"25\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"34\"\n      text=\"\u4f59\u989d\"/>\n    <TextView\n      x=\"100\"\n      y=\"65\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"28\"\n      textColor=\"#888888\"\n      text=\"0.30\"/>\n  </View>\n\n  <View\n    x=\"360\"\n    width=\"360\"\n    height=\"126\">\n    <ImageView\n      y=\"25\"\n      x=\"30\"\n      width=\"48\"\n      height=\"48\"\n      imageUrl=\"https://img.alicdn.com/tps/i3/T1OjaVFl4dXXa.JOZB-114-114.png\"/>\n    <TextView\n      x=\"100\"\n      y=\"25\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"34\"\n      text=\"\u8682\u8681\u82b1\u5457\"/>\n    <TextView\n      x=\"100\"\n      y=\"65\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"28\"\n      textColor=\"#888888\"\n      text=\"\u666e\u60e0\u91d1\u878d\"/>\n  </View>\n  <View x=\"360\"\n        height=\"126\"\n        width=\"1px\"\n        background=\"#ECEDF0\"/>\n  <View y=\"126\"\n        height=\"1px\"\n        width=\"720\"\n        background=\"#ECEDF0\"/>\n</View>\n");
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  background=\"#FFFFFF\"\n  screenUnit=\"720\"\n  width=\"720\">\n  <View\n    width=\"360\"\n    height=\"126\">\n    <ImageView\n      y=\"25\"\n      x=\"30\"\n      width=\"48\"\n      height=\"48\"\n      imageUrl=\"https://img.alicdn.com/tps/i3/T1OjaVFl4dXXa.JOZB-114-114.png\"/>\n    <TextView\n      x=\"100\"\n      y=\"25\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"34\"\n      text=\"\u4f59\u989d\u5b9d\"/>\n    <TextView\n      x=\"100\"\n      y=\"65\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"28\"\n      textColor=\"#888888\"\n      text=\"+0.80\"/>\n  </View>\n\n  <View\n    x=\"360\"\n    width=\"360\"\n    height=\"126\">\n    <ImageView\n      y=\"25\"\n      x=\"30\"\n      width=\"48\"\n      height=\"48\"\n      imageUrl=\"https://img.alicdn.com/tps/i3/T1OjaVFl4dXXa.JOZB-114-114.png\"/>\n    <TextView\n      x=\"100\"\n      y=\"25\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"34\"\n      text=\"\u62db\u8d22\u5b9d\"/>\n    <TextView\n      x=\"100\"\n      y=\"65\"\n      width=\"240\"\n      height=\"40\"\n      textSize=\"28\"\n      textColor=\"#888888\"\n      text=\"\u5b9a\u671f\u7406\u8d22\"/>\n  </View>\n  <View x=\"360\"\n        height=\"126\"\n        width=\"1px\"\n        background=\"#ECEDF0\"/>\n  <View y=\"126\"\n        height=\"1px\"\n        width=\"720\"\n        background=\"#ECEDF0\"/>\n</View>\n");
}]);
