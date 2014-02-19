/*
 * Developers name 	Cory Green
 * 
 * Project name		Recip-Ez
 * 
 * Date				Feb 11, 2014
 */
package com.Cory.recip_ez;



import com.Cory.service_package.PearsonAPIService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // -- starting of the service intent
        // -- this will be tied in with the 'go' button on the keypad
        // -- that gets brought up when the user searches
        
        Intent serviceIntent = new Intent(this, PearsonAPIService.class);
        
        // -- this will be the extras that the user passes in via search 
        serviceIntent.putExtra("search", "");
        
        // -- this is the random offset that will be generated
        // -- to give a random recipe
        serviceIntent.putExtra("offset", 30);
        
        startService(serviceIntent);

        
        

        
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
