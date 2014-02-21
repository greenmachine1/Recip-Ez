/*
 * Developers name 	Cory Green
 * 
 * Project name		Recip-Ez
 * 
 * Date				Feb 11, 2014
 */
package com.Cory.recip_ez;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

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
        

    }
	


}
