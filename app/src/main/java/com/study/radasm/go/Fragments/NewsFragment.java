package com.study.radasm.go.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.baoyz.widget.PullRefreshLayout;
import com.study.radasm.go.Adapters.NewsRecyclerAdapter;
import com.study.radasm.go.Dao.NewsDao;
import com.study.radasm.go.Model.NewsModel;
import com.study.radasm.go.R;
import com.study.radasm.go.Utils.HtmlUtils;
import com.study.radasm.go.Utils.LogUtils;
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

    private NewsDao newsDao;
    private SharedPreferences sp;
    private boolean is_newsList_cached;
    private View newsView;
    private PullRefreshLayout swipeRefreshLayout;
    private RecyclerView news_recyclerView;
    private NewsRecyclerAdapter newsRecyclerAdapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<NewsModel> cachedNewsList;
    private ArrayList<NewsModel> newsList;
    private NewsHandler newsHandler = new NewsHandler();

    @Override
    protected View loadingSuccessView() {


        newsDao = new NewsDao(mContext);
        sp = mContext.getSharedPreferences(Constants.CONFIG, Context.MODE_PRIVATE);
        is_newsList_cached = sp.getBoolean(Constants.IS_HASCACHE, false);

        cachedNewsList = new ArrayList<NewsModel>();
        newsView = ViewUtils.inflate(mContext, R.layout.view_news);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        swipeRefreshLayout = (PullRefreshLayout) newsView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_WATER_DROP);
        swipeRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initNewsView();
            }
        });
        news_recyclerView = (RecyclerView) newsView.findViewById(R.id.news_recyclerView);
        news_recyclerView.setHasFixedSize(true);
        news_recyclerView.setLayoutManager(layoutManager);
        news_recyclerView.setOnScrollListener(new MyRecyclerViewScrollListener());

        return newsView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        /**
         * as what I asumed:
         *      first,show data from newDB;
         *      once someone pulledto refresh,we got data from net and cache it.
         */
        Log.e("is_newsList_cached", is_newsList_cached + "");
        if (is_newsList_cached) {
            //have cache
            cachedNewsList = newsDao.getNewsListFromDB();
            // NewsModel hahah = cachedNewsList.get(1);
            // boolean b = newsDao.checkItem(hahah);
            //  Log.e("haha",b+"");
            newsRecyclerAdapter = new NewsRecyclerAdapter(mContext, cachedNewsList);
            news_recyclerView.setAdapter(newsRecyclerAdapter);
        } else {
            initNewsView();
        }
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
            int i1 = newsDao.deleteNewsDB();
            Log.e("newsDB中删除的行数", i1 + "");
            //put the first 15 news-items to newsDB
            for (int i = 0; i < 15; i++) {
                NewsModel newsModel = newsList.get(i);
                boolean isThisNews_saved = newsDao.saveNews2DB(newsModel);
                LogUtils.e(newsModel.getTitle(), isThisNews_saved + "");
            }
            is_newsList_cached = true;
            sp.edit().putBoolean(Constants.IS_HASCACHE, is_newsList_cached).commit();
            newsRecyclerAdapter = new NewsRecyclerAdapter(mContext, newsList);
            news_recyclerView.setAdapter(newsRecyclerAdapter);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private class MyRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
            int itemCount = layoutManager.getItemCount();
            if (lastVisibleItemPosition >= itemCount - 2 && dy > 0) {
                //自动加载更多  15个15个的加载
            }

        }
    }
}
