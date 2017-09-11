package com.example.carol.instarecipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RecipesActivity extends AppCompatActivity {
    private Button mSubmitRecipeButton;
    private TextView mRecipeTextView;
    private ListView mListView;
    //declare array to display the categories of recipes available
    private String[] recipeOptions=new String[] {"Protein Slow-Cooker Meals", "Pastries and Deserts", "Juices and Smoothies", "Slow Cooker Beef"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        mListView = (ListView) findViewById(R.id.listView);
        mRecipeTextView=(TextView) findViewById(R.id.recipeTextView);
        mSubmitRecipeButton=(Button) findViewById(R.id.submitRecipe);

        Intent findRecipes=getIntent();
        String guestName=findRecipes.getStringExtra("guestName");
        mRecipeTextView.setText("Hello " +guestName+".We invite you to savour our delicious recipes!");

//implementing array adapters to allow dispaly of the list items on the layout
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recipeOptions);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(RecipesActivity.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });
    }
}


