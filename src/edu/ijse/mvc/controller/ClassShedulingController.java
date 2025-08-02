/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.controller;
import edu.ijse.layerd.dto.ClassDto;
import edu.ijse.layerd.service.ClassService;
import edu.ijse.layerd.service.ServiceFactory;
import java.util.List;

public class ClassController {

 
    private final ClassService classService = ServiceFactory.getInstance().getClassService();

   
    public String saveClass(ClassDto classDto) {
        return classService.saveClass(classDto);
    }

  
    public ClassDto getClassById(int classId) {
        return classService.getClassById(classId);
    }

    
    public List<ClassDto> getAllClasses() {
        return classService.getAllClasses();
    }

    public String updateClass(ClassDto classDto) {
        return classService.updateClass(classDto);
    }

  
    public String deleteClass(int classId) {
        return classService.deleteClass(classId);
    }
}
