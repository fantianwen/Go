package com.study.radasm.go.Sina;

/**
 * Created by RadAsm on 15/5/18.
 */
public class Constants {
    public static final String APP_KEY = "889228361"; // 应用的APP_KEY
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";// 应用的回调页 public static final String SCOPE
    //申请的高级权限
    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";

    /**
     * 保存的在sp中的标签的名字，用于初始化叫“sina_token”的shareprefrence
     */
    public static final String SINA_TOKEN = "sina_token";

    public static final String UID="uid";
    public static final String ACCESS_TOKEN="access_token";
    public static final String REFRESH_TOKEN="refresh_token";
    public static final String EXPIRES_IN="expires_in";

}
