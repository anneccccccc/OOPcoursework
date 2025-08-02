/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.dao.user;

/**
 *
 * @author Chanuri Fernando

import edu.ijse.layerd.dao.CrudDao;
import edu.ijse.layerd.dao.SuperDao;
import edu.ijse.layerd.entity.Course;
import edu.ijse.layerd.entity.SuperEntity;

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
 *
 * This interface extends the generic `CrudDao` and specifies the `Course`
 * entity and `Integer` as the primary key type. It's an important part of the
 * layered architecture, ensuring the data access layer for courses is well-defined.
 */
public interface CourseDao extends CrudDao<Course, Integer> {
    // No custom methods are needed here, as all standard CRUD operations
    // are inherited from the CrudDao interface.
}
