package com.Cory.service_package;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import fileManager.FileManager;
import fileManager.ParseJSON;


/*
 * -- this service goes out to the Pearson api and returns data
 */
public class PearsonAPIService extends IntentService {
	
	public final String FILENAME = "pearsonJSON.txt";

	public PearsonAPIService() {
		super("PearsonAPIService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		// creation of url
		String completeURL = "https://api.pearson.com/v2/foodanddrink/recipes?apikey=zTTymSsKBxBB59T9AoBBnyMgZ1VUW4AB";
				
		URL finalURL = null;
		try {
			finalURL = new URL(completeURL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// temp string to hold the response
		String response = "";
		response = WebInfo.getURLStringResponse(finalURL);
		
		FileManager fileManager = new FileManager();
		fileManager.writeStringFile(getApplicationContext(), FILENAME, response);

		Log.i("json returned string", response);
		
		ParseJSON parseJSON = new ParseJSON();
		parseJSON.loadJSON(this);
		
		// stops the service
		stopSelf();
		
		
		
	}

}
