package com.Cory.recip_ez;

import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;



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

	

	
}