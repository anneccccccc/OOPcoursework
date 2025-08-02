/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.db;

/**
 *
 * @author Chanuri Fernando
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // The single static instance of the DBConnection class.
    private static DBConnection dBConnection;

    // The java.sql.Connection object, which will be initialized once.
    private Connection connection;

    /**
     * Private constructor to prevent direct instantiation from outside the class.
     * This is a key part of the Singleton pattern.
     *
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private DBConnection() throws ClassNotFoundException, SQLException {
        // Step 1: Load the MySQL JDBC driver.
        // This is a necessary step to register the driver with the DriverManager.
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Step 2: Establish the connection to the database.
        // The connection details are hardcoded here. It's a good practice to
        // move these to a configuration file for production applications.
        String url = "jdbc:mysql://localhost:3306/sams_db"; // Using your database name
        String user = "root";
        String password = "mysql"; // Using the password from your example

        this.connection = DriverManager.getConnection(url, user, password);
        System.out.println("Database connection established successfully to sams_db.");
    }

    /**
     * The static method to get the single instance of the DBConnection class.
     * It creates the instance if it doesn't already exist.
     *
     * @return the single DBConnection instance.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    public static DBConnection getInstance() throws ClassNotFoundException, SQLException {
        // Check if the instance is null. If it is, create a new one.
        if (dBConnection == null) {
            dBConnection = new DBConnection();
        }

        // Return the single instance.
        return dBConnection;
    }

    /**
     * Public method to retrieve the Connection object.
     *
     * @return the java.sql.Connection object.
     */
    public Connection getConnection() {
        return connection;
    }
}