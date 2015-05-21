package com.dowa.java.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by rabanita on 17/05/15.
 */
public class ConnectionToDB {
    private static final String _dbURL = "jdbc:h2:~/test";
    private String _dbUser = "sa";
    private String _dbPwd = "";
    private static ConnectionToDB instance = new ConnectionToDB();

    private ConnectionToDB(){}

    private Connection createConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(_dbURL, _dbUser, _dbPwd);
        } catch (SQLException e) {
            System.err.println("SQL Exception " + e.getMessage());
        }
        catch (RuntimeException e) {
            System.err.println("Runtime Exception " + e.getMessage());
        }
        return connection;
    }
    public static Connection getConnection(){
        return instance.createConnection();
    }
}
