package com.Cory.service_package;

import java.net.MalformedURLException;
import java.net.URL;

import com.Cory.recip_ez.GridViewFragment;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import fileManager.FileManager;



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
		
		String completeURL = "";
		
		// -- will be needing to get extras from the calling intent
		String extraString = intent.getStringExtra("search");
		String extraOffsetnumber = "" + intent.getIntExtra("offset", 0);
		
		Log.i("extras", extraString);
		Log.i("offset extra", extraOffsetnumber);
		
		// -- this is the search extras that is passed in
		if(!(extraString.isEmpty())){
			// -- adding in the users search
			completeURL = "https://api.pearson.com/v2/foodanddrink/recipes?offset=0&search=" + extraString +"&apikey=zTTymSsKBxBB59T9AoBBnyMgZ1VUW4AB";
		}else{
			// -- default URL
			completeURL = "https://api.pearson.com/v2/foodanddrink/recipes?offset=" + extraOffsetnumber + "&apikey=zTTymSsKBxBB59T9AoBBnyMgZ1VUW4AB";
		}
				
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
		
		// -- sends out a true signal that the service has ended
		Intent newIntent = new Intent("android.intent.action.MAIN").putExtra("DONE", true);
		this.sendBroadcast(newIntent);
				
		// stops the service
		this.stopSelf();

	}


	
	
	
	

}
