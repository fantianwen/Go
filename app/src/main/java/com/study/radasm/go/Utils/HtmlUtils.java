package com.study.radasm.go.Utils;

import android.util.Log;

import com.study.radasm.go.Model.NewsModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * First fo all,this is awesome to parse all the interface from sina,but it is invain after wasting my 3 hours
 * <p/>
 * so I begin to parse all the data from "http://sports.sina.com.cn/chess/weiqi/" by utils Jsoup.
 * <p/>
 * Created by RadAsm on 15/5/8.
 */
public class HtmlUtils {
    //list contained url link
    private static ArrayList<NewsModel> newsList =new ArrayList<NewsModel>();


    /**
     * form a url in net,we can parse all the useful information for us
     * @param url
     * @return
     */
    public static ArrayList<NewsModel> parseDataFromHtml(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = doc.select("a[href]");
     //   Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");
        Elements times = doc.select("font[class]");
        Log.e("time",times.size()+"");
//        print("\nMedia: (%d)", media.size());
//        for (Element src : media) {
//            if (src.tagName().equals("img"))
//                print(" * %s: <%s> %sx%s (%s)",
//                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
//                        trim(src.attr("alt"), 20));
//            else
//                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
//        }
//        print("\nImports: (%d)", imports.size());
//        for (Element link : imports) {
//            print(" * %s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"));
//
//
//        }
//        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            /**
             * put all the url to list
             */
            //judge wheather this news is a weiqi news
            String content=trim(link.text(),35);
            if(content.length()>13){
                //we can judge that this is a weiqi news,and we add this news to the list
                NewsModel newsModel = new NewsModel();
                newsModel.setUrl(link.attr("abs:href"));
                newsModel.setContent(trim(link.text(),35));

                newsList.add(newsModel);
            }

        }

        Log.e("links",newsList.size()+"");

        for (int i=0;i<times.size();i++){
            NewsModel newsModel = newsList.get(i);
            Element element = times.get(i);
            newsModel.setUpdateTime(trim(element.text(),35));
            Log.e("lklklkl", newsList.get(i).getUpdateTime());
        }

        return newsList;

    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }
}
