package com.example.carol.instarecipe.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.carol.instarecipe.Recipe;
import com.example.carol.instarecipe.util.ItemTouchHelperAdapter;
import com.example.carol.instarecipe.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by carol on 9/29/17.
 */

    public class FirebaseRecipeListAdapter extends FirebaseRecyclerAdapter<Recipe, FirebaseRecipeViewHolder> implements ItemTouchHelperAdapter {
        private DatabaseReference mRef;
        private Context mContext;
        private OnStartDragListener mOnStartDragListener;
        public FirebaseRecipeListAdapter(Class<Recipe> modelClass, int modelLayout,
                                             Class<FirebaseRecipeViewHolder> viewHolderClass,
                                             Query ref, OnStartDragListener onStartDragListener, Context context) {
            super(modelClass, modelLayout, viewHolderClass, ref);
            mRef = ref.getRef();
            mOnStartDragListener = onStartDragListener;
            mContext = context;
        }


    @Override
    protected void populateViewHolder(final FirebaseRecipeViewHolder viewHolder, Recipe model, int position) {
        viewHolder.bindRecipe(model);

        viewHolder.mRecipeImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }

        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        getRef(position).removeValue();
    }

    public ImageView mRecipeImageView;//varibale to hold the value for making the image view public

    }




