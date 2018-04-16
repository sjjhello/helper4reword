package com.example.sjj.help4reword.activies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.DatePicker;
import android.widget.EditText;

import com.amap.api.maps.MapsInitializer;
import com.example.sjj.help4reword.BaseApplication;
import com.example.sjj.help4reword.MissionInfo;
import com.example.sjj.help4reword.R;
import com.example.sjj.help4reword.adapters.MainViewPagerAdapter;
import com.example.sjj.help4reword.adapters.MissionViewAdapter;
import com.example.sjj.help4reword.bean.MissionListBean;
import com.example.sjj.help4reword.db.DatabaseHelper;
import com.example.sjj.help4reword.views.ChangeColorIconWithTextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 * Create by sufuring on 2018/3/5
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{


    private final static String TAG = "MainActivity";
    private ViewPager mViewPager;
    private List<ChangeColorIconWithTextView> mTabIndicator = new ArrayList<ChangeColorIconWithTextView>();

    private List<MissionListBean> missionListBeanList;
    private DatabaseHelper mDatabaseHelper;
    private MissionViewAdapter missionViewAdapter;
    private String location;   //位置

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

        try{
            MapsInitializer.initialize(this.getApplicationContext());
        }catch (RemoteException e){
            e.printStackTrace();
        }
        mViewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
        initTabIndicator();
        initFab();
    }


    private void initTabIndicator() {

        ChangeColorIconWithTextView map = (ChangeColorIconWithTextView) findViewById(R.id.id_map);
        mTabIndicator.add(map);
        ChangeColorIconWithTextView list = (ChangeColorIconWithTextView) findViewById(R.id.id_list);
        mTabIndicator.add(list);
        ChangeColorIconWithTextView chat = (ChangeColorIconWithTextView) findViewById(R.id.id_chat);
        mTabIndicator.add(chat);
        ChangeColorIconWithTextView personal = (ChangeColorIconWithTextView) findViewById(R.id.id_personal);
        mTabIndicator.add(personal);

        map.setOnClickListener(this);
        list.setOnClickListener(this);
        chat.setOnClickListener(this);
        personal.setOnClickListener(this);

        map.setIconAlpha(1.0f);

    }
    private void initFab(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        getLocation();
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View viewDialog = inflater.inflate(R.layout.new_misson_date,null);
                final EditText title = (EditText) viewDialog.findViewById(R.id.title);
                final EditText reword = (EditText) viewDialog.findViewById(R.id.reword);
                final EditText context = (EditText) viewDialog.findViewById(R.id.context);
                final DatePicker finish_time = (DatePicker) viewDialog.findViewById(R.id.finish_time);
                reword.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                final String new_title = title.getText().toString();
                final String new_reword = reword.getText().toString();
                final String new_context = context.getText().toString();
                final String new_dealine = finish_time.toString();
                builder.setView(viewDialog);
                builder.setTitle("发布任务");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*MissionListBean missionListBean = new MissionListBean();
                        missionListBean.title = title.getText().toString();
                        missionListBean.reword = reword.getText().toString();
                        missionListBean.context = context.getText().toString();
                        missionListBean.location = null;
                        missionListBean.time = finish_time.getYear() + "/" + (finish_time.getMonth()+1) + "/" + finish_time.getDayOfMonth();
                        //添加操作，插入数据库或者发送服务端
                        mDatabaseHelper.insertMission(missionListBean);
                        missionListBeanList.add(missionListBean);
                        missionViewAdapter.notifyDataSetChanged();*/
                        try{
                            MissionInfo missionInfo = new MissionInfo(null,new_title,new_reword,new_context,location,new_dealine,1);
                            saveMission2DB(missionInfo);
                            initView();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                builder.setNegativeButton("Cancel",null);
                builder.create().show();
            }
        });
    }


    /**
     * 保存数据到数据库中
     * @param missionInfo
     */
    private void saveMission2DB(final MissionInfo missionInfo){
        new Thread(new Runnable() {
            @Override
            public void run() {
                long insert = BaseApplication.getDaoSession().getMissionInfoDao().insert(missionInfo);
            }
        }).start();
    }

    public void getLocation(){
        Intent getLocation = getIntent();
        location = getLocation.getStringExtra("location");
    }


    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {

        if (positionOffset > 0) {
            ChangeColorIconWithTextView left = mTabIndicator.get(position);
            ChangeColorIconWithTextView right = mTabIndicator.get(position + 1);

            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }
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
            case R.id.id_chat:
                mTabIndicator.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.id_personal:
                mTabIndicator.get(3).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(3, false);
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