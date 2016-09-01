package com.efurture.glue.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.efurture.glue.ui.XmlView;
import com.efurture.glue.utils.ViewUtils;

import org.xml.sax.Attributes;

/**
 * Created by furture on 16/6/2.
 */
public class GWebView extends WebView{

    public GWebView(Context context) {
        super(context);
    }

    public GWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 初始化协议
     * */
    public void initViewAtts(Attributes attrs) {
        getSettings().setJavaScriptEnabled(true);
        setWebViewClient(new WebViewClient(){
            //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        final String url = attrs.getValue("url");
        if(url != null){
            post(new Runnable() {
                @Override
                public void run() {
                    String absUrl = url;
                    XmlView hybridView = ViewUtils.findParent(GWebView.this);
                    if(hybridView != null && hybridView.getResourceLoader() != null){
                        absUrl = hybridView.getResourceLoader().toAbsPageUrl(url).toString();
                    }
                    loadUrl(url);
                }
            });
        }
    }



}
