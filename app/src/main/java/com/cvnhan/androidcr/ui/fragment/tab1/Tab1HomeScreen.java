package com.cvnhan.androidcr.ui.fragment.tab1;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.cvnhan.androidcr.R;
import com.cvnhan.androidcr.ui.activity.ActivityBase;

import butterknife.ButterKnife;

/**
 * Created by nhancao on 6/12/16.
 */
public class Tab1HomeScreen extends FrameLayout {

    public Tab1HomeScreen(Context context) {
        this(context, null, 0);
    }

    public Tab1HomeScreen(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Tab1HomeScreen(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (getContentViewId() != 0)
            inflate(context, getContentViewId(), this);
        ButterKnife.bind(this);
        injectDependencies();
        setupViews();
    }

    protected void injectDependencies() {
        ((ActivityBase) getContext()).component().inject(this);
    }

    protected
    @LayoutRes
    int getContentViewId() {
        return R.layout.fragment_tab1_home_screen;
    }

    protected void setupViews() {

    }


}
