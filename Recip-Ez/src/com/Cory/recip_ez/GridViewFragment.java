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
	
	

	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		// place holders for images
		Integer[] imageIDs = {
			R.drawable.main_logo,
			R.drawable.ic_share,
			R.drawable.ic_launcher,
			R.drawable.ic_ingredients_search
		};
		
		
		View view;
		
		view = inflater.inflate(R.layout.grid_view_fragment_layout, container, false);
		
		GridView gridView = (GridView)view.findViewById(R.id.gridView1);
		
		// setting the gridview adapter
		gridView.setAdapter(new GridRecipeAdapter(getActivity(), imageIDs));
		
		return view;
		
	}
	
	
	
	



	

}
