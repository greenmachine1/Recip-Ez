package com.Cory.service_package;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import fileManager.FileManager;
import fileManager.ParseJSON;


/*
 * -- this service goes out to the Pearson api and saves data
 */
public class PearsonAPIService extends IntentService {
	
	public final String FILENAME = "pearsonJSON.txt";
	
	
	public PearsonAPIService() {
		super("PearsonAPIService");
	}

	
	@Override
	protected void onHandleIntent(Intent intent) {
		
		// -- will be needing to get extras from the calling intent
		String extraString = intent.getStringExtra("search");
		String extraOffsetnumber = "" + intent.getIntExtra("offset", 0);
		
		Log.i("extras", extraString);
		Log.i("offset extra", extraOffsetnumber);
		
		// -- creation of url
		String completeURL = "https://api.pearson.com/v2/foodanddrink/recipes?apikey=zTTymSsKBxBB59T9AoBBnyMgZ1VUW4AB";
				
		URL finalURL = null;
		try {
			finalURL = new URL(completeURL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// -- temp string to hold the response
		String response = "";
		response = WebInfo.getURLStringResponse(finalURL);
		
		
		
		// -- writes string to file
		FileManager fileManager = new FileManager();
		
		fileManager.writeStringFile(this, FILENAME, response);

		Log.i("json returned string", response);
				
		// stops the service
		stopSelf();

	}
	
	
	
	

}
