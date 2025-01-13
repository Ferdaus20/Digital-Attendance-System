/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Ferdaws Ahmed
 */
public class ConnectionProvider {
    private static final String DB_NAME = "attendanceJframebd";
    private static final String DB_URL = "jdbc:mysql://localhost:3306";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "2ferdaws2";

    public static Connection getCon() {
        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL server without specifying the database
            Connection con = DriverManager.getConnection(DB_URL + "?allowPublicKeyRetrieval=true&useSSL=false", DB_USERNAME, DB_PASSWORD);

            // Check if the database exists, create it if it doesn't
            if (!databaseExists(con, DB_NAME)) {
                createDatabase(con, DB_NAME);
            }

            // Connect to the newly created database
            con = DriverManager.getConnection(DB_URL + "/" + DB_NAME + "?allowPublicKeyRetrieval=true&useSSL=false", DB_USERNAME, DB_PASSWORD);
            return con;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // Check if the database exists
    private static boolean databaseExists(Connection con, String dbName) throws Exception {
        Statement stmt = con.createStatement();
        return stmt.executeQuery("SHOW DATABASES LIKE '" + dbName + "'").next();
    }

    // Create the database
    private static void createDatabase(Connection con, String dbName) throws Exception {
        Statement stmt = con.createStatement();
        stmt.executeUpdate("CREATE DATABASE " + dbName);
        System.out.println("Database '" + dbName + "' created successfully.");
    }
}
