package com.cvnhan.androidcr.ui.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.cvnhan.androidcr.ui.fragment.tab1.Tab1Fragment;
import com.cvnhan.androidcr.ui.fragment.tab2.Tab2Fragment;
import com.cvnhan.androidcr.ui.fragment.tab3.Tab3Fragment;

/**
 * Created by nhancao on 6/10/16.
 */
public class NFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"TAB1", "TAB2", "TAB3"};
    private Context context;

    public NFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Tab1Fragment.getInstance();
            case 1:
                return Tab2Fragment.getInstance();
            case 2:
                return Tab3Fragment.getInstance();
        }
        return Tab1Fragment.getInstance();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}