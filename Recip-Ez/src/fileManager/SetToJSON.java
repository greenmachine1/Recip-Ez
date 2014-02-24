package fileManager;

import java.io.File;
import java.util.ArrayList;

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
	
	File file;
	FileManager newFileManager;

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
		
		file = _context.getFileStreamPath(NAME_OF_FILE);
		
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
			
			Log.i("top level object to be saved", topLevelObject.toString());

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
	
	
	
	
	// -- deletes the item passed in
	public void deleteJSON(String itemToDelete){
		
		file = _context.getFileStreamPath(NAME_OF_FILE);

		
		if(file.exists() == true){
			newFileManager = new FileManager();
			
			// -- reading in the file
			String fileContents = newFileManager.readNewFile(_context, NAME_OF_FILE);
			
			try {
				
				// -- targetting the main json object
				JSONObject mainObject = new JSONObject(fileContents);
				
				// -- targetting the 'main' array
				JSONArray mainJSONArray = mainObject.getJSONArray("main");
				
				for(int i = 0; i < mainJSONArray.length(); i++){
					String nameOfRecipeToDelete = mainJSONArray.getJSONObject(i).names().toString();
					String modifiedNameString = cutTheFatFromNamesList(nameOfRecipeToDelete);
					
					if(modifiedNameString.equals(itemToDelete)){
						Log.i("yes", "yes it is equal to " + itemToDelete);
						
						//JSONArray itemToDeleteArray = mainJSONArray.getJSONObject(i).getJSONArray(itemToDelete);
						
						mainJSONArray.getJSONObject(i).remove(itemToDelete);
						
						//Log.i("array value", itemToDeleteArray.toString());
					}
					
				}
				
				
				if((cutTheFatFromMainFile(mainJSONArray.toString()).equals("") == true)){
					
					file.delete();
					
				}else if((cutTheFatFromMainFile(mainJSONArray.toString()).equals("[{}]") == true)){
				
					Log.i("file contains", cutTheFatFromMainFile(mainJSONArray.toString()));
					
					file.delete();

				}else if((cutTheFatFromMainFile(mainJSONArray.toString()).equals("{\"main\":}") == true)){
					
					file.delete();
				}
				
				// -- write to file
				else{
					String fileWithMain = "{\"main\":" + cutTheFatFromMainFile(mainJSONArray.toString()) + "}";
					
					// -- writing back to the file
					newFileManager.writeStringFile(_context, NAME_OF_FILE, fileWithMain);
				}
				
				
	
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e("error deleting", e.getMessage().toString());
			}

		}

	}

	
	
	// -- trimming up the main file
	public String cutTheFatFromMainFile(String mainString){
		String finalString = "";
		
		if(mainString.contains("{},")){
			finalString = mainString.replace("{},", "");
		}else if(mainString.contains(",{},")){
			finalString = mainString.replace(",{},", ",");
		}else if(mainString.contains(",{}")){
			finalString = mainString.replace(",{}", "");
		}else if(mainString.contains("[{}]")){
			finalString = mainString.replace("[{}]", "");
		}else if(mainString.contains("\"main\":[{}]")){
			finalString = mainString.replace("\"main\":[{}]", "");
		}
		
		return finalString;
	}
	
	// -- trimming the name variables
	public String cutTheFatFromNamesList(String name){
		
		String withoutFrontBracketAndQuote = name.replace("[\"", "");
		String withoutBothBracketsAndQuotes = withoutFrontBracketAndQuote.replace("\"]", "");
		
		return withoutBothBracketsAndQuotes;
	}
	
	
	
	
	
}
