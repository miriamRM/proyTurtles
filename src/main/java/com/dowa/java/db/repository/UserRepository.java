package com.dowa.java.db.repository;

import com.dowa.java.db.connection.ConnectionToDB;
import com.dowa.java.db.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rabanita on 20/05/15.
 */
public class UserRepository {
    //Insertar nuevo usuario
    public void insertUser(User user) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        try{
            PreparedStatement pStatement = conn.prepareStatement("INSERT INTO users(userName,eMail,pass) VALUES(?,?,?)");
            pStatement.setString(1,user.getUserName());
            pStatement.setString(2,user.geteMail());
            pStatement.setString(3,user.getPass());
            pStatement.execute();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
    }

    //Buscar usuario
    public User findUserByMail(String eMail) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        User user = new User();
        try{
            PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM users WHERE eMail = ? ORDER BY idUser ASC");
            pStatement.setString(1,eMail);
            ResultSet result = pStatement.executeQuery();
            if (result.next()){
                user.setIdUser(result.getInt(1));
                user.setUserName(result.getString(2));
                user.seteMail(result.getString(3));
                user.setPass(result.getString(4));
            }
        }finally {
            if(conn != null){
                conn.close();
            }
        }
        return user;
    }

    public User findUserById(int userId) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        User user = new User();
        try {
            PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM users WHERE userId = ?");
            pStatement.setInt(1, userId);
            ResultSet result = pStatement.executeQuery();
            if (result.next()) {
                user.setIdUser(userId);
                user.setUserName(result.getString(2));
                user.seteMail(result.getString(3));
                user.setPass(result.getString(4));
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public boolean usernameUsed(String userName) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        boolean isUsed;
        try{
            PreparedStatement pStatement = conn.prepareStatement("SELECT userName FROM users WHERE userName LIKE ?");
            pStatement.setString(1,userName);
            ResultSet result = pStatement.executeQuery();
            if (!result.isBeforeFirst()){
                isUsed = false; //the username isn't used
            } else {
                isUsed = true;
            }
        }finally {
            if(conn != null){
                conn.close();
            }
        }
        return isUsed;
    }

    //Modificar usuario
    public int updateUser(User user) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        int updatedRow;
        try{
            PreparedStatement pStatement = conn.prepareStatement("UPDATE users SET userName = ?, pass = ? WHERE eMail = ?");
            pStatement.setString(1,user.getUserName());
            pStatement.setString(2,user.getPass());
            pStatement.setString(3,user.geteMail());
            updatedRow = pStatement.executeUpdate();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
        return updatedRow;
    }

    //eliminar usuario
    public void deleteUser(String mail) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        try{
            PreparedStatement pStatement = conn.prepareStatement("DELETE FROM users WHERE eMail = ?");
            pStatement.setString(1,mail);
            pStatement.execute();
        } finally {
            if(conn != null){
                conn.close();
            }
        }
    }
}
