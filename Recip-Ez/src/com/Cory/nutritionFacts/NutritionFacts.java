package com.Cory.nutritionFacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.Cory.recip_ez.R;
import com.Cory.service_package.EdamamService;

public class NutritionFacts extends Activity{
	
	String ingredientsString;

	   @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.nutrition_facts_layout);
	        
	        Bundle extras = getIntent().getExtras();
	        ingredientsString = extras.getString("ingredients");
	        
	        // -- start of my service
	        Intent edamamServiceIntent = new Intent(this, EdamamService.class);
	        edamamServiceIntent.putExtra("ingredients", ingredientsString);
	        
	        startService(edamamServiceIntent);

	   }
	
}
