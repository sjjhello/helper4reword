package com.example.sjj.help4reword.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sjj.help4reword.fragments.ListFragment;
import com.example.sjj.help4reword.fragments.MapFragment;
import com.example.sjj.help4reword.fragments.PersonalFragment;

/**
 * Created by sjj on 2017/11/24.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private ListFragment listFragment;
    private MapFragment mapFragment;
    private PersonalFragment personalFragment;

    private static final String[] titles = { "地图","列表", "个人中心"};

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (mapFragment == null) {
                    mapFragment = new MapFragment();
                }
                return mapFragment;
            case 1:
                if (listFragment == null) {
                    listFragment = new ListFragment();
                }
                return listFragment;
            case 2:
                if (personalFragment == null){
                    personalFragment = new PersonalFragment();
                }
                return personalFragment;
            default:
                return null;
        }
    }
}
