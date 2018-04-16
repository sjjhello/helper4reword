package com.example.sjj.help4reword.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sjj.help4reword.bean.ChatListBean;
import com.example.sjj.help4reword.R;
import com.example.sjj.help4reword.adapters.ChatViewAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sjj on 2018/4/10.
 */

public class ChatFragment extends Fragment implements AdapterView.OnItemClickListener{
    private List<ChatListBean> chatList;
    private ChatViewAdapter adapter = null;
    private ListView lv_chat;

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv_chat = (ListView) getView().findViewById(R.id.lv_chat);
        List<ChatListBean> list_chat = new ArrayList<>();
        ChatViewAdapter chatViewAdapter = new ChatViewAdapter(list_chat ,getActivity());
        lv_chat.setAdapter(chatViewAdapter);
        //添加数据
        list_chat.add(new ChatListBean(R.drawable.user5,"cengjing","今天有空呢~~"));
        list_chat.add(new ChatListBean(R.drawable.user2,"zJunshao","正常接单"));
        list_chat.add(new ChatListBean(R.drawable.user3,"toby1352015146","Good job"));
        list_chat.add(new ChatListBean(R.drawable.user4,"MaXnihaker","砸我，砸我，拼命赚钱"));
        list_chat.add(new ChatListBean(R.drawable.user1,"Betty","幸福过每一天"));

        chatViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
