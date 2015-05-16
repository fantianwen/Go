package com.study.radasm.go;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by RadAsm on 15/5/15.
 */
public abstract class BaseActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        
        initView();
        
        initData();
        
    }


    protected abstract int getLayoutID();

    protected abstract void initView();

    protected abstract void initData();
    
}
