package com.dowa.java.db.model;

import java.sql.Timestamp;

/**
 * Created by rabanita on 22/05/15.
 */
public class Stories {
    private int idStory;
    private int idUser;
    private String story;
    private int idTopic;
    private int up;
    private int down;
    private int votes;
    private Timestamp dateTime;

    public void setIdStory(int idStory) {
        this.idStory = idStory;
    }

    public int getIdStory() {
        return idStory;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }
}
