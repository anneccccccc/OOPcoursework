/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.dto;

/**
 *
 * @author Chanuri Fernando
 */
public class LecturerDto {
    private int lecturerId;
    private String lecturerName;

    /**
     * Default constructor.
     */
    public LecturerDto() {}

    /**
     * Parameterized constructor to create a LecturerDto instance.
     * @param lecturerId The unique identifier of the lecturer.
     * @param lecturerName The name of the lecturer.
     */
    public LecturerDto(int lecturerId, String lecturerName) {
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
    }

    // --- Getters and Setters ---

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    /**
     * Overrides the default toString() method to provide a more descriptive string representation of the object.
     * @return A string containing the lecturer's details.
     */
    @Override
    public String toString() {
        return "LecturerDto{" + "lecturerId=" + lecturerId + ", lecturerName=" + lecturerName + '}';
    }
}
