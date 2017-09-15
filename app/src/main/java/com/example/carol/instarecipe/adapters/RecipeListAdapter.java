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
    //method creates the ViewHolder object required from the adapter and inflates the layout
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        return viewHolder;
    }

    @Override
    //updates the contents of the itemView to represent a recipe at a given position
    public void onBindViewHolder(RecipeListAdapter.RecipeViewHolder holder, int position) {
        holder.bindRecipe(mRecipes.get(position));
    }

    @Override
    //sets the number of items the adapter will display
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
        @Bind(R.id.prepTimeTextView) TextView mPrepTimeTextView;
       // @Bind(R.id.ingredientsTextView) TextView mIngredientsTextView;

        private Context mContext;

        public RecipeViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext=itemView.getContext();
        }

        public void bindRecipe(Recipe recipe){
        mNameTextView.setText(recipe.getRecipeName());
        mRatingTextView.setText("Rating:"+ recipe.getRating() +"/5");
        //mPrepTimeTextView.setText(recipe.getPrepTime());

        }
    }







}
