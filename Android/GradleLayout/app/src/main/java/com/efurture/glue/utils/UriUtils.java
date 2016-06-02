package com.efurture.glue.utils;

import android.net.Uri;

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
        if(!uri.startsWith("/")){
            return  Uri.parse(uri);
        }

        if(parent.toString().startsWith("/")){
            return  Uri.parse(uri);
        }

        return parent.buildUpon().path(uri).build();
    }

}
