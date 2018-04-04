package com.example.sjj.help4reword.fragments;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sjj.help4reword.R;
import com.example.sjj.help4reword.adapters.MyFragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by sjj on 2018/2/18.
 */

public class ListFragment extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener {
    private View view;
    private Button btAllList;
    private Button btMyList;
    private Button btChatList;
    private ImageView ivTab;
    private ViewPager vpMain;
    private ArrayList<Fragment> fragments;
    private Button[] btArgs;
    private int index;
    private int imgleth;
    private int offset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        onPageSelected(0);
    }

    private void initView() {
        vpMain = (ViewPager) getActivity().findViewById(R.id.vp_main);
        ivTab = (ImageView) getActivity().findViewById(R.id.iv_tab);
        btAllList = (Button) getActivity().findViewById(R.id.bt_AllList);
        btMyList = (Button) getActivity().findViewById(R.id.bt_MyList);
        btChatList = (Button) getActivity().findViewById(R.id.bt_ChatList);

        fragments = new ArrayList<Fragment>();
        fragments.add(new List_AllFragment());
        fragments.add(new List_ChatFragment());
        fragments.add(new List_MyFrament());

        btArgs = new Button[]{btAllList,btMyList,btChatList};

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
        vpMain.setAdapter(adapter);

        //获取图片宽度
        imgleth = BitmapFactory.decodeResource(getResources(), R.drawable.tab).getWidth();
        //获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        // 把屏幕尺寸信息赋值给DisplayMetrics dm，注意不是set
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 屏幕宽度
        int count = dm.widthPixels;
        //计算偏移量
        offset = (count / 3 - imgleth) / 2;

        //平移动画(第一页的)
        Animation an = new TranslateAnimation(0, offset, 0, 0);
        an.setFillAfter(true);
        an.setDuration(200);
        ivTab.setAnimation(an);

        vpMain.addOnPageChangeListener(this);
        vpMain.setOffscreenPageLimit(3);


    }

    //重置按钮颜色 为浅黑色
    public void resetButtonTextColor() {
        btMyList.setTextColor(Color.parseColor("#D0D0D0"));
        btAllList.setTextColor(Color.parseColor("#D0D0D0"));
        btChatList.setTextColor(Color.parseColor("#D0D0D0"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_list,null);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int i) {

        int one = offset * 2 + imgleth;//相邻页面的偏移量

        resetButtonTextColor();
        btArgs[i].setTextColor(Color.parseColor("#272727"));

        //评议动画
        Animation anima = new TranslateAnimation(index * one + offset, i * one + offset, 0, 0);
        index = i; //当前页跟着变
        anima.setFillAfter(true); // 动画终止时停留在最后一帧，不然会回到没有执行前的状态
        anima.setDuration(200);// 动画持续时间0.2秒
        ivTab.startAnimation(anima);// 是用ImageView来显示动画的
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {

        switch (view.getId()){
            case R.id.bt_AllList:
                vpMain.setCurrentItem(0);
                break;
            case R.id.bt_MyList:
                vpMain.setCurrentItem(1);
                break;
            case R.id.bt_ChatList:
                vpMain.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

}
