package com.efurture.glue.utils;

import android.net.Uri;

import com.efurture.glue.engine.XmlException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by furture on 16/6/1.
 */
public class UriUtils {



    public  static List<Uri> toUris(String xmlUrls, Uri parent){
        ArrayList<Uri> uris = new ArrayList<Uri>(2);
        final String[] pages = xmlUrls.split(";");
        for(String page : pages){
            uris.add(toUri(page.trim(), parent));
        }
        return  uris;
    }

    public  static Uri toUri(String uri, Uri parent){
        if(parent == null){
            return  Uri.parse(uri);
        }

        try {
            URL url =  new URL(new URL(parent.toString()), uri);
            return  Uri.parse(url.toString());
        } catch (MalformedURLException e) {
            throw  new XmlException(e);
        }
    }

}
