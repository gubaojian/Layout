importApi("ui");
importApi("nav");
importClass("com.efurture.glue.ui.XmlViewLoadListener")
importClass("android.widget.BaseAdapter");
importClass("com.efurture.gule.hybrid.adapter.MultiTypeAdapter");
importClass("android.widget.PopupWindow");
importClass("android.view.View.OnClickListener");
importClass("com.efurture.gule.hybrid.ui.HPopupWindow");

var homeUrl = "./alipay.xml";
var gridItemXml = require('raw!./home/gridItem.xml');
var listViewGridItemXml = require('raw!./home/listViewGridItem.xml');
var listViewViewPagerItemXml = require('raw!./home/listViewViewPagerItem.xml');
var addPopupMenuXml = require('raw!./home/addPopupMenu.xml');

var listView; //listview

var popupWindow; //下拉菜单
var tabName = 'home';

var page = {
    createTopGridView : function(){
      var gridView = ui.fromXml(listViewGridItemXml, null);
      gridView.setAdapter(new BaseAdapter({
        getCount : function() {
          return 8;
        },
        getView : function(position,  convertView, parent){
            if(convertView == null){
                convertView = ui.fromXml(gridItemXml, null);
            }
            return convertView;
        }
      }));
      return gridView;
    },
    createMiddleBannerView : function(){
      var listViewViewPagerItem = ui.fromXml(listViewViewPagerItemXml, null);
      return listViewViewPagerItem;
    },
    createBottomGridView : function(){
      var gridView = ui.fromXml(listViewGridItemXml, null);
      gridView.setAdapter(new BaseAdapter({
        getCount : function() {
          return 24;
        },
        getView : function(position,  convertView, parent){
            if(convertView == null){
                convertView = ui.fromXml(gridItemXml, null);
            }
            return convertView;
        }
      }));
      return gridView;
    },
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
    onLoadHomeXml : function(){
      var _self = this;


      //初始化listview
      var jsAdapter = {
        getCount : function() {
          return 3;
        },
        getView : function(position,  convertView, parent){
            var type = jsAdapter.getItemViewType(position);
            if(convertView == null){
               if(type == 'MiddleBanner'){
                  convertView = _self.createMiddleBannerView();
               }else if(type == 'BottomBanner'){
                  convertView = _self.createBottomGridView();
               }else{
                  convertView = _self.createTopGridView();
               }
            }
            print("getView js " + type);
            return convertView;
        },
        getItemViewType : function(position){
            if(position == 0){
               return 'TopGrid';
            }
            if(position == 1){
               return 'MiddleBanner';
            }
            if(position == 2){
               return 'BottomBanner';
            }
        },
        getViewTypeCount : function(){
          return 3
        }
      };
      listView = hybridView.findViewWithTag("homeListView");
      listView.setAdapter(new MultiTypeAdapter(jsAdapter));

       //绑定按钮事件
       var addFriendView = ui.find("top_add_friend_logo");
       addFriendView.setOnClickListener(new OnClickListener(function () {
           if(popupWindow){
                popupWindow.dismiss();
                popupWindow = null;
           }
           popupWindow = new HPopupWindow(activity, addPopupMenuXml);
           popupWindow.showAsDropDown(addFriendView);
       }));

       //tab 事件
       _self.bindTabEvent(tabName);




    }
};
hybridView.onLoad = function(result){
     page.onLoadHomeXml();
};
hybridView.loadUrl(homeUrl);
