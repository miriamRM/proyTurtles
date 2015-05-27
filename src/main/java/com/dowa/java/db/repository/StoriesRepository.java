package com.dowa.java.db.repository;

import com.dowa.java.db.connection.ConnectionToDB;
import com.dowa.java.db.model.Stories;
import com.dowa.java.db.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
                    "down, votes, dateTime) VALUES (?,?,?,?,?,?,?)");
            pStatement.setInt(1,story.getIdUser());
            pStatement.setString(2,story.getStory());
            pStatement.setInt(3,story.getIdTopic());
            pStatement.setInt(4,0); //al crear la nueva historia no hay  votos a favor ni en contra
            pStatement.setInt(5,0);
            pStatement.setInt(6,0);
            pStatement.setTimestamp(7,getCurrentTimeStamp());
            pStatement.execute();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
    }

    //Contar total de elementos en la tabla
    public int numRows(int where) throws SQLException {
        Connection conn  = ConnectionToDB.getConnection();
        int totalRows;
        try{
            PreparedStatement pStatement;
            if (where == 0) {
                pStatement = conn.prepareStatement("SELECT COUNT(*) FROM stories");
            } else {
                pStatement = conn.prepareStatement("SELECT COUNT(*) FROM stories where idUser = ?");
                pStatement.setInt(1,where);
            }
            ResultSet result = pStatement.executeQuery();
            result.next();
            totalRows = result.getInt(1);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return totalRows;
    }

    //Buscar Historia por Id
    public Stories findStoryById(int idStory) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        Stories story = new Stories();
        try{
            PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM stories WHERE idStory = ?");
            pStatement.setInt(1,idStory);
            ResultSet result = pStatement.executeQuery();
            while (result.next()){
                story.setIdStory(idStory);
                story.setIdUser(result.getInt(2));
                story.setStory(result.getString(3));
                story.setIdTopic(result.getInt(4));
                story.setUp(result.getInt(5));
                story.setDown(result.getInt(6));
                story.setVotes(result.getInt(7));
                story.setDateTime(result.getTimestamp(8));
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return story;
    }

    //Buscar todas las historias
    public List<Stories> findAllStories(int offset) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        List <Stories> allStories = new ArrayList<Stories>();
        try {
            PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM stories ORDER BY dateTime DESC " +
                    "LIMIT 10 OFFSET ?");
            pStatement.setInt(1,offset);
            ResultSet result = pStatement.executeQuery();
            while (result.next()){
                Stories story = new Stories();
                story.setIdStory(result.getInt(1));
                story.setIdUser(result.getInt(2));
                story.setStory(result.getString(3));
                story.setIdTopic(result.getInt(4));
                story.setUp(result.getInt(5));
                story.setDown(result.getInt(6));
                story.setVotes(result.getInt(7));
                story.setDateTime(result.getTimestamp(8));
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
    public List <Stories> findStoriesFromUser(int idUser, int offset) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        List <Stories> allStories = new ArrayList<Stories>();
        try {
            PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM stories WHERE idUser = ? ORDER BY " +
                    "dateTime DESC LIMIT 10 OFFSET ?");
            pStatement.setInt(1,idUser);
            pStatement.setInt(2,offset);
            ResultSet result = pStatement.executeQuery();
            while (result.next()){
                Stories story = new Stories();
                story.setIdStory(result.getInt(1));
                story.setIdUser(result.getInt(2));
                story.setStory(result.getString(3));
                story.setIdTopic(result.getInt(4));
                story.setUp(result.getInt(5));
                story.setDown(result.getInt(6));
                story.setVotes(result.getInt(7));
                story.setDateTime(result.getTimestamp(8));
                allStories.add(story);
            }
        } finally {
            if(conn != null){
                conn.close();
            }
        }
        return allStories;
    }

    //Obtener el ultimo idStory de la tabla
    public int getLastRecord() throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        int lastRec;
        try {
            PreparedStatement pStatement = conn.prepareStatement("SELECT idStory FROM stories ORDER BY idStory DESC " +
                    "LIMIT 1");
            ResultSet result = pStatement.executeQuery();
            result.next();
            lastRec = result.getInt(1);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return lastRec;
    }

    //Modificar historia
    public int updateStory(Stories story) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        int updatedRow;
        try{
            PreparedStatement pStatement = conn.prepareStatement("UPDATE stories SET  up = ?, down = ?, votes = ? " +
                    "WHERE idStory = ?");
            pStatement.setInt(1,story.getUp());
            pStatement.setInt(2,story.getDown());
            pStatement.setInt(3,story.getVotes());
            pStatement.setInt(4,story.getIdStory());
            updatedRow = pStatement.executeUpdate();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
        return updatedRow;
    }

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

    //Method to get the current timeStamp from http://www.mkyong.com/jdbc/how-to-insert-timestamp-value-in-preparedstatement/
    private static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }
}
