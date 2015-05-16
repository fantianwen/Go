package com.study.radasm.go.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by RadAsm on 15/5/15.
 */
public class SharedPrefrenceUtils {
    private SharedPreferences sp;
    private String app_config;

    public SharedPrefrenceUtils(Context context,String app_config){
        this.app_config=app_config;
        sp=context.getSharedPreferences(app_config,context.MODE_PRIVATE);
    }

    public boolean getBoolean(String tag){
        boolean flag=false;
        if(this.sp!=null){
            flag = sp.getBoolean(tag, false);
        }
        return flag;
    }

}
