package com.Cory.recip_ez;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GridViewFragment extends Fragment{
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		View view;
		
		view = inflater.inflate(R.layout.grid_view_fragment_layout, container, false);

		return view;
		
	}


	

}
