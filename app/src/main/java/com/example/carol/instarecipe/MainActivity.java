package com.example.carol.instarecipe;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//declare member variables(for the views-textview,editview and button) and define it at runtime in the onCreate method
    //use butterknife to bind them
    @Bind(R.id.findRecipesButton) Button mFindRecipesButton;
    @Bind(R.id.editTextView) EditText mEditTextView;
    @Bind(R.id.welcomePage) ImageView mWelcomePage;
    @Bind(R.id.introMessage)TextView mIntroMessage;

    @Bind(R.id.savedRecipesButton) Button mSavedRecipesButton;

    private DatabaseReference mSearchedRecipeReference;

    private ValueEventListener mSearchedRecipeReferenceListener;//
    //private DatabaseReference FirebaseDatabase;

    //private TextView mIntroMessage;
    //private Button mFindRecipesButton;
    //private ImageView mWelcomePage;
    //private EditText mEditTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedRecipeReference= FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_RECIPE);



//attaching a listener
        mSearchedRecipeReferenceListener= mSearchedRecipeReference.addValueEventListener(new ValueEventListener() { //attach listener

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot recipeSnapshot : dataSnapshot.getChildren()) {
                    String recipes = recipeSnapshot.getValue().toString();
                    Log.d("Recipes updated", "recipes: " + recipes); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.

            }
        });




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //mIntroMessage=(TextView) findViewById(R.id.introMessage);

        //define the font as type Typeface

        Typeface ralewayFont=Typeface.createFromAsset(getAssets(),"fonts/Raleway-ExtraLight.ttf");
        mIntroMessage.setTypeface(ralewayFont);

//
//        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Raleway-ExtraLight.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf



         mFindRecipesButton.setTypeface(ralewayFont);

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
        mFindRecipesButton.setOnClickListener(this);
        mSavedRecipesButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        //declare intent of type Intent which will allow user to enter their name
        //which is displayed in the next activity-RecipeListActivity
        String recipes = mEditTextView.getText().toString();

        saveLocationToFirebase(recipes);
        if(v==mFindRecipesButton){
        Intent enterRecipe = new Intent(MainActivity.this, RecipeListActivity.class);
        enterRecipe.putExtra("recipes", recipes);
        startActivity(enterRecipe);
    }

        if(v == mSavedRecipesButton) {
            Intent intent = new Intent(MainActivity.this, SavedRecipeListActivity.class);
            startActivity(intent);
    }

    }


    //method that runs when activity is halted
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedRecipeReference.removeEventListener(mSearchedRecipeReferenceListener);
    }

    public void saveLocationToFirebase(String recipes) {
        mSearchedRecipeReference.setValue(recipes);
    }

    //used to inflate the overflow menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

//instruct app to performa a specific action when logout option is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
