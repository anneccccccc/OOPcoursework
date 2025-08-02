/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.controller;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.dto.LecturerDto;
import edu.ijse.layerd.service.LecturerService;
import edu.ijse.layerd.service.ServiceFactory;
import java.util.List;

public class LecturerController {

    private final LecturerService lecturerService = ServiceFactory.getInstance().getLecturerService();

    
    public String saveLecturer(LecturerDto lecturerDto) {
        return lecturerService.saveLecturer(lecturerDto);
    }

    
    public LecturerDto getLecturer(int lecturerId) {
        return lecturerService.getLecturer(lecturerId);
    }

    
    public List<LecturerDto> getAllLecturers() {
        return lecturerService.getAllLecturers();
    }

   
    public String updateLecturer(LecturerDto lecturerDto) {
        return lecturerService.updateLecturer(lecturerDto);
    }

   
    public String deleteLecturer(int lecturerId) {
        return lecturerService.deleteLecturer(lecturerId);
    }
}