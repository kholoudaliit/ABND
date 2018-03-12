package com.example.kholoudali.newsapp;

import java.util.Date;

/**
 * Created by kholoudali on 1/12/18.
 */

public class News {

    private String article_name;
    private String section_name;
    private String auther;
    private Date date;
    private String URL;
    public News(String article_name, String section_name, String auther, Date date, String url) {
        this.article_name = article_name;
        this.section_name = section_name;
        this.auther = auther;
        this.date = date;
        URL = url;
    }
    public News(){}

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {

        this.date = date;
    }


    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
