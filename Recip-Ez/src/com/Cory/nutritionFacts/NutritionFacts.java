package com.Cory.nutritionFacts;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.Cory.recip_ez.R;
import com.Cory.service_package.EdamamService;

import fileManager.FileManager;

public class NutritionFacts extends Activity{
	
	String ingredientsString;
	String titleString;
	
	public final String NAME_OF_FILE = "nutrition.txt";
	
	FileManager fileManager;
	
	private BroadcastReceiver myReciever;
	
	IntentFilter intentFilter;

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
	        
	        startService(edamamServiceIntent);
	        
	        intentFilter = new IntentFilter("android.intent.action.MAIN");
	        
	        myReciever = new BroadcastReceiver(){

				@Override
				public void onReceive(Context context, Intent intent) {
					// TODO Auto-generated method stub
					
					// -- getting the call back from the edamam service 
					boolean intentThing = intent.getBooleanExtra("DONE", false);
					if(intentThing == true){
						
						File file = getApplication().getFileStreamPath(NAME_OF_FILE);
						if(file.exists()){
						
							String openJSONFile = fileManager.readNewFile(getApplication(), NAME_OF_FILE);
						
						}
						
					}
					
				}
	        
	        };
	        
	     // -- setting the reciever to be registered
		this.registerReceiver(myReciever, intentFilter);

	   }
	   
	   
	   @Override
	   public void onResume() {
		   // TODO Auto-generated method stub
		   super.onResume();
			
		   // -- setting the reciever to be registered
		   this.registerReceiver(myReciever, intentFilter);
	   }
		
		@Override
		public void onStop() {
			// TODO Auto-generated method stub
			super.onStop();

			// -- unregistering the reciever
			this.unregisterReceiver(myReciever);
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

		   
		   // -- start of saving and converting title and ingredients
		   // -- to json format
		   saveToJSONFormat(title, ingredientsArray);
		   
	   }
	   
	   
	   
	   
	   // -- saves the data to json format
	   public void saveToJSONFormat(String title, ArrayList<String> ingredientsArray){
		   
		   fileManager = new FileManager();
		   
		   JSONObject mainObject = new JSONObject();
		   JSONObject ingredientsArrayObject = new JSONObject();
		   try {
			
			
			   JSONArray ingredientsJSONArray = new JSONArray();
			   for(int i = 0; i < ingredientsArray.size(); i++){
				
				   String temp = new String();
				   temp = ingredientsArray.get(i).toString();
				
				   ingredientsJSONArray.put(temp);
			   }
			   
			mainObject.put("ingr", ingredientsJSONArray);
			
			mainObject.put("title", title);

			// -- finally savng the file in the proper format
			fileManager.writeStringFile(getApplication(), NAME_OF_FILE, mainObject.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("json error", e.getMessage().toString());
		}
		   
		   
	   }
	   
	   
	   
	   
	   
	   
	   
	   // -- removal of the brackets
	   public String removeFatFromString(String passedInString){
		   
		   
		   String stringWithoutBracket = passedInString.replace("[", "");
		   String finalStringWithoutBrackets = stringWithoutBracket.replace("]", "");
		   
		  
		return finalStringWithoutBrackets;
		   
	   }
	   
	   
	   
	   
	
}
