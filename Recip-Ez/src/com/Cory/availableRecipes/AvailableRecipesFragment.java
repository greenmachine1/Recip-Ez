package com.Cory.availableRecipes;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.Cory.recip_ez.R;
import come.Cory.recipeDetails.RecipeDetails;

import fileManager.ParseJSON;

public class AvailableRecipesFragment extends Fragment {
	
	public final boolean CAME_FROM_MAIN = false;
	
	HashMap<String, String> directionsHashMap = new HashMap<String, String>();
	
	public ArrayList<String> nameOfRecipeArrayList = new ArrayList<String>();
	public ArrayList<String> directionsOfRecipeArrayList = new ArrayList<String>();
	
	ArrayAdapter<String> adapter;
	
	private BroadcastReceiver myReciever;
	
	IntentFilter intentFilter;
	
	ListView recipeListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view;
		
		// -- inflating the view
		view = inflater.inflate(R.layout.available_recipe_fragment, container, false);
		
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nameOfRecipeArrayList);
		
		// -- setting up the intent filter
		intentFilter = new IntentFilter("android.intent.action.MAIN");
		

		
		nameOfRecipeArrayList.clear();
		directionsOfRecipeArrayList.clear();
		
		myReciever = new BroadcastReceiver(){

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				
				
				boolean intentThing = intent.getBooleanExtra("DONE", false);
				Log.i("returned value", "boolean " + intentThing);
				if(intentThing == true){
					
					nameOfRecipeArrayList.clear();
		
					// -- starting the parseJSON class which takes in the context
					// -- then chooses which file to use, in this case, its the 
					// -- search file
					ParseJSON newParseJSON = new ParseJSON(context, CAME_FROM_MAIN);
					directionsHashMap = newParseJSON.returnDirectionData();
					
					// -- cycling through my directions hashmap 
					// -- and picking out only the names of the 
					// -- recipes
					for(String name:directionsHashMap.keySet()){
						nameOfRecipeArrayList.add(name);
					}
					
					// -- doing the same for the actual directions
					for(String name:directionsHashMap.values()){
						directionsOfRecipeArrayList.add(name);
					}
					
					// -- setting the contents of the array adapter
					//adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nameOfRecipeArrayList);
				}
				adapter.notifyDataSetChanged();
			}
		};
		
		// -- setting the reciever to be registered
		getActivity().registerReceiver(myReciever, intentFilter);
		
		recipeListView = (ListView)view.findViewById(R.id.recipe_list_view);
		
		recipeListView.setAdapter(adapter);
		
		recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				Log.i("item number", nameOfRecipeArrayList.get(position) + " was selected");
				
				Intent recipeDetailsIntent = new Intent(getActivity(), RecipeDetails.class);
				
				// -- putting values into the intents extras
				recipeDetailsIntent.putExtra("title", nameOfRecipeArrayList.get(position));
				recipeDetailsIntent.putExtra("directions", directionsOfRecipeArrayList.get(position));
				startActivity(recipeDetailsIntent);
			}
		});
		
		return view;

	}
	
	
	

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// -- setting the reciever to be registered
		getActivity().registerReceiver(myReciever, intentFilter);
	}




	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		// -- unregister the reciever
		getActivity().unregisterReceiver(myReciever);
	}
	
	

}
