package com.study.radasm.go.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.study.radasm.go.common.Constants;

/**
 * Created by RadAsm on 15/5/22.
 */
public class WBUserSQLiteHelper extends SQLiteOpenHelper{


    private static final String CREATE_DB_WEIBOUSER="create table "+ Constants.CACHED_USER_TABLE_NAME+"(id integer primary key autoincrement,user_json text)";

    public WBUserSQLiteHelper(Context context) {
        super(context, Constants.WEIBOUSER_DB, null, Constants.WEIBOUSER_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_WEIBOUSER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
