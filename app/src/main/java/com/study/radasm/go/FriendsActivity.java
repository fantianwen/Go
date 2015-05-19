package com.study.radasm.go;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.study.radasm.go.Adapters.FriendsRecycleAdapter;
import com.study.radasm.go.Sina.AccessTokenKeeper;
import com.study.radasm.go.Sina.openapi.models.User;

import java.util.ArrayList;

/**
 * Created by RadAsm on 15/5/14.
 */
public class FriendsActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private RecyclerView rv_myFriends;
    private ArrayList<User> friendsLists;
    private FriendsRecycleAdapter friendsRecycleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.view_myfriend);

        /**
         * 获取到用户的uid，获取到该用户的好友的信息，进行展示
         */
        Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(this);


        initToolbar();

        friendsRecycleAdapter=new FriendsRecycleAdapter(this,friendsLists);
        rv_myFriends = (RecyclerView) findViewById(R.id.rv_myFriends);
        rv_myFriends.setAdapter(friendsRecycleAdapter);

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

