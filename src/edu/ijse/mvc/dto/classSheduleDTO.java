/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.dto;

/**
 *
 * @author Chanuri Fernando
 */
import java.util.Date;

/**
 * ClassDto.java
 * DTO for the Class entity. It is used to transfer data related to a class session
 * between different layers of the application.
 */
public class ClassDto {
    private int classId;
    private Date classDate;
    private int subjectId;
    private int lecturerId;

    /**
     * Default constructor.
     */
    public ClassDto() {}

    /**
     * Parameterized constructor to create a ClassDto instance.
     * @param classId The unique identifier of the class session.
     * @param classDate The date of the class session.
     * @param subjectId The ID of the subject for the class.
     * @param lecturerId The ID of the lecturer teaching the class.
     */
    public ClassDto(int classId, Date classDate, int subjectId, int lecturerId) {
        this.classId = classId;
        this.classDate = classDate;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
    }

    // --- Getters and Setters ---

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    /**
     * Overrides the default toString() method to provide a more descriptive string representation of the object.
     * @return A string containing the class session's details.
     */
    @Override
    public String toString() {
        return "ClassDto{" + "classId=" + classId + ", classDate=" + classDate + ", subjectId=" + subjectId + ", lecturerId=" + lecturerId + '}';
    }
}
