/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.dao;

/**
 *
 * @author Chanuri Fernando

import edu.ijse.layerd.entity.SuperEntity;
import java.util.ArrayList;

/**
 * SuperDao.java
 * This is a marker interface. All specific DAO interfaces should extend it.
 */
public interface SuperDao {
    // This interface is intentionally empty.
}

/**
 * CrudDao.java
 *
 * Defines a generic contract for Data Access Objects (DAOs) to perform
 * standard CRUD (Create, Read, Update, Delete) operations. This interface
 * uses generic types <T> for the entity and <ID> for the primary key.
 */
public interface CrudDao<T extends SuperEntity, ID> extends SuperDao {

    /**
     * Saves a new entity record to the database.
     * @param t The entity object to save.
     * @return true if the save operation was successful, false otherwise.
     * @throws Exception if a data access error occurs.
     */
    boolean save(T t) throws Exception;

    /**
     * Updates an existing entity record in the database.
     * @param t The entity object with updated data.
     * @return true if the update operation was successful, false otherwise.
     * @throws Exception if a data access error occurs.
     */
    boolean update(T t) throws Exception;

    /**
     * Deletes an entity record from the database by its ID.
     * @param id The ID of the entity to delete.
     * @return true if the delete operation was successful, false otherwise.
     * @throws Exception if a data access error occurs.
     */
    boolean delete(ID id) throws Exception;

    /**
     * Retrieves a specific entity record from the database by its ID.
     * @param id The ID of the entity to retrieve.
     * @return The entity object if found, otherwise null.
     * @throws Exception if a data access error occurs.
     */
    T search(ID id) throws Exception;

    /**
     * Retrieves all entity records from the database.
     * @return An ArrayList of all entity objects.
     * @throws Exception if a data access error occurs.
     */
    ArrayList<T> getAll() throws Exception;
}

/**
 * SuperEntity.java
 * A marker interface for all entity classes.
 */
interface SuperEntity {
    // This interface is intentionally empty.
}
