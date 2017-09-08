package com.example.carol.instarecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RecipesActivity extends AppCompatActivity {
    private TextView mRecipeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        mRecipeTextView=(TextView) findViewById(R.id.recipeTextView);

        Intent findRecipes=getIntent();
        String guestName=findRecipes.getStringExtra("guestName");
        mRecipeTextView.setText("Hello " +guestName+".We invite you to savour our delicious recipes");
    }
}


