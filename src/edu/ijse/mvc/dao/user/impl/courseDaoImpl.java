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
import edu.ijse.layerd.entity.Course;
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
 * Course.java
 * The entity class for a Course.
 */
class Course implements SuperEntity {
    private int courseId;
    private String courseName;

    public Course() {}
    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
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
 * CourseDao.java
 * The specific DAO interface for the Course entity.
 */
interface CourseDao extends CrudDao<Course, Integer> {
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
 * CourseDaoImpl.java
 *
 * This class is the concrete implementation of the `CourseDao` interface.
 * It contains the actual database operations using SQL queries and a
 * `PreparedStatement` to interact with the database.
 */
public class CourseDaoImpl implements CourseDao {

    private Connection connection;

    public CourseDaoImpl() throws SQLException, ClassNotFoundException {
        // Initializes the database connection using the singleton pattern.
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(Course course) throws Exception {
        String sql = "INSERT INTO Course VALUES(?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, course.getCourseId());
        statement.setString(2, course.getCourseName());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean update(Course course) throws Exception {
        String sql = "UPDATE Course SET courseName = ? WHERE courseId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, course.getCourseName());
        statement.setInt(2, course.getCourseId());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "DELETE FROM Course WHERE courseId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        return statement.executeUpdate() > 0;
    }

    @Override
    public Course search(Integer id) throws Exception {
        String sql = "SELECT * FROM Course WHERE courseId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Course(
                resultSet.getInt("courseId"),
                resultSet.getString("courseName")
            );
        }
        return null;
    }

    @Override
    public ArrayList<Course> getAll() throws Exception {
        String sql = "SELECT * FROM Course";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<Course> courses = new ArrayList<>();
        while (resultSet.next()) {
            courses.add(new Course(
                resultSet.getInt("courseId"),
                resultSet.getString("courseName")
            ));
        }
        return courses;
    }
}
