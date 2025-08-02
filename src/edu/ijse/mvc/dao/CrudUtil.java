/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.dao;

/**
 *
 * @author Chanuri Fernando
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * DBConnection.java
 * Placeholder for the database connection singleton.
 */
class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() {
        // Placeholder for a real connection setup
        try {
            // Replace with your actual database connection logic
            // For example: connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "user", "password");
            System.out.println("Placeholder: Database connection established.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}


/**
 * CrudUtil.java
 *
 * This utility class simplifies database interactions by providing methods to
 * prepare statements and execute queries or updates. It reduces repetitive
 * code in DAO implementations and enhances readability.
 */
public class CrudUtil {
    
    /**
     * Prepares a SQL statement with a given connection and sets the arguments.
     * @param sql The SQL query string.
     * @param args A variable number of arguments to set in the PreparedStatement.
     * @return A PreparedStatement object ready for execution.
     * @throws Exception if a database access error occurs.
     */
    private static PreparedStatement getStatement(String sql, Object... args) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                statement.setObject((i + 1), args[i]);
            }
        }
        return statement;
    }

    /**
     * Executes a SQL update (INSERT, UPDATE, DELETE) statement.
     * @param sql The SQL update statement string.
     * @param args A variable number of arguments for the statement.
     * @return true if the operation was successful (at least one row affected), false otherwise.
     * @throws Exception if a database access error occurs.
     */
    public static boolean executeUpdate(String sql, Object... args) throws Exception {
        return getStatement(sql, args).executeUpdate() > 0;
    }

    /**
     * Executes a SQL query (SELECT) statement.
     * @param sql The SQL query statement string.
     * @param args A variable number of arguments for the statement.
     * @return A ResultSet containing the data retrieved from the query.
     * @throws Exception if a database access error occurs.
     */
    public static ResultSet executeQuery(String sql, Object... args) throws Exception {
        return getStatement(sql, args).executeQuery();
    }
}
