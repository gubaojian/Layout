package com.efurture.glue.utils;

import android.net.Uri;

import com.efurture.glue.engine.XmlException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by furture on 16/6/1.
 */
public class UriUtils {




    private static  final Pattern SPLIT_PATTERN = Pattern.compile(";");

    public  static List<String> toList(String xmlUrls){
        ArrayList<String> uris = new ArrayList<String>(2);
        final String[] pages =  SPLIT_PATTERN.split(xmlUrls);
        for(String page : pages){
            uris.add(page.trim());
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
