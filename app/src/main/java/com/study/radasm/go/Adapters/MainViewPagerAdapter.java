package com.study.radasm.go.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.study.radasm.go.Fragments.BaseFragment;
import com.study.radasm.go.Fragments.FragmentFactory;
import com.study.radasm.go.common.Constants;

/**
 * Created by RadAsm on 15/5/11.
 */
public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final String[] titles = Constants.TITLES;
    private static final int NEWSCENTER = 0;
    private static final int INDIVUALCENTER = 1;

    private Context mContext;

    public MainViewPagerAdapter(FragmentManager fm, Context ctx) {
        super(fm);
        this.mContext = ctx;
    }

    @Override
    public BaseFragment getItem(int position) {
        BaseFragment baseFragment =
                FragmentFactory.getInstance().createFragment(position);
        return baseFragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


}
