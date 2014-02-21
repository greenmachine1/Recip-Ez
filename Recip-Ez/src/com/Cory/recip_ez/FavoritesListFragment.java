package com.Cory.recip_ez;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fileManager.FavoritesJSONParsing;

public class FavoritesListFragment extends Fragment {
	
	FavoritesJSONParsing newFavoritesParsing;
	
	ArrayList<String> nameArrayList = new ArrayList<String>();
	
	HashMap<String, String> nameHashMap = new HashMap<String, String>();
	
	ListView listView;
	
	ArrayAdapter<String> adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view;
	
		view = inflater.inflate(R.layout.favorites_fragment, container, false);
		
		listView = (ListView)view.findViewById(R.id.favorites_list_view);

		newFavoritesParsing = new FavoritesJSONParsing(getActivity());
		
		nameHashMap = newFavoritesParsing.returnName();
		
		// -- putting the contents of my hashmap into an array list
		for(String name:nameHashMap.values()){
			nameArrayList.add(name);
		}
		
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nameArrayList);

		listView.setAdapter(adapter);
	
		return view; 
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// -- putting the contents of my hashmap into an array list
		for(String name:nameHashMap.values()){
			nameArrayList.add(name);
		}
		
		adapter.notifyDataSetChanged();
	}

	
	
	
}
