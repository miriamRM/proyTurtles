package com.dowa.java.db.model;

/**
 * Created by rabanita on 22/05/15.
 */
public class Topics {
    private int idTopic;
    private String topic;

    public int getIdTopic() {
        return idTopic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }
}
