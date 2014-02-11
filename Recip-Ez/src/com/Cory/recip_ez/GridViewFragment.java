package com.Cory.recip_ez;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridViewFragment extends Fragment{
	
	// my items arrayList
	public ArrayList<GridViewAdapterDefinition> items = new ArrayList<GridViewAdapterDefinition>();
	
	// upon creation of the fragment
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		
		// place holders for images
		Integer[] imageIDs = {
			R.drawable.main_logo,
			R.drawable.main_logo,
			R.drawable.main_logo,
			R.drawable.main_logo
		};
		
		// place holder for titles
		String[] titles = {
				"first",
				"second",
				"third",
				"forth"
		};
		
		
		View view;
		
		view = inflater.inflate(R.layout.grid_view_fragment_layout, container, false);
		
		GridView gridView = (GridView)view.findViewById(R.id.gridView1);
		
		for(int i = 0; i < titles.length; i++){
			
			GridViewAdapterDefinition item = new GridViewAdapterDefinition(imageIDs[i], titles[i]);
			
			// adding that to items
			items.add(item);
		}
		
		
		// setting the gridview adapter and sending over the images
		gridView.setAdapter(new GridRecipeAdapter(getActivity(), R.layout.grid_view_item,items));
		
		return view;
		
	}

}
