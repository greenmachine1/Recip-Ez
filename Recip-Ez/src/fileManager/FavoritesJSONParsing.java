package fileManager;

import java.io.File;
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
					
					Log.i("name", nameString);
					
					nameHashMap.put(nameString, nameString);
					
					
				}
				Log.i("hash map hash map", nameHashMap.toString());
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e("error", e.getMessage().toString());
			}
			
			Log.i("contents of hashmap", nameHashMap.toString());
			return nameHashMap;
		}
		
		Log.i("contents of hashmap", nameHashMap.toString());
		return null;
	}
	
	
	// -- cropping of string
	public String cropString(String stringToBeCropped){
		
		String minusBrackets = stringToBeCropped.replace("[\"", "");
		
		String minusBracketsAgain = minusBrackets.replace("\"]", "");
		
		return minusBracketsAgain;
	}
	
	
}
