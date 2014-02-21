package fileManager;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;


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
		
		try {
			
			// -- starting on working on building the JSON format
			mainObject.put("name", title);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
}
