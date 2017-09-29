package com.example.carol.instarecipe.util;

/**
 * Created by carol on 9/29/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);

}


