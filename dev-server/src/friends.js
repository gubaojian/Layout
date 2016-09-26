importApi("ui");
importApi("nav");
importClass("com.efurture.glue.ui.XmlViewLoadListener")
importClass("android.widget.BaseAdapter");
importClass("com.efurture.gule.hybrid.adapter.StickyRecycleAdapter");
importClass("com.efurture.gule.hybrid.adapter.RecycleAdapter");
importClass("android.widget.PopupWindow");
importClass("android.view.View.OnClickListener");
importClass("com.efurture.gule.hybrid.ui.HPopupWindow");

var homeUrl = "./friends.xml";
var lifeCommunityItemXml = require('raw!./friends/lifeCommunityItem.xml');
var normalCellTopXml = require('raw!./friends/normalCellTop.xml');
var normalCellMiddleXml = require('raw!./friends/normalCellMiddle.xml');
var normalCellBottomXml = require('raw!./friends/normalCellBottom.xml');

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
            if(viewType == 'LifeCommunityItem'){
                view = ui.fromXml(lifeCommunityItemXml, parent, false);
            }else if(viewType == 'NormalCellTop'){
                view = ui.fromXml(normalCellTopXml, parent, false);
            }else if(viewType == 'NormalCellMiddle'){
                view = ui.fromXml(normalCellMiddleXml, parent, false);
            }else if(viewType == 'NormalCellBottom'){
                view = ui.fromXml(normalCellBottomXml, parent, false);
            }else{
                view = ui.fromXml(lifeCommunityItemXml, parent, false);
            }
            print("getView js " + viewType );
            return view;
        },
        onBindView : function(view, position){
        },
        getItemViewType : function(position){
            if(position == 0){
              return 'LifeCommunityItem';
            }

            if(position == 1){
              return 'NormalCellTop';
            }

            if(position == 29){
              return 'NormalCellBottom';
            }
            
            return 'NormalCellMiddle';
        }
      };
      recycleView = hybridView.findViewWithTag("recycleView");
      recycleView.setAdapter(new RecycleAdapter(jsAdapter));

      _self.bindTabEvent('friends');

    }
};
hybridView.onLoad = function(result){
     page.onLoadXml();
};
hybridView.loadUrl(homeUrl);
