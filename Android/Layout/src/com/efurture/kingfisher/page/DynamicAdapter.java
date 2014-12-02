package com.efurture.kingfisher.page;

import java.util.HashMap;
import java.util.Map;

import android.widget.BaseAdapter;

public abstract class DynamicAdapter extends BaseAdapter{

	private Map<String, Integer> typeMap = new HashMap<String, Integer>();
	private int maxType = -1;
	
	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	
	@Override
	public final int getItemViewType(int position) {
		String itemType = getItemType(position);
		Integer viewType = typeMap.get(itemType);
		if(viewType != null)
			return viewType;
		else{
			maxType++;
			typeMap.put(itemType, maxType);
			return maxType;
		}
	}
	


	public abstract String getItemType(int position);
}
