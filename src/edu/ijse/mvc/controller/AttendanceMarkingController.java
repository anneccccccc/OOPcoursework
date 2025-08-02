/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.controller;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.dto.AttendanceDto;
import edu.ijse.layerd.service.AttendanceService;
import edu.ijse.layerd.service.ServiceFactory;
import java.util.List;

public class AttendanceController {

    // Get the service instance from the factory to decouple the controller
    // from the concrete service implementation.
    private final AttendanceService attendanceService = ServiceFactory.getInstance().getAttendanceService();

    /**
     * Delegates the request to save a new attendance record to the service layer.
     * This method is used for **marking** a student as present or absent for a class.
     * @param attendanceDto The attendance data to be saved.
     * @return A message indicating the success or failure of the operation.
     */
    public String saveAttendance(AttendanceDto attendanceDto) {
        return attendanceService.saveAttendance(attendanceDto);
    }

    /**
     * Delegates the request to get a specific attendance record to the service layer.
     * This method is used for **reporting** on a single attendance record.
     * @param studentId The ID of the student.
     * @param classId The ID of the class session.
     * @return The AttendanceDto object if found, otherwise null.
     */
    public AttendanceDto getAttendance(int studentId, int classId) {
        return attendanceService.getAttendance(studentId, classId);
    }

    /**
     * Delegates the request to get all attendance records to the service layer.
     * This method is used for a full **report** of all attendance.
     * @return A list of all AttendanceDto objects.
     */
    public List<AttendanceDto> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    /**
     * Delegates the request to update an existing attendance record to the service layer.
     * This method is used for **correcting** a previously marked attendance record.
     * @param attendanceDto The attendance data to be updated.
     * @return A message indicating the success or failure of the operation.
     */
    public String updateAttendance(AttendanceDto attendanceDto) {
        return attendanceService.updateAttendance(attendanceDto);
    }

    /**
     * Delegates the request to delete an attendance record to the service layer.
     * @param studentId The ID of the student.
     * @param classId The ID of the class session.
     * @return A message indicating the success or failure of the operation.
     */
    public String deleteAttendance(int studentId, int classId) {
        return attendanceService.deleteAttendance(studentId, classId);
    }
}
