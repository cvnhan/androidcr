package com.cvnhan.androidcr.dagger;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import com.cvnhan.androidcr.ui.activity.YKMAMainActivity;
import com.cvnhan.core.dagger.NCMCIPerActivity;
import com.cvnhan.core.dagger.module.NCMCModuleActivity;

import dagger.Component;

@NCMCIPerActivity
@Component(modules = NCMCModuleActivity.class, dependencies = YKMAIComponentSingleton.class)
public interface YKMAIComponentUi {
    void inject(YKMAMainActivity mainActivity);
}
