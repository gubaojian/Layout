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
  importClass("android.widget.BaseAdapter");
  importClass("com.efurture.gule.hybrid.adapter.StickyRecycleAdapter");
  importClass("com.efurture.gule.hybrid.adapter.RecycleAdapter");
  importClass("android.widget.PopupWindow");
  importClass("android.view.View.OnClickListener");
  var homeUrl = "./friends.xml";
  var lifeCommunityItemXml = __webpack_require__(5);
  var normalCellTopXml = __webpack_require__(6);
  var normalCellMiddleXml = __webpack_require__(7);
  var normalCellBottomXml = __webpack_require__(8);
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
  if (viewType == 'LifeCommunityItem') 
  {
    view = ui.__c("fromXml", lifeCommunityItemXml, parent, false);
  } else if (viewType == 'NormalCellTop') 
  {
    view = ui.__c("fromXml", normalCellTopXml, parent, false);
  } else if (viewType == 'NormalCellMiddle') 
  {
    view = ui.__c("fromXml", normalCellMiddleXml, parent, false);
  } else if (viewType == 'NormalCellBottom') 
  {
    view = ui.__c("fromXml", normalCellBottomXml, parent, false);
  } else {
    view = ui.__c("fromXml", lifeCommunityItemXml, parent, false);
  }
  print("getView js " + viewType);
  return view;
}, onBindView: function onBindView(view, position) {
}, getItemViewType: function getItemViewType(position) {
  if (position == 0) 
  {
    return 'LifeCommunityItem';
  }
  if (position == 1) 
  {
    return 'NormalCellTop';
  }
  if (position == 29) 
  {
    return 'NormalCellBottom';
  }
  return 'NormalCellMiddle';
}};
  recycleView = hybridView.__c("findViewWithTag", "recycleView");
  recycleView.__c("setAdapter", new RecycleAdapter(jsAdapter));
  _self.__c("bindTabEvent", 'friends');
}};
  hybridView.__s("onLoad", function(result) {
  page.__c("onLoadXml");
});
  hybridView.__c("loadUrl", homeUrl);
}, , , , , function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  width=\"720\"\n  screenUnit=\"720\"\n  background=\"#FFFFFF\">\n\n  <ImageView\n    x=\"20\"\n    y=\"20\"\n    width=\"88\"\n    height=\"88\"\n    imageUrl=\"https://t.alipayobjects.com/images/rmsweb/T1TH4gXohcXXXXXXXX.png\"/>\n <TextView\n      x=\"128\"\n      width=\"530\"\n      height=\"128\"\n      textSize=\"32\"\n      text=\"\u751f\u6d3b\u5708\"/>\n  <IconTextView\n     x=\"640\"\n     width=\"60\"\n     height=\"128\"\n     text=\"0f125\"\n     textSize=\"40\"\n     textColor=\"#888888\"\n     textAlign=\"center\"/>\n\n<View\n     y=\"128\"\n     width=\"720\"\n     height=\"1px\"\n     background=\"#ECEDF0\"/>\n</View>\n");
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  width=\"720\"\n  screenUnit=\"720\">\n  <View\n       width=\"720\"\n       height=\"1\"\n       background=\"#ECEDF0\"/>\n  <View\n    y=\"30\"\n    background=\"#FFFFFF\">\n    <ImageView\n      x=\"20\"\n      y=\"20\"\n      width=\"88\"\n      height=\"88\"\n      imageUrl=\"https://t.alipayobjects.com/images/rmsweb/T1TH4gXohcXXXXXXXX.png\"/>\n   <TextView\n        x=\"128\"\n        y=\"20\"\n        width=\"300\"\n        height=\"50\"\n        textSize=\"32\"\n        text=\"\u670d\u52a1\u7a97\"/>\n  <TextView\n        x=\"460\"\n        y=\"20\"\n        width=\"230\"\n        height=\"50\"\n        textSize=\"26\"\n        textColor=\"#888888\"\n        textAlign=\"right\"\n        text=\"\u4e0a\u5348 11:48\"/>\n  <TextView\n          x=\"128\"\n          y=\"68\"\n          width=\"560\"\n          height=\"40\"\n          textSize=\"28\"\n          textColor=\"#888888\"\n          lineBreakMode=\"tail\"\n          text=\"\u5929\u8679\u57fa\u91d1\uff1a\u4f59\u989d\u5b9d\u4e09\u5468\u5e74\uff01\u520650\u4ebf\u865a\u62df\u8d44\u91d1\uff0c\u9886\u7ea2\u5305\"/>\n\n  <View\n       y=\"128\"\n       x=\"20\"\n       width=\"700\"\n       height=\"1px\"\n       background=\"#ECEDF0\"/>\n   </View>\n</View>\n");
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  width=\"720\"\n  screenUnit=\"720\"\n  background=\"#FFFFFF\">\n  <ImageView\n    x=\"20\"\n    y=\"20\"\n    width=\"88\"\n    height=\"88\"\n    imageUrl=\"https://t.alipayobjects.com/images/rmsweb/T1TH4gXohcXXXXXXXX.png\"/>\n <TextView\n      x=\"128\"\n      y=\"20\"\n      width=\"300\"\n      height=\"50\"\n      textSize=\"32\"\n      text=\"\u5fae\u535a\u5934\u6761\"/>\n<TextView\n      x=\"460\"\n      y=\"20\"\n      width=\"230\"\n      height=\"50\"\n      textSize=\"26\"\n      textColor=\"#888888\"\n      textAlign=\"right\"\n      text=\"10:48\"/>\n<TextView\n        x=\"128\"\n        y=\"68\"\n        width=\"560\"\n        height=\"40\"\n        textSize=\"28\"\n        textColor=\"#888888\"\n        lineBreakMode=\"tail\"\n        text=\"\u6df1\u9677\u7ea6\u70ae\u95e8\u7684\u5434\u4ea6\u51e1\u8fd8\u80fd\u6d17\u767d\u5417\uff1f\"/>\n\n<View\n     y=\"128\"\n     x=\"20\"\n     width=\"700\"\n     height=\"1px\"\n     background=\"#ECEDF0\"/>\n</View>\n");
}, function(module, exports) {
  module.__s("exports", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<View\n  width=\"720\"\n  screenUnit=\"720\"\n  background=\"#FFFFFF\">\n  <ImageView\n    x=\"20\"\n    y=\"20\"\n    width=\"88\"\n    height=\"88\"\n    imageUrl=\"https://t.alipayobjects.com/images/rmsweb/T1TH4gXohcXXXXXXXX.png\"/>\n  <TextView\n      x=\"128\"\n      y=\"20\"\n      width=\"300\"\n      height=\"50\"\n      textSize=\"32\"\n      text=\"\u9e3f\u8363\"/>\n  <TextView\n      x=\"460\"\n      y=\"20\"\n      width=\"230\"\n      height=\"50\"\n      textSize=\"26\"\n      textColor=\"#888888\"\n      textAlign=\"right\"\n      text=\"16/5/19\"/>\n  <TextView\n        x=\"128\"\n        y=\"68\"\n        width=\"560\"\n        height=\"40\"\n        textSize=\"28\"\n        textColor=\"#888888\"\n        lineBreakMode=\"tail\"\n        text=\"[\u8f6c\u8d26]\u60f3\u4f60\u8f6c\u8d269.00\u5143\"/>\n\n  <View\n     y=\"128\"\n     width=\"720\"\n     height=\"1px\"\n     background=\"#ECEDF0\"/>\n</View>\n");
}]);
