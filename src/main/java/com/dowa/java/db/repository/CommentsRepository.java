package com.dowa.java.db.repository;

import com.dowa.java.db.connection.ConnectionToDB;
import com.dowa.java.db.model.Comments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rabanita on 22/05/15.
 */
public class CommentsRepository {
    //Insertar
    public void insertComment(Comments comment) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        try{
            PreparedStatement pStatement = conn.prepareStatement("INSERT INTO comments (idStory, idUser, comment, " +
                    "dateTime) VALUES (?, ?, ?, ?)");
            pStatement.setInt(1,comment.getIdStory());
            pStatement.setInt(2,comment.getIdUser());
            pStatement.setString(3,comment.getComment());
            pStatement.setTimestamp(4,getCurrentTimeStamp());
            pStatement.execute();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
    }

    //Buscar todos los comentarios de una historia
    public List<Comments> findCommentsOfStory(int idStory) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        Comments comments = new Comments();
        List <Comments> allComments = new ArrayList<>();
        try{
            PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM comments WHERE idStory = ? ORDER BY " +
                    "dateTime DESC");
            pStatement.setInt(1,idStory);
            ResultSet result = pStatement.executeQuery();
            while (result.next()){
                comments.setIdComment(result.getInt(1));
                comments.setIdStory(idStory);
                comments.setIdUser(result.getInt(3));
                comments.setComment(result.getString(4));
                comments.setDateTime(result.getTimestamp(5));
                allComments.add(comments);
            }
        } finally {
            if(conn != null){
                conn.close();
            }
        }
        return allComments;
    }

    //Modificar... No se pueden modificar los comentarios

    //Eliminar
    public void deleteComment(int idComment) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        try{
            PreparedStatement pStatement = conn.prepareStatement("DELETE FROM comments WHERE idComment = ?");
            pStatement.setInt(1,idComment);
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
