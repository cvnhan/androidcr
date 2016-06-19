package com.cvnhan.androidcr.ui.fragment.tab1;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cvnhan.androidcr.MyApp;
import com.cvnhan.androidcr.R;
import com.cvnhan.androidcr.ui.activity.MainActivity;
import com.cvnhan.androidcr.ui.activity.MainSession;
import com.cvnhan.androidcr.ui.fragment.FragmentBase;
import com.cvnhan.androidcr.ui.view.ViewFlow;
import com.cvnhan.androidcr.utils.AppConfig;
import com.cvnhan.androidcr.utils.Utils;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.BindView;
import butterknife.OnClick;

public class Tab1Fragment extends FragmentBase {
    private static final String TAG = Tab1Fragment.class.getSimpleName();
    private static final String ARG_PARAM = "param";
    private static Tab1Fragment instance = new Tab1Fragment();
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.vToolBar)
    android.support.v7.widget.Toolbar vToolBar;
    @BindView(R.id.vToolBarButtons)
    View vToolBarButtons;
    @BindView(R.id.tvToolBarTitle)
    TextView tvToolBarTitle;
    ViewFlow viewFlow;

    private String mParam;

    public Tab1Fragment() {
    }

    public static Tab1Fragment getInstance() {
        return getInstance(null);
    }

    public static Tab1Fragment getInstance(String param) {
        if (param == null) return instance;
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        instance.setArguments(args);
        return instance;
    }

    public ViewFlow getViewFlow() {
        return viewFlow;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    protected void injectDependencies() {
        ((MainActivity) getActivity()).component().inject(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_tab1;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApp.bus.register(this);
    }

    @Override
    public void onPause() {
        MyApp.bus.unregister(this);
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    protected void onInjected() {
        super.onInjected();
        viewFlow = ViewFlow.create(container);
        MainSession.getInstance().updateCurrentViewChangeListener(Tab1Fragment.class, currentView -> {
            if (MainSession.getInstance().isTab1Fragment()) {
                YoYo.with(Techniques.BounceInDown).duration(700).playOn(vToolBarButtons);
            }
        });
    }

    @OnClick(R.id.btNavNext)
    public void btNavNextOnClick(View view) {
        Utils.hideKeyboard(view);
        view.postDelayed(() -> {
            if (Tab1Session.getInstance().isHomeScreen()) {
                Tab1Session.showScreen(viewFlow, Tab1NextScreen.create(getActivity(), null, this));
            }
        }, AppConfig.delayPressed);
    }

    @OnClick(R.id.btNavPre)
    public void btNavPreOnClick(View view) {
        Utils.hideKeyboard(view);
        view.postDelayed(() -> {
            Tab1Session.goBack(viewFlow);
        }, AppConfig.delayPressed);
    }

}
