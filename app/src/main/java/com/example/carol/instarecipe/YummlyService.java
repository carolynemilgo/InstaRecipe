package com.example.carol.instarecipe;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;

import okhttp3.Call;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by carol on 9/14/17.
 */
//Contains logic neccesary for interacting with the Yummly API including using the OkHtp
public class YummlyService {
    public static void findRecipes(String recipes, Callback callback){

        OkHttpClient client=new OkHttpClient();

        HttpUrl.Builder urlBuilder=HttpUrl.parse(Constants.YUMMLY_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YUMMLY_QUERY_PARAMETER, recipes);
        String url=urlBuilder.build().toString();
        //String url2= url+Constants.YUMMLY_APP_ID+"&"+Constants.YUMMLY_TOKEN+"&"+Constants.;
        Log.v("the URL",url);

    //Create a new request with OkHttp
        Request request=new Request.Builder().url(url).build();

        //calling the request asynchronously
        Call call=client.newCall(request);
        call.enqueue(callback);
    }

    //parsing json data into the recipe constructor
public ArrayList<Recipe> processResults(Response response){
    //create an araylist-recipes-to contain all recipe objects
    ArrayList<Recipe> recipes=new ArrayList<>();

    try{
        String jsonData=response.body().string();
        if(response.isSuccessful()){
            JSONObject yummlyJSON=new JSONObject(jsonData);
            JSONArray matchesArray = yummlyJSON.getJSONArray("matches");
            for (int i = 0; i <matchesArray.length() ; i++) {
                JSONObject recipeJSON=matchesArray.getJSONObject(i);


            //target the matches array that contains information that the recipe constructor requires
            String recipeName=recipeJSON.getString("recipeName");
            String imageUrl=recipeJSON.getString("smallImageUrls");
            double rating=recipeJSON.getDouble("rating");
            int prepTime=recipeJSON.getInt("totalTimeInSeconds");
            ArrayList<String> ingredients=new ArrayList<>();
            JSONArray ingredientsJSON=recipeJSON.getJSONArray("ingredients");
                for (int j = 0; j < ingredientsJSON.length(); j++) {
                    ingredients.add(ingredientsJSON.get(j).toString());
                }

            Recipe recipe=new Recipe(recipeName,ingredients,rating,imageUrl,prepTime);
            recipes.add(recipe);
            }
        }
    }
    catch(IOException e){
        e.printStackTrace();
    }catch(JSONException e){
        e.printStackTrace();
    }
    return recipes;
}

}
