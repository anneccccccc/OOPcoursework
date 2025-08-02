/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.dto;

/**
 *
 * @author Chanuri Fernando
 */
public class CourseDto {
    private int courseId;
    private String courseName;

    /**
     * Default constructor.
     */
    public CourseDto() {}

    /**
     * Parameterized constructor to create a CourseDto instance.
     * @param courseId The unique identifier of the course.
     * @param courseName The name of the course.
     */
    public CourseDto(int courseId, String courseName) {
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

    /**
     * Overrides the default toString() method to provide a more descriptive string representation of the object.
     * @return A string containing the courseId and courseName.
     */
    @Override
    public String toString() {
        return "CourseDto{" + "courseId=" + courseId + ", courseName=" + courseName + '}';
    }
}