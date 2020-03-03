package com.github.nicon777.kafkaexample.model;

import java.util.Date;

public class Review {
    private int id;
    private String title;
    private String body;
    private Date date;
    private int likes;
    private int useful;

    public Review() {
    }

    public Review(int id, String title, String body, Date date, int likes, int useful) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.likes = likes;
        this.useful = useful;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getUseful() {
        return useful;
    }

    public void setUseful(int useful) {
        this.useful = useful;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                ", likes=" + likes +
                ", useful=" + useful +
                '}';
    }
}
