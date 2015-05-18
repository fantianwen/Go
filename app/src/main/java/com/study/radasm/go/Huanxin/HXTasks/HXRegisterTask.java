package com.study.radasm.go.Huanxin.HXTasks;

import com.easemob.cloud.HttpClientManager;
import com.easemob.exceptions.EaseMobException;
import com.study.radasm.go.Huanxin.HXCallback;
import com.study.radasm.go.Huanxin.HXConstants;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by RadAsm on 15/5/15.
 */
public class HXRegisterTask implements Runnable {
    private HXCallback hxCallback;
    private String username;
    private String password;
    private String nickname;

    public HXRegisterTask(String username, String password,String nickname,HXCallback hxCallback) {
        this.username = username;
        this.password = password;
        this.nickname=nickname;
        this.hxCallback=hxCallback;
    }

    @Override
    public void run() {
        /**
         * register in Huanxin
         */
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = null;
        String responseResult = null;
        try {
            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("password", password);
            json.put("nicakname",nickname);
            String encoderJson = URLEncoder.encode(json.toString(), HTTP.UTF_8);
            String url = HXConstants.BASE_URL+HXConstants.REGISTER;
            responseResult = HttpClientManager.sendHttpRequest(url, (Map) null, json.toString(), HttpClientManager.Method_POST);

            hxCallback.getResponseJson(responseResult);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (EaseMobException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
