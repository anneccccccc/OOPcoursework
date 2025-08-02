/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.Entity;

/**
 *
 * @author Chanuri Fernando
 */
public class Lecturer {
    private int lecturerId;
    private String lecturerName;

    /**
     * Default constructor.
     */
    public Lecturer() {}

    /**
     * Parameterized constructor to create a Lecturer instance.
     * @param lecturerId The unique identifier of the lecturer.
     * @param lecturerName The name of the lecturer.
     */
    public Lecturer(int lecturerId, String lecturerName) {
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
}
