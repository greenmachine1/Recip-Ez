package com.Cory.availableRecipes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.Cory.recip_ez.R;

public class AvailableRecipesFragment extends Fragment {
	
	ListView recipeListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view;
		
		String[] testMyListView = {"First", "Second", "Third"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, testMyListView);
		
		view = inflater.inflate(R.layout.available_recipe_fragment, container, false);
		
		recipeListView = (ListView)view.findViewById(R.id.recipe_list_view);
		
		
		
		recipeListView.setAdapter(adapter);
		
		return view;
		
		
		
		
	}

}
