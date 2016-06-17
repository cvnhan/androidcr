package com.cvnhan.androidcr.utils.rvhelper;

/**
 * Created by nhancao on 6/16/16.
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
