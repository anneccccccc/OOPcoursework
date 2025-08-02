/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.service;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.service.custom.impl.AttendanceServiceImpl;
import edu.ijse.layerd.service.custom.impl.ClassServiceImpl;
import edu.ijse.layerd.service.custom.impl.CourseServiceImpl;
import edu.ijse.layerd.service.custom.impl.LecturerServiceImpl;
import edu.ijse.layerd.service.custom.impl.StudentServiceImpl;

/**
 * ServiceFactory.java
 *
 * This class implements the Factory design pattern. It provides a single point
 * of access for creating and retrieving service instances, ensuring that the
 * application's controller layer is not tightly coupled to the concrete service
 * implementations.
 *
 * It uses a singleton pattern to ensure only one instance of the factory exists.
 *
 * @author Gemini
 */
public class ServiceFactory {
    
    private static ServiceFactory serviceFactory;

    /**
     * Private constructor to prevent direct instantiation from outside the class.
     */
    private ServiceFactory() {}
    
    /**
     * Provides a single, globally accessible instance of the ServiceFactory.
     * @return The singleton instance of ServiceFactory.
     */
    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }
    
    /**
     * A utility enum to define the types of services available in the factory.
     */
    public enum ServiceTypes {
        COURSE,
        STUDENT,
        LECTURER,
        CLASS,
        ATTENDANCE
    }
    
    /**
     * Factory method to get a service instance based on the specified type.
     * @param type The type of service to retrieve, defined by the ServiceTypes enum.
     * @return A new service implementation, or null if the type is invalid.
     */
    public SuperService getService(ServiceTypes type) {
        switch (type) {
            case COURSE:
                return new CourseServiceImpl();
            case STUDENT:
                return new StudentServiceImpl();
            case LECTURER:
                return new LecturerServiceImpl();
            case CLASS:
                return new ClassServiceImpl();
            case ATTENDANCE:
                return new AttendanceServiceImpl();
            default:
                return null;
        }
    }
}
