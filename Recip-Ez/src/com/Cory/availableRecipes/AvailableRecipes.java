package com.Cory.availableRecipes;

import com.Cory.recip_ez.R;
import com.Cory.service_package.PearsonAPIService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class AvailableRecipes extends FragmentActivity{
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_recipes_layout);
        
        // -- getting the extras passed in from the main screen
        Bundle extras = getIntent().getExtras();
        
        String userPassedInSearch = extras.getString("search");
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        
        // -- starting of the service intent
        // -- this will be tied in with the 'go' button on the keypad
        // -- that gets brought up when the user searches
        
        Intent serviceIntent = new Intent(this, PearsonAPIService.class);
        
        // -- this will be the extras that the user passes in via search 
        serviceIntent.putExtra("search", "");
        
        // -- this tells the service that the searching service
        // -- was called by the main activity and not
        // -- the search function
        serviceIntent.putExtra("fromMainActivity", false);
        
        // -- this is the random offset that will be generated
        // -- to give a random recipe
        serviceIntent.putExtra("offset", 0);
        
        startService(serviceIntent);
        
	}
	
	

}
