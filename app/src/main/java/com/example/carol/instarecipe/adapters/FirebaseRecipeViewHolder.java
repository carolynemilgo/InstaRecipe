package com.example.carol.instarecipe.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carol.instarecipe.Constants;
import com.example.carol.instarecipe.R;
import com.example.carol.instarecipe.Recipe;
import com.example.carol.instarecipe.RecipeDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by carol on 9/22/17.
 * for mapping recipe data from firebase to android app
 */

public class FirebaseRecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView mRecipeImageView;//varibale to hold the value for making the image view public

        private static final int MAX_WIDTH = 200;
        private static final int MAX_HEIGHT = 200;

        View mView;
        Context mContext;

    public FirebaseRecipeViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindRecipe(Recipe recipe){
        mRecipeImageView = (ImageView) mView.findViewById(R.id.recipeImageView);
        ImageView recipeImageView = (ImageView) mView.findViewById(R.id.recipeImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.recipeNameTextView);
        TextView prepTextView = (TextView) mView.findViewById(R.id.prepTimeTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);


        Picasso.with(mContext).load(recipe.getImageUrl()).resize(MAX_WIDTH,MAX_HEIGHT).centerCrop().into(mRecipeImageView);

        nameTextView.setText(recipe.getRecipeName());
        prepTextView.setText(String.valueOf(recipe.getPrepTime()));
        ratingTextView.setText("Rating: " + recipe.getRating() + "/5");
    }


    public void onClick(View view) {
        final ArrayList<Recipe> recipes=  new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RECIPES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    recipes.add(snapshot.getValue(Recipe.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, RecipeDetailActivity.class);intent.putExtra("position", itemPosition + "");
                intent.putExtra("recipes", Parcels.wrap(recipes));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}