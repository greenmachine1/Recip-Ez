/*
 * Developers name 	Cory Green
 * 
 * Project name		Recip-Ez
 * 
 * Date				Feb 11, 2014
 */
package com.Cory.recip_ez;

import fileManager.ParseJSON;
import android.app.Activity;
import android.os.Bundle;

public class About extends Activity{

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        
    }
	
}
