/*
 * Developers name 	Cory Green
 * 
 * Project name		Recip-Ez
 * 
 * Date				Feb 26, 2014
 */
package com.Cory.recip_ez;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import fileManager.SetToJSON;

public class Favorites extends FragmentActivity{
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_layout);
        
        
        
        Bundle extrasBundle = getIntent().getExtras();
        
        String title = extrasBundle.getString("title");
        String imageUrl = extrasBundle.getString("url");
        String directions = extrasBundle.getString("directions");
        String ingredients = extrasBundle.getString("ingredients");
        boolean cameFromMainActivity = extrasBundle.getBoolean("fromMain");
        
        // -- signifies that the calling activity
        // -- was the main activity
        if(cameFromMainActivity == true){
        	Log.i("came from main is yes", "true");
        	// -- basically do nothing
        	
        // -- signifies that the calling activity
        // -- was the recipe details screen
        }else if(cameFromMainActivity == false){
        	
        	Log.i("not coming from main", "True");
        	
        	// -- start of the new JSON file creation
        	SetToJSON newSetToJSON = new SetToJSON(this);
        	newSetToJSON.setEverythingToJSON(title, imageUrl, directions, ingredients);

        }
        

    }
	


}
