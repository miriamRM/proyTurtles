package com.dowa.java.db.model;

import java.sql.Timestamp;

/**
 * Created by rabanita on 22/05/15.
 */
public class Comments {
    private int idComment;
    private int idStory;
    private int idUser;
    private String comment;
    private Timestamp dateTime;

    public int getIdComment() {
        return idComment;
    }

    public int getIdStory() {
        return idStory;
    }

    public void setIdStory(int idStory) {
        this.idStory = idStory;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }
}
