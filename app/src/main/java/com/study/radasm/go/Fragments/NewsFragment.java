package com.study.radasm.go.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.study.radasm.go.Adapters.NewsRecyclerAdapter;
import com.study.radasm.go.Model.NewsModel;
import com.study.radasm.go.R;
import com.study.radasm.go.Utils.HtmlUtils;
import com.study.radasm.go.Utils.ViewUtils;
import com.study.radasm.go.common.Constants;

import java.util.ArrayList;

/**
 * Created by RadAsm on 15/5/11.
 */
public class NewsFragment extends BaseFragment {
    private static NewsFragment mNewsFragment;

    private NewsFragment() {

    }

    public static NewsFragment getInstance() {
        if (mNewsFragment == null) {
            synchronized (NewsFragment.class) {
                if (mNewsFragment == null) {
                    mNewsFragment = new NewsFragment();
                }
            }
        }
        return mNewsFragment;
    }

    private View newsView;
    private RecyclerView news_recyclerView;
    private NewsRecyclerAdapter newsRecyclerAdapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<NewsModel> newsList;
    private NewsHandler newsHandler = new NewsHandler();

    @Override
    protected View loadingSuccessView() {
        newsView = ViewUtils.inflate(mContext, R.layout.view_news);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        news_recyclerView = (RecyclerView) newsView.findViewById(R.id.news_recyclerView);
        news_recyclerView.setHasFixedSize(true);
        news_recyclerView.setLayoutManager(layoutManager);
        return newsView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        initNewsView();
        super.onActivityCreated(savedInstanceState);
    }

    private void initNewsView() {
        new Thread() {
            @Override
            public void run() {
                ArrayList<NewsModel> lists = HtmlUtils.parseDataFromHtml(Constants.HTML_URL);
                Message msg = Message.obtain();
                msg.obj = lists;
                newsHandler.sendMessage(msg);
            }
        }.start();

    }

    private class NewsHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            newsList = (ArrayList<NewsModel>) msg.obj;
            newsRecyclerAdapter = new NewsRecyclerAdapter(mContext, newsList);
            news_recyclerView.setAdapter(newsRecyclerAdapter);

        }
    }

}
