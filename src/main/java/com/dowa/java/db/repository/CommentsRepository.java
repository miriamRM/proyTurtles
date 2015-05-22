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
            PreparedStatement pStatement = conn.prepareStatement("INSERT INTO comments (idStory, idUser, comment) " +
                    "VALUES (?, ?, ?)");
            pStatement.setInt(1,comment.getIdStory());
            pStatement.setInt(2,comment.getIdUser());
            pStatement.setString(3,comment.getComment());
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
            PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM comments WHERE idStory = ?");
            pStatement.setInt(1,idStory);
            ResultSet result = pStatement.executeQuery();
            while (result.next()){
                comments.setIdComment(result.getInt(1));
                comments.setIdStory(idStory);
                comments.setIdUser(result.getInt(3));
                comments.setComment(result.getString(4));
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
}
