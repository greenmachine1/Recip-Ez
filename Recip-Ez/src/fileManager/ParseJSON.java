package fileManager;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

/*
 * -- this is where the parsing of the returned json data takes place
 */

public class ParseJSON extends Activity{

	public final String FILENAME = "pearsonJSON.txt";
	
	private static ParseJSON parseJSON;
	
	
	// constructor
	public static ParseJSON getInstance(){
		if(parseJSON == null){
			parseJSON = new ParseJSON();
		}
		return parseJSON;
	}
	
	
	// loads json from the file
	public void loadJSON(Context context){
		
		File file = context.getFileStreamPath(FILENAME);
		if(file.exists() == true){
			Log.i("It exsists", "true");
		}else{
			Log.i("It exsists", "false");
		}
	}
	
}
