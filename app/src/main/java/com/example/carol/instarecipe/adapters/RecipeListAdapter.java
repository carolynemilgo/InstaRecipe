package com.example.carol.instarecipe.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carol.instarecipe.R;
import com.example.carol.instarecipe.Recipe;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by carol on 9/15/17.
 */
//class extends the recyclerviewadapter class
public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private ArrayList<Recipe> mRecipes=new ArrayList<>();//required to calculate the recipe counts so
    //the recycler is aware how many individual list item views it needs to recycle
    private Context mContext;//required to create viewholder


    public RecipeListAdapter(Context context, ArrayList<Recipe> recipes){
        mContext=context;
        mRecipes=recipes;
    }
//create the methods required by the RecycleViewAdapter

    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListAdapter.RecipeViewHolder holder, int position) {
        holder.bindRecipe(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }





    //create a nested class which is our view holder
    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.recipeNameTextView)
        TextView mNameTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        @Bind(R.id.recipeImageView)
        ImageView mRecipeImageView;

        private Context mContext;

        public RecipeViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext=itemView.getContext();
        }

        public void bindRecipe(Recipe recipe){
        mNameTextView.setText(recipe.getRecipeName());
        mRatingTextView.setText("Rating:"+ recipe.getRating() +"/5");
        }
    }







}
