package com.cvnhan.androidcr.ui.view;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.cvnhan.androidcr.R;

import java.util.Stack;

/**
 * Created by nhancao on 6/10/16.
 */
public class ViewFlow {
    private static final String TAG = ViewFlow.class.getSimpleName();
    private final FrameLayout container;
    private Stack<View> backStack;

    private ViewFlow(FrameLayout container) {
        this.container = container;
        this.backStack = new Stack();
    }

    public static ViewFlow create(FrameLayout container) {
        return new ViewFlow(container);
    }

    public void clearStack() {
        if (this.backStack != null) {
            this.backStack.clear();
        }
    }

    public void showScreen(View enterView, boolean addToBackStack) {
        View exitView = container.getChildAt(0);
        if (addToBackStack) {
            if (exitView != null) {
                this.backStack.push(exitView);
            }
        }

        if (!backStack.isEmpty()) {
            Animation enter = AnimationUtils.loadAnimation(enterView.getContext(), R.anim.slide_in_left);
            enter.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            if (exitView != null) {
                Animation exit = AnimationUtils.loadAnimation(exitView.getContext(), R.anim.slide_out_right);
                exit.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                exitView.startAnimation(exit);
                exitView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addView(enterView);
                        enterView.startAnimation(enter);
                    }
                }, exit.getDuration() / 2);
            }

        }
    }

    public void pop() {
        this.backStack.pop();
    }

    public boolean navigateBack() {
        if (this.backStack != null && !this.backStack.isEmpty()) {
            View enterView = (View) this.backStack.pop();
            View exitView = container.getChildAt(0);
            Animation enter = AnimationUtils.loadAnimation(enterView.getContext(), R.anim.slide_in_right);
            enter.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            if (exitView != null) {
                Animation exit = AnimationUtils.loadAnimation(exitView.getContext(), R.anim.slide_out_left);
                exit.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                exitView.startAnimation(exit);
                exitView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addView(enterView);
                        enterView.startAnimation(enter);
                    }
                }, exit.getDuration() / 2);
            }

            return true;
        } else {
            return false;
        }
    }

    private void addView(View view) {
        container.removeAllViews();
        container.addView(view);
    }

}