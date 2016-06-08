package com.cvnhan.androidcr.dagger.module;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import com.cvnhan.androidcr.mvp.model.MRadio;
import com.cvnhan.androidcr.mvp.presenter.PRadio;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModulePresenter {

    @Provides
    @Singleton
    public PRadio providePRadio(MRadio model) {
        return new PRadio(model);
    }

}
