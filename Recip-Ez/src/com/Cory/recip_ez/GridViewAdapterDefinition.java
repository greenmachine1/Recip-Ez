/*
 * Developers name 	Cory Green
 * 
 * Project name		Recip-Ez
 * 
 * Date				Feb 11, 2014
 */
package com.Cory.recip_ez;



public class GridViewAdapterDefinition {
	
	public String mainImage;
	public String recipeTitle;
	
	public GridViewAdapterDefinition(){
		super();
	}

	public GridViewAdapterDefinition(String totalUrl, String titles){
		super();
		
		this.mainImage = totalUrl;
		this.recipeTitle = titles;
		
	}

}

