package com.cvnhan.androidcr.ui.fragment.tab3;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cvnhan.androidcr.MyApp;
import com.cvnhan.androidcr.R;
import com.cvnhan.androidcr.bus.TabIndex;
import com.cvnhan.androidcr.ui.activity.MainActivity;
import com.cvnhan.androidcr.ui.fragment.FragmentBase;
import com.cvnhan.androidcr.ui.view.ViewFlow;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.otto.Subscribe;

import butterknife.BindView;

public class Tab3Fragment extends FragmentBase {
    private static final String TAG = Tab3Fragment.class.getSimpleName();
    private static Tab3Fragment instance = new Tab3Fragment();
    private static final String ARG_PARAM = "param";
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

    public Tab3Fragment() {
    }

    public static Tab3Fragment getInstance() {
        return getInstance(null);
    }

    public static Tab3Fragment getInstance(String param) {
        if (param == null) return instance;
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        instance.setArguments(args);
        return instance;
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
        return R.layout.fragment_tab3;
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
    }

    @Subscribe
    public void tabIndexListener(TabIndex index) {
        if (index == TabIndex.TAB3) {
            YoYo.with(Techniques.BounceInDown).duration(700).playOn(vToolBarButtons);
        }
    }


}
