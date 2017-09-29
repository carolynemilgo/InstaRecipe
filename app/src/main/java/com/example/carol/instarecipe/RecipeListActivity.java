package com.example.carol.instarecipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.carol.instarecipe.adapters.RecipeListAdapter;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipeListActivity extends AppCompatActivity {
    public static final String TAG = RecipeListActivity.class.getSimpleName();

    //add shared preferences to allow users search recipes with the search widget and the editor
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentRecipe;

    private Button mSubmitRecipeButton;
    //  private TextView mRecipeTextView;
    //private ListView mListView;

    //use butterknife to bind the views
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.listView)ListView mListView;
    @Bind(R.id.recipeTextView) TextView mRecipeTextView;
    //declare array to display the categories of recipes available*Commented out because we no longer need a hardcoded arraylist*
   // private String[] recipeOptions = new String[]{"Protein Slow-Cooker Meals", "Pastries and Deserts", "Juices and Smoothies", "Slow Cooker Beef"};
    private RecipeListAdapter mAdapter;


    public ArrayList<Recipe> mRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
       // mListView = (ListView) findViewById(R.id.listView);
       // mRecipeTextView = (TextView) findViewById(R.id.recipeTextView);
        //mSubmitRecipeButton = (Button) findViewById(R.id.submitRecipe);
        ButterKnife.bind(this);



        Intent enterRecipe = getIntent();
        String recipes = enterRecipe.getStringExtra("recipes");
        mRecipeTextView.setText("Find all the dishes related to your search:" + recipes +" !");

//implementing array adapters to allow display of the list items on the layout*Commented out: previously used on the hard coded array*
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recipeOptions);
//        mListView.setAdapter(adapter);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String restaurant = ((TextView) view).getText().toString();
//                Toast.makeText(RecipeListActivity.this, restaurant, Toast.LENGTH_LONG).show();
//            }
//        });
        getRecipes(recipes);
    }


    //method to write data to shared preferences
    private void addToSharedPreferences(String recipe) {
        mEditor.putString(Constants.PREFERENCES_RECIPE_KEY, recipe).apply();
    }

    //receiving a response
    private void getRecipes(String recipes) {
        //Create a new instance of yummly service and call the find recipes method
        final YummlyService yummlyService = new YummlyService();
        yummlyService.findRecipes(recipes, new Callback() {

            //Method onFailure triggered when the request fails
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            //Method onResponse triggered when the request is successful
            @Override
            public void onResponse(Call call, Response response) {
                mRecipes = yummlyService.processResults(response);

                RecipeListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mAdapter=new RecipeListAdapter(getApplicationContext(),mRecipes);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(RecipeListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

//                        //create a list of names to allow user to view several options when enter a recipe name
//                        String[] recipeNames = new String[mRecipes.size()];
//                        for (int i = 0; i < recipeNames.length; i++) {
//                            recipeNames[i] = mRecipes.get(i).getRecipeName();
//                        }
//                        //create an array adapter to pass data into the view
//                        ArrayAdapter adapter = new ArrayAdapter(RecipeListActivity.this,
//                                android.R.layout.simple_list_item_1, recipeNames);
//                        mListView.setAdapter(adapter);
//                        for(Recipe recipe: mRecipes){
//                            Log.d(TAG, "recipeName:" + recipe.getRecipeName());
//                            Log.d(TAG, "Ingredients:" + recipe.getIngredients().toString());
//                        }
                    }

//
//
//                    throws IOException {
//                try{
//                    //create new string-jsonData-and set it to the string of the response body
//                    String jsonData=response.body().string();
//                    //print data in the log cat and siplay error messages
//                    Log.v(TAG,jsonData);
//                    //trigger the process results method inside the recipesActivity on response method
//                    //to collect its return value in a member variable called mRecipes
//                }catch(IOException e){
//                    e.printStackTrace();
//
//                }
//
//            }
                });
            }
        });
    }
}


