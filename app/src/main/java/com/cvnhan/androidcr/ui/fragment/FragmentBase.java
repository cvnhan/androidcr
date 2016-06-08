package com.cvnhan.androidcr.ui.fragment;
/**
 * Created by NhanCao on 13-Sep-15.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class FragmentBase extends Fragment {

    private Unbinder unbinder;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectDependencies();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final int layoutId = getContentViewId();
        if (layoutId == 0) {
            final View contentView = getContentView();
            if (contentView != null) {
                return contentView;
            }
        } else {
            return inflater.inflate(getContentViewId(), container, false);
        }
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
        onInjected();
    }

    protected abstract void injectDependencies();

    protected abstract int getContentViewId();

    protected View getContentView() {
        return null;
    }

    protected void injectViews(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    protected void onInjected() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
