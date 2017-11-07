package cn.com.lyk.recordk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by LeeYK on 2017/10/24.
 * E-Mail Address：liyukaiit@163.com
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;
    public SectionsPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
