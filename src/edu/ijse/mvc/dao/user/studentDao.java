/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.dao.user;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.dao.CrudDao;
import edu.ijse.layerd.dao.SuperDao;
import edu.ijse.layerd.entity.Student;
import edu.ijse.layerd.entity.SuperEntity;

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
 *
 * This interface extends the generic `CrudDao` and specifies the `Student`
 * entity and `Integer` as the primary key type. It's a key part of the
 * layered architecture, ensuring the data access layer for students is well-defined.
 */
public interface StudentDao extends CrudDao<Student, Integer> {
    // This interface is intentionally empty as all necessary CRUD methods
    // are inherited from the CrudDao interface.
}
