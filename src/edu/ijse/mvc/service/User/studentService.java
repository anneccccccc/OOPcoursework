/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.service.User;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.dto.StudentDto;
import java.util.List;

/**
 * SuperService.java
 * This is a marker interface. All specific service interfaces should extend it.
 */
interface SuperService {
    // This interface is intentionally empty.
}

/**
 * StudentDto.java
 * DTO for the Student entity. Provided here for the completeness of the service interface.
 */
class StudentDto {
    private int studentId;
    private String studentName;
    private String regNo;
    private String contactDetails;
    private int courseId;

    public StudentDto() {}
    
    public StudentDto(int studentId, String studentName, String regNo, String contactDetails, int courseId) {
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
 * StudentService.java
 *
 * Defines the contract for the StudentService. This interface outlines the business
 * methods that the service implementation must provide, ensuring a consistent
 * and well-defined API for the controller layer.
 */
public interface StudentService extends SuperService {

    /**
     * Saves a new student record.
     * @param studentDto The DTO containing the student data.
     * @return A message indicating the success or failure of the operation.
     * @throws Exception if a service or data access error occurs.
     */
    String saveStudent(StudentDto studentDto) throws Exception;

    /**
     * Retrieves a specific student record by its ID.
     * @param studentId The ID of the student to retrieve.
     * @return The StudentDto object if found, otherwise null.
     * @throws Exception if a service or data access error occurs.
     */
    StudentDto getStudent(Integer studentId) throws Exception;

    /**
     * Retrieves all student records.
     * @return A list of all StudentDto objects.
     * @throws Exception if a service or data access error occurs.
     */
    List<StudentDto> getAllStudents() throws Exception;

    /**
     * Updates an existing student record.
     * @param studentDto The DTO containing the updated student data.
     * @return A message indicating the success or failure of the operation.
     * @throws Exception if a service or data access error occurs.
     */
    String updateStudent(StudentDto studentDto) throws Exception;

    /**
     * Deletes a student record by its ID.
     * @param studentId The ID of the student to delete.
     * @return A message indicating the success or failure of the operation.
     * @throws Exception if a service or data access error occurs.
     */
    String deleteStudent(Integer studentId) throws Exception;
}
