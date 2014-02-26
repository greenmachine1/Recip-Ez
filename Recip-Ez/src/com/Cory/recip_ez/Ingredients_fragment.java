package com.Cory.recip_ez;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Ingredients_fragment extends Fragment{
	
	String[] nameOfIngredients = {"Sugar", "Brown Sugar", "Flour", "Salt", "Pepper", "Baking Powder", 
							      "Cinnamon", "Vanilla", "Cocoa", "Vinegar", "Milk", "Corn Syrup", 
							      "Eggs", "Butter", "Oregano", "Basil", "Dill", "Garlic", "Cloves",
							      "Tomatoes", "Tuna", "Chicken", "Olive Oil", "Vegetable Oil", "Corn Oil"};
	
	
	
	// -- upon creation of the fragment
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		View view;
	
		view = inflater.inflate(R.layout.ingredients_fragment, container, false);
	
		return view;
	}
	

}
