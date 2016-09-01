importApi("ui");
importApi("nav");
importClass("com.efurture.glue.ui.XmlViewLoadListener")
importClass("com.efurture.gule.hybrid.adapter.RecycleAdapter");
importClass("android.view.View.OnClickListener");

var homeUrl = "./koubei.xml";
var gridItemXml = require('raw!./home/gridItem.xml');
var categoryBannerXml = require('raw!./koubei/categoryBanner.xml');
var middleSeparatorLineXml = require('raw!./koubei/middleSeparatorLine.xml');
var recommendItemsXml = require('raw!./koubei/recommendItems.xml');
var oneImageBannerXml = require('raw!./koubei/oneImageBanner.xml');
var emptyStickyHeaderXml = require('raw!./koubei/emptyStickyHeader.xml');
var koubeiShopItemXml = require('raw!./koubei/koubeiShopItem.xml');




var recycleView; //


var page = {
    bindTabEvent : function(tabName){

      if(tabName != 'home'){
        var homeTabIcon = ui.find("homeTabTextIcon");
        homeTabIcon.setOnClickListener(new OnClickListener(function () {
               nav.toUrl('./alipay.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
        }));

        var homeTabText = ui.find("homeTabText");
        homeTabText.setOnClickListener(new OnClickListener(function () {
               nav.toUrl('./alipay.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
        }));
      }

      if(tabName != 'koubei'){
        var koubeiTabIcon = ui.find("koubeiTabTextIcon");
        koubeiTabIcon.setOnClickListener(new OnClickListener(function () {
               nav.toUrl('./koubei.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
        }));

        var koubeiTabText = ui.find("koubeiTabText");
        koubeiTabText.setOnClickListener(new OnClickListener(function () {
               nav.toUrl('./koubei.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
        }));
      }

      if(tabName != 'friends'){
        var friendsTabIcon = ui.find("friendsTabTextIcon");
        friendsTabIcon.setOnClickListener(new OnClickListener(function () {
               nav.toUrl('./friends.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
        }));

        var friendsTabText = ui.find("friendsTabText");
        friendsTabText.setOnClickListener(new OnClickListener(function () {
               nav.toUrl('./friends.js', 'com.efurture.hybrid.demo.AlipayHomeActivity', true);
        }));
      }

      if(tabName != 'wealth'){
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
    onLoadXml : function(){
      var _self = this;

      //初始化listview
      var jsAdapter = {
        getItemCount : function() {
          return 30;
        },
        onCreateView : function(parent, viewType){
            var view = null;
            if(viewType == 'CategoryBanner'){
                view = ui.fromXml(categoryBannerXml, parent, false);
            }else if(viewType == 'MiddleSeparatorLine'){
               view = ui.fromXml(middleSeparatorLineXml, parent, false);
            }else if(viewType == 'RecommendItems'){
               view = ui.fromXml(recommendItemsXml, parent, false);
            }else if(viewType == 'oneImageBanner'){
               view = ui.fromXml(oneImageBannerXml, parent, false);
            }else if(viewType == 'EmptyStickyHeader'){
               view = ui.fromXml(emptyStickyHeaderXml, parent, false);
            }else if(viewType == 'KoubeiShopItem'){
               view = ui.fromXml(koubeiShopItemXml, parent, false);
            }else{
                view = ui.fromXml(gridItemXml, parent, false);
            }
            print("getView js " + viewType );
            return view;
        },
        onBindView : function(view, position){
        },
        stickViewType : function (position) {
             return ['EmptyStickyHeader', "stickGridItem"];
        },
        getItemViewType : function(position){
            if(position == 0){
              return 'EmptyStickyHeader';
            }
            if(position == 1){
               return 'CategoryBanner';
            }
            if(position == 2 || position == 8){
               return 'MiddleSeparatorLine'
            }
            if(position == 3){
                 return 'RecommendItems'
            }
            if(position >= 4  && position <= 7){
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
hybridView.onLoad = function(result){
     page.onLoadXml();
};
hybridView.loadUrl(homeUrl);
