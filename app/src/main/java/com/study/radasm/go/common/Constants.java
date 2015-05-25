package com.study.radasm.go.common;

/**
 * Created by RadAsm on 15/5/7.
 */
public class Constants {
    public static final String LOG_BASE_PATH = "Go";
    public static final String DIR_LOG = "log";

    //request a server for Go-url from sina
    public static final String HTML_URL = "http://sports.sina.com.cn/chess/weiqi";
    public static final String lOG_URL = "/log";


    public static final String BUNDLE = "bundle";

    /**
     * intent transfer type of date in bundle*
     */
    public static final String BUNDLE_URL = "boudle_url";


    public static final String[] TITLES = {"新闻中心", "用户中心"};


    public static final String NEWSDB_NAME = "newsDB.db";
    public static final String CACHED_NEWS_TABLE_NAME = "news";

    public static final String IS_HASCACHE = "is_newsList_cached";

    public static final String CONFIG = "config";

    public static final String IS_LOGGED = "is_logged";


    /**
     * 存放获取到的用户的uid的列表的数据库的相关参数信息
     */
    public static final String WEIBOUSER_DB = "weiboDb.db";
    public static final int WEIBOUSER_DB_VERSION = 1;
    public static final String CACHED_USER_TABLE_NAME = "weibo_user_uids";
    public static final String FANS = "fans";

    /**
     * 从授权的sina微博跳转到FriendsActivity
     */
    public static final String FROM_WHERE="from_where";

    public static final int FROM_WEIBO = 1;
    /**
     * 从bomb登陆而来
     */
    public static final int FROM_BOMB = 2;
    /**
     * 从授权的QQ处而来
     */
    public static final int FROM_QQ = 3;
}
