package com.example.carol.instarecipe;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carol on 9/14/17.
 */
@Parcel
public class Recipe {
    String recipeName;
    //private String  mId;
   List <String> ingredients = new ArrayList<>();
    double rating;
    String imageUrl;
    int prepTime;
    //private double mCalories;
    String webUrl;


    public Recipe() {}

    public Recipe(String recipeName, ArrayList<String> ingredients, double rating, String imageUrl, int prepTime) {
        this.recipeName = recipeName;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.ingredients = ingredients;
        this.prepTime=prepTime;
        //this.mCalories=calories;
       //this.mWebUrl=webUrl;
    }

    //getter methods
    public String getRecipeName() {
        return recipeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public double getRating() {
        return rating;
    }
    public int getPrepTime(){
        return prepTime/60;
    }
//    public double getCalories(){
//        return mCalories;
//    }

//    public String getUrl(){
//        return mWebUrl;
//    }
}