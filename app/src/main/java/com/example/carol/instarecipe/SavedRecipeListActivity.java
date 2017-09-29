package com.example.carol.instarecipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.carol.instarecipe.adapters.FirebaseRecipeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRecipeListActivity extends AppCompatActivity {


    private DatabaseReference mRecipeReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);

        mRecipeReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RECIPES);
        setUpFirebaseAdapter();
    }
    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Recipe, FirebaseRecipeViewHolder>
                (Recipe.class, R.layout.recipe_list_item_drag, FirebaseRecipeViewHolder.class,
                        mRecipeReference) {

            @Override
            protected void populateViewHolder(FirebaseRecipeViewHolder viewHolder,
                                              Recipe model, int position) {

                viewHolder.bindRecipe(model);
            }
        };
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
