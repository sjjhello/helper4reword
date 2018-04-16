package com.example.sjj.help4reword.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sjj.help4reword.MissionInfo;
import com.example.sjj.help4reword.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjj on 2018/4/16.
 */

public class MissionListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<MissionInfo> mData = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private Context mContext;

    public MissionListRecyclerAdapter(Context context){
        mContext = context;
    }

    public void setmData(List<MissionInfo> data){
        mData.addAll(data);
        notifyDataSetChanged();

    }

    public void clearData(){
        mData.clear();
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(OnItemClickListener onClickListener) {
        this.onItemClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mission_list_item , parent ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof  ViewHolder){
            final  MissionInfo missionInfo = mData.get(position);
            String reword = missionInfo.getReword();
            String title = missionInfo.getTitle();
            String location = missionInfo.getLocation();
            String deadline = missionInfo.getDeadline();
            location = location.substring(0,3);

            ((ViewHolder) holder).tvReword.setText(reword);
            ((ViewHolder) holder).tvTitle.setText(title);
            ((ViewHolder) holder).tvLocation.setText(location);
            ((ViewHolder) holder).tvDeadline.setText(deadline);


            if(onItemClickListener == null)return;
            holder.itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position , missionInfo);
                }
            });

            }
        }


    @Override
    public int getItemCount() {
        return 0;
    }


    class  ViewHolder extends  RecyclerView.ViewHolder{
        private TextView tvReword;
        private TextView tvTitle;
        private TextView tvLocation;
        private TextView tvDeadline;


        public ViewHolder(View itemView) {
            super(itemView);
            tvReword = (TextView) itemView.findViewById(R.id.reword);
            tvTitle = (TextView) itemView.findViewById(R.id.title);
            tvLocation = (TextView) itemView.findViewById(R.id.location);
            tvDeadline = (TextView) itemView.findViewById(R.id.date);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, MissionInfo missionInfo);
    }


}
