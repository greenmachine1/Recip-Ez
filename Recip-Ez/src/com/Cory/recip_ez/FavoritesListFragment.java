/*
 * Developers name 	Cory Green
 * 
 * Project name		Recip-Ez
 * 
 * Date				Feb 26, 2014
 */
package com.Cory.recip_ez;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import come.Cory.recipeDetails.RecipeDetails;

import fileManager.FavoritesJSONParsing;

public class FavoritesListFragment extends Fragment {
	
	
	
	ArrayList<String> nameArrayList = new ArrayList<String>();
	ArrayList<String> urlArrayList = new ArrayList<String>();
	ArrayList<String> directionsArrayList = new ArrayList<String>();
	ArrayList<String> ingredientsArrayList = new ArrayList<String>();
	
	HashMap<String, String> nameHashMap = new HashMap<String, String>();
	HashMap<String, String> urlHashMap = new HashMap<String, String>();
	HashMap<String, String> directionsHashMap = new HashMap<String, String>();
	HashMap<String, String> ingredientsHashMap = new HashMap<String, String>();

	ListView listView;
	
	Favorites_adapter newFavoritesAdapter;
	
	// -- custom array list
	public ArrayList<Favorites_adapter_definition> favoritesList = new ArrayList<Favorites_adapter_definition>();
	
	ArrayAdapter<String> adapter;
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view;
	
		view = inflater.inflate(R.layout.favorites_fragment, container, false);
		
		listView = (ListView)view.findViewById(R.id.favorites_list_view);
		
		// -- setting up the costom adapter
		newFavoritesAdapter = new Favorites_adapter(getActivity(), R.layout.favorites_list_item, favoritesList);
		
		nameHashMap.clear();
		urlHashMap.clear();
		directionsHashMap.clear();
		ingredientsHashMap.clear();
		nameArrayList.clear();
		urlArrayList.clear();
		directionsArrayList.clear();
		ingredientsArrayList.clear();

		File file = getActivity().getFileStreamPath("favoritesJSON.txt");
		if(file.exists() == true){
		
			FavoritesJSONParsing newFavoritesParsing = new FavoritesJSONParsing(getActivity());
			if(newFavoritesParsing.loadJSON() == true){
				
				nameHashMap = newFavoritesParsing.returnName();
				
				urlHashMap = newFavoritesParsing.returnUrl();
				
				ingredientsHashMap = newFavoritesParsing.returnIngredients();
				
				directionsHashMap = newFavoritesParsing.returnDirections();
				
				// -- putting the contents into the nameArrayList
				for(String name:nameHashMap.values()){
					nameArrayList.add(name);
				}
				
				// -- putting the contents into the urlArrayList
				for(String urlValue:urlHashMap.values()){
					urlArrayList.add(urlValue);
				}
				
				// -- putting the contents into the ingredientsArrayList
				for(String ingredientsValue:ingredientsHashMap.values()){
					ingredientsArrayList.add(ingredientsValue);
				}
				
				// -- putting the contents into the directionsArrayList
				for(String directionsValue:directionsHashMap.values()){
					directionsArrayList.add(directionsValue);
				}

			}
		}
		
		
		// -- setting up the costom adapter
		//newFavoritesAdapter = new Favorites_adapter(getActivity(), R.layout.favorites_list_item, favoritesList);
		favoritesList.clear();
		
		for(int i = 0; i < nameArrayList.size(); i++){
			Favorites_adapter_definition item = new Favorites_adapter_definition(nameArrayList.get(i).toString(), R.drawable.ic_blue_star);
			favoritesList.add(item);
			newFavoritesAdapter.notifyDataSetChanged();
		}
		
		//newFavoritesAdapter.notifyDataSetChanged();
		
		
		listView.setAdapter(newFavoritesAdapter);
		
	
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				
				Log.i("action mode", "engaged");
				
				// -- start of the recipeDetails activity
				Intent recipeDetailsIntent = new Intent(getActivity(), RecipeDetails.class);
				// -- putting values into the intents extras
				recipeDetailsIntent.putExtra("title", nameArrayList.get(position));
				recipeDetailsIntent.putExtra("directions", directionsArrayList.get(position));
				recipeDetailsIntent.putExtra("url", urlArrayList.get(position));
				recipeDetailsIntent.putExtra("ingredients", ingredientsArrayList.get(position));
				recipeDetailsIntent.putExtra("comingFromFavorites", true);
				recipeDetailsIntent.putExtra("position", position);
				
				startActivity(recipeDetailsIntent);
				
			}
			
		});
		
		return view; 
	}

	
	// -- on resume method
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		nameHashMap.clear();
		urlHashMap.clear();
		directionsHashMap.clear();
		ingredientsHashMap.clear();
		nameArrayList.clear();
		urlArrayList.clear();
		directionsArrayList.clear();
		ingredientsArrayList.clear();

		File file = getActivity().getFileStreamPath("favoritesJSON.txt");
		
		if(file.exists() == true){
			FavoritesJSONParsing newFavoritesParsing = new FavoritesJSONParsing(getActivity());
			
			if(newFavoritesParsing.loadJSON() == true){
				nameHashMap = newFavoritesParsing.returnName();
				
				urlHashMap = newFavoritesParsing.returnUrl();
				
				ingredientsHashMap = newFavoritesParsing.returnIngredients();
				
				directionsHashMap = newFavoritesParsing.returnDirections();
				
				// -- putting the contents into the nameArrayList
				for(String name:nameHashMap.values()){
					nameArrayList.add(name);
				}
				
				// -- putting the contents into the urlArrayList
				for(String urlValue:urlHashMap.values()){
					urlArrayList.add(urlValue);
				}
				
				// -- putting the contents into the ingredientsArrayList
				for(String ingredientsValue:ingredientsHashMap.values()){
					ingredientsArrayList.add(ingredientsValue);
				}
				
				// -- putting the contents into the directionsArrayList
				for(String directionsValue:directionsHashMap.values()){
					directionsArrayList.add(directionsValue);
				}
			}
		}
		
		favoritesList.clear();
		for(int i = 0; i < nameArrayList.size(); i++){
			Favorites_adapter_definition item = new Favorites_adapter_definition(nameArrayList.get(i).toString(), R.drawable.ic_blue_star);
			favoritesList.add(item);
			//newFavoritesAdapter.notifyDataSetChanged();
		}
		newFavoritesAdapter.notifyDataSetChanged();
	}
	
}
