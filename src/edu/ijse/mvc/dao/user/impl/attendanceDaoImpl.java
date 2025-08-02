/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.dao.user.impl;

/**
 *
 * @author Chanuri Fernando
import edu.ijse.layerd.dao.CrudDao;
import edu.ijse.layerd.dao.SuperDao;
import edu.ijse.layerd.db.DBConnection;
import edu.ijse.layerd.entity.Attendance;
import edu.ijse.layerd.entity.SuperEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

/**
 * SuperEntity.java
 * A marker interface for all entity classes.
 */
interface SuperEntity {
}

/**
 * Attendance.java
 * The entity class for a student's attendance.
 */
class Attendance implements SuperEntity {
    private int attendanceId;
    private int scheduleId;
    private int studentId;
    private Date date;

    public Attendance() {}
    public Attendance(int attendanceId, int scheduleId, int studentId, Date date) {
        this.attendanceId = attendanceId;
        this.scheduleId = scheduleId;
        this.studentId = studentId;
        this.date = date;
    }
    public int getAttendanceId() { return attendanceId; }
    public void setAttendanceId(int attendanceId) { this.attendanceId = attendanceId; }
    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
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
 * AttendanceDao.java
 * The specific DAO interface for the Attendance entity.
 */
interface AttendanceDao extends CrudDao<Attendance, Integer> {
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
 * AttendanceDaoImpl.java
 *
 * This class is the concrete implementation of the `AttendanceDao` interface.
 * It contains the actual database operations using SQL queries and a
 * `PreparedStatement` to interact with the database.
 */
public class AttendanceDaoImpl implements AttendanceDao {

    private Connection connection;

    public AttendanceDaoImpl() throws SQLException, ClassNotFoundException {
        // Initializes the database connection using the singleton pattern.
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(Attendance attendance) throws Exception {
        String sql = "INSERT INTO Attendance (attendanceId, scheduleId, studentId, date) VALUES(?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, attendance.getAttendanceId());
        statement.setInt(2, attendance.getScheduleId());
        statement.setInt(3, attendance.getStudentId());
        statement.setDate(4, attendance.getDate());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean update(Attendance attendance) throws Exception {
        String sql = "UPDATE Attendance SET scheduleId = ?, studentId = ?, date = ? WHERE attendanceId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, attendance.getScheduleId());
        statement.setInt(2, attendance.getStudentId());
        statement.setDate(3, attendance.getDate());
        statement.setInt(4, attendance.getAttendanceId());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "DELETE FROM Attendance WHERE attendanceId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        return statement.executeUpdate() > 0;
    }

    @Override
    public Attendance search(Integer id) throws Exception {
        String sql = "SELECT * FROM Attendance WHERE attendanceId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Attendance(
                resultSet.getInt("attendanceId"),
                resultSet.getInt("scheduleId"),
                resultSet.getInt("studentId"),
                resultSet.getDate("date")
            );
        }
        return null;
    }

    @Override
    public ArrayList<Attendance> getAll() throws Exception {
        String sql = "SELECT * FROM Attendance";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<Attendance> attendances = new ArrayList<>();
        while (resultSet.next()) {
            attendances.add(new Attendance(
                resultSet.getInt("attendanceId"),
                resultSet.getInt("scheduleId"),
                resultSet.getInt("studentId"),
                resultSet.getDate("date")
            ));
        }
        return attendances;
    }
}