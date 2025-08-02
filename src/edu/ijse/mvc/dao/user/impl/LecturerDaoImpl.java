/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.dao.user.impl;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.dao.CrudDao;
import edu.ijse.layerd.dao.SuperDao;
import edu.ijse.layerd.db.DBConnection;
import edu.ijse.layerd.entity.Lecturer;
import edu.ijse.layerd.entity.SuperEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * SuperEntity.java
 * A marker interface for all entity classes.
 */
interface SuperEntity {
}

/**
 * Lecturer.java
 * The entity class for a Lecturer.
 */
class Lecturer implements SuperEntity {
    private int lecturerId;
    private String lecturerName;
    private String contactDetails;
    private int courseId;

    public Lecturer() {}
    public Lecturer(int lecturerId, String lecturerName, String contactDetails, int courseId) {
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
        this.contactDetails = contactDetails;
        this.courseId = courseId;
    }
    public int getLecturerId() { return lecturerId; }
    public void setLecturerId(int lecturerId) { this.lecturerId = lecturerId; }
    public String getLecturerName() { return lecturerName; }
    public void setLecturerName(String lecturerName) { this.lecturerName = lecturerName; }
    public String getContactDetails() { return contactDetails; }
    public void setContactDetails(String contactDetails) { this.contactDetails = contactDetails; }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
}

/**
 * SuperDao.java
 * A marker interface for all DAO classes.
 */
interface SuperDao {
}

/**
 * CrudDao.java
 * The generic DAO interface for common CRUD operations.
 */
interface CrudDao<T extends SuperEntity, ID> extends SuperDao {
    boolean save(T t) throws Exception;
    boolean update(T t) throws Exception;
    boolean delete(ID id) throws Exception;
    T search(ID id) throws Exception;
    ArrayList<T> getAll() throws Exception;
}

/**
 * LecturerDao.java
 * The specific DAO interface for the Lecturer entity.
 */
interface LecturerDao extends CrudDao<Lecturer, Integer> {
    // No custom methods are needed here, as all standard CRUD operations
    // are inherited from the CrudDao interface.
}

/**
 * DBConnection.java
 * Mock DBConnection class for self-contained example. In a real project,
 * this would connect to a database.
 */
class DBConnection {
    private static DBConnection dBConnection;
    // Mock connection object.
    private Connection connection;

    private DBConnection() {
        // In a real application, a database connection would be established here.
    }

    public static DBConnection getInstance() {
        if (dBConnection == null) {
            dBConnection = new DBConnection();
        }
        return dBConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}


/**
 * LecturerDaoImpl.java
 *
 * This class is the concrete implementation of the `LecturerDao` interface.
 * It contains the actual database operations using SQL queries and a
 * `PreparedStatement` to interact with the database.
 */
public class LecturerDaoImpl implements LecturerDao {

    private Connection connection;

    public LecturerDaoImpl() throws SQLException, ClassNotFoundException {
        // Initializes the database connection using the singleton pattern.
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(Lecturer lecturer) throws Exception {
        String sql = "INSERT INTO Lecturer (lecturerId, lecturerName, contactDetails, courseId) VALUES(?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, lecturer.getLecturerId());
        statement.setString(2, lecturer.getLecturerName());
        statement.setString(3, lecturer.getContactDetails());
        statement.setInt(4, lecturer.getCourseId());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean update(Lecturer lecturer) throws Exception {
        String sql = "UPDATE Lecturer SET lecturerName = ?, contactDetails = ?, courseId = ? WHERE lecturerId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, lecturer.getLecturerName());
        statement.setString(2, lecturer.getContactDetails());
        statement.setInt(3, lecturer.getCourseId());
        statement.setInt(4, lecturer.getLecturerId());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "DELETE FROM Lecturer WHERE lecturerId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        return statement.executeUpdate() > 0;
    }

    @Override
    public Lecturer search(Integer id) throws Exception {
        String sql = "SELECT * FROM Lecturer WHERE lecturerId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Lecturer(
                resultSet.getInt("lecturerId"),
                resultSet.getString("lecturerName"),
                resultSet.getString("contactDetails"),
                resultSet.getInt("courseId")
            );
        }
        return null;
    }

    @Override
    public ArrayList<Lecturer> getAll() throws Exception {
        String sql = "SELECT * FROM Lecturer";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<Lecturer> lecturers = new ArrayList<>();
        while (resultSet.next()) {
            lecturers.add(new Lecturer(
                resultSet.getInt("lecturerId"),
                resultSet.getString("lecturerName"),
                resultSet.getString("contactDetails"),
                resultSet.getInt("courseId")
            ));
        }
        return lecturers;
    }
}
