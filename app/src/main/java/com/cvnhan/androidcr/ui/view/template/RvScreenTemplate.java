package com.cvnhan.androidcr.ui.view.template;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.cvnhan.androidcr.MyApp;
import com.cvnhan.androidcr.R;
import com.cvnhan.androidcr.ui.activity.ActivityBase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nhancao on 6/15/16.
 */
public class RvScreenTemplate extends FrameLayout {

    @BindView(R.id.etInput)
    SearchView etInput;
    @BindView(R.id.vListsItems)
    RecyclerView vListsItems;
    RvCallback rvCallback = new RvCallBackBase();

    private RvScreenTemplate(Context context, Object bundle, RvCallback callback) {
        this(context, null, 0);
        if (callback != null)
            this.rvCallback = callback;
        if (bundle != null) {

        }
    }

    private RvScreenTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, getContentViewId(), this);
        ButterKnife.bind(this, view);
        injectDependencies();
        setupViews();
    }

    public static RvScreenTemplate create(Context context) {
        return new RvScreenTemplate(context, null, null);
    }

    public static RvScreenTemplate create(Context context, Object bundle, RvCallback callback) {
        return new RvScreenTemplate(context, bundle, callback);
    }

    protected void injectDependencies() {
        ((ActivityBase) getContext()).component().inject(this);
    }

    protected
    @LayoutRes
    int getContentViewId() {
        return R.layout.template_screen;
    }

    protected void setupViews() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        vListsItems.setHasFixedSize(true);
        vListsItems.setLayoutManager(llm);
        RvAdapterTemplate adapter = new RvAdapterTemplate();
        vListsItems.setAdapter(adapter);
        adapter.setListsItems(new ArrayList<>());
        //etInput.onActionViewExpanded();
        etInput.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.setSearchText(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.setSearchText(newText);
                return true;
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MyApp.bus.register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        MyApp.bus.unregister(this);
        super.onDetachedFromWindow();
    }

    interface RvCallback {
        void click();
    }

    private class RvCallBackBase implements RvCallback {

        @Override
        public void click() {

        }
    }
}