package fileManager;

import java.io.File;

import org.json.JSONException;
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
	
	FileManager fileManager;
	
	JSONObject mainJSONObject;
	
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
	
	
	public void parseJSONData(String data){
		
		
	}
	

}
