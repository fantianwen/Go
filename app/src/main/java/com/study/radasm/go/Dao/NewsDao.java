package com.study.radasm.go.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.study.radasm.go.DB.NewSQLiteHelper;
import com.study.radasm.go.Model.NewsModel;
import com.study.radasm.go.common.Constants;

import java.util.ArrayList;

/**
 * Created by RadAsm on 15/5/12.
 */
public class NewsDao {
    private Context context;
    private NewSQLiteHelper newSQLiteHelper;

    public NewsDao(Context context) {
        this.context = context;
        this.newSQLiteHelper = new NewSQLiteHelper(context);
    }

    public SQLiteDatabase getNewsDB() {
        SQLiteDatabase newsDB = null;
        if (newSQLiteHelper != null) {
            newsDB = newSQLiteHelper.getWritableDatabase();
        }
        return newsDB;
    }

    /**
     * parse news from newsDB
     *
     * @return
     */
    public ArrayList<NewsModel> getNewsListFromDB() {
        ArrayList<NewsModel> newsList = new ArrayList<NewsModel>();
        SQLiteDatabase newsDB = null;
        Cursor newsQuery = null;
        if (newSQLiteHelper != null) {
            newsDB = newSQLiteHelper.getReadableDatabase();
        }
        if (newsDB != null) {
            newsQuery = newsDB.query(Constants.CACHED_NEWS_TABLE_NAME, null, null, null, null, null, null);
        }
        while (newsQuery.moveToNext()) {
            String title = newsQuery.getString(newsQuery.getColumnIndex("title"));
            String updateTime = newsQuery.getColumnName(newsQuery.getColumnIndex("updateTime"));
            String url = newsQuery.getColumnName(newsQuery.getColumnIndex("url"));
            NewsModel news = new NewsModel(updateTime, url, title);
            newsList.add(news);
        }
        return newsList;
    }

    /**
     * put one news to newsDB
     *
     * @param news the data to insert
     * @return weather the news has been inserted
     */
    public boolean saveNews2DB(NewsModel news) {
        boolean isSaved = false;
        SQLiteDatabase newsDB = null;
        if (newSQLiteHelper != null) {
            newsDB = newSQLiteHelper.getWritableDatabase();
        }
        /**
         * need to check weather the item has been inth db,if it has already in,don't save it
         */
        if (newsDB != null) {
            ContentValues values = new ContentValues();
            values.put("title", news.getTitle());
            values.put("updateTime", news.getUpdateTime());
            values.put("url", news.getUrl());
            long insert = newsDB.insert(Constants.CACHED_NEWS_TABLE_NAME, null, values);
            if (insert != -1) {
                isSaved = true;
            }
        }
        //row ID of the newly inserted row, or -1 if an error occurred
        return isSaved;
    }

    /**
     * delete all the data from newsDB
     * @return
     */
    public int deleteNewsDB(){
        int delete=0;
        SQLiteDatabase newsDB = null;
        if (newSQLiteHelper != null) {
            newsDB = newSQLiteHelper.getWritableDatabase();
        }
        if (newsDB != null) {
            delete = newsDB.delete(Constants.CACHED_NEWS_TABLE_NAME, null, null);
        }
        return delete;
    }


}
