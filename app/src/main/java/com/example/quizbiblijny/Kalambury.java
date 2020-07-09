package com.example.quizbiblijny;

public class Kalambury {
    public Integer id;
    public String subject;
    public String content;

    public Kalambury() {
    }

    public Kalambury(String Subject, String Content) {
        this.subject = Subject;
        this.content = Content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer Id) {
        id = Id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String Subject) {
        this.subject = Subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String Content) {
        this.content = Content;
    }
}
