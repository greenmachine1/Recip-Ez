package come.Cory.recipeDetails;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.Cory.recip_ez.R;
import com.loopj.android.image.SmartImageView;

public class RecipeDetails extends Activity{
	
	TextView title;
	TextView ingredientsContent;
	TextView directionsContent;
	SmartImageView mainImage;
	
	String titleString;
	String ingredientsString;
	String directionsString;
	String imageUrl;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_details);
        
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        
        // -- getting some extras that are passed in
        Bundle extras = getIntent().getExtras();
        titleString = extras.getString("title");
        ingredientsString = extras.getString("ingredients");
        directionsString = extras.getString("directions");
        imageUrl = extras.getString("url");
        
        
        title = (TextView)findViewById(R.id.title_details_view);
        ingredientsContent = (TextView)findViewById(R.id.ingredients_content);
        directionsContent = (TextView)findViewById(R.id.directions_content);
        mainImage = (SmartImageView)findViewById(R.id.main_image_detail_view);
        
        title.setText(titleString);
        cuttingTheFatFromDirections(directionsString);
        
        mainImage.setImageUrl(imageUrl);
        
        
        
    }
    
    
    // -- here I need to go through the directions and 
    // -- get rid of the [] at the beginning and end
    // -- and also get rid of "" throughout
    public void cuttingTheFatFromDirections(String passedInDirections){
    	directionsContent.setText(passedInDirections);
    }
}
