/*
 * Developers name 	Cory Green
 * 
 * Project name		Recip-Ez
 * 
 * Date				Feb 26, 2014
 */
package com.Cory.recip_ez;

import java.util.List;

import com.loopj.android.image.SmartImageView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// -- my custom adapter
public class Ingredients_adapter extends ArrayAdapter<Ingredients_adapter_definition> {
	
	// -- my context, layout id, and the data
	Context context;
	int layoutId;
	List<Ingredients_adapter_definition> data = null;

	// -- constructor
	public Ingredients_adapter(Context context, int resourceId,
			List<Ingredients_adapter_definition> data) {
		super(context, resourceId, data);
		
		this.data = data;
		this.context = context;
		this.layoutId = resourceId;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = convertView;
		IngredientsAdapterHolder holder = null;
		
		if(row == null){
			
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutId, parent, false);
			
			holder = new IngredientsAdapterHolder();
			holder.imgIcon = (ImageView)row.findViewById(R.id.check_icon);
			holder.txtTitle = (TextView)row.findViewById(R.id.ingredient_title);
			
			row.setTag(holder);
			
		}else{
			
			holder = (IngredientsAdapterHolder)row.getTag();
		}
		
		Ingredients_adapter_definition ingredients_adapter = getItem(position);
		
		holder.txtTitle.setText(ingredients_adapter.mainTextView);
		holder.imgIcon.setImageResource(ingredients_adapter.checkIcon);

		return row;

	}


	static class IngredientsAdapterHolder{
		ImageView imgIcon;
		TextView txtTitle;
	}
	
	
	

}
