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
import edu.ijse.layerd.entity.Attendance;
import edu.ijse.layerd.entity.SuperEntity;

import java.util.ArrayList;

/**
 * SuperEntity.java
 * A marker interface for all entity classes.
 */
interface SuperEntity {
}

/**
 * Attendance.java
 * The entity class for a student's attendance record.
 */
class Attendance implements SuperEntity {
    private int studentId;
    private int classId;
    private boolean isPresent;

    public Attendance() {}

    public Attendance(int studentId, int classId, boolean isPresent) {
        this.studentId = studentId;
        this.classId = classId;
        this.isPresent = isPresent;
    }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public int getClassId() { return classId; }
    public void setClassId(int classId) { this.classId = classId; }
    public boolean isPresent() { return isPresent; }
    public void setPresent(boolean present) { isPresent = present; }
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
 *
 * This interface defines the data access contract for the `Attendance` entity.
 * It does not extend the generic `CrudDao` because `Attendance` uses a
 * composite primary key (studentId and classId). This interface provides custom
 * methods to handle the composite key for searching and deleting records.
 */
public interface AttendanceDao extends SuperDao {
    boolean save(Attendance attendance) throws Exception;
    boolean update(Attendance attendance) throws Exception;
    boolean delete(int studentId, int classId) throws Exception;
    Attendance search(int studentId, int classId) throws Exception;
    ArrayList<Attendance> getAll() throws Exception;
}
