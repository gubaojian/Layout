package com.efurture.kingfisher.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.text.TextUtils;

import com.efurture.kingfisher.ViewFactory;
import com.efurture.kingfisher.ViewInflater;
import com.efurture.kingfisher.view.GLayout;

public class DefaultViewInflater extends ViewInflater {

	
	
	private Context mContext;
	private GLayout<?>  viewNode = null;
	private GLayout<?>  rootView = null;
	
	public DefaultViewInflater(Context context){
		mContext = context;
	}
	
	@Override
	public GLayout<?> inflate(String name, int version, String downloadUrl) {
		GLayout<?>  view = null;
		String fileName = version + "/" + name;
		File file = new File(GSystem.getFileSystem().getPath(), fileName);
		if (file.exists()) {
			try {
	           view  = inflate(new FileInputStream(file));
			} catch (Exception e) {
				GLog.e("Infalte Error, name" + name, e);
			}
			if (view != null) {
				return view;
			}
		}
		if (!TextUtils.isEmpty(downloadUrl)) {
			FileDownloader.downloadTemplate(mContext, downloadUrl, name, version);
		}
		int rawId = mContext.getResources().getIdentifier(name, "raw", mContext.getPackageName());
		if (rawId >0) {
			view = inflate(rawId);
		}
		return view;
	}



	@Override
	public GLayout<?> inflate(int rawId) {
		InputStream inputStream = mContext.getResources().openRawResource(rawId);
		return inflate(inputStream);
	}
	
	public GLayout<?> inflate(InputStream inputStream){
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
	        SAXParser parser = factory.newSAXParser();
	        parser.parse(inputStream, new DefaultHandler(){

				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					try {
						GLayout<?> xmlView = ViewFactory.shareFactory().createView(mContext, qName, attributes);
						if (viewNode != null) {
							viewNode.addView(xmlView);
						}
						if (rootView == null) {
							rootView = xmlView;
						}
						viewNode = xmlView;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}

				@Override
				public void endElement(String uri, String localName, String qName)throws SAXException {
					if (viewNode.getParent() != null) {
						viewNode = (GLayout<?>) viewNode.getParent();
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
	        GLayout<?> xmlView = rootView;
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
