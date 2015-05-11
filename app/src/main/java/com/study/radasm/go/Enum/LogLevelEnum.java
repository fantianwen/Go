package com.study.radasm.go.Enum;

import java.io.Serializable;

/**
 * Created by RadAsm on 15/5/7.
 */
public enum LogLevelEnum implements Serializable{

    //debug模式
    DEBUG("debug","debug for it"),
    //info模式
    INFO("info","information"),
    //error模式
    ERROR("error","some error ocurrs");

    private String level;
    private String description;

    LogLevelEnum(String level,String description){
        this.level=level;
        this.description=description;
    }

    public String getLevel() {
        return this.level;
    }

    public String getDescription() {
        return this.description;
    }
}
