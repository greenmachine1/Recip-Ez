package fileManager;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

public class FileManager {

	private static FileManager file_Manager_instance;
	
	// constructor
	public FileManager(){
		
	}
	
	public static FileManager getInstance(){
		if(file_Manager_instance == null){
			file_Manager_instance = new FileManager();
		}
		return file_Manager_instance;
	}
	
	// -- works to write file 
	public Boolean writeStringFile(Context context, String fileName, String content){
		Boolean result = false;
		
		FileOutputStream fos = null;
		
		try {
			fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.write(content.getBytes());
			Log.i("Write String file", "Success");
			
		} catch (Exception e){
			Log.e("Write file error", e.toString());
		}
		return result;
	}
	

	
	// -- works to read the file contents
	public String readNewFile(Context context, String fileName){
		String tempString = "";
		
		try{
			
			Log.i("in the midst of trying", "true");
			File file = context.getFileStreamPath(fileName);
			
			if(file.exists() && file.length() != 0){
				
				Log.i("file exists again", "true");
				
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				String line;
				
				while((line = bufferedReader.readLine()) != null){
					
					tempString += line;
					
				}
			}
			
		}catch(Exception e){
			Log.e("error in writing", e.getMessage().toString());
		}
		
		
		return tempString;
	}
	

	
}