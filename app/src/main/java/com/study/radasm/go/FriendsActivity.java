package com.study.radasm.go;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.study.radasm.go.Adapters.FriendsRecycleAdapter;
import com.study.radasm.go.Sina.Fans;
import com.study.radasm.go.Utils.LogUtils;
import com.study.radasm.go.Utils.SharedPrefrenceUtils;
import com.study.radasm.go.common.Constants;

import java.util.ArrayList;

/**
 * Created by RadAsm on 15/5/14.
 */
public class FriendsActivity extends ActionBarActivity {
    private int fromWhere;

    private static final String TAG = FriendsActivity.class.getSimpleName();

    private Toolbar toolbar;
    private RecyclerView rv_myFriends;
    private ArrayList<com.study.radasm.go.Sina.User> friendsLists;


    private FriendsRecycleAdapter friendsRecycleAdapter;
    private SharedPrefrenceUtils spUtils;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.view_myfriend);

        initToolbar();

        init();

    }

    private void init() {

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_myFriends= (RecyclerView) findViewById(R.id.rv_myFriends);
        rv_myFriends.setHasFixedSize(true);
        rv_myFriends.setLayoutManager(layoutManager);

        spUtils=new SharedPrefrenceUtils(this, Constants.CONFIG);
        fromWhere= spUtils.getInt(Constants.FROM_WHERE);
        switch (fromWhere){
            case Constants.FROM_WEIBO:
                //使用微博登陆
                Bundle bundle = getIntent().getBundleExtra(Constants.BUNDLE);
                String ss= bundle.getString(Constants.FANS);
                if(ss==null){
                    Log.e(TAG,null);
                }else{
                    Log.e(TAG,ss);
                    LogUtils.delete();
                    LogUtils.keep(ss);
                }
                Fans fans1 = new Gson().fromJson(ss, Fans.class);
                Log.e(TAG,fans1.toString());

                friendsLists=fans1.users;

                Log.e(TAG + "hehe", friendsLists.get(0).profile_image_url);



                friendsRecycleAdapter = new FriendsRecycleAdapter(FriendsActivity.this, friendsLists);

                rv_myFriends.setAdapter(friendsRecycleAdapter);

                break;
            case Constants.FROM_BOMB:
                break;
            case Constants.FROM_QQ:
                break;
            default:
                break;
        }

    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.friends);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friends, menu);
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


}

