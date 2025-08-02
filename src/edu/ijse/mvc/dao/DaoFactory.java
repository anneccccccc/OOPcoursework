/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.dao;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.dao.custom.impl.AttendanceDaoImpl;
import edu.ijse.layerd.dao.custom.impl.ClassDaoImpl;
import edu.ijse.layerd.dao.custom.impl.CourseDaoImpl;
import edu.ijse.layerd.dao.custom.impl.LecturerDaoImpl;
import edu.ijse.layerd.dao.custom.impl.StudentDaoImpl;
import edu.ijse.layerd.dao.custom.impl.SubjectDaoImpl;

/**
 * DaoFactory.java
 *
 * This class implements the Factory design pattern. It provides a single point
 * of access for creating and retrieving DAO instances, ensuring that the
 * application's business logic is not tightly coupled to the concrete DAO
 * implementations.
 *
 * It uses a singleton pattern to ensure only one instance of the factory exists.
 *
 * @author Gemini
 */
public class DaoFactory {

    private static DaoFactory daoFactory;

    /**
     * Private constructor to prevent direct instantiation from outside the class.
     */
    private DaoFactory() {}

    /**
     * Provides a single, globally accessible instance of the DaoFactory.
     * @return The singleton instance of DaoFactory.
     */
    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }
    
    /**
     * A utility enum to define the types of DAOs available in the factory.
     */
    public enum DaoTypes {
        COURSE,
        STUDENT,
        SUBJECT,
        LECTURER,
        CLASS,
        ATTENDANCE
    }
    
    /**
     * Factory method to get a DAO instance based on the specified type.
     * @param type The type of DAO to retrieve, defined by the DaoTypes enum.
     * @return A new DAO implementation that extends SuperDao, or null if the type is invalid.
     */
    public SuperDao getDao(DaoTypes type) {
        switch (type) {
            case COURSE:
                return new CourseDaoImpl();
            case STUDENT:
                return new StudentDaoImpl();
            case SUBJECT:
                return new SubjectDaoImpl();
            case LECTURER:
                return new LecturerDaoImpl();
            case CLASS:
                return new ClassDaoImpl();
            case ATTENDANCE:
                return new AttendanceDaoImpl();
            default:
                return null;
        }
    }
}