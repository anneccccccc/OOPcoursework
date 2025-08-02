/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.Entity;

/**
 *
 * @author Chanuri Fernando
 */
import java.util.Date;

/**
 * Class.java
 *
 * This class represents the Class entity, which is a scheduled class session.
 * It maps to a corresponding table in the database and is used to hold and
 * transfer data between different application layers.
 */
public class Class {
    private int classId;
    private Date classDate;
    private int subjectId;
    private int lecturerId;

    /**
     * Default constructor.
     */
    public Class() {}

    /**
     * Parameterized constructor to create a Class instance.
     * @param classId The unique identifier of the class session.
     * @param classDate The date of the class session.
     * @param subjectId The ID of the subject for the class.
     * @param lecturerId The ID of the lecturer teaching the class.
     */
    public Class(int classId, Date classDate, int subjectId, int lecturerId) {
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
}
