/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.dto;

/**
 *
 * @author Chanuri Fernando
 */
public class AttendanceDto {
    private int studentId;
    private int classId;
    private boolean isPresent;

    /**
     * Default constructor.
     */
    public AttendanceDto() {}

    /**
     * Parameterized constructor to create an AttendanceDto instance.
     * @param studentId The ID of the student.
     * @param classId The ID of the class session.
     * @param isPresent A boolean indicating if the student was present.
     */
    public AttendanceDto(int studentId, int classId, boolean isPresent) {
        this.studentId = studentId;
        this.classId = classId;
        this.isPresent = isPresent;
    }

    // --- Getters and Setters ---

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    /**
     * Overrides the default toString() method to provide a more descriptive string representation of the object.
     * @return A string containing the attendance details.
     */
    @Override
    public String toString() {
        return "AttendanceDto{" + "studentId=" + studentId + ", classId=" + classId + ", isPresent=" + isPresent + '}';
    }
}
