package com.dowa.java.db.repository;

import com.dowa.java.db.connection.ConnectionToDB;
import com.dowa.java.db.model.PruebaStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rabanita on 17/05/15.
 */
public class PruebaStudRepo {
    public void insertStudent(PruebaStudent student) throws SQLException {
        Connection conn = ConnectionToDB.getConnection();
        try{
            PreparedStatement pStatement = conn.prepareStatement("INSERT INTO students(firstName, lastName) VALUES(?,?)");
            pStatement.setString(1,student.getFirstName());
            pStatement.setString(2,student.getLastName());
            pStatement.execute();
        }catch (SQLException e){
            System.err.println("SQLException:" + e.getMessage());
        }
        finally {
            if(conn != null){
                conn.close();
            }
        }
    }

    public PruebaStudent findStudentByFirstName(String firstName) throws SQLException{
        Connection conn = ConnectionToDB.getConnection();
        PruebaStudent student = new PruebaStudent();
        try{
            PreparedStatement pStatement = conn.prepareStatement("SELECT * FROM students WHERE firstName = ?");
            pStatement.setString(1,firstName);
            ResultSet result = pStatement.executeQuery();
            if(result.next()){
                student.setFirstName(result.getString(2));
                student.setLastName(result.getString(3));
            }
        }finally {
            if(conn != null){
                conn.close();
            }
        }
        return student;
    }
}
