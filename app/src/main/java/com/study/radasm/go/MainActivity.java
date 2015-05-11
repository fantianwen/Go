package com.study.radasm.go;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.study.radasm.go.Adapters.NewsRecyclerAdapter;
import com.study.radasm.go.Model.NewsModel;
import com.study.radasm.go.Utils.HtmlUtils;
import com.study.radasm.go.common.Constants;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private static final String formatter = "%s,%s";

    private Toolbar toolbar;
    private RecyclerView news_recyclerView;

    private MyHandler myHandler;
    private NewsRecyclerAdapter newsRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myHandler = new MyHandler();
        news_recyclerView = (RecyclerView) findViewById(R.id.news_recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        news_recyclerView.setLayoutManager(layoutManager);

        new Thread() {
            @Override
            public void run() {
                ArrayList<NewsModel> cachedList = HtmlUtils.parseDataFromHtml(Constants.HTML_URL);
                Log.e("first...",cachedList.get(0).getContent());
                Message msg = Message.obtain();
                msg.obj = cachedList;
                myHandler.sendMessage(msg);

            }
        }.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            ArrayList<NewsModel> newsList = (ArrayList<NewsModel>) msg.obj;
            if(newsList==null){
                Log.e("hahahhahahah","yes");
            }else{
                Log.e("first+++", newsList.get(199).getContent()+"..."+newsList.get(199).getUpdateTime());
            }



            newsRecyclerAdapter = new NewsRecyclerAdapter(MainActivity.this,newsList);

            news_recyclerView.setAdapter(newsRecyclerAdapter);

        }
    }

}
