package com.dowa.java.db.model;

/**
 * Created by rabanita on 20/05/15.
 */
public class User {
    private int idUser;
    private String userName;
    private String eMail;
    private String pass;

    public int getIdUser() {
        return idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
