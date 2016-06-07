package com.cvnhan.androidcr.core.dagger;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.app.Activity;

import com.cvnhan.core.dagger.module.NCMCModuleActivity;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.  Activity-level components
 * should extend this component.
 */
@NCMCIPerActivity // Subtypes of ActivityComponent should be decorated with @PerActivity.
@Component(modules = NCMCModuleActivity.class)
public interface NCMCIActivityComponent {
    Activity activity(); // Expose the activity to sub-graphs.
}