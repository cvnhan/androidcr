package com.cvnhan.androidcr.core.dagger;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.app.Activity;

import com.cvnhan.androidcr.core.dagger.module.ModuleActivity;
import com.cvnhan.androidcr.dagger.ComponentSingleton;

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