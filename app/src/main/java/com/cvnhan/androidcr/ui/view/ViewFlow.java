package com.cvnhan.androidcr.ui.view;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.cvnhan.androidcr.R;

import java.util.Stack;

/**
 * Created by nhancao on 6/10/16.
 * Handle flow screen by Stack, every view will store in stack with ShowScreen method (flag saveSate must be true).
 */
public class ViewFlow {
    private static final String TAG = ViewFlow.class.getSimpleName();
    private final FrameLayout container;
    private Stack<View> viewStack;

    private ViewFlow(FrameLayout container) {
        this.container = container;
        this.viewStack = new Stack<>();
        this.syncLayout2Stack();
    }

    public static ViewFlow create(FrameLayout container) {
        return new ViewFlow(container);
    }

    /**
     * Add view element of view container to stack
     */
    private void syncLayout2Stack() {
        if (this.viewStack == null) this.viewStack = new Stack<>();
        for (int i = 0; i < container.getChildCount(); i++) {
            viewStack.push(container.getChildAt(i));
            if (container.getChildCount() == 1) break;
            container.removeViewAt(i);
            i--;
        }
    }

    public void replaceScreenTop(View enterView) {
        this.viewStack.pop();
        popView();
        showScreen(enterView);
    }

    public void showScreen(View enterView) {
        this.showScreen(enterView, true);
    }

    public void showScreen(View enterView, boolean saveThisSate) {
        if (saveThisSate) {
            this.viewStack.push(enterView);
        }
        View exitView = container.getChildAt(container.getChildCount() - 1);
        if (enterView != null) {
            Animation enter = AnimationUtils.loadAnimation(enterView.getContext(), R.anim.activity_open_translate);
            if (exitView != null) {
                Animation exit = AnimationUtils.loadAnimation(exitView.getContext(), R.anim.activity_close_scale);
                exitView.startAnimation(exit);
                exitView.postDelayed(() -> {
                    addView(enterView);
                    enterView.startAnimation(enter);
                }, exit.getDuration() / 2);
            } else {
                addView(enterView);
                enterView.startAnimation(enter);
            }
        }
    }

    public void emptyFlow() {
        if (this.viewStack != null) {
            this.viewStack.clear();
            this.container.removeAllViews();
        }
    }

    /**
     * Function will check ability can go back or not
     * Screen: 1 -> 2 -> 3 -> 4. When current screen is 1 that mean can not go back => viewStack.size() == 1
     *
     * @return true: can go back
     * false: can not
     */
    public boolean canGoBack() {
        return this.viewStack != null && this.viewStack.size() > 1;
    }

    public View getViewTop() {
        if (viewStack.isEmpty()) return null;
        return this.viewStack.peek();
    }

    public boolean goBack() {
        if (canGoBack()) {
            this.viewStack.pop();
            View enterView = this.getViewTop();
            View exitView = container.getChildAt(container.getChildCount() - 1);

            if (enterView != null) {
                Animation enter = AnimationUtils.loadAnimation(enterView.getContext(), R.anim.activity_open_scale);
                if (exitView != null) {
                    Animation exit = AnimationUtils.loadAnimation(exitView.getContext(), R.anim.activity_close_translate);
                    exitView.startAnimation(exit);
                    exitView.postDelayed(() -> {
                        addView(enterView);
                        enterView.startAnimation(enter);
                    }, exit.getDuration() / 2);
                } else {
                    addView(enterView);
                    enterView.startAnimation(enter);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private void popView() {
        if (container.getChildCount() > 0) {
            container.removeViewAt(container.getChildCount() - 1);
        }
    }

    private void addView(View view) {
        if (container.getChildCount() > 0) {
            container.removeViewAt(container.getChildCount() - 1);
        }
        container.addView(view);
    }

}