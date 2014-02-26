/*
 * Developers name 	Cory Green
 * 
 * Project name		Recip-Ez
 * 
 * Date				Feb 26, 2014
 */
package com.Cory.recip_ez;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.Cory.availableRecipes.AvailableRecipes;




public class Ingredients_fragment extends Fragment{
	
	Ingredients_adapter newIngredientsAdapter;
	
	Button goButton;
	
	// -- creating a new array list for my custom adapter
	public ArrayList<Ingredients_adapter_definition> ingredientsAndCheckIcon = new ArrayList<Ingredients_adapter_definition>();
	
	
	public ArrayList<String> finalArrayList = new ArrayList<String>();
	
	// -- ingredients
	String[] nameOfIngredients = {"Sugar", "Brown Sugar", "Flour", "Salt", "Pepper", "Baking Powder", 
							      "Cinnamon", "Vanilla", "Cocoa", "Vinegar", "Milk", "Corn Syrup", 
							      "Eggs", "Butter", "Oregano", "Basil", "Dill", "Garlic", "Cloves",
							      "Tomatoes", "Tuna", "Chicken", "Olive Oil", "Vegetable Oil", "Corn Oil"};
	
	
	public ArrayList<String> toggle = new ArrayList<String>();
	
	// -- my checkbox images
	int[] images = {R.drawable.ic_checkbox_unchecked, R.drawable.ic_checkbox_checked};
	
	
	// -- upon creation of the fragment
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		View view;
		
		// -- this is for toggling my image of the checkmark
		for(int i = 0; i < nameOfIngredients.length; i++){
			toggle.add(i, "false");
		}
		
		newIngredientsAdapter = new Ingredients_adapter(getActivity(), R.layout.ingredients_item, ingredientsAndCheckIcon);
	
		view = inflater.inflate(R.layout.ingredients_fragment, container, false);
		
		finalArrayList.clear();
		
		for(int i = 0; i < nameOfIngredients.length; i++){
			Ingredients_adapter_definition item = new Ingredients_adapter_definition(nameOfIngredients[i], R.drawable.ic_checkbox_unchecked);
			ingredientsAndCheckIcon.add(item);
		}
		
		newIngredientsAdapter.notifyDataSetChanged();
		
		ListView listView = (ListView)view.findViewById(R.id.ingredients_list);
		
		listView.setAdapter(newIngredientsAdapter);
		
		// -- the onclick listener for the rows
		// -- this toggles back and forth the check mark image
		// -- and also adds and takes away from the main list of
		// -- ingredients to search for
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id_item) {
				
				if(toggle.get(position).equals("false")){
					Ingredients_adapter_definition item = new Ingredients_adapter_definition(nameOfIngredients[position], R.drawable.ic_checkbox_checked);
					ingredientsAndCheckIcon.set(position, item);
					newIngredientsAdapter.notifyDataSetChanged();
					
					finalArrayList.add(nameOfIngredients[position]);
					toggle.set(position, "true");
					
				}else if(toggle.get(position).equals("true")){
					Ingredients_adapter_definition item = new Ingredients_adapter_definition(nameOfIngredients[position], R.drawable.ic_checkbox_unchecked);
					ingredientsAndCheckIcon.set(position, item);
					newIngredientsAdapter.notifyDataSetChanged();
					
					finalArrayList.remove(nameOfIngredients[position]);
					toggle.set(position, "false");
				}
				
			}
			
		});
		
		
		// -- the go button
		goButton = (Button)view.findViewById(R.id.button1);
		goButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				// -- if the array is empty
				if(finalArrayList.size() == 0){
					
					Toast toast = Toast.makeText(getActivity(), "Please enter more ingredients to search for.", Toast.LENGTH_LONG);
					toast.show();
					
				}else{
				
					String finalPassedInString = "";
					
					for(int i = 0; i < finalArrayList.size(); i++){
						
						String tempString = finalArrayList.get(i).toString();
						
						finalPassedInString = finalPassedInString + " " +tempString;
					}
					
					// -- replacing the whitespace with a '+'
					String tempFinalString = null;
					if(finalPassedInString.contains(" ")){
						tempFinalString = finalPassedInString.replace(" ", "+");
					}
						
					
					// -- starts the available recipes intent
					Intent availableRecipesIntent = new Intent(getActivity(), AvailableRecipes.class);
					
					
					// -- sending over the user input search
					// -- also of note, the first character has been removed
					// -- originally this is a '+' and the api doesnt understand 
					// -- it
					availableRecipesIntent.putExtra("search", tempFinalString.substring(1));
					
					startActivity(availableRecipesIntent);
					
				}
			}
		});
		
		return view;
	}
	

}
