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
import edu.ijse.layerd.entity.Lecturer;
import edu.ijse.layerd.entity.SuperEntity;

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

    public Lecturer() {}

    public Lecturer(int lecturerId, String lecturerName) {
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
    }

    public int getLecturerId() { return lecturerId; }
    public void setLecturerId(int lecturerId) { this.lecturerId = lecturerId; }
    public String getLecturerName() { return lecturerName; }
    public void setLecturerName(String lecturerName) { this.lecturerName = lecturerName; }
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
 *
 * This interface extends the generic `CrudDao` and specifies the `Lecturer`
 * entity and `Integer` as the primary key type. It's a key part of the
 * layered architecture, ensuring the data access layer for lecturers is well-defined.
 */
public interface LecturerDao extends CrudDao<Lecturer, Integer> {
    // This interface is intentionally empty as all necessary CRUD methods
    // are inherited from the CrudDao interface.
}
