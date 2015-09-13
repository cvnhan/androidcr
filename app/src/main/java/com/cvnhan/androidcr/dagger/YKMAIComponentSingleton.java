package com.cvnhan.androidcr.dagger;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.app.Application;

import com.cvnhan.androidcr.dagger.module.YKMAModuleModel;
import com.cvnhan.androidcr.dagger.module.YKMAModulePresenter;
import com.cvnhan.androidcr.dagger.module.YKMAModuleUtils;
import com.cvnhan.androidcr.utils.YKMAIUtilImageLoader;
import com.cvnhan.core.dagger.module.NCMCModuleApplication;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {YKMAModuleUtils.class, NCMCModuleApplication.class, YKMAModuleModel.class, YKMAModulePresenter.class})
public interface YKMAIComponentSingleton {

    Application application();

    Bus bus();

    YKMAIUtilImageLoader imageLoader();

}