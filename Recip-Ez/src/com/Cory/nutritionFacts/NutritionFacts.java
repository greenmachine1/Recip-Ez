package com.Cory.nutritionFacts;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
	
	TextView calories;
	TextView fat;
	TextView saturated_fat;
	TextView carbs;
	TextView protein;
	TextView sodium;
	TextView fiber;
	TextView cholesterol;

	   @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.nutrition_facts_layout);
	        
	        
	        calories = (TextView)findViewById(R.id.calories_text_field);
	        fat = (TextView)findViewById(R.id.fat_text_field);
	        saturated_fat = (TextView)findViewById(R.id.saturated_fat_text_field);
	        carbs = (TextView)findViewById(R.id.carbohydrates_text_field);
	        protein = (TextView)findViewById(R.id.protein_text_field);
	        sodium = (TextView)findViewById(R.id.sodium_text_field);
	        fiber = (TextView)findViewById(R.id.fiber_text_field);
	        cholesterol = (TextView)findViewById(R.id.cholesterol_text_field);
	        
	        
	        
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
						
						File file = getApplication().getFileStreamPath("edemam.txt");
						if(file.exists()){
						
							String openJSONFile = fileManager.readNewFile(getApplication(), "edemam.txt");
							parseJSON(openJSONFile);
							
						}
						
					}
					
				}
	        
	        };
	        
	     // -- setting the reciever to be registered
		this.registerReceiver(myReciever, intentFilter);

	   }
	   
	   
	   
	   // -- the actual parsing of the json data
	   public void parseJSON(String passedInString){
		   
		   try {
			   
			   JSONObject mainJSONOBject = new JSONObject(passedInString);
			   
			   String firstElement = mainJSONOBject.names().toString();
			   
			   // -- signifies that there is no
			   // -- data to display
			   if(firstElement.contains("message")){
				   
				   Log.i("error no data", "no data for this recipe");
				   
				   Toast toast = Toast.makeText(getApplication(), "No Nutritional Data found", Toast.LENGTH_LONG);
					
				   toast.show();
				   
				   finish();
				   
			   // -- the recipe data recieved is processable	   
			   }else{
				   
				   // -- getting my yield and the total calories
				   int yieldObject = mainJSONOBject.getInt("yield");
				   int caloriesObject = mainJSONOBject.getInt("calories");
				   
				   // -- dividing total calories by my yield to get calories
				   // -- per serving
				   int totalCaloriesPerServing = caloriesObject / yieldObject;
				   
				   // -- setting the text view
				   calories.setText("Calories: " + "" + totalCaloriesPerServing);
				   
				   
				   // ----------------- going into the nutrients level -------------------- //
				   JSONObject nutrientsObject = mainJSONOBject.getJSONObject("totalNutrients");
				   
				   // -- getting the fat object
				   JSONObject fatObject = nutrientsObject.getJSONObject("FAT");
				   
				   int fatQuantity = fatObject.getInt("quantity");
				   
				   // -- getting the totla fat content
				   int totalFat = fatQuantity / yieldObject;
				   
				   String fatUnit = fatObject.getString("unit");
				   
				   // -- setting the textview for total fat
				   fat.setText("Fat: " + totalFat + fatUnit);
				   
				   
				   // -- getting the saturated fat object
				   JSONObject saturatedFatObject = nutrientsObject.getJSONObject("FASAT");
				   
				   int satFatQuantity = saturatedFatObject.getInt("quantity");
				   
				   int totalSatFat = satFatQuantity / yieldObject;
				   
				   String satFatUnit = saturatedFatObject.getString("unit");
				   
				   // -- setting the textview for total saturated fat
				   saturated_fat.setText("Saturated Fat: " + totalSatFat + satFatUnit);
				   
				   
				   // -- getting the carbohydrates object
				   JSONObject carbohydratesObject = nutrientsObject.getJSONObject("CHOCDF");
				   
				   int carbQuantity = carbohydratesObject.getInt("quantity");
				   
				   int totalCarb = carbQuantity / yieldObject;
				   
				   String carbUnit = carbohydratesObject.getString("unit");
				   
				   carbs.setText("Carbohydrates: " + totalCarb + carbUnit);
				   
				   
				   
				   // -- getting the protein object
				   JSONObject proteinObject = nutrientsObject.getJSONObject("PROCNT");
				   
				   int proteinQuantity = proteinObject.getInt("quantity");
				   
				   int totalProtein = proteinQuantity / yieldObject;
				   
				   String proteinUnit = proteinObject.getString("unit");
				   
				   protein.setText("Protein: " + totalProtein + proteinUnit);
				   
				   
				   // -- getting the sodium object
				   JSONObject sodiumObject = nutrientsObject.getJSONObject("NA");
				   
				   int sodiumQuantity = sodiumObject.getInt("quantity");
				   
				   int totalSodium = sodiumQuantity / yieldObject;
				   
				   String sodiumUnit = sodiumObject.getString("unit");
				   
				   sodium.setText("Sodium: " + totalSodium + sodiumUnit);
				   
				   
				   // -- getting the fiber object
				   JSONObject fiberObject = nutrientsObject.getJSONObject("FIBTG");
				   
				   int fiberQuantity = fiberObject.getInt("quantity");
				   
				   int totalFiber = fiberQuantity / yieldObject;
				   
				   String fiberUnit = fiberObject.getString("unit");
				   
				   fiber.setText("Fiber: " + fiberQuantity + fiberUnit);
				   
				   
				   // -- getting the cholesterol object
				   JSONObject cholesterolObject = nutrientsObject.getJSONObject("CHOLE");
				   
				   int cholesterolQuantity = cholesterolObject.getInt("quantity");
				   
				   int totalCholesterol = cholesterolQuantity / yieldObject;
				   
				   String cholesterolUnit = cholesterolObject.getString("unit");
				   
				   cholesterol.setText("Cholesterol: " + totalCholesterol + cholesterolUnit);
				   	   
			   }
			
		   } catch (JSONException e) {
			   // TODO Auto-generated catch block
			   Log.e("json parse error", e.getMessage().toString());
		   }
		   
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


