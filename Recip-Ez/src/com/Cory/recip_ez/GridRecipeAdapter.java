package com.Cory.recip_ez;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

// -- Grid adapter section -- //
public class GridRecipeAdapter extends BaseAdapter{
	
	private Context context;
	public Integer[] listOfImages = null;
	
	public GridRecipeAdapter(Context c, Integer[] imageIDs){
		context = c;
		listOfImages = imageIDs;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listOfImages.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	
	// setting the image to the individual squares
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ImageView imageView;
		if(convertView == null){
			
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(200,200));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

			imageView.setPadding(10, 10, 10, 10);
		
		}else{
			imageView = (ImageView) convertView;
		}
		imageView.setImageResource(listOfImages[position]);
		return imageView;

	}
	
}