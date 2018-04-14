package com.example.sjj.help4reword.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sjj.help4reword.bean.MissionListBean;
import com.example.sjj.help4reword.R;

import java.util.List;

/**
 * Created by sjj on 2018/4/10.
 */

public class MissionViewAdapter extends BaseAdapter{

    private List<MissionListBean> mData;
    private Context mContext;


    public MissionViewAdapter(List<MissionListBean> mData, Context mContext){
        this.mData = mData;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.mission_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.reword = (TextView) convertView.findViewById(R.id.reword);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.location = (TextView) convertView.findViewById(R.id.location);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MissionListBean missionListBean = mData.get(position);
        viewHolder.reword.setText(missionListBean.getReword());
        viewHolder.title.setText(missionListBean.getTitle());
        viewHolder.location.setText(missionListBean.getLocation());
        viewHolder.date.setText(missionListBean.getTime());
        return convertView;
    }

    private class ViewHolder{
        TextView reword;
        TextView title;
        TextView location;
        TextView date;
    }
}
