package com.example.carol.instarecipe;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
//declare member variables(for the views-textview,editview and button) and define it at runtime in the onCreate method
    //use butterknife to bind them
    @Bind(R.id.findRecipesButton) Button mFindRecipesButton;
    @Bind(R.id.editTextView) EditText mEditTextView;
    @Bind(R.id.welcomePage) ImageView mWelcomePage;
    @Bind(R.id.introMessage)TextView mIntroMessage;
    //private TextView mIntroMessage;
    //private Button mFindRecipesButton;
    //private ImageView mWelcomePage;
    //private EditText mEditTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //mIntroMessage=(TextView) findViewById(R.id.introMessage);

        //define the font as type Typeface
        Typeface ralewayFont=Typeface.createFromAsset(getAssets(),"fonts/Raleway-ExtraLight.ttf");
        mIntroMessage.setTypeface(ralewayFont);
        //mFindRecipesButton.setTypeface(ralewayFont);

        //adding a toast to the main image on the home page using click listeners
        //mWelcomePage=(ImageView) findViewById(R.id.welcomePage);
        mWelcomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View imageView) {
                Toast.makeText(MainActivity.this, "Explore our world of recipes", Toast.LENGTH_SHORT).show();
            }
        })  ;


        //mFindRecipesButton=(Button) findViewById(R.id.findRecipesButton);
       // mFindRecipesButton.setOnClickListener(new View.OnClickListener(){
        //mEditTextView=(EditText) findViewById(R.id.editTextView);
        //mFindRecipesButton=(Button) findViewById(R.id.findRecipesButton);
        //adding a click listener to the button
        mFindRecipesButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               //declare intent of type Intent which will allow user to enter their name
               //which is displayed in the next activity-RecipeListActivity
               String recipes=mEditTextView.getText().toString();
               Intent enterRecipe=new Intent(MainActivity.this, RecipeListActivity.class);
               enterRecipe.putExtra("recipes",recipes);
               startActivity(enterRecipe);
           }

      });
    }
}
