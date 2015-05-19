package com.study.radasm.go;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.study.radasm.go.Huanxin.HXCallback;
import com.study.radasm.go.Huanxin.HXTasks.HXRegisterTask;
import com.study.radasm.go.Sina.AccessTokenKeeper;
import com.study.radasm.go.Sina.Constants;
import com.study.radasm.go.Sina.WeiboUser;
import com.study.radasm.go.Sina.openapi.UsersAPI;
import com.study.radasm.go.Sina.openapi.legacy.GroupAPI;
import com.study.radasm.go.Sina.openapi.models.User;
import com.study.radasm.go.Utils.SharedPrefrenceUtils;
import com.study.radasm.go.Utils.TextUtils;

/**
 * Created by RadAsm on 15/5/15.
 */
public class LoggingActivity extends BaseActivity {
    private static final String TAG = LoggingActivity.class.getSimpleName();

    private TextView check;
    private TextView logging_by_weibo;

    private EditText et_username;
    private EditText et_password;
    private EditText et_nickname;

    private String username;
    private String password;
    private String nickname;

    private HXRegisterTask hxRegisterTask;
    private GoWeiboAuthListener goWeiboAuthListener;

    private SharedPrefrenceUtils spUtils;
    private SsoHandler ssoHandler;
    private UsersAPI usersAPI;
    private GroupAPI groupAPI;


    /**
     * 这里的回调是在inActiveResult之中才会运行的，在这里，我们做一些其他的事情。
     */
    private class GoWeiboAuthListener implements WeiboAuthListener {


        @Override
        public void onComplete(Bundle values) {
            final Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(values);// 从 Bundle 中解析 Token
            if (mAccessToken.isSessionValid()) {

                /**获取当前登陆用户的好友分组*/
                /**
                 * 此接口sina的Sso方式暂不支持，残念(需要申请)
                 */
                groupAPI = new GroupAPI(LoggingActivity.this, Constants.APP_KEY, mAccessToken);
                groupAPI.groups(new RequestListener() {
                    @Override
                    public void onComplete(String s) {
                        Log.e("groups", s);
                    }

                    @Override
                    public void onWeiboException(WeiboException e) {
                        Log.e("groupException:",e.getMessage());

                    }
                });


                Log.e(TAG, mAccessToken.toString());
                //将token信息保存在sharedPrefrence中
                AccessTokenKeeper.writeAccessToken(LoggingActivity.this, mAccessToken);

                //user api 取得该用户的用户名
                WeiboUser.getUserName(LoggingActivity.this, new RequestListener() {
                    @Override
                    public void onComplete(String response) {
                        Log.e(TAG, "成功获取到用户的信息为：" + response);
                        // 调用 User#parse 将JSON串解析成User对象
                        User user = User.parse(response);
                        if (user != null) {
                            Toast.makeText(LoggingActivity.this,
                                    "获取User信息成功，用户昵称：" + user.screen_name,
                                    Toast.LENGTH_LONG).show();





                            /**
                             * 成功获取用户信息成功后，跳转FriendsActivity中
                             */
                            //Bundle userBundle=UserKeeper.write2Bundle(user);

                            //  AppUtils.transfer2Activity(userBundle, LoggingActivity.this, FriendsActivity.class);
                            //LoggingActivity.this.finish();


                        } else {
                            Toast.makeText(LoggingActivity.this, response, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onWeiboException(WeiboException e) {
                        Log.e(TAG, "获取用户信息出错，出错信息为：" + e.getMessage());
                    }
                });






            } else {
                // 当您注册的应用程序签名不正确时,就会收到错误Code,请确保签名正确
                String code = values.getString("code", "");
                Log.e(TAG, "返回token错误，错误信息：" + code);
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Log.e(TAG, "auth-Exception" + e.getMessage());

        }

        @Override
        public void onCancel() {
            Log.e(TAG, "授权取消");

        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_log_in;
    }

    @Override
    protected void initView() {
        goWeiboAuthListener = new GoWeiboAuthListener();
        check = (TextView) findViewById(R.id.check);
        logging_by_weibo = (TextView) findViewById(R.id.logging_by_weibo);

        et_username = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);
        et_nickname = (EditText) findViewById(R.id.nickname);
        check.setVisibility(View.GONE);


        spUtils = new SharedPrefrenceUtils(this, com.study.radasm.go.common.Constants.CONFIG);

        et_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.setVisibility(View.GONE);
            }
        });
        et_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.setVisibility(View.GONE);
            }
        });

        /**
         * 使用新浪微博账号进行登陆
         */
        logging_by_weibo.setClickable(true);
        logging_by_weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthInfo authInfo = new AuthInfo(LoggingActivity.this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
                ssoHandler = new SsoHandler(LoggingActivity.this, authInfo);
                ssoHandler.authorizeClientSso(goWeiboAuthListener);
            }
        });

    }

    /**
     * 一定要这句话，才能将token的信息获取到
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }


    }

    @Override
    protected void initData() {

    }

    public void login(View view) {

    }

    public void regist(View view) {
        username = et_username.getText().toString().trim();
        password = et_password.getText().toString().trim();
        nickname = et_nickname.getText().toString().trim();

        Log.e(TAG, username + ":" + password);
        //first to check,当然，这里的用户名和密码要合乎环信的命名规范，这点要检查，但是这里省去检查了
        boolean isLegal = TextUtils.isNotEmpty(username, password);
        if (isLegal) {
            registInHuanxin();
        } else {
            //用户名或者密码不合法，提示用户
            check.setVisibility(View.VISIBLE);
            et_username.setText("");
            et_password.setText("");
        }
    }

    private void registInHuanxin() {
        //register directly,to analasys the result
        hxRegisterTask = new HXRegisterTask(username, password, nickname, new HXCallback() {
            @Override
            public void getResponseJson(String responseJson) {
                Log.e("HXresult", responseJson);

                /**
                 * 如果返回的json串只有“error”,就做出相应的提示，否则注册成功，直接携带数据跳转
                 */
                if (responseJson.contains("error")) {
                    check.setText("用户名重复！");
                    check.setVisibility(View.VISIBLE);
                } else {

                }

            }
        });

        new Thread(hxRegisterTask).start();
    }
}
