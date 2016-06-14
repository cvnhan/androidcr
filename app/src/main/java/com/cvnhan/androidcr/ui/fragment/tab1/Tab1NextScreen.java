package com.cvnhan.androidcr.ui.fragment.tab1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.cvnhan.androidcr.R;
import com.cvnhan.androidcr.ui.activity.ActivityBase;

import butterknife.ButterKnife;

/**
 * Created by nhancao on 6/14/16.
 */
public class Tab1NextScreen extends FrameLayout {
    Fragment fragment;

    private Tab1NextScreen(Context context, Bundle bundle, Fragment fragment) {
        this(context, null, 0);
        this.fragment = fragment;
        if (bundle != null) {

        }
    }

    private Tab1NextScreen(Context context) {
        this(context, null, 0);
    }

    private Tab1NextScreen(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private Tab1NextScreen(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, getContentViewId(), this);
        ButterKnife.bind(this, view);
        injectDependencies();
        setupViews();
    }

    public static Tab1NextScreen create(Context context, Bundle bundle, Fragment fragment) {
        return new Tab1NextScreen(context, bundle, fragment);
    }

    protected void injectDependencies() {
        ((ActivityBase) getContext()).component().inject(this);
    }

    protected
    @LayoutRes
    int getContentViewId() {
        return R.layout.fragment_tab1_next_screen;
    }

    protected void setupViews() {

    }
}
