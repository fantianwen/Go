package com.study.radasm.go;

import android.app.Application;
import android.content.Context;

import com.easemob.chat.EMChat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * when this appLCient starts,everthing will run after this class
 * <p/>
 * Created by RadAsm on 15/5/7.
 */
public class AppClient extends Application {
    public static int log_level;

    private static AppClient appClient;

    public AppClient(){

    }

    public static AppClient getInstance(){
        if(appClient!=null){
            appClient=new AppClient();
        }
        return appClient;
    }

    public static Context getContext(){
        return getInstance().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        loadConfig();
        initHX();
    }


    /**
     * load this appClient's config set by dever
     */
    private void loadConfig() {
        InputStream is = null;
        try {
            is = getApplicationContext().getAssets().open("config.properties");
            Properties prop = new Properties();
            prop.load(is);
            //the log_level given in config.properties
            String level = prop.getProperty("log_level", "0");
            this.log_level = Integer.parseInt(level);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * init Huanxin
     */
    private void initHX() {
        EMChat.getInstance().init(getApplicationContext());

/**
 * debugMode == true 时为打开，sdk 会在log里输入调试信息
 * @param debugMode
 * 在做代码混淆的时候需要设置成false
 */
        EMChat.getInstance().setDebugMode(true);//在做打包混淆时，要关闭debug模式，如果未被关闭，则会出现程序无法运行问题
    }
}
