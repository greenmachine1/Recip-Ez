package com.Cory.service_package;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


import fileManager.FileManager;

public class EdamamService extends IntentService {
	
	String startOfUrl = "https://api.edamam.com/api/nutrient-info?extractOnly&app_id=463aca97&app_key=08c7b0c2f67acfff719613a2d169e1cc";
	
	String ingredientsPassedIn;
	
	public final String FILE_NAME = "edemam.txt";
	
	String title;
	String ingredients;

	public EdamamService() {
		super("EdamamService");
		// TODO Auto-generated constructor stub
	}

	
	// -- service that passes the file nutrition.txt to the
	// -- api and retrieves its data and stores it in edamam.txt
	
	@Override
	protected void onHandleIntent(Intent intent) {
		
		FileManager fileManager = new FileManager();
		fileManager.writeStringFile(getApplication(), FILE_NAME, downloadTheText(startOfUrl));

		// -- sends out a true signal that the service has ended
		Intent newIntent = new Intent("android.intent.action.MAIN").putExtra("DONE", true);
		this.sendBroadcast(newIntent);
		
		this.stopSelf();
	}
	
	
	
	
	
	
	
	
	
	public InputStream OpenHttpPOSTConntection(String url){
		
		File file = getApplication().getFileStreamPath("nutrition.txt");
		if(file.exists() == true){
			
			FileManager fileManager = new FileManager();
			
			// -- loading the file into a string to be passed on
			String fileContents = fileManager.readNewFile(getApplication(), "nutrition.txt");
		
			HttpClient client = new DefaultHttpClient();
			InputStream inputStream = null;
			HttpResponse response = null;
			try{
				HttpPost post = new HttpPost(url);
				StringEntity stringEntity = new StringEntity(fileContents);
				stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
				post.setEntity(stringEntity);
				response = client.execute(post);
				
				if(response != null){
					inputStream = response.getEntity().getContent();
					
					Log.i("response", inputStream.toString());
					return inputStream;
				}
				
			}catch (Exception e){
				Log.e("json error", e.getMessage().toString());
			}
			
		}
		
		return null;
	}
	
	
	// -- processing the information
	public String downloadTheText(String URL){
		
		int BUFFER_SIZE = 2000;
		InputStream input = null;
		try{
			input = OpenHttpPOSTConntection(URL);
			
		}catch(Exception e){
			return "";
		}
		
		InputStreamReader inputStreamReader = new InputStreamReader(input);
		int charRead;
		String str = "";
		
		char[] inputBuffer = new char[BUFFER_SIZE];
		try{
			while ((charRead = inputStreamReader.read(inputBuffer)) > 0){
				
				String readString = String.copyValueOf(inputBuffer, 0, charRead);
				str += readString;
				inputBuffer = new char[BUFFER_SIZE];
			}
			input.close();
			
		}catch(IOException e){
			return "";
		}
		
		Log.i("last element", "true");
		return str;
	}

}
