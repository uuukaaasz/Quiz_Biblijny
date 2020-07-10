package com.example.quizbiblijny;

public class Cytaty {
    public Integer id;
    public String Content;

    public Cytaty() {
    }

    public Cytaty(String Content) {
        this.Content = Content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer Id) {
        id = Id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
}
