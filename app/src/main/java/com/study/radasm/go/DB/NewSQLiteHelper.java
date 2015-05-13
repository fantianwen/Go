package com.study.radasm.go.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.study.radasm.go.common.Constants;

/**
 * Created by RadAsm on 15/5/12.
 */
public class NewSQLiteHelper extends SQLiteOpenHelper{

    private static final int NEWSDB_VERSION=1;

    private static final String CREATE_NEWS="create table "+Constants.CACHED_NEWS_TABLE_NAME+"(id integer primary key autoincrement,title text,updateTime text,url text)";

    public NewSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * rewrite Construction Method
     * @param context
     */
    public NewSQLiteHelper(Context context){
        this(context, Constants.NEWSDB_NAME, null, NEWSDB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NEWS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
