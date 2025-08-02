/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.Entity;

/**
 *
 * @author Chanuri Fernando
 */
public class Course {
    private int courseId;
    private String courseName;

    /**
     * Default constructor.
     */
    public Course() {}

    /**
     * Parameterized constructor to create a Course instance.
     * @param courseId The unique identifier of the course.
     * @param courseName The name of the course.
     */
    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    // --- Getters and Setters ---

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
