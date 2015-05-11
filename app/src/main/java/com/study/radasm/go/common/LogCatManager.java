package com.study.radasm.go.common;

import android.os.Environment;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.study.radasm.go.Model.LogModel;
import com.study.radasm.go.Utils.DateTimeUtils;
import com.study.radasm.go.Utils.LogUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 将打印的日志文件存在/sdcard/go/log/这个文件目录下面，并将这个文件在某些需要的时候上传到服务器
 * <p/>
 * Created by RadAsm on 15/5/7.
 */
public class LogCatManager {
    private static final String TAG = "LogCatManager";

    private static final String LOG_UPLOAD_URL = Constants.HTML_URL + Constants.lOG_URL;

    //the log file is here
    private String mBasePath;

    private Queue<LogModel> mQueue;

    //httpUtils for upload log-files
    private HttpUtils httpUtils;


    public LogCatManager() {
        httpUtils = new HttpUtils();
        /**
         * we use linklist cause it is convient to remove or add one element
         */
        mQueue = new LinkedList<>();

        /**
         * the basepath where log-file put is /sdcard/Go/log/
         */
        mBasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Constants.LOG_BASE_PATH + "/" + Constants.DIR_LOG;

        File file = new File(mBasePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        Log.e(TAG, mBasePath);


    }

    /**
     * cache log to Queue
     *
     * @param log
     */
    public void cache(LogModel log) {
        if (log != null) {
            LogUtils.i(TAG, log.toString());
        }

        mQueue.offer(log);
    }

    /**
     * transfer cached log in queue to file under sdcard，as the queue is unique,we should synchronized this source
     * the best way to trandfer cache to file is running in child-thread
     */
    public synchronized void cache2File() {
        new Thread(new Cache2FileTask()).start();
    }

    public String getCurrentFilepath() {
        Date time = Calendar.getInstance().getTime();
        String hmmss = DateTimeUtils.data2String(time, "yyyyMMddHHmmss");
        return mBasePath + "/" + hmmss + ".log";
    }


    private class Cache2FileTask implements Runnable {


        @Override
        public void run() {
            StringBuilder sb = new StringBuilder();
            while (!mQueue.isEmpty()) {
                sb.append(mQueue.poll().toString());
            }
            try {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getCurrentFilepath())));
                bw.write(sb.toString());
                bw.flush();
                bw.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * send all log-file to server,after finishing this,we will delete all the file in mbasepath/Go/log
     */
    public synchronized void send2Server() {
        File logFile = new File(mBasePath);
        for (final File file : logFile.listFiles()) {
            if (file.isFile()) {
                //TODO use volley to upload file(although there is not any method to upload in volley) for studying and coding
                //TODO first we use xUtils to do this
                RequestParams uploadParams = new RequestParams();
                //TODO 传递参数，和服务器商量好
                getHttpUtilsInstance().send(HttpRequest.HttpMethod.POST, LOG_UPLOAD_URL, uploadParams, new RequestCallBack<Object>() {
                    @Override
                    public void onSuccess(ResponseInfo<Object> responseInfo) {
                        LogUtils.i(TAG, "%s,%s", file.getPath(), "log file upload success!");
                        //when success,delete te file in phone under log
                        file.delete();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        LogUtils.d(TAG, "%s,%s", file.getPath(), "log file upload failure!!");
                        onUploadileFailure();
                    }
                });
            }
        }
    }

    /**
     * let implement class to overWrite
     */
    protected void onUploadileFailure() {

    }


    private HttpUtils getHttpUtilsInstance() {
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }


    public void moniSend2Server() {
        File file1 = new File(mBasePath);
        for (File file : file1.listFiles()) {
            file.delete();
        }
    }

}
