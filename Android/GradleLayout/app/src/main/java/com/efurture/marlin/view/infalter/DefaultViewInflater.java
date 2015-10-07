package com.efurture.marlin.view.infalter;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.os.Looper;

import com.efurture.marlin.GLog;
import com.efurture.marlin.view.element.GView;
import com.efurture.marlin.view.element.XmlView;
import com.efurture.marlin.view.engine.ViewFactory;
import com.efurture.marlin.view.engine.ViewInflater;

public class DefaultViewInflater extends ViewInflater {

	
	
	private Context mContext;
	private XmlView<?>  viewNode = null;
	private XmlView<?>  rootView = null;
	
	public DefaultViewInflater(Context context){
		mContext = context;
	}
	

	public XmlView<?> inflate(InputStream inputStream){
		if (Looper.getMainLooper().getThread() != Thread.currentThread()){
			throw new IllegalStateException("Method call should happen from the main thread.");
		}
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
	        SAXParser parser = factory.newSAXParser();
	        parser.parse(inputStream, new DefaultHandler(){

				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					try {
						XmlView<?> xmlView = ViewFactory.shareFactory().createView(mContext, qName, attributes);
						if (viewNode != null) {
							viewNode.addView(xmlView);
						}
						if (rootView == null) {
							rootView = xmlView;
						}
						viewNode = xmlView;
					} catch (Exception e) {
						GLog.e("inflate Parse Xml Error", e);
					}
					
				}

				@Override
				public void endElement(String uri, String localName, String qName)throws SAXException {
					if (viewNode.getParent() != null) {
						viewNode = (GView<?>) viewNode.getParent();
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
			XmlView<?> xmlView = rootView;
	        viewNode = null;
	        rootView = null;
			return xmlView;
		} catch (Exception e) {
			GLog.e("inflate InputStream Error", e);
			return null;
		}finally{
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
