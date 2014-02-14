package fileManager;

import java.io.File;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

/*
 * -- this is where the parsing of the returned json data takes place
 */

public class ParseJSON extends Activity{

	public final String FILENAME = "pearsonJSON.txt";
	
	private static ParseJSON parseJSON;
	
	File file;
	
	// the entire json string
	String mainJSONString;
	
	// constructor
	public static ParseJSON getInstance(){
		if(parseJSON == null){
			parseJSON = new ParseJSON();
		}
		return parseJSON;
	}
	
	
	// loads json from the file
	public void loadJSON(Context context){
		
		// -- if the file exists then do something....
		file = context.getFileStreamPath(FILENAME);
		if(file.exists() == true){
			
			FileManager fileManager = new FileManager();
			
			mainJSONString = fileManager.readStringFile(context, FILENAME);
			
			
			Log.i("It exsists", "true");
		// -- if the file does not exist....	
		}else{
			
			Log.i("It exsists", "false");
		}
	}
	
	// this is the main screen gridview parsing
	public void parseJSONforGridView(){
		

		
		
	}
	
}
