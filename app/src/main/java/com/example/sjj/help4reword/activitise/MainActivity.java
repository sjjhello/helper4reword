package com.example.sjj.help4reword.activitise;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewConfiguration;

import com.example.sjj.help4reword.R;
import com.example.sjj.help4reword.adapters.MainViewPagerAdapter;
import com.example.sjj.help4reword.views.ChangeColorIconWithTextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 * Create by sufuring on 2018/3/5
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private List<ChangeColorIconWithTextView> mTabIndicator = new ArrayList<ChangeColorIconWithTextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }

    private void setListener() {
        mViewPager.setOnPageChangeListener(this);
    }

    private void initView() {
        setOverflowShowingAlways();
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mViewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
        initTabIndicator();
    }

    private void initTabIndicator() {

        ChangeColorIconWithTextView map = (ChangeColorIconWithTextView) findViewById(R.id.id_map);
        mTabIndicator.add(map);
        ChangeColorIconWithTextView list = (ChangeColorIconWithTextView) findViewById(R.id.id_list);
        mTabIndicator.add(list);
        ChangeColorIconWithTextView personal = (ChangeColorIconWithTextView) findViewById(R.id.id_personal);
        mTabIndicator.add(personal);

        map.setOnClickListener(this);
        list.setOnClickListener(this);
        personal.setOnClickListener(this);

        map.setIconAlpha(1.0f);

    }



    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {

        /*if (positionOffset > 0) {
            ChangeColorIconWithTextView left = mTabIndicator.get(position);
            ChangeColorIconWithTextView right = mTabIndicator.get(position + 1);

            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }*/
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        resetOtherTabs();

        switch (v.getId()) {
            case R.id.id_map:
                mTabIndicator.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.id_list:
                mTabIndicator.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.id_personal:
                mTabIndicator.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);
                break;
        }
    }

    private void resetOtherTabs() {
        for (int i = 0; i < mTabIndicator.size(); i++) {
            mTabIndicator.get(i).setIconAlpha(0);
        }
    }

    private void setOverflowShowingAlways() {
        try {
            // true if a permanent menu key is present, false otherwise.
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}