package com.Cory.availableRecipes;

import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.Cory.recip_ez.R;

import fileManager.ParseJSON;

public class AvailableRecipesFragment extends Fragment {
	
	public final boolean CAME_FROM_MAIN = false;
	
	HashMap<String, String> directionsHashMap = new HashMap<String, String>();
	
	public ArrayList<String> nameOfRecipeArrayList = new ArrayList<String>();
	
	ArrayAdapter<String> adapter;
	
	private BroadcastReceiver myReciever;
	
	ListView recipeListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view;
		
		// -- inflating the view
		view = inflater.inflate(R.layout.available_recipe_fragment, container, false);
		
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nameOfRecipeArrayList);
		
		// -- setting up the intent filter
		IntentFilter intentFilter = new IntentFilter("android.intent.action.MAIN");
		
		nameOfRecipeArrayList.clear();

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
		
		return view;

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		// -- unregister the reciever
		getActivity().unregisterReceiver(myReciever);
	}
	
	

}
