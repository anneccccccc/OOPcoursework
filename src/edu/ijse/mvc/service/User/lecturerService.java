/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.service.User;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.dto.LecturerDto;
import java.util.List;

/**
 * SuperService.java
 * This is a marker interface. All specific service interfaces should extend it.
 */
interface SuperService {
    // This interface is intentionally empty.
}

/**
 * LecturerDto.java
 * DTO for the Lecturer entity. Provided here for the completeness of the service interface.
 */
class LecturerDto {
    private int lecturerId;
    private String lecturerName;

    public LecturerDto() {}

    public LecturerDto(int lecturerId, String lecturerName) {
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
    }

    public int getLecturerId() { return lecturerId; }
    public void setLecturerId(int lecturerId) { this.lecturerId = lecturerId; }
    public String getLecturerName() { return lecturerName; }
    public void setLecturerName(String lecturerName) { this.lecturerName = lecturerName; }
}

/**
 * LecturerService.java
 *
 * Defines the contract for the LecturerService. This interface outlines the business
 * methods that the service implementation must provide, ensuring a consistent
 * and well-defined API for the controller layer.
 */
public interface LecturerService extends SuperService {

    /**
     * Saves a new lecturer record.
     * @param lecturerDto The DTO containing the lecturer data.
     * @return A message indicating the success or failure of the operation.
     * @throws Exception if a service or data access error occurs.
     */
    String saveLecturer(LecturerDto lecturerDto) throws Exception;

    /**
     * Retrieves a specific lecturer record by its ID.
     * @param lecturerId The ID of the lecturer to retrieve.
     * @return The LecturerDto object if found, otherwise null.
     * @throws Exception if a service or data access error occurs.
     */
    LecturerDto getLecturer(Integer lecturerId) throws Exception;

    /**
     * Retrieves all lecturer records.
     * @return A list of all LecturerDto objects.
     * @throws Exception if a service or data access error occurs.
     */
    List<LecturerDto> getAllLecturers() throws Exception;

    /**
     * Updates an existing lecturer record.
     * @param lecturerDto The DTO containing the updated lecturer data.
     * @return A message indicating the success or failure of the operation.
     * @throws Exception if a service or data access error occurs.
     */
    String updateLecturer(LecturerDto lecturerDto) throws Exception;

    /**
     * Deletes a lecturer record by its ID.
     * @param lecturerId The ID of the lecturer to delete.
     * @return A message indicating the success or failure of the operation.
     * @throws Exception if a service or data access error occurs.
     */
    String deleteLecturer(Integer lecturerId) throws Exception;
}
