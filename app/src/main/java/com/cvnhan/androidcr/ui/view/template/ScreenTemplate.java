package com.cvnhan.androidcr.ui.view.template;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.cvnhan.androidcr.MyApp;
import com.cvnhan.androidcr.R;
import com.cvnhan.androidcr.ui.activity.ActivityBase;

import butterknife.ButterKnife;

/**
 * Created by nhancao on 6/15/16.
 */
public class ScreenTemplate extends FrameLayout {

    RvCallback rvCallback = new RvCallBackBase();

    private ScreenTemplate(Context context, Object bundle, RvCallback callback) {
        this(context, null, 0);
        if (callback != null)
            this.rvCallback = callback;
        if (bundle != null) {

        }
    }

    public ScreenTemplate(Context context) {
        this(context, null, 0);
    }

    public ScreenTemplate(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private ScreenTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, getContentViewId(), this);
        ButterKnife.bind(this, view);
        injectDependencies();
        setupViews();
    }

    public static ScreenTemplate create(Context context) {
        return new ScreenTemplate(context, null, null);
    }

    public static ScreenTemplate create(Context context, Object bundle, RvCallback callback) {
        return new ScreenTemplate(context, bundle, callback);
    }

    protected void injectDependencies() {
        ((ActivityBase) getContext()).component().inject(this);
    }

    protected
    @LayoutRes
    int getContentViewId() {
        return R.layout.template_screen;
    }

    protected void setupViews() {

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MyApp.bus.register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        MyApp.bus.unregister(this);
        super.onDetachedFromWindow();
    }

    interface RvCallback {
        void click();
    }

    private class RvCallBackBase implements RvCallback {

        @Override
        public void click() {

        }
    }
}