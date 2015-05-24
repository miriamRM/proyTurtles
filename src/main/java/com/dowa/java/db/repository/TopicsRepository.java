package com.dowa.java.db.repository;

import com.dowa.java.db.connection.ConnectionToDB;
import com.dowa.java.db.model.Topics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rabanita on 22/05/15.
 */
public class TopicsRepository {
    //Insertar
    public void insertTopics(Topics topic) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        try{
            PreparedStatement pStatement = conn.prepareStatement("INSERT INTO topics(topic) VALUES (?)");
            pStatement.setString(1,topic.getTopic());
            pStatement.execute();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
    }

    //Buscar todos los topics
    public List<Topics> findAllTopics() throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        Topics topic = new Topics();
        List<Topics> allTopics = new ArrayList<>();
        try{
            PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM topics ORDER BY idTopic ASC");
            ResultSet result = pStatement.executeQuery();
            while (result.next()){
                topic.setIdTopic(result.getInt(1));
                topic.setTopic(result.getString(2));
                allTopics.add(topic);
            }
        } finally {
            if(conn != null){
                conn.close();
            }
        }
        return allTopics;
    }

    //Modificar
    public int updateTopic(Topics topic) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        int updatedRow;
        try{
            PreparedStatement pStatement = conn.prepareStatement("UPDATE topics SET topic = ? WHERE idTopic = ?");
            pStatement.setString(1,topic.getTopic());
            pStatement.setInt(2,topic.getIdTopic());
            updatedRow = pStatement.executeUpdate();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
        return updatedRow;
    }

    //Eliminar
    public void deleteTopic(int idTopic) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        try{
            PreparedStatement pStatement = conn.prepareStatement("DELETE FROM topics WHERE idTopic = ?");
            pStatement.setInt(1,idTopic);
            pStatement.execute();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
    }
}
