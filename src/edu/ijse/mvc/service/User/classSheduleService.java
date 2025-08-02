/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.service.User;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.dto.ClassDto;
import java.util.List;
import java.util.Date;

/**
 * SuperService.java
 * This is a marker interface. All specific service interfaces should extend it.
 */
interface SuperService {
    // This interface is intentionally empty.
}

/**
 * ClassDto.java
 * DTO for the Class entity. Provided here for the completeness of the service interface.
 */
class ClassDto {
    private int classId;
    private Date classDate;
    private int subjectId;
    private int lecturerId;

    public ClassDto() {}

    public ClassDto(int classId, Date classDate, int subjectId, int lecturerId) {
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
 * ClassService.java
 *
 * Defines the contract for the ClassService. This interface outlines the business
 * methods that the service implementation must provide, ensuring a consistent
 * and well-defined API for the controller layer.
 */
public interface ClassService extends SuperService {

    /**
     * Saves a new class record.
     * @param classDto The DTO containing the class data.
     * @return A message indicating the success or failure of the operation.
     * @throws Exception if a service or data access error occurs.
     */
    String saveClass(ClassDto classDto) throws Exception;

    /**
     * Retrieves a specific class record by its ID.
     * @param classId The ID of the class to retrieve.
     * @return The ClassDto object if found, otherwise null.
     * @throws Exception if a service or data access error occurs.
     */
    ClassDto getClass(Integer classId) throws Exception;

    /**
     * Retrieves all class records.
     * @return A list of all ClassDto objects.
     * @throws Exception if a service or data access error occurs.
     */
    List<ClassDto> getAllClasses() throws Exception;

    /**
     * Updates an existing class record.
     * @param classDto The DTO containing the updated class data.
     * @return A message indicating the success or failure of the operation.
     * @throws Exception if a service or data access error occurs.
     */
    String updateClass(ClassDto classDto) throws Exception;

    /**
     * Deletes a class record by its ID.
     * @param classId The ID of the class to delete.
     * @return A message indicating the success or failure of the operation.
     * @throws Exception if a service or data access error occurs.
     */
    String deleteClass(Integer classId) throws Exception;
}
