package com.dowa.java.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by rabanita on 17/05/15.
 */
public class ConnectionToDB {
    private static String dbURL = "jdbc:h2:~/test";
    private static String user = "sa";
    private static String pass = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbURL,user,pass);
    }
}
