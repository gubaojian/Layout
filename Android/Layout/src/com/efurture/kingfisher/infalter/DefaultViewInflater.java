package com.efurture.kingfisher.infalter;

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
import android.os.Environment;
import android.text.TextUtils;

import com.efurture.kingfisher.GLog;
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
	public GLayout<?> inflate(String name, String downloadUrl) {
		GLayout<?>  view = null;
		File file = toXmlFile(name);
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
			XmlViewDownloader.downloadXmlView(mContext, downloadUrl, name, file);
		}
		int rawId = mContext.getResources().getIdentifier(name, "raw", mContext.getPackageName());
		if (rawId > 0) {
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


	/**
	 * hash functions copy from hashmap 
	 * */
	 static int hash(int h) {
	        // This function ensures that hashCodes that differ only by
	        // constant multiples at each bit position have a bounded
	        // number of collisions (approximately 8 at default load factor).
	        h ^= (h >>> 20) ^ (h >>> 12);
	        return h ^ (h >>> 7) ^ (h >>> 4);
	    }

	private File toXmlFile(String name){
		initIfNeed();
		return new File(mFileXmlPath.getAbsolutePath(), (hash(name.hashCode())&(16-1) )+ "/" + name);
	}
	
	private void initIfNeed(){
		if (mFileXmlPath != null) {
			if(!mFileXmlPath.canWrite()){
				mFileXmlPath = null;
			}
		}
		if (mFileXmlPath == null) {
			File parentPath = null ;
			if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
				try {
					parentPath = mContext.getExternalFilesDir(null);
				} catch (Exception e) {
					GLog.e("AndroidFileSystemInitDirError", e);
					parentPath = null;
				}
				if (parentPath != null && !parentPath.canWrite()) {
					parentPath = null;
				}
				
				if (parentPath == null) {
					parentPath = mContext.getExternalCacheDir();
					if (parentPath != null && !parentPath.canWrite()) {
						parentPath = null;
					}
				}
			}
			if (parentPath == null) {
				parentPath = mContext.getFilesDir();	
				if (parentPath != null && !parentPath.canWrite()) {
					parentPath = null;
				}
			}
			

			if (parentPath == null) {
				parentPath = mContext.getCacheDir();
				if (parentPath != null && !parentPath.canWrite()) {
					parentPath = null;
				}
			}
			
			if (parentPath == null) {
				return;
			}
			
			String dir = DIR;
			mFileXmlPath = new File(parentPath, dir);
		}
		
		if (!mFileXmlPath.exists()) {
			boolean success = mFileXmlPath.mkdirs();
			if(!success){
				mFileXmlPath.mkdirs();
			}
		}
	}
	
	private static final String DIR = "xml_views";
	private File mFileXmlPath;
	
}
