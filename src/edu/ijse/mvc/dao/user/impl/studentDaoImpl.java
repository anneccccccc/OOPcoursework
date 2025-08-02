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
import edu.ijse.layerd.entity.Student;
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
 * Student.java
 * The entity class for a Student.
 */
class Student implements SuperEntity {
    private int studentId;
    private String studentName;
    private String regNo;
    private String contactDetails;
    private int courseId;

    public Student() {}
    public Student(int studentId, String studentName, String regNo, String contactDetails, int courseId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.regNo = regNo;
        this.contactDetails = contactDetails;
        this.courseId = courseId;
    }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }
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
 * StudentDao.java
 * The specific DAO interface for the Student entity.
 */
interface StudentDao extends CrudDao<Student, Integer> {
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
 * StudentDaoImpl.java
 *
 * This class is the concrete implementation of the `StudentDao` interface.
 * It contains the actual database operations using SQL queries and a
 * `PreparedStatement` to interact with the database.
 */
public class StudentDaoImpl implements StudentDao {

    private Connection connection;

    public StudentDaoImpl() throws SQLException, ClassNotFoundException {
        // Initializes the database connection using the singleton pattern.
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(Student student) throws Exception {
        String sql = "INSERT INTO Student (studentId, studentName, regNo, contactDetails, courseId) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, student.getStudentId());
        statement.setString(2, student.getStudentName());
        statement.setString(3, student.getRegNo());
        statement.setString(4, student.getContactDetails());
        statement.setInt(5, student.getCourseId());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean update(Student student) throws Exception {
        String sql = "UPDATE Student SET studentName = ?, regNo = ?, contactDetails = ?, courseId = ? WHERE studentId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, student.getStudentName());
        statement.setString(2, student.getRegNo());
        statement.setString(3, student.getContactDetails());
        statement.setInt(4, student.getCourseId());
        statement.setInt(5, student.getStudentId());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "DELETE FROM Student WHERE studentId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        return statement.executeUpdate() > 0;
    }

    @Override
    public Student search(Integer id) throws Exception {
        String sql = "SELECT * FROM Student WHERE studentId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Student(
                resultSet.getInt("studentId"),
                resultSet.getString("studentName"),
                resultSet.getString("regNo"),
                resultSet.getString("contactDetails"),
                resultSet.getInt("courseId")
            );
        }
        return null;
    }

    @Override
    public ArrayList<Student> getAll() throws Exception {
        String sql = "SELECT * FROM Student";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        ArrayList<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            students.add(new Student(
                resultSet.getInt("studentId"),
                resultSet.getString("studentName"),
                resultSet.getString("regNo"),
                resultSet.getString("contactDetails"),
                resultSet.getInt("courseId")
            ));
        }
        return students;
    }
}
