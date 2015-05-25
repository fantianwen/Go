package com.study.radasm.go.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.study.radasm.go.DB.WBUserSQLiteHelper;
import com.study.radasm.go.Sina.Fans;
import com.study.radasm.go.common.Constants;

/**
 * Created by RadAsm on 15/5/22.
 */
public class WeiboUserDao {
    private Context context;
    private WBUserSQLiteHelper wbUserSQLiteHelper;

    public WeiboUserDao(Context context, WBUserSQLiteHelper wbUserSQLiteHelper) {
        this.context = context;
        this.wbUserSQLiteHelper = wbUserSQLiteHelper;
    }

    /**
     * 获取到保存的数据库中的user_json
     *
     * @return
     */
    public Fans queryUserID() {
        String user_json = null;
        SQLiteDatabase uidDB = wbUserSQLiteHelper.getReadableDatabase();
        Cursor query = uidDB.query(Constants.CACHED_USER_TABLE_NAME, null, null, null, null, null, null);
        if (query.moveToNext()) {
            user_json = query.getString(query.getColumnIndex("user_json"));
            if (query != null) {
                query.close();
            }
        }
        //资源释放
        if (uidDB != null) {
            uidDB.close();
        }
        return userJson2Bean(user_json);
    }

    /**
     * 将从数据库中的信息转换成bean信息
     *
     * @param user_json
     * @return
     */
    private Fans userJson2Bean(String user_json) {
        Gson gson = new Gson();
        Fans fans = gson.fromJson(user_json, Fans.class);
        return fans;
    }


    /**
     * 将json数据存储到数据库中
     * @param s
     * @return
     */
    public boolean save2DB(String s) {
        boolean isSaved = false;
        SQLiteDatabase db = wbUserSQLiteHelper.getWritableDatabase();
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("user_json", s);
            long insert = db.insert(Constants.CACHED_USER_TABLE_NAME, null, values);
            if (insert != -1) {
                isSaved = true;
            }
        }
        return isSaved;
    }
}
