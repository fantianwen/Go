package com.study.radasm.go;

import android.app.Application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * when this appLCient starts,everthing will run after this class
 *
 * Created by RadAsm on 15/5/7.
 */
public class AppClient extends Application {
    public static int log_level;

    @Override
    public void onCreate() {

        loadConfig();

    }


    /**
     * load this appClient's config set by dever
     */
    private void loadConfig() {
        InputStream is=null;
        try {
            is = getApplicationContext().getAssets().open("config.properties");
            Properties prop = new Properties();
            prop.load(is);
            //the log_level given in config.properties
            String level = prop.getProperty("log_level", "0");
            this.log_level=Integer.parseInt(level);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
