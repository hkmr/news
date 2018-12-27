package com.example.harshkumar.mynews.data;

import java.io.Serializable;

public class News implements Serializable {

    private String title;
    private String content;
    private String date;
    private String author;
    private String sectionName;
    private String url;
    private String source;
    private String imageUrl;

    public News(String title, String content, String date, String author, String sectionName,String url,
                String source,String imageUrl){
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;
        this.sectionName = sectionName;
        this.url = url;
        this.imageUrl = imageUrl;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSource() {
        return source;
    }
}
