package com.cvnhan.androidcr.ui.fragment.tab2;

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
public class Tab2HomeScreen extends FrameLayout {

    public Tab2HomeScreen(Context context) {
        this(context, null, 0);
    }

    public Tab2HomeScreen(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Tab2HomeScreen(Context context, AttributeSet attrs, int defStyleAttr) {
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
        return R.layout.fragment_tab2_home;
    }

    protected void setupViews() {

    }


}
