package com.efurture.glue.engine;

import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

import com.efurture.glue.GLog;
import com.efurture.glue.utils.ViewUtils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

class DefaultViewInflater extends ViewInflater {

	
	
	private Context mContext;
	private View  viewNode = null;
	private View  rootView = null;
	
	public DefaultViewInflater(Context context){
		mContext = context;
	}
	

	public View inflate(InputStream inputStream, final ViewGroup parent) throws ParserConfigurationException, SAXException, IOException {
		if (Looper.getMainLooper().getThread() != Thread.currentThread()){
			throw new IllegalStateException("Method call should happen from the main thread.");
		}
		long start = System.currentTimeMillis();
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
	        SAXParser parser = factory.newSAXParser();
	        parser.parse(inputStream, new DefaultHandler(){

				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
						if (rootView == null) {
							String screenUnit = attributes.getValue("screenUnit");
							if(screenUnit != null){
								setScreenUnit(Float.parseFloat(screenUnit));
							}
						}
					    View xmlView;
					    if(rootView == null && parent != null && "merge".equals(localName)){ //merge 标签
							xmlView = parent;
						}else{
							xmlView = ViewFactory.shareFactory().createView(mContext, qName, attributes);
						}
						if (viewNode != null) {
							((ViewGroup)viewNode).addView(xmlView);
						}
						if (rootView == null) {
							rootView = xmlView;
							if(parent != rootView){
								parent.addView(xmlView);
							}
						}
						ViewUtils.initAttrs(xmlView, attributes, DefaultViewInflater.this);
						viewNode = xmlView;
				}

				@Override
				public void endElement(String uri, String localName, String qName)throws SAXException {
					if (viewNode.getParent() != null) {
						viewNode =  (View) viewNode.getParent();
					}
				}

				@Override
				public void error(SAXParseException e) throws SAXException {
					super.error(e);
				}

				@Override
				public void fatalError(SAXParseException e) throws SAXException {
					super.fatalError(e);
				}
	        	
	        });
			View xmlView = rootView;
	        viewNode = null;
	        rootView = null;
			return xmlView;
		}finally{
			if(GLog.logEnable){
				GLog.i(getUri() + " inflate used " + (System.currentTimeMillis() - start) + "ms");
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					GLog.e("Inflater Close InputStream Error", e);
				}
			}
		}
	}
}
