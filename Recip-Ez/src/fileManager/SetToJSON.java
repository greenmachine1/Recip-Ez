package fileManager;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;


// -- class thats set up to gather information and convert
// -- it to json then save it
public class SetToJSON {
	
	Context _context;
	
	public final String NAME_OF_FILE = "favoritesJSON.txt";

	public SetToJSON(Context context) {
		_context = context;
	}
	
	
	// -- taking in all the information and converting it to JSON
	// -- format
	public void setEverythingToJSON(String title, String url, String directions, String ingredients){
		
		JSONObject mainObject = new JSONObject();
		JSONObject urlObject = new JSONObject();
		JSONObject directionsObject = new JSONObject();
		JSONObject ingredientsObject = new JSONObject();
		JSONObject topLevelObject = new JSONObject();
		
		JSONArray mainJSONArray = new JSONArray();
		JSONArray topLevelArray = new JSONArray();
		
		String tempMainFileString = "";
		
		File file = _context.getFileStreamPath(NAME_OF_FILE);
		
		FileManager newFileManager = new FileManager();
		
		try {
			
			// -- second level of json object
			urlObject.put("url", url);
			
			directionsObject.put("directions", directions);
			
			ingredientsObject.put("ingredients", ingredients);
			
			
			// -- putting all my elements into an array
			mainJSONArray.put(urlObject);
			
			mainJSONArray.put(directionsObject);
			
			mainJSONArray.put(ingredientsObject);
			
			
			// -- then putting that array back into
			// -- my main object
			mainObject.put(title, mainJSONArray);
			topLevelArray.put(mainObject);
			
			
			topLevelObject.put("main", topLevelArray);
			
			
			
			// -- if the file does not exist, make it
			if(!(file.exists()) == true){
				
				newFileManager.writeStringFile(_context, NAME_OF_FILE, topLevelObject.toString());
				
			// -- if the file already exists, then append data	
			}else{
				
				// -- opening of the file and putting it into a json object
				tempMainFileString = newFileManager.readNewFile(_context, NAME_OF_FILE);
				
				JSONObject newAppendObject = new JSONObject(tempMainFileString);
				
				// -- targetting my 'main' array
				JSONArray newAppendArray = newAppendObject.getJSONArray("main");
				
				// -- putting in the main object
				newAppendArray.put(mainObject);
				
				// -- overwriting the existing file
				newFileManager.writeStringFile(_context, NAME_OF_FILE, newAppendObject.toString());
				
				
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
}
