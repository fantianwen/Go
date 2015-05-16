package com.study.radasm.go.Huanxin.HXTasks;

import com.easemob.cloud.HttpClientManager;
import com.easemob.exceptions.EaseMobException;
import com.study.radasm.go.Huanxin.HXConstants;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by RadAsm on 15/5/15.
 */
public class HXLoggingTask implements Runnable {
    private String username;
    private String password;

    public HXLoggingTask(String username,String password){
        this.username=username;
        this.password=password;
    }

    @Override
    public void run() {
        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = null;
            String responseResult = null;
            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("password", password);
            String encoderJson = URLEncoder.encode(json.toString(), HTTP.UTF_8);
            String url = HXConstants.BASE_URL;
            responseResult = HttpClientManager.sendHttpRequest(url, (Map) null, json.toString(), HttpClientManager.Method_GET);
        }catch (IOException e) {
            e.printStackTrace();
        } catch (EaseMobException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
