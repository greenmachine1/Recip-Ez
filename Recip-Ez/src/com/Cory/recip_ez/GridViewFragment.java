/*
 * Developers name 	Cory Green
 * 
 * Project name		Recip-Ez
 * 
 * Date				Feb 11, 2014
 */
package com.Cory.recip_ez;


import java.util.ArrayList;
import java.util.HashMap;

import com.Cory.availableRecipes.AvailableRecipes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import fileManager.ParseJSON;

public class GridViewFragment extends Fragment{
	
	EditText userSearch;
	
	GridRecipeAdapter newGridRecipeAdapter;
	
	public final String BEGINNING_OF_URL = "https://api.pearson.com";

	// my items arrayList
	public ArrayList<GridViewAdapterDefinition> items = new ArrayList<GridViewAdapterDefinition>();
	
	public HashMap<String, String> imagesHashMap = new HashMap<String, String>();
	
	public ArrayList<String> urlArrayList = new ArrayList<String>();
	
	public ArrayList<String> nameOfRecipeArrayList = new ArrayList<String>();
	
	private BroadcastReceiver myReciever;

	
	// -- upon creation of the fragment
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

		newGridRecipeAdapter = new GridRecipeAdapter(getActivity(), R.layout.grid_view_item, items);

		View view;
		
		// -- inflating my gridview fragment
		view = inflater.inflate(R.layout.grid_view_fragment_layout, container, false);

		// -- section gets info from the PearsonAPIService and loads it into the 
		// -- grid array when it finishes loading all the data
		IntentFilter intentFilter = new IntentFilter("android.intent.action.MAIN");
		
		myReciever = new BroadcastReceiver(){

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				
				imagesHashMap.clear();
				
				boolean intentThing = intent.getBooleanExtra("DONE", false);
				Log.i("returned value", "" + intentThing);
				if(intentThing == true){
					
					ParseJSON newParseJson = new ParseJSON(context);
					
					imagesHashMap = newParseJson.returnImageUrl();

					// -- putting my hashmap into an arrayList
					for(String value:imagesHashMap.values()){
						urlArrayList.add(BEGINNING_OF_URL + value);
					}
					
					// -- putting the name from the images hashmap into 
					// -- an array list
					for(String name:imagesHashMap.keySet()){
						nameOfRecipeArrayList.add(name);
					}
					
					// -- cycling through my titles and adding them to my GridViewAdapterDefinition
					// -- only delivers 4 total
					for(int i = 0; i < imagesHashMap.size() - 6; i++){
						
						// -- loading the gridviewadapterdefinition with the image and title
						GridViewAdapterDefinition item = new GridViewAdapterDefinition(urlArrayList.get(i).toString() , nameOfRecipeArrayList.get(i).toString());
						
						// -- adding the item to my items ArrayList
						items.add(item);
					}
				}
				newGridRecipeAdapter.notifyDataSetChanged();
				
			}
			
		};
		
		// -- setting the reciever to be registered
		getActivity().registerReceiver(myReciever, intentFilter);


		
		
		
		
		// -- targetting the user search box
		userSearch = (EditText)view.findViewById(R.id.user_search_main);
		userSearch.setOnKeyListener(new OnKeyListener(){

			@Override
			public boolean onKey(View viewText, int keyCode, KeyEvent event) {
				
				if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
					
					String userEntered = userSearch.getText().toString();

					// -- starts the available recipes intent
					Intent availableRecipesIntent = new Intent(getActivity(), AvailableRecipes.class);
					
					// -- sending over the user input search
					availableRecipesIntent.putExtra("search", userEntered);
					
					startActivity(availableRecipesIntent);
					
					Log.i("user has pressed enter", userEntered);
					
					return true;
				}
				return false;
			}
		});
		
		GridView gridView = (GridView)view.findViewById(R.id.gridView1);

			
		// -- setting the gridview adapter and sending over the images
		// -- context, layout for each element, then the elements themselves
		gridView.setAdapter(newGridRecipeAdapter);
		
		return view;
		
	}
	



	


}
