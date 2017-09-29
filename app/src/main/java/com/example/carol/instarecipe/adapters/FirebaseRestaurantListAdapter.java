package com.example.carol.instarecipe.adapters;

import android.content.Context;


import com.example.carol.instarecipe.Recipe;
import com.example.carol.instarecipe.util.ItemTouchHelperAdapter;
import com.example.carol.instarecipe.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by carol on 9/29/17.
 */

public class FirebaseRestaurantListAdapter extends FirebaseRecyclerAdapter<Recipe, FirebaseRecipeViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseRestaurantListAdapter(Class<Recipe> modelClass, int modelLayout,
                                         Class<FirebaseRecipeViewHolder> viewHolderClass,
                                         Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void populateViewHolder(FirebaseRecipeViewHolder viewHolder, Recipe model, int position) {
        viewHolder.bindRecipe(model);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}
