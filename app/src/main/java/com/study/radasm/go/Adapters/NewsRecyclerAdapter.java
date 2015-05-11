package com.study.radasm.go.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.radasm.go.Model.NewsModel;
import com.study.radasm.go.R;
import com.study.radasm.go.Utils.AppUtils;
import com.study.radasm.go.Utils.LogUtils;
import com.study.radasm.go.WebActivities.WebActivity;
import com.study.radasm.go.common.Constants;

import java.util.ArrayList;


/**
 * Created by RadAsm on 15/5/8.
 */
public class NewsRecyclerAdapter extends RecyclerView.Adapter {

    private Context mContext;

    private ArrayList<NewsModel> newsList=new ArrayList<>();

    public NewsRecyclerAdapter(Context context,ArrayList<NewsModel> newsList){
        this.mContext=context;
        this.newsList=newsList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1,-2);
        v.setLayoutParams(lp);
        NewsViewHolder newsViewHolder=new NewsViewHolder(v);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsViewHolder holder1= (NewsViewHolder) holder;
        final NewsModel newsModel = newsList.get(position);
        String content = newsModel.getContent();
        String updateTime = newsModel.getUpdateTime();
        String[] strings = content.split(" ");
        StringBuffer buffer = new StringBuffer(strings[0]);
        for(int i=1;i<strings.length;i++){
            buffer.append("\n");
            buffer.append(strings[i]);
        }
        LogUtils.e("contents",buffer.toString());
        holder1.tv_title.setText(buffer.toString());
        holder1.updateTime.setText("update:"+updateTime);
        holder1.position=position;
        holder1.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newsUrl = newsModel.getUrl();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.BUNDLE_URL, newsUrl);
                Class webActivity= WebActivity.class;
                AppUtils.transfer2Activity(bundle,mContext ,webActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    private static class NewsViewHolder extends RecyclerView.ViewHolder{
        public View container;
        public TextView tv_title;
        public TextView updateTime;
        public int position;

        public NewsViewHolder(View itemView) {
            super(itemView);
            this.container = itemView.findViewById(R.id.container);
            this.tv_title= (TextView) container.findViewById(R.id.news_title);
            this.updateTime= (TextView) container.findViewById(R.id.updateTime);
        }
    }
}
