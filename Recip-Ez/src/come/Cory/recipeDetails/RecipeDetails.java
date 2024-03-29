/*
 * Developers name 	Cory Green
 * 
 * Project name		Recip-Ez
 * 
 * Date				Feb 26, 2014
 */
package come.Cory.recipeDetails;

import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.Cory.nutritionFacts.NutritionFacts;
import com.Cory.recip_ez.About;
import com.Cory.recip_ez.Favorites;
import com.Cory.recip_ez.IngredientsSearch;
import com.Cory.recip_ez.R;
import com.loopj.android.image.SmartImageView;

import fileManager.ParseJSON;
import fileManager.SetToJSON;

public class RecipeDetails extends Activity{
	
	TextView title;
	TextView ingredientsContent;
	TextView directionsContent;
	SmartImageView mainImage;
	
	String titleString;
	String ingredientsString;
	String directionsString;
	String imageUrl;
	
	int positionInList;
	
	boolean comingFromFavorites = false;
	
	private ShareActionProvider mShareActionProvider;
	
	ActionMode mActionMode;


	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_details);

        
        // -- getting some extras that are passed in
        Bundle extras = getIntent().getExtras();
        titleString = extras.getString("title");
        ingredientsString = extras.getString("ingredients");
        directionsString = extras.getString("directions");
        imageUrl = extras.getString("url");
        comingFromFavorites = extras.getBoolean("comingFromFavorites");
        
        // -- telling the activity that if its coming
        // -- from the favorites activity
        // -- to set the action bar a certian way
        if(comingFromFavorites == true){
        	mActionMode = this.startActionMode(mActionModeCallBack);
        	positionInList = extras.getInt("position");
        	
        	Log.i("position selected", "" + positionInList);
        }
        
        
        
        title = (TextView)findViewById(R.id.title_details_view);
        ingredientsContent = (TextView)findViewById(R.id.ingredients_content);
        directionsContent = (TextView)findViewById(R.id.directions_content);
        mainImage = (SmartImageView)findViewById(R.id.main_image_detail_view);
        
        title.setText(titleString);
        
        // -- trimming off pieces of the directions and ingredients
        // -- then setting them to the view
        
        directionsContent.setText(cuttingTheFatFromDirections(directionsString));
        ingredientsContent.setText(cuttingTheFatFromIngredients(ingredientsString));
        
        //Log.i("ingredients", ingredientsString);
        
        mainImage.setImageUrl(imageUrl);
        
        
        
    }
    
    // my action bar inflater method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details_menu, menu);
        
        MenuItem item = menu.findItem(R.id.share_icon);
        
        // -- this sets up my share intent
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, titleString);
        shareIntent.putExtra(Intent.EXTRA_TEXT, cuttingTheFatFromDirections(directionsString));
        shareIntent.setType(HTTP.PLAIN_TEXT_TYPE);
        
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();
        mShareActionProvider.setShareIntent(shareIntent);
        return true;
    }
    
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()){
    	
    	// what to do when the add event icon is selected 
    	case R.id.nutrition_facts_icon:
    		
    		Log.i("-->", "nutrition facts selected");
    		
    		Intent nutritionFacts = new Intent(this, NutritionFacts.class);
    		nutritionFacts.putExtra("title", titleString);
    		nutritionFacts.putExtra("ingredients", ingredientsString);

			startActivity(nutritionFacts);
			
    		return true;
    	

    	// what to do when the about this app overflow is selected	
    	case R.id.add_to_favorites_icon:
    		
    		Log.i("-->", "Add to favorites selected");
    		
    		// -- start of my favorites intent
    		// -- passing in pretty much everything about this ingredient
    		Intent favoritesIntent = new Intent(this, Favorites.class);
    		
    		favoritesIntent.putExtra("title", titleString);
    		favoritesIntent.putExtra("url", imageUrl);
    		favoritesIntent.putExtra("ingredients", ingredientsString);
    		favoritesIntent.putExtra("directions", directionsString);
    		favoritesIntent.putExtra("fromMain", false);
    		

			startActivity(favoritesIntent);

			
    		return true;
    		
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    	
    }
    
    private void setShareIntent(Intent shareIntent){
    	if(mShareActionProvider != null){
    		mShareActionProvider.setShareIntent(shareIntent);
    	}
    }
    
    
	// -- contextual action bar
	private ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
		
		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void onDestroyActionMode(ActionMode mode) {
			// TODO Auto-generated method stub
			mActionMode = null;
		}
		
		// -- inflates the contextual action bar
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			
			MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate(R.menu.favorites_contextual_action_bar, menu);
			return true;
		}
		
		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			
			switch (item.getItemId()){
			case R.id.delete_icon:
				
				// -- getting into the setToJson stuff
				SetToJSON newSetToJSON = new SetToJSON(getApplication());
				newSetToJSON.deleteJSON(titleString);
				
				mode.finish();
				finish();
				return true;
			default:
				return false;
			}

			
			

		}
	};
    
    
    
    
    
    
    // -- here I need to go through the directions and 
    // -- get rid of the [] at the beginning and end
    // -- and also get rid of "" throughout
    public String cuttingTheFatFromDirections(String passedInDirections){
    	
    	String tempString = passedInDirections;
    	
    	String newStringMinusFrontBracket = tempString.replace("[", "");
    	String newStringMinusBothBrackets = newStringMinusFrontBracket.replace("]", "");
    	
    	String newStringMinusFrontAndRearQuotes = newStringMinusBothBrackets.replace("\"", "");
    	
    	String finalStringToBeDisplayed = newStringMinusFrontAndRearQuotes.replace(".,", ". ");
    	
    	return finalStringToBeDisplayed;
    	
    }
    
    // -- method used to cut the [] and add new lines where needed
    public String cuttingTheFatFromIngredients(String passedInIngredients){
    	
    	String tempString = passedInIngredients;
    	
    	String newStringMinusFrontBracket = tempString.replace("[", "");
    	
    	String newStringMinusBothBrackets = newStringMinusFrontBracket.replace("]", "");
    	
    	String newStringWithNewLine = newStringMinusBothBrackets.replace(",", "\n");
    	
    	
    	return newStringWithNewLine;
    	
    	
    }
}
