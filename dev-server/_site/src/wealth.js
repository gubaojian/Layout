importApi("ui");
importApi("nav");
importClass("com.efurture.glue.ui.XmlViewLoadListener")
importClass("com.efurture.gule.hybrid.adapter.RecycleAdapter");
importClass("android.view.View.OnClickListener");

var homeUrl = "./wealth.xml";
var userHeaderItemXml = require('raw!./wealth/userHeaderItem.xml');
var middleLineXml = require('raw!./wealth/middleLine.xml');
var assetAmountItemXml = require('raw!./wealth/assetAmountItem.xml');
var topGridItemXml = require('raw!./wealth/topGridItem.xml');
var bottomGridItemXml = require('raw!./wealth/bottomGridItem.xml');

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
          return 13;
        },
        onCreateView : function(parent, viewType){
            var view = null;
            if(viewType == 'UserHeaderItem'){
                view = ui.fromXml(userHeaderItemXml, parent, false);
            }else if(viewType == 'MiddleLine'){
                view = ui.fromXml(middleLineXml, parent, false);
            }else if(viewType == 'AssetAmountItem'){
                view = ui.fromXml(assetAmountItemXml, parent, false);
            }else if(viewType == 'TopGridItem'){
                view = ui.fromXml(topGridItemXml, parent, false);
            }else if(viewType == 'BottomGridItem'){
                view = ui.fromXml(bottomGridItemXml, parent, false);
            }else{
                view = ui.fromXml(userHeaderItemXml, parent, false);
            }


            print("getView js " + viewType );
            return view;
        },
        onBindView : function(view, position){
        },
        getItemViewType : function(position){
            if(position == 0){
              return 'UserHeaderItem';
            }

            if(position == 1
              || position == 5
              || position == 8
              || position == 12 ){
              return 'MiddleLine'
            }
            if(position == 2){
              return 'AssetAmountItem'
            }

            if(position == 3
              || position == 6
              || position == 9 ){
               return 'TopGridItem';
            }

            if(position == 4
              || position == 7
              || position == 10
              || position == 11){
               return 'BottomGridItem';
            }
            return 'UserHeaderItem';
        }
      };
      recycleView = hybridView.findViewWithTag("recycleView");
      recycleView.setAdapter(new RecycleAdapter(jsAdapter));
      _self.bindTabEvent('wealth');
    }
};
hybridView.onLoad = function(result){
     page.onLoadXml();
};
hybridView.loadUrl(homeUrl);
