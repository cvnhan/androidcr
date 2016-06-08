package com.cvnhan.androidcr.dagger;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.app.Application;
import android.location.LocationManager;

import com.cvnhan.androidcr.MyApplication;
import com.cvnhan.androidcr.dagger.module.ModuleApplication;
import com.cvnhan.androidcr.utils.ImageLoader;
import com.cvnhan.androidcr.dagger.module.ModuleModel;
import com.cvnhan.androidcr.dagger.module.ModulePresenter;
import com.cvnhan.androidcr.dagger.module.ModuleUtils;
import com.cvnhan.androidcr.mvp.presenter.PRadio;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {ModuleUtils.class, ModuleApplication.class, ModuleModel.class, ModulePresenter.class})
public interface ComponentSingleton {
    // Field injections of any dependencies of the DemoApplication
    void inject(MyApplication application);

    // Exported for child-components.
    Application application();

    LocationManager locationManager();
    Bus bus();

    ImageLoader imageLoader();

    //Presenter
    PRadio pRadio();
}