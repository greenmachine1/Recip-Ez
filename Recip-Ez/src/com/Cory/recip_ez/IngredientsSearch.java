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

public class IngredientsSearch extends FragmentActivity{

	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_activity);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        
    }
}
