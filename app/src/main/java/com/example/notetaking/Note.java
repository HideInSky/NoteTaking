package com.example.notetaking;

public class Note {
    private long ID;
    private String title;
    private String content;
    private String date;
    private String time;
    Note(){

    }

    Note(String title, String content, String date, String time){
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getID() {
        return ID;
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

    public String getTime() {
        return time;
    }

    Note(long ID, String title, String content, String date, String time){
        this.ID = ID;
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
    }

}
