package com.cvnhan.androidcr.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cvnhan.androidcr.MyApp;
import com.cvnhan.androidcr.R;
import com.cvnhan.androidcr.bus.TabIndex;
import com.cvnhan.androidcr.ui.fragment.NFragmentPagerAdapter;
import com.cvnhan.androidcr.ui.fragment.tab1.Tab1Fragment;
import com.cvnhan.androidcr.ui.fragment.tab2.Tab2Fragment;
import com.cvnhan.androidcr.ui.fragment.tab3.Tab3Fragment;
import com.cvnhan.androidcr.ui.view.NSwipeViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by NhanCao on 13-Sep-15.
 */
public class MainActivity extends ActivityBase {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.vTab1)
    View vTab1;
    @BindView(R.id.imgTab1)
    ImageView imgTab1;
    @BindView(R.id.tvTab1)
    TextView tvTab1;
    @BindView(R.id.vTab2)
    View vTab2;
    @BindView(R.id.imgTab2)
    ImageView imgTab2;
    @BindView(R.id.tvTab2)
    TextView tvTab2;
    @BindView(R.id.vTab3)
    View vTab3;
    @BindView(R.id.imgTab3)
    ImageView imgTab3;
    @BindView(R.id.tvTab3)
    TextView tvTab3;
    @BindView(R.id.viewPager)
    NSwipeViewPager viewPager;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    NFragmentPagerAdapter adapterFragmentPager;

    @Override
    protected void onCreated(Bundle savedInstanceState) {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(Tab1Fragment.getInstance());
        fragments.add(Tab2Fragment.getInstance());
        fragments.add(Tab3Fragment.getInstance());

        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(adapterFragmentPager = new NFragmentPagerAdapter.Builder(getSupportFragmentManager()).setItems(fragments).build());
        viewPager.addOnPageChangeListener(new NSwipeViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateTabBarView(position);
            }
        });
        updateTabBarView(0);
    }


    public void updateTabBarView(int position) {
        vTab1.setEnabled(true);
        vTab2.setEnabled(true);
        vTab3.setEnabled(true);
        swipeRefreshLayout.setEnabled(false);
        imgTab1.setBackgroundResource(R.drawable.tab_icon);
        tvTab1.setTextColor(getResources().getColor(R.color.tab_text_normal));

        imgTab2.setBackgroundResource(R.drawable.tab_icon);
        tvTab2.setTextColor(getResources().getColor(R.color.tab_text_normal));

        imgTab3.setBackgroundResource(R.drawable.tab_icon);
        tvTab3.setTextColor(getResources().getColor(R.color.tab_text_normal));

        switch (position) {
            case 0:
                vTab1.setEnabled(false);
                imgTab1.setBackgroundResource(R.drawable.tab_icon);
                tvTab1.setTextColor(getResources().getColor(R.color.white));
                break;
            case 1:
                vTab2.setEnabled(false);
                imgTab2.setBackgroundResource(R.drawable.tab_icon);
                tvTab2.setTextColor(getResources().getColor(R.color.white));
                break;
            case 2:
                vTab3.setEnabled(false);
                imgTab3.setBackgroundResource(R.drawable.tab_icon);
                tvTab3.setTextColor(getResources().getColor(R.color.white));
                break;
        }
        if (position < adapterFragmentPager.getCount())
            viewPager.setCurrentItem(position, true);
        MyApp.bus.post(TabIndex.getTabIndex(position));
    }

    @OnClick(R.id.vTab1)
    public void vTab1OnClick() {
        updateTabBarView(0);
    }

    @OnClick(R.id.vTab2)
    public void vTab2OnClick() {
        updateTabBarView(1);
    }

    @OnClick(R.id.vTab3)
    public void vTab3OnClick() {
        updateTabBarView(2);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectDependencies() {
        component().inject(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
