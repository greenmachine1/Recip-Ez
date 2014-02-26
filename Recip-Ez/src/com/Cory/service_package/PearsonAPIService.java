/*
 * Developers name 	Cory Green
 * 
 * Project name		Recip-Ez
 * 
 * Date				Feb 26, 2014
 */
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
	
	public final String FILENAME_FOR_PEARSON = "pearsonJSON.txt";
	
	public final String FILENAME_FOR_SEARCH = "searchJSON.txt";
	
	String completeURL = "";
	
	String extraString;
	
	String extraOffsetnumber;
	
	
	public PearsonAPIService() {
		super("PearsonAPIService");
	}

	
	@Override
	protected void onHandleIntent(Intent intent) {
		
		// -- will be needing to get extras from the calling intent
		extraString = intent.getStringExtra("search");
		extraOffsetnumber = "" + intent.getIntExtra("offset", 0);
		boolean cameFromMainActivity = intent.getBooleanExtra("fromMainActivity", true);
		
		// -- judging where the service call came form
		// -- this came from the search function
		if(cameFromMainActivity == true){
			
			Log.i("did come from main activity", "true");
			
			cameFromMainActivityJSONParse(extraOffsetnumber);
		
		// -- this came from the main activity
		}else{
			
			Log.i("did not come from main activity", "false");
			
			cameFromSearchActivityJSONParse(extraOffsetnumber, extraString);
		}
		
		Log.i("extras", extraString);
		Log.i("offset extra", extraOffsetnumber);


	}
	
	
	// -- this gets ran if it did come from the main
	// -- activity (passes in the offset number)
	public void cameFromMainActivityJSONParse(String offSet){
		
		completeURL = "https://api.pearson.com/v2/foodanddrink/recipes?offset=" + offSet + "&apikey=zTTymSsKBxBB59T9AoBBnyMgZ1VUW4AB";
		
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
		
		fileManager.writeStringFile(this, FILENAME_FOR_PEARSON, response);

		Log.i("json returned string", response);
		
		// -- sends out a true signal that the service has ended
		Intent newIntent = new Intent("android.intent.action.MAIN").putExtra("DONE", true);
		this.sendBroadcast(newIntent);
				
		// stops the service
		this.stopSelf();
		
	}
	
	
	// -- this gets ran if it came from the search 'return'
	// -- button (passes in the search)
	public void cameFromSearchActivityJSONParse(String offSet , String searchSting){
		
		completeURL = "https://api.pearson.com/v2/foodanddrink/recipes?offset=" + offSet + "&search=" + searchSting + "&apikey=zTTymSsKBxBB59T9AoBBnyMgZ1VUW4AB";
		
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
		
		// -- saving this to the searchJSON.txt
		fileManager.writeStringFile(this, FILENAME_FOR_SEARCH, response);

		Log.i("json returned string", response);
		
		// -- sends out a true signal that the service has ended
		Intent newIntent = new Intent("android.intent.action.MAIN").putExtra("DONE", true);
		this.sendBroadcast(newIntent);
				
		// stops the service
		this.stopSelf();
		
		
	}


	
	
	
	

}
