package com.study.radasm.go.Sina;

import android.content.Context;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.net.RequestListener;
import com.study.radasm.go.Sina.openapi.UsersAPI;

/**
 * Created by RadAsm on 15/5/18.
 */
public class WeiboUser {
    public static final String FAN_UIDS = "fansUids";


    /**
     * 根据uid获取到用户的username信息
     *
     * @param context
     * @param requestListener
     */
    public static void getUserName(Context context, RequestListener requestListener) {

        //获取当前用户的token
        Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(context);
        UsersAPI usersAPI = new UsersAPI(context, Constants.APP_KEY, accessToken);
        long uid = Long.parseLong(accessToken.getUid());
        usersAPI.show(uid, requestListener);

    }
}
