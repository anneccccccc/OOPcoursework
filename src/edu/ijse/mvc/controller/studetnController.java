/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.controller;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.dto.StudentDto;
import edu.ijse.layerd.service.ServiceFactory;
import edu.ijse.layerd.service.StudentService;
import java.util.List;

public class StudentController {
    
    // Get the service instance from the factory to decouple the controller
    // from the concrete service implementation.
    private final StudentService studentService = ServiceFactory.getInstance().getStudentService();
    
    /**
     * Delegates the request to save a student to the service layer.
     * @param studentDto The student data to be saved.
     * @return A message indicating the success or failure of the operation.
     */
    public String saveStudent(StudentDto studentDto) {
        return studentService.saveStudent(studentDto);
    }
    
    /**
     * Delegates the request to get a student by ID to the service layer.
     * @param studentId The ID of the student to retrieve.
     * @return The StudentDto object if found, otherwise null.
     */
    public StudentDto getStudent(int studentId) {
        return studentService.getStudent(studentId);
    }
    
    /**
     * Delegates the request to get all students to the service layer.
     * @return A list of all StudentDto objects.
     */
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents();
    }
    
    /**
     * Delegates the request to update a student to the service layer.
     * @param studentDto The student data to be updated.
     * @return A message indicating the success or failure of the operation.
     */
    public String updateStudent(StudentDto studentDto) {
        return studentService.updateStudent(studentDto);
    }
    
    /**
     * Delegates the request to delete a student to the service layer.
     * @param studentId The ID of the student to delete.
     * @return A message indicating the success or failure of the operation.
     */
    public String deleteStudent(int studentId) {
        return studentService.deleteStudent(studentId);
    }
}
