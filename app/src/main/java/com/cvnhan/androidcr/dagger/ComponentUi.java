package com.cvnhan.androidcr.dagger;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import com.cvnhan.androidcr.dagger.module.ModuleActivity;
import com.cvnhan.androidcr.ui.activity.MainActivity;
import com.cvnhan.androidcr.ui.fragment.tab1.Tab1Fragment;
import com.cvnhan.androidcr.ui.fragment.tab1.Tab1HomeScreen;
import com.cvnhan.androidcr.ui.fragment.tab1.Tab1NextScreen;
import com.cvnhan.androidcr.ui.fragment.tab2.Tab2Fragment;
import com.cvnhan.androidcr.ui.fragment.tab2.Tab2HomeScreen;
import com.cvnhan.androidcr.ui.fragment.tab3.Tab3Fragment;
import com.cvnhan.androidcr.ui.fragment.tab3.Tab3HomeScreen;
import com.cvnhan.androidcr.ui.view.template.RvScreenTemplate;
import com.cvnhan.androidcr.ui.view.template.RvStickyScreenTemplate;
import com.cvnhan.androidcr.ui.view.template.ScreenTemplate;

import dagger.Component;

@IPerActivity
@Component(modules = ModuleActivity.class, dependencies = ComponentSingleton.class)
public interface ComponentUi extends IActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(ScreenTemplate screenTemplate);

    void inject(RvScreenTemplate rvScreenTemplate);

    void inject(RvStickyScreenTemplate rvStickyScreenTemplate);

    void inject(Tab1Fragment tab1Fragment);

    void inject(Tab1HomeScreen tab1HomeScreen);

    void inject(Tab1NextScreen tab1NextScreen);

    void inject(Tab2Fragment tab2Fragment);

    void inject(Tab2HomeScreen tab2HomeScreen);

    void inject(Tab3Fragment tab3Fragment);

    void inject(Tab3HomeScreen tab3HomeScreen);

}
