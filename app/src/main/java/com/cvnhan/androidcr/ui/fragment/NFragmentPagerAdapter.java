package com.cvnhan.androidcr.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by nhancao on 6/10/16.
 */
public class NFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentPages;

    private NFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return fragmentPages.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentPages.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    public static final class Builder {

        NFragmentPagerAdapter fragmentPagerAdapter;

        public Builder(FragmentManager fm) {
            this.fragmentPagerAdapter = new NFragmentPagerAdapter(fm);
        }

        public Builder setItems(@NonNull ArrayList<Fragment> fragmentPages) {
            fragmentPagerAdapter.fragmentPages = fragmentPages;
            return this;
        }


        public NFragmentPagerAdapter build() {
            if (fragmentPagerAdapter.fragmentPages == null || fragmentPagerAdapter.fragmentPages.size() == 0) {
                throw new ArrayIndexOutOfBoundsException("Fragment page list must be > 0");
            }
            return this.fragmentPagerAdapter;
        }
    }

}