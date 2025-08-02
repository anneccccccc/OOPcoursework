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
import edu.ijse.layerd.entity.ClassScheduling;
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
 * ClassScheduling.java
 * The entity class for a Class Scheduling record.
 */
class ClassScheduling implements SuperEntity {
    private int scheduleId;
    private int courseId;
    private int lecturerId;
    private String day;
    private String time;

    public ClassScheduling() {}
    public ClassScheduling(int scheduleId, int courseId, int lecturerId, String day, String time) {
        this.scheduleId = scheduleId;
        this.courseId = courseId;
        this.lecturerId = lecturerId;
        this.day = day;
        this.time = time;
    }
    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public int getLecturerId() { return lecturerId; }
    public void setLecturerId(int lecturerId) { this.lecturerId = lecturerId; }
    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
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
 * ClassSchedulingDao.java
 * The specific DAO interface for the ClassScheduling entity.
 */
interface ClassSchedulingDao extends CrudDao<ClassScheduling, Integer> {
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
 * ClassSchedulingDaoImpl.java
 *
 * This class is the concrete implementation of the `ClassSchedulingDao` interface.
 * It contains the actual database operations using SQL queries and a
 * `PreparedStatement` to interact with the database.
 */
public class ClassSchedulingDaoImpl implements ClassSchedulingDao {

    private Connection connection;

    public ClassSchedulingDaoImpl() throws SQLException, ClassNotFoundException {
        // Initializes the database connection using the singleton pattern.
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(ClassScheduling schedule) throws Exception {
        String sql = "INSERT INTO ClassScheduling (scheduleId, courseId, lecturerId, day, time) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, schedule.getScheduleId());
        statement.setInt(2, schedule.getCourseId());
        statement.setInt(3, schedule.getLecturerId());
        statement.setString(4, schedule.getDay());
        statement.setString(5, schedule.getTime());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean update(ClassScheduling schedule) throws Exception {
        String sql = "UPDATE ClassScheduling SET courseId = ?, lecturerId = ?, day = ?, time = ? WHERE scheduleId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, schedule.getCourseId());
        statement.setInt(2, schedule.getLecturerId());
        statement.setString(3, schedule.getDay());
        statement.setString(4, schedule.getTime());
        statement.setInt(5, schedule.getScheduleId());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "DELETE FROM ClassScheduling WHERE scheduleId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        return statement.executeUpdate() > 0;
    }

    @Override
    public ClassScheduling search(Integer id) throws Exception {
        String sql = "SELECT * FROM ClassScheduling WHERE scheduleId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new ClassScheduling(
                resultSet.getInt("scheduleId"),
                resultSet.getInt("courseId"),
                resultSet.getInt("lecturerId"),
                resultSet.getString("day"),
                resultSet.getString("time")
            );
        }
        return null;
    }

    @Override
    public ArrayList<ClassScheduling> getAll() throws Exception {
        String sql = "SELECT * FROM ClassScheduling";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<ClassScheduling> schedules = new ArrayList<>();
        while (resultSet.next()) {
            schedules.add(new ClassScheduling(
                resultSet.getInt("scheduleId"),
                resultSet.getInt("courseId"),
                resultSet.getInt("lecturerId"),
                resultSet.getString("day"),
                resultSet.getString("time")
            ));
        }
        return schedules;
    }
}
