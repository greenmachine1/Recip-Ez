package fileManager;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


// -- parsing of the main json Data takes place here
public class ParseJSON{

	// -- declaring file names
	public final String FILENAME_FOR_PEARSON = "pearsonJSON.txt";
	public final String FILENAME_FOR_SEARCH = "searchJSON.txt";
	
	FileManager fileManager;

	File file;
	
	Context _context;
	
	String mainJSONString = "";
	
	JSONObject mainJSONObject;
	
	JSONArray mainJSONArray;
	
	boolean whereItCameFrom;
	
	// -- constructor -- passes in the context, and also where it 
	// -- came from.  If it came from main activity it will be 
	// -- true.  If it came from the search from gridview fragment
	// -- it will be false
	public ParseJSON(Context context, boolean fromMainActivity) {
		_context = context;
		whereItCameFrom = fromMainActivity;
	}
	
	
	
	
	// -- checking to see if the file exists
	public boolean fileDoesExist(){
		
		// -- checking to see if the file exists
		if(whereItCameFrom == true){
			file = _context.getFileStreamPath(FILENAME_FOR_PEARSON);
		}else{
			file = _context.getFileStreamPath(FILENAME_FOR_SEARCH);
		}
		
		if(file.exists() == true){	
			return true;
		}else{
			return false;		
		}
	}
	
	
	
	
	
	// -- this loads the json data into a JSONObject for
	// -- the rest of the class to use
	public boolean loadJSON(){
		
		if(fileDoesExist() == true){
			
			// -- getting the json file and put it into a string
			fileManager = new FileManager();
			
			if(whereItCameFrom == true){
				mainJSONString = fileManager.readNewFile(_context, FILENAME_FOR_PEARSON);
			}else{
				mainJSONString = fileManager.readNewFile(_context, FILENAME_FOR_SEARCH);
			}
			
			try {
				// -- contains the JSON returned object for the rest
				// -- of the class to use
				mainJSONObject = new JSONObject(mainJSONString);
				
				mainJSONArray = new JSONArray();
				
				// -- getting the results array from the json data
				mainJSONArray = mainJSONObject.getJSONArray("results");
				
			} catch (JSONException e) {
				Log.e("error with json", e.getMessage().toString());
			}
			
		return true;
			
		}else{
			Log.i("it does not exist", "nope");	
		}
		return false;
	}
	
	
	
	
	
	// -- goes through the json data and picks out the directions
	public HashMap<String, String> returnDirectionData(){
		
		JSONArray directionsArray = new JSONArray();
		String titleOfRecipe;
		
		HashMap<String, String> returnHashMap = new HashMap<String, String>();
		
		if(loadJSON() == true){
			
			try {				
				// -- iterating through my array values
				for(int i = 0; i < mainJSONArray.length(); i++){
					
					// -- grabbing the title from the json data
					titleOfRecipe = mainJSONArray.getJSONObject(i).getJSONObject("summary").getString("title");
				
					// -- getting the directions back from the main json data
					directionsArray = mainJSONArray.getJSONObject(i).getJSONArray("directions");
					
					// -- inserting elements into the hashmap
					returnHashMap.put(titleOfRecipe, directionsArray.toString());
				}
			} catch (JSONException e) {
			
				Log.e("json error", e.getMessage().toString());
			}
		}
		
		// -- returning the hashmap
		return returnHashMap;
	}
	
	
	
	
	// -- method for returning the titles
	public ArrayList<String> returnTitles(){
		
		String titleOfRecipe;
		
		ArrayList<String> titleArrayList = new ArrayList<String>();
		
		if(loadJSON() == true){
		
			try{
				for(int i = 0; i < mainJSONArray.length(); i++){	
					titleOfRecipe = mainJSONArray.getJSONObject(i).getJSONObject("summary").getString("title");
					titleArrayList.add(titleOfRecipe);
				}
				
			}catch(Exception e){
				Log.e("error in title", e.getMessage().toString());
			}
		}
		// -- returning the arrayList of titles
		return titleArrayList;
	}
	
	
	
	// -- method for returning the ingredients
	public HashMap<String, String> returnIngredients(){
		
		JSONArray ingredientsArray = new JSONArray();
		
		//JSONObject descriptionOfIngredient = new JSONObject();
		
		String descriptionOfIngredient = "";
		
		String titleOfRecipe;
		
		String mainOutputString = "";
		
		HashMap<String, String> ingredientsHashMap = new HashMap<String, String>();
		
		ArrayList<String>descriptions = new ArrayList<String>();
		
		if(loadJSON() == true){
			
			for(int i = 0; i < mainJSONArray.length(); i++){
				
				// -- getting the ingredients back from the main json data
				try {
					
					ingredientsArray = mainJSONArray.getJSONObject(i).getJSONArray("ingredients");
					
					titleOfRecipe = mainJSONArray.getJSONObject(i).getJSONObject("summary").getString("title");
					
					descriptions.clear();
					
					for(int j = 0; j < ingredientsArray.length(); j++){
						
						String ingredientsArrayString = ingredientsArray.getJSONObject(j).getString("description");
						descriptions.add(ingredientsArrayString);
						
					}
					

					// -- putting the ingredients into a hashMap
					ingredientsHashMap.put(titleOfRecipe, descriptions.toString());
					
					
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}	

		}
		// -- returning the ingredients hashmap
		return ingredientsHashMap;
		
	}
	
	
	
	
	
	public HashMap<String, String> returnImageUrl(){
		
		String titleOfRecipe;
		
		String sizeString = "512x512";
		
		JSONArray imagesArray = new JSONArray();
		
		HashMap<String, String> imageUrlHashMap = new HashMap<String, String>();
		
		if(loadJSON() == true){
		
			for(int i = 0; i < mainJSONArray.length(); i++){
				
				String finalUrlForImage = "";
				
				try {
					
					titleOfRecipe = mainJSONArray.getJSONObject(i).getJSONObject("summary").getString("title");
					
					imagesArray = mainJSONArray.getJSONObject(i).getJSONArray("images");
					
					// -- cycling through my images array to find the right image to 
					// -- return
					for(int j = 0; j < imagesArray.length(); j++){
						
						String imageString = imagesArray.getJSONObject(j).getString("url");
						
						// -- if the image string contains "512x512"
						// -- then it is the final string that gets returned
						if(imageString.contains(sizeString) == true){
							
							finalUrlForImage = imageString;
						}
	
					}
					
					// -- adding in the name of the recipe and
					// -- the final url for the image
					imageUrlHashMap.put(titleOfRecipe, finalUrlForImage);
									
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			}
		}
		
		// -- returning the image hashmap
		return imageUrlHashMap;
	}
	
	


}
