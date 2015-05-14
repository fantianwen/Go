package com.study.radasm.go;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.RecyclerView;

/**
 * Created by RadAsm on 15/5/14.
 */
public class FriendsActivity extends Activity {
    private RecyclerView rv_myFriends;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.view_myfriend);

        rv_myFriends= (RecyclerView) findViewById(R.id.rv_myFriends);

    }
}

