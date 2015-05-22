package com.dowa.java.db.repository;

import com.dowa.java.db.connection.ConnectionToDB;
import com.dowa.java.db.model.Stories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rabanita on 22/05/15.
 */
public class StoriesRepository {
    //Insertar
    public void insertStory(Stories story) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        try {
            PreparedStatement pStatement = conn.prepareStatement("INSERT INTO stories (idUser, story, idTopic, up, " +
                    "down, votes) VALUES (?,?,?,?,?,?)");
            pStatement.setInt(1,story.getIdUser());
            pStatement.setString(2,story.getStory());
            pStatement.setInt(3,story.getIdTopic());
            pStatement.setInt(4,0); //al crear la nueva historia no hay  votos a favor ni en contra
            pStatement.setInt(5,0);
            pStatement.setInt(6,0);
            pStatement.execute();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
    }

    //Buscar todas las historias
    public List<Stories> findAllStories() throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        Stories story = new Stories();
        List <Stories> allStories = new ArrayList<Stories>();
        try {
            PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM stories");
            ResultSet result = pStatement.executeQuery();
            while (result.next()){
                story.setIdStory(result.getInt(1));
                story.setIdUser(result.getInt(2));
                story.setStory(result.getString(3));
                story.setIdTopic(result.getInt(4));
                story.setUp(result.getInt(5));
                story.setDown(result.getInt(6));
                story.setVotes(result.getInt(7));
                allStories.add(story);
            }
        } finally {
            if(conn != null){
                conn.close();
            }
        }
        return allStories;
    }

    //Buscar historias segun el usuario
    public List <Stories> findStoriesFromUser(int idUser) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        Stories story = new Stories();
        List <Stories> allStories = new ArrayList<Stories>();
        try {
            PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM stories WHERE idUser = ?");
            pStatement.setInt(1,idUser);
            ResultSet result = pStatement.executeQuery();
            while (result.next()){
                story.setIdStory(result.getInt(1));
                story.setIdUser(result.getInt(2));
                story.setStory(result.getString(3));
                story.setIdTopic(result.getInt(4));
                story.setUp(result.getInt(5));
                story.setDown(result.getInt(6));
                story.setVotes(result.getInt(7));
                allStories.add(story);
            }
        } finally {
            if(conn != null){
                conn.close();
            }
        }
        return allStories;
    }

    //Modificar... no se pueden modificar las historias

    //Eliminar
    public void deleteStory(int idStory) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        try{
            PreparedStatement pStatement = conn.prepareStatement("DELETE FROM stories WHERE idStory = ?");
            pStatement.setInt(1,idStory);
            pStatement.execute();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
    }
}
