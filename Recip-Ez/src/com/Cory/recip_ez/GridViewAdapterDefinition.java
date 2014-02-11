/*
 * Developers name 	Cory Green
 * 
 * Project name		Recip-Ez
 * 
 * Date				Feb 11, 2014
 */
package com.Cory.recip_ez;

public class GridViewAdapterDefinition {
	
	public Integer mainImage;
	public String recipeTitle;
	
	public GridViewAdapterDefinition(){
		super();
	}

	public GridViewAdapterDefinition(Integer imageIDs, String titles){
		super();
		
		this.mainImage = imageIDs;
		this.recipeTitle = titles;
		
	}

}

