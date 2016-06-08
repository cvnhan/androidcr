package com.cvnhan.androidcr.ui.activity;

import android.app.Activity;

import com.cvnhan.androidcr.core.dagger.IPerActivity;

import javax.inject.Inject;

/**
 * Created by nhancao on 6/8/16.
 */
@IPerActivity
public class ActivityTitleController {
    private final Activity activity;

    @Inject
    public ActivityTitleController(Activity activity) {
        this.activity = activity;
    }

    public void setTitle(CharSequence title) {
        activity.setTitle(title);
    }
}
