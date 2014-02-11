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
			R.drawable.ic_ingredients,
			R.drawable.ic_favorites_solid,
			R.drawable.ic_launcher
		};
		
		// place holder for titles
		String[] titles = {
				"first",
				"second",
				"third",
				"forth"
		};
		
		
		View view;
		
		// inflating my gridview fragment
		view = inflater.inflate(R.layout.grid_view_fragment_layout, container, false);
		
		GridView gridView = (GridView)view.findViewById(R.id.gridView1);
		
		// cycling through my titles and adding them to my GridViewAdapterDefinition
		for(int i = 0; i < titles.length; i++){
			
			GridViewAdapterDefinition item = new GridViewAdapterDefinition(imageIDs[i], titles[i]);
			
			// adding the item to my items ArrayList
			items.add(item);
		}
		
		
		// setting the gridview adapter and sending over the images
		// context, layout for each element, then the elements themselves
		gridView.setAdapter(new GridRecipeAdapter(getActivity(), R.layout.grid_view_item, items));
		
		return view;
		
	}

}
