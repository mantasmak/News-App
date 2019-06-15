package com.newsapp.android.newsapp;
import java.io.Serializable;

public class NewsModel implements Serializable {
    public String img;
    public String title;
    public String publishedAt;
    public String author;
    public String content;
    public String url;
    public String source;
    public String details;

    public NewsModel(String img, String title, String publishedAt, String author, String content, String url, String source){
        this.img = img;
        this.title = title;
        this.publishedAt = publishedAt;
        this.author = author;
        this.content = content;
        this.url = url;
        this.source = source;

        String date = publishedAt.replace("T", " ").substring(0, 16);
        details = date + " | " + source;
    }

    public String toString() {
        return title;
    }
}
