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



public class GridRecipeAdapter extends ArrayAdapter<GridViewAdapterDefinition>{
	
	Context context;
	int layoutResourceId;
	List<GridViewAdapterDefinition> data = null;
	
	// constructor, takes in the context, the layoutResource
	// and the data
	public GridRecipeAdapter(Context context, int layoutResourceId,
			List<GridViewAdapterDefinition> data) {
		super(context, layoutResourceId, data);
		
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
		
	}
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		View row = convertView;
		GridRecipeAdapterHolder holder = null;
		
		
		if(row == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new GridRecipeAdapterHolder();
			
			holder.imgIcon = (ImageView)row.findViewById(R.id.recipe_image);
			holder.txtTitle = (TextView)row.findViewById(R.id.title_text);

			
			row.setTag(holder);
		}else{
			
			holder = (GridRecipeAdapterHolder)row.getTag();
			
		}
		
		GridViewAdapterDefinition gridRecipeAdapter = getItem(position);
		
		holder.txtTitle.setText(gridRecipeAdapter.recipeTitle);
		holder.imgIcon.setImageResource(gridRecipeAdapter.mainImage);

		return row;
		
	}
	
	static class GridRecipeAdapterHolder
	{
		ImageView imgIcon;
		TextView txtTitle;

		
	}

	

	
}