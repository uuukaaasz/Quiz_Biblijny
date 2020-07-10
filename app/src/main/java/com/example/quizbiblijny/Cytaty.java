package com.example.quizbiblijny;

public class Cytaty {
    public Integer id;
    public String Content;
    public String FootNote;

    public Cytaty() {
    }

    public Cytaty(String Content, String FootNote) {
        this.Content = Content;
        this.FootNote = FootNote;
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

    public String getFootNote() {
        return FootNote;
    }

    public void setFootNote(String FootNote) {
        this.FootNote = FootNote;
    }
}
