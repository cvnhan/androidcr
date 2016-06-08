package com.cvnhan.androidcr.ui.fragment;

import android.os.Bundle;
import android.util.Log;

import com.cvnhan.androidcr.MyApp;
import com.cvnhan.androidcr.R;
import com.cvnhan.androidcr.ui.activity.ActivityTitleController;
import com.cvnhan.androidcr.ui.activity.MainActivity;

import javax.inject.Inject;

import butterknife.OnClick;

public class HomeFragment extends FragmentBase {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private static final String ARG_PARAM = "param";
    @Inject
    ActivityTitleController titleController;

    private String mParam;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
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
        return R.layout.fragment_home;
    }

    @Override
    public void onResume() {
        super.onResume();
        titleController.setTitle("Home Fragment");
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.btnHomeFragment)
    public void btnHomeFragmentOnClick() {
        Log.e(TAG, "btnHomeFragmentOnClick: ");
        ((MyApp) getActivity().getApplication()).getApplicationBus().post("test");
    }


}
