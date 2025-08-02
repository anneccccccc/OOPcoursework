/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.service.User;

/**
 *
 * @author Chanuri Fernando
 */
mport edu.ijse.layerd.dto.AttendanceDto;
import java.util.List;

/**
 * SuperService.java
 * This is a marker interface. All specific service interfaces should extend it.
 */
interface SuperService {
    // This interface is intentionally empty.
}

/**
 * AttendanceDto.java
 * DTO for the Attendance entity. Provided here for the completeness of the service interface.
 */
class AttendanceDto {
    private int studentId;
    private int classId;
    private boolean isPresent;

    public AttendanceDto() {}

    public AttendanceDto(int studentId, int classId, boolean isPresent) {
        this.studentId = studentId;
        this.classId = classId;
        this.isPresent = isPresent;
    }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public int getClassId() { return classId; }
    public void setClassId(int classId) { this.classId = classId; }
    public boolean isPresent() { return isPresent; }
    public void setPresent(boolean present) { isPresent = present; }
}


/**
 * AttendanceService.java
 *
 * Defines the contract for the AttendanceService. This interface outlines the business
 * methods that the service implementation must provide, ensuring a consistent
 * and well-defined API for the controller layer.
 */
public interface AttendanceService extends SuperService {

    /**
     * Saves a new attendance record.
     * @param attendanceDto The DTO containing the attendance data.
     * @return A message indicating the success or failure of the operation.
     * @throws Exception if a service or data access error occurs.
     */
    String saveAttendance(AttendanceDto attendanceDto) throws Exception;

    /**
     * Retrieves a specific attendance record by student and class IDs.
     * @param studentId The ID of the student.
     * @param classId The ID of the class.
     * @return The AttendanceDto object if found, otherwise null.
     * @throws Exception if a service or data access error occurs.
     */
    AttendanceDto getAttendance(Integer studentId, Integer classId) throws Exception;

    /**
     * Retrieves all attendance records.
     * @return A list of all AttendanceDto objects.
     * @throws Exception if a service or data access error occurs.
     */
    List<AttendanceDto> getAllAttendances() throws Exception;

    /**
     * Updates an existing attendance record.
     * @param attendanceDto The DTO containing the updated attendance data.
     * @return A message indicating the success or failure of the operation.
     * @throws Exception if a service or data access error occurs.
     */
    String updateAttendance(AttendanceDto attendanceDto) throws Exception;

    /**
     * Deletes an attendance record by student and class IDs.
     * @param studentId The ID of the student.
     * @param classId The ID of the class.
     * @return A message indicating the success or failure of the operation.
     * @throws Exception if a service or data access error occurs.
     */
    String deleteAttendance(Integer studentId, Integer classId) throws Exception;
}
