package com.cvnhan.androidcr.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by nhancao on 6/20/16.
 */
public class ResizeAnimation extends Animation {
    Integer startWidth;
    Integer startHeight;
    Integer targetWidth;
    Integer targetHeight;
    View view;

    /**
     * If you don't want effect to any edge, just set it to null
     * @param view
     * @param targetWidth
     * @param targetHeight
     */
    public ResizeAnimation(View view, Integer targetWidth, Integer targetHeight) {
        this.view = view;
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        startWidth = view.getWidth();
        startHeight = view.getHeight();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        if (targetWidth != null) {
            int newWidth = (int) (startWidth + (targetWidth - startWidth) * interpolatedTime);
            view.getLayoutParams().width = newWidth;
        }
        if(targetHeight != null){
            int newHeight = (int) (startHeight + (targetHeight - startHeight) * interpolatedTime);
            view.getLayoutParams().height = newHeight;
        }
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}