/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.service.User;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.dto.CourseDto;
import java.util.List;

/**
 * SuperService.java
 * This is a marker interface. All specific service interfaces should extend it.
 */
interface SuperService {
    // This interface is intentionally empty.
}

/**
 * CourseDto.java
 * DTO for the Course entity. Provided here for the completeness of the service interface.
 */
class CourseDto {
    private int courseId;
    private String courseName;
    
    public CourseDto() {}
    public CourseDto(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
}

/**
 * CourseService.java
 *
 * Defines the contract for the CourseService. This interface outlines the business
 * methods that the service implementation must provide, ensuring a consistent
 * and well-defined API for the controller layer.
 */
public interface CourseService extends SuperService {

    /**
     * Saves a new course record.
     * @param courseDto The DTO containing the course data.
     * @return A message indicating the success or failure of the operation.
     * @throws Exception if a service or data access error occurs.
     */
    String saveCourse(CourseDto courseDto) throws Exception;

    /**
     * Retrieves a specific course record by its ID.
     * @param courseId The ID of the course to retrieve.
     * @return The CourseDto object if found, otherwise null.
     * @throws Exception if a service or data access error occurs.
     */
    CourseDto getCourse(Integer courseId) throws Exception;

    /**
     * Retrieves all course records.
     * @return A list of all CourseDto objects.
     * @throws Exception if a service or data access error occurs.
     */
    List<CourseDto> getAllCourses() throws Exception;

    /**
     * Updates an existing course record.
     * @param courseDto The DTO containing the updated course data.
     * @return A message indicating the success or failure of the operation.
     * @throws Exception if a service or data access error occurs.
     */
    String updateCourse(CourseDto courseDto) throws Exception;

    /**
     * Deletes a course record by its ID.
     * @param courseId The ID of the course to delete.
     * @return A message indicating the success or failure of the operation.
     * @throws Exception if a service or data access error occurs.
     */
    String deleteCourse(Integer courseId) throws Exception;
}
