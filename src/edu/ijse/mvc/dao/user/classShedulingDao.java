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
import edu.ijse.layerd.entity.Class;
import edu.ijse.layerd.entity.SuperEntity;

import java.util.ArrayList;
import java.util.Date;

/**
 * SuperEntity.java
 * A marker interface for all entity classes.
 */
interface SuperEntity {
}

/**
 * Class.java
 * The entity class for a Class.
 */
class Class implements SuperEntity {
    private int classId;
    private Date classDate;
    private int subjectId;
    private int lecturerId;

    public Class() {}

    public Class(int classId, Date classDate, int subjectId, int lecturerId) {
        this.classId = classId;
        this.classDate = classDate;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
    }

    public int getClassId() { return classId; }
    public void setClassId(int classId) { this.classId = classId; }
    public Date getClassDate() { return classDate; }
    public void setClassDate(Date classDate) { this.classDate = classDate; }
    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }
    public int getLecturerId() { return lecturerId; }
    public void setLecturerId(int lecturerId) { this.lecturerId = lecturerId; }
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
 *
 * This interface extends the generic `CrudDao` and specifies the `Class`
 * entity and `Integer` as the primary key type. It's a key part of the
 * layered architecture, ensuring the data access layer for classes is well-defined.
 */
public interface ClassSchedulingDao extends CrudDao<Class, Integer> {
    // This interface is intentionally empty as all necessary CRUD methods
    // are inherited from the CrudDao interface.
}
