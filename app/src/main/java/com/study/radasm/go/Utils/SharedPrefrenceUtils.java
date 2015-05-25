package com.study.radasm.go.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.study.radasm.go.common.Constants;

/**
 * Created by RadAsm on 15/5/15.
 */
public class SharedPrefrenceUtils {
    private SharedPreferences sp;
    private String app_config;

    public SharedPrefrenceUtils(Context context, String app_config) {
        this.app_config = app_config;
        sp = context.getSharedPreferences(app_config, context.MODE_PRIVATE);
    }

    public boolean getBoolean(String tag) {
        boolean flag = false;
        if (this.sp != null) {
            flag = sp.getBoolean(tag, false);
        }
        return flag;
    }

    public int getInt(String fromWhere) {
        /**默认来自微博登陆*/
        int where = Constants.FROM_WEIBO;
        if (this.sp != null) {
            where = sp.getInt(fromWhere, Constants.FROM_WEIBO);
        }
        return where;
    }

//    /**
//     * 将一个Object保存在sp中
//     * @param sinaToken
//     * @param obj
//     */
//    public void saveObject(String sinaToken, Object obj) {
//        sp.edit().put
//
//    }
}
