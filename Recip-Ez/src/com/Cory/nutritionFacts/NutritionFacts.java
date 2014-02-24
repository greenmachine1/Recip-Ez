package com.Cory.nutritionFacts;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.Cory.recip_ez.R;
import com.Cory.service_package.EdamamService;

public class NutritionFacts extends Activity{
	
	String ingredientsString;
	String titleString;

	   @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.nutrition_facts_layout);
	        
	        Bundle extras = getIntent().getExtras();
	        titleString = extras.getString("title");
	        ingredientsString = extras.getString("ingredients");
	        
	        // -- converts the ingredients string into an array 
	        // -- then saves it to json format
	        convertInfo(titleString, ingredientsString);
	        
	        // -- start of my service
	        Intent edamamServiceIntent = new Intent(this, EdamamService.class);
	        edamamServiceIntent.putExtra("ingredients", ingredientsString);
	        
	        startService(edamamServiceIntent);

	   }
	   
	   
	   
	   // -- saving the title and ingredients to json so my 
	   // -- service call can understand it
	   // -- ingrdients needs to be an array
	   public void convertInfo(String title, String ingredients){
		   
		   // -- saving the string after it has had the [] removed
		   String finalPassedInString = removeFatFromString(ingredients);
		   
		   ArrayList<String> ingredientsArray = new ArrayList<String>();
		   
		   // -- splitting the string by ","
		   String[] splitStrings = finalPassedInString.split(",");
		   
		   for(int i = 0; i < splitStrings.length; i++){
			   Log.i("string split", splitStrings[i].toString());
			   ingredientsArray.add(splitStrings[i].toString());
		   }
		   
		   Log.i("ingredients array", ingredientsArray.get(0).toString());

		   
		   // -- start of saving and converting title and ingredients
		   // -- to json format
		   saveToJSONFormat(title, ingredientsArray);
		   
	   }
	   
	   // -- saves the data to json format
	   public void saveToJSONFormat(String title, ArrayList<String> ingredientsArray){
		   
		   JSONObject mainObject = new JSONObject();
		   
		   
		   
	   }
	   
	   
	   
	   
	   
	   // -- removal of the brackets
	   public String removeFatFromString(String passedInString){
		   
		   String stringWithoutBracket = passedInString.replace("[", "");
		   String finalStringWithoutBrackets = stringWithoutBracket.replace("]", "");
		   
		return finalStringWithoutBrackets;
		   
	   }
	   
	   
	   
	   
	
}
