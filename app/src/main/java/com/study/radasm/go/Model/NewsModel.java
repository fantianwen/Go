package com.study.radasm.go.Model;

/**
 * this model is used for save date parsed from html(weiqi news)
 *
 * Created by RadAsm on 15/5/8.
 */
public class NewsModel {
    private String url;
    private String title;
    private String updateTime;

    public NewsModel() {
    }

    public NewsModel(String updateTime, String url, String title) {
        this.updateTime = updateTime;
        this.url = url;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "content='" + title + '\'' +
                ", url='" + url + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
