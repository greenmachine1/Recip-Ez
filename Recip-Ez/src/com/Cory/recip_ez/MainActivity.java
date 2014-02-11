package com.Cory.recip_ez;

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
