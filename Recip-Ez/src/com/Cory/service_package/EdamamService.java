package com.Cory.service_package;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class EdamamService extends IntentService {
	
	String startOfUrl = "https://api.edamam.com/api/nutrient-info";
	
	String ingredientsPassedIn;
	
	public final String FILE_NAME = "edemam.txt";
	
	String title;
	String ingredients;

	public EdamamService() {
		super("EdamamService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		ingredientsPassedIn = intent.getStringExtra("ingredients");
		
		Log.i("service", ingredientsPassedIn);
		
		this.stopSelf();
	}
	
	
	public void saveDataToFile(){
		
		
	}
	
	
	

}
