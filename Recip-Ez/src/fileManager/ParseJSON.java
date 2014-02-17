package fileManager;

import java.io.File;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

/*
 * -- this is where the parsing of the returned json data takes place
 */

public class ParseJSON{

	public final String FILENAME = "pearsonJSON.txt";
	
	FileManager fileManager;

	File file;
	
	Context _context;
	
	String mainJSONString = "";
	
	JSONObject mainJSONObject;
	
	JSONArray mainJSONArray;
	
	// -- constructor -- passes in the context
	public ParseJSON(Context context) {
		
		_context = context;
		
	}
	
	
	// -- checking to see if the file exists
	public boolean fileDoesExist(){
		
		// -- checking to see if the file exists
		file = _context.getFileStreamPath(FILENAME);
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
			mainJSONString = fileManager.readNewFile(_context, FILENAME);
			
			try {
				// -- contains the JSON returned object for the rest
				// -- of the class to use
				mainJSONObject = new JSONObject(mainJSONString);
				
				mainJSONArray = new JSONArray();
				
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
		
		JSONArray directionsArray;
		String titleOfRecipe;
		
		HashMap<String, String> returnHashMap = new HashMap<String, String>();
		
		if(loadJSON() == true){
			
			try {
			
				// -- getting the results array from the json data
				mainJSONArray = mainJSONObject.getJSONArray("results");
				
				// -- iterating through my array values
				for(int i = 0; i < mainJSONArray.length(); i++){
					
					// -- grabbing the title from the json data
					titleOfRecipe = mainJSONArray.getJSONObject(i).getJSONObject("summary").getString("title");
				
					// -- getting the directions back from the main json data
					directionsArray = mainJSONArray.getJSONObject(i).getJSONArray("directions");
					
					returnHashMap.put(titleOfRecipe, directionsArray.toString());
					
				}
			} catch (JSONException e) {
			
				Log.e("json error", e.getMessage().toString());
			}
		}
		
		// -- returning the hashmap
		return returnHashMap;
	}
	

}
