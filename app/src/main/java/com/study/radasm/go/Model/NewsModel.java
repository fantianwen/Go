package com.study.radasm.go.Model;

/**
 * this model is used for save date parsed from html(weiqi news)
 *
 * Created by RadAsm on 15/5/8.
 */
public class NewsModel {
    String url;
    String content;
    String updateTime;

    public NewsModel() {
    }

    public NewsModel(String updateTime, String url, String content) {
        this.updateTime = updateTime;
        this.url = url;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
