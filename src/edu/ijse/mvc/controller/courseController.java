/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.controller;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.dto.*;
import edu.ijse.layerd.service.*;
import java.util.List;

// --- CourseController ---
public class CourseController {

    private final CourseService courseService = new CourseServiceImpl();

    public String saveCourse(CourseDto courseDto) {
        return courseService.saveCourse(courseDto);
    }

    public CourseDto getCourse(int courseId) {
        return courseService.getCourse(courseId);
    }

    public List<CourseDto> getAllCourses() {
        return courseService.getAllCourses();
    }

    public String updateCourse(CourseDto courseDto) {
        return courseService.updateCourse(courseDto);
    }

    public String deleteCourse(int courseId) {
        return courseService.deleteCourse(courseId);
    }
}