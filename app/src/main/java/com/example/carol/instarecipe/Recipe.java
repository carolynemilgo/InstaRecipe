package com.example.carol.instarecipe;

import java.util.ArrayList;

/**
 * Created by carol on 9/14/17.
 */

public class Recipe {
    private String mRecipeName;
    //private String  mId;
    private ArrayList<String> mIngredients = new ArrayList<>();
    private double mRating;
    private String mImageUrl;
    private double mPrepTime;


    public Recipe(String recipeName, ArrayList<String> ingredients, double rating, String imageUrl, double prepTime) {
        this.mRecipeName = recipeName;
        this.mImageUrl = imageUrl;
        this.mRating = rating;
        this.mIngredients = ingredients;
        this.mPrepTime=prepTime;
    }

    //getter methods
    public String getRecipeName() {
        return mRecipeName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public ArrayList<String> getIngredients() {
        return mIngredients;
    }

    public double getRating() {
        return mRating;
    }
    public double getPrepTime(){
        return mPrepTime/60;
    }
}