/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.Entity;

/**
 *
 * @author Chanuri Fernando
 */
*/
public class Student {
    private int studentId;
    private String studentName;
    private String regNo;
    private String contactDetails;
    private int courseId;

    /**
     * Default constructor.
     */
    public Student() {}

    /**
     * Parameterized constructor to create a Student instance.
     * @param studentId The unique identifier of the student.
     * @param studentName The name of the student.
     * @param regNo The registration number of the student.
     * @param contactDetails The contact details of the student.
     * @param courseId The ID of the course the student is enrolled in.
     */
    public Student(int studentId, String studentName, String regNo, String contactDetails, int courseId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.regNo = regNo;
        this.contactDetails = contactDetails;
        this.courseId = courseId;
    }

    // --- Getters and Setters ---

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
    
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}

