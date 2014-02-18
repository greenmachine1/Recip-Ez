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

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import fileManager.ParseJSON;

public class GridViewFragment extends Fragment{
	
	EditText userSearch;

	Context _context;
	
	public final String BEGINNING_OF_URL = "https://api.pearson.com";

	
	// my items arrayList
	public ArrayList<GridViewAdapterDefinition> items = new ArrayList<GridViewAdapterDefinition>();
	
	public HashMap<String, String> imagesHashMap = new HashMap<String, String>();
	
	public ArrayList<String> urlArrayList = new ArrayList<String>();

	
	// upon creation of the fragment
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		_context = getActivity();
		
		View view;
		
		ParseJSON newParseJson = new ParseJSON(_context);
		
		imagesHashMap = newParseJson.returnImageUrl();
		
		// -- putting my hashmap into an arrayList
		for(String value:imagesHashMap.values()){
			urlArrayList.add(BEGINNING_OF_URL + value);
		}


		
		
		// targetting the user search box
		userSearch = (EditText)getActivity().findViewById(R.id.user_search_main);
		
		
		// the listener for when the user chooses to initiate the search
		/*
		userSearch.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				boolean handled = false;
				if(actionId == EditorInfo.IME_ACTION_SEND){
					
					Log.i("user pushed search", "true");
					handled = true;
				}
				
				return handled;
			}
		});
		*/
		
		
		// inflating my gridview fragment
		view = inflater.inflate(R.layout.grid_view_fragment_layout, container, false);
		
		GridView gridView = (GridView)view.findViewById(R.id.gridView1);
		
		// cycling through my titles and adding them to my GridViewAdapterDefinition
		for(int i = 0; i < 4; i++){
			
			// -- loading the gridviewadapterdefinition with the image and title
			GridViewAdapterDefinition item = new GridViewAdapterDefinition(urlArrayList.get(i).toString() , newParseJson.returnTitles().get(i).toString());
			
			// adding the item to my items ArrayList
			items.add(item);
		}
		
		
		// setting the gridview adapter and sending over the images
		// context, layout for each element, then the elements themselves
		gridView.setAdapter(new GridRecipeAdapter(getActivity(), R.layout.grid_view_item, items));
		
		return view;
		
	}

}
