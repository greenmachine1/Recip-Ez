package com.Cory.recip_ez;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FavoritesListFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view;
		
		view = inflater.inflate(R.layout.favorites_fragment, container, false);
		
		return view; 
	}

	
	
	
}
