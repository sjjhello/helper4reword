package com.example.sjj.help4reword.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sjj.help4reword.Bean.ChatListBean;
import com.example.sjj.help4reword.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjj on 2018/4/10.
 */

public class ChatViewAdapter extends BaseAdapter{

    private List<ChatListBean> cData  = new ArrayList<>();
    private Context cContext;

    public ChatViewAdapter(List<ChatListBean> cData, Context cContext){
        this.cData = cData;
        this.cContext = cContext;
    }

    public final class ViewHolder{
        public ImageView head;
        public TextView name;
        public TextView says;
    }


    @Override
    public int getCount() {
        return cData.size();
    }

    @Override
    public Object getItem(int position) {
        return cData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(cContext).inflate(R.layout.chat_list_item, parent, false);
            viewHolder.head = (ImageView) convertView.findViewById(R.id.head);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.says = (TextView) convertView.findViewById(R.id.says);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ChatListBean chatListBean = cData.get(position);
        viewHolder.head.setBackgroundResource(chatListBean.getImage());
        viewHolder.name.setText(chatListBean.getName());
        viewHolder.says.setText(chatListBean.getSays());

        return convertView;
    }
}
