package com.Cory.recip_ez;

import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Favorites_adapter extends ArrayAdapter<Favorites_adapter_definition>{

	Context context;
	int layoutId;
	List<Favorites_adapter_definition> data = null;
	
	
	public Favorites_adapter(Context context, int resource,
			List<Favorites_adapter_definition> data) {
		super(context, resource, data);
		
		this.data = data;
		this.context = context;
		this.layoutId = resource;
		
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = convertView;
		FavoritesAdapterHolder holder = null;
		if(row == null){
			
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutId, parent, false);
			
			holder = new FavoritesAdapterHolder();
			holder.imgIcon = (ImageView)row.findViewById(R.id.favorites_image);
			holder.txtTitle = (TextView)row.findViewById(R.id.favorites_text);
			
			row.setTag(holder);
			
		}else{
			
			holder = (FavoritesAdapterHolder)row.getTag();
		}
		Favorites_adapter_definition ingredients_adapter = getItem(position);
		
		holder.txtTitle.setText(ingredients_adapter.mainTextView);
		holder.imgIcon.setImageResource(ingredients_adapter.checkIcon);

		return row;
	}
	
	static class FavoritesAdapterHolder{
		ImageView imgIcon;
		TextView txtTitle;
	}
	
	

}
