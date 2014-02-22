package fileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class FavoritesJSONParsing {

	public final String NAME_OF_FILE = "favoritesJSON.txt";
	
	Context _context;
	
	FileManager fileManager;
	
	File file;
	
	JSONObject mainObject;
	
	ArrayList<String> namesOfRecipes = new ArrayList<String>();
	
	
	public FavoritesJSONParsing(Context context) {
		_context = context;
	}
	
	
	public boolean loadJSON(){
		
		// -- getting the file
		file = _context.getFileStreamPath(NAME_OF_FILE);
		
		if(file.exists() == true){
			
			fileManager = new FileManager();
			String favoritesJSONString = fileManager.readNewFile(_context, NAME_OF_FILE);
			
			try {
				// -- loading up the main object with the file 
				// -- contents
				mainObject = new JSONObject(favoritesJSONString);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}else{
			
			return false;
		}
		
	}
	
	// -- returns a hashmap of the name object from
	// -- the main json data
	public HashMap<String, String> returnName(){
		
		HashMap<String, String> nameHashMap = new HashMap<String, String>();

		if(loadJSON() == true){
			
			try {
				
				JSONArray mainJSONArray = new JSONArray();
				JSONObject nameObject = new JSONObject();
				
				// -- getting the array object from the main object
				mainJSONArray = mainObject.getJSONArray("main");
				
				// -- cycling through the mainJSONArray for the next elements
				for(int i = 0; i < mainJSONArray.length(); i++){
					
					String nameString = cropString(mainJSONArray.getJSONObject(i).names().toString());
					
					namesOfRecipes.add(nameString);
					
					Log.i("name", nameString);
					
					nameHashMap.put(nameString, nameString);
					
				}				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e("error with title", e.getMessage().toString());
			}

			return nameHashMap;
		}
		

		return null;
	}
	
	public HashMap<String, String> returnUrl(){
		
		HashMap<String, String> urlHashMap = new HashMap<String, String>();
		
		if(loadJSON() == true){
			
			try {
				
				JSONArray mainJSONArray = new JSONArray();
				JSONObject nameObject = new JSONObject();
				
				// -- getting the array object from the main object
				mainJSONArray = mainObject.getJSONArray("main");
				
				// -- cycling through the mainJSONArray for the next elements
				for(int i = 0; i < mainJSONArray.length(); i++){
				
					String urlString = mainJSONArray.getJSONObject(i).getJSONArray(namesOfRecipes.get(i)).getJSONObject(0).getString("url");
					
					String backFromCropping = cropUrlString(urlString);
					
					urlHashMap.put(namesOfRecipes.get(i), backFromCropping);
					
				}
				
				Log.i("url", urlHashMap.toString());
			}catch(Exception e){
				Log.e("error with url", e.getMessage().toString());
			}
			
			return urlHashMap;
		}
		return null;
	}
	
	
	public HashMap<String, String> returnIngredients(){
		
		HashMap<String, String> ingredientsHashMap = new HashMap<String, String>();
		
		if(loadJSON() == true){
			
			try {
				
				JSONArray mainJSONArray = new JSONArray();
				JSONObject nameObject = new JSONObject();
				
				// -- getting the array object from the main object
				mainJSONArray = mainObject.getJSONArray("main");
				
				// -- cycling through the mainJSONArray for the next elements
				for(int i = 0; i < mainJSONArray.length(); i++){
				
					// -- the getJSONObject(2) denotes the 3rd object in the recipe
					String ingredientsString = mainJSONArray.getJSONObject(i).getJSONArray(namesOfRecipes.get(i)).getJSONObject(2).getString("ingredients");
					
					ingredientsHashMap.put(namesOfRecipes.get(i), ingredientsString);
					
				}
				Log.i("ingredients", ingredientsHashMap.toString());
			}catch(Exception e){
				Log.e("error with ingredients", e.getMessage().toString());
			}
			
			return ingredientsHashMap;
		}
		
		return null;
	}
	
	public HashMap<String, String> returnDirections(){
		
		HashMap<String, String> directionsHashMap = new HashMap<String, String>();
		
		if(loadJSON() == true){
			
			try {
				
				JSONArray mainJSONArray = new JSONArray();
				JSONObject nameObject = new JSONObject();
				
				// -- getting the array object from the main object
				mainJSONArray = mainObject.getJSONArray("main");
				
				// -- cycling through the mainJSONArray for the next elements
				for(int i = 0; i < mainJSONArray.length(); i++){
				
					// -- the getJSONObject(2) denotes the 3rd object in the recipe
					String directionsString = mainJSONArray.getJSONObject(i).getJSONArray(namesOfRecipes.get(i)).getJSONObject(1).getString("directions");
					
					directionsHashMap.put(namesOfRecipes.get(i), directionsString);
					
				}
				Log.i("directions", directionsHashMap.toString());
			}catch(Exception e){
				Log.e("error with directions", e.getMessage().toString());
			}
			
			return directionsHashMap;
		}
		
		
		return null;
	}
	
	
	
	
	public String cropUrlString(String urlString){
		
		String minusAngleBrackets = urlString.replace("\\", "");
		
		return minusAngleBrackets;
	}
	
	
	// -- cropping of string
	public String cropString(String stringToBeCropped){
		
		String minusBrackets = stringToBeCropped.replace("[\"", "");
		
		String minusBracketsAgain = minusBrackets.replace("\"]", "");
		
		return minusBracketsAgain;
	}
	
	
}
