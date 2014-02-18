/*
 * Developers name 	Cory Green
 * 
 * Project name		Recip-Ez
 * 
 * Date				Feb 11, 2014
 */
package com.Cory.recip_ez;

import java.util.HashMap;

import com.Cory.service_package.PearsonAPIService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import fileManager.ParseJSON;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
// -- just for testing purposes -------------------------------------------        
        // -- starting of the service intent
        // -- this will be tied in with the 'go' button on the keypad
        // -- that gets brought up when the user searches
        Intent serviceIntent = new Intent(this, PearsonAPIService.class);
        
        // -- this will be the extras that the user passes in via search 
        serviceIntent.putExtra("search", "search value from user");
        
        // -- this is the random offset that will be generated
        // -- to give a random recipe
        serviceIntent.putExtra("offset", 20);
        
        startService(serviceIntent);

        HashMap<String, String> newHashMap = new HashMap<String, String>();
        HashMap<String, String> ingredientsHashMap = new HashMap<String, String>();
        HashMap<String, String> imagesHashMap = new HashMap<String, String>();
        /*
        ParseJSON newParseJSON = new ParseJSON(this);

        
        newHashMap = newParseJSON.returnDirectionData();
        ingredientsHashMap = newParseJSON.returnIngredients();
        imagesHashMap = newParseJSON.returnImageUrl();
        
        Log.i("elements in returned hashMap -->", "" + newHashMap.size());
        Log.i("titles in parsing", newParseJSON.returnTitles().toString());
        
        Log.i("elements in ingredients Hash Map", "" + ingredientsHashMap.size());
        
        // -- returns the url for 512x512 image size
        Log.i("image url ", imagesHashMap.get("Grilled Buffalo Steak"));
        */
        
        
// -- just for testing purposes ------------------------------------------- 
        
    }


    
    
    // my action bar inflater method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }




	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()){
    	
    	// what to do when the add event icon is selected 
    	case R.id.favorites_icon:
    		
    		Log.i("-->", "favorites selected");
    		
    		Intent favoritesIntent = new Intent(this, Favorites.class);

			startActivity(favoritesIntent);
			
    		return true;
    	
    	// what to do when the search overflow is selected
    	case R.id.ingredients_search_icon:
    		
    		Log.i("-->", "ingredients search selected");
    		
    		Intent ingredientsIntent = new Intent(this, IngredientsSearch.class);

			startActivity(ingredientsIntent);
    		
    		
    		return true;
    		
    		
    	// what to do when the about this app overflow is selected	
    	case R.id.about_overflow:
    		
    		Log.i("-->", "About selected");
    		
    		Intent aboutIntent = new Intent(this, About.class);

			startActivity(aboutIntent);
			
    		return true;
    		
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    	
    }
    
}
