package com.Cory.recip_ez;

import android.graphics.drawable.Drawable;

public class Ingredients_adapter_definition {

	public String mainTextView;
	public int checkIcon;
	
	
	public Ingredients_adapter_definition(){
		super();
	}
	
	public Ingredients_adapter_definition(String textViewText, int images){
		super();
		
		this.mainTextView = textViewText;
		this.checkIcon = images;
	}
	
}
