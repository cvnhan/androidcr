package com.cvnhan.androidcr.dagger;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.app.Activity;

import com.cvnhan.androidcr.dagger.module.ModuleActivity;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.  Activity-level components
 * should extend this component.
 */
@IPerActivity // Subtypes of ActivityComponent should be decorated with @PerActivity.
@Component(dependencies = ComponentSingleton.class, modules = ModuleActivity.class)
public interface IActivityComponent {
    Activity activity();
}