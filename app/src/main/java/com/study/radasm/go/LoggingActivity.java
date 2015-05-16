package com.study.radasm.go;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.study.radasm.go.Huanxin.HXCallback;
import com.study.radasm.go.Huanxin.HXTasks.HXRegisterTask;

/**
 * Created by RadAsm on 15/5/15.
 */
public class LoggingActivity extends BaseActivity {
    private EditText et_username;
    private EditText et_password;
    private EditText et_nickname;

    private String username;
    private String password;
    private String nickname;

    private HXRegisterTask hxRegisterTask;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_log_in;
    }

    @Override
    protected void initView() {
        et_username = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);
        et_nickname= (EditText) findViewById(R.id.nickname);

        username = et_username.getText().toString().trim();
        password = et_password.getText().toString().trim();
        nickname=et_nickname.getText().toString().trim();

        //register directly,to analasys the result
        hxRegisterTask = new HXRegisterTask(username, password,nickname, new HXCallback() {
            @Override
            public void getResponseJson(String responseJson) {
                Log.e("HXresult",responseJson);
            }
        });

        new Thread(hxRegisterTask).start();

    }

    @Override
    protected void initData() {

    }

    public void login(View view){

    }

    public void regist(View view){
        //first to check


    }
}
