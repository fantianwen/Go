package com.study.radasm.go.Model;

import com.google.gson.Gson;
import com.study.radasm.go.Enum.LogLevelEnum;

import java.io.Serializable;

/**
 * LogModel 日志模型，需要上传给服务器的日志中包含的信息
 * 我们需要实现Serializable接口，以便能够在传输。
 *
 * Created by RadAsm on 15/5/7.
 */
public class LogModel implements Serializable{
    //用户信息
    private String userInfo;

    //Go的版本信息
    private String goVersion;

    //终端信息
    private String terminalId;

    //手机信息
    private String phoneInfo;

    //打印的日志的等级
    private LogLevelEnum level;

    public LogModel(String goVersion, LogLevelEnum level, String phoneInfo, String terminalId, String userInfo) {
        this.goVersion = goVersion;
        this.level = level;
        this.phoneInfo = phoneInfo;
        this.terminalId = terminalId;
        this.userInfo = userInfo;
    }

    public LogModel() {

    }

    /**
     * rewrite tiString() method
     * @return
     */
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
