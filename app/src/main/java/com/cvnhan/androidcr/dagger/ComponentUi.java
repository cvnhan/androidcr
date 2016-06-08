package com.cvnhan.androidcr.dagger;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import com.cvnhan.androidcr.core.dagger.IActivityComponent;
import com.cvnhan.androidcr.core.dagger.IPerActivity;
import com.cvnhan.androidcr.core.dagger.module.ModuleActivity;
import com.cvnhan.androidcr.ui.activity.MainActivity;
import com.cvnhan.androidcr.ui.fragment.HomeFragment;

import dagger.Component;

@IPerActivity
@Component(modules = ModuleActivity.class, dependencies = ComponentSingleton.class)
public interface ComponentUi extends IActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(HomeFragment homeFragment);
}
