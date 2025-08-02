/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.service.User.impl;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.service.custom.StudentService;
import edu.ijse.layerd.dto.StudentDto;
import edu.ijse.layerd.dao.DaoFactory;
import edu.ijse.layerd.dao.SuperDao;
import edu.ijse.layerd.dao.custom.StudentDao;
import edu.ijse.layerd.entity.Student;
import edu.ijse.layerd.db.DBConnection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * SuperService.java
 * Marker interface for all service interfaces.
 */
interface SuperService {
}

/**
 * StudentService.java
 * Defines the contract for the StudentService.
 */
public interface StudentService extends SuperService {
    String saveStudent(StudentDto studentDto) throws Exception;
    StudentDto getStudent(Integer studentId) throws Exception;
    List<StudentDto> getAllStudents() throws Exception;
    String updateStudent(StudentDto studentDto) throws Exception;
    String deleteStudent(Integer studentId) throws Exception;
}

/**
 * StudentDto.java
 * DTO for the Student entity.
 */
class StudentDto {
    private int studentId;
    private String studentName;
    private String regNo;
    private String contactDetails;
    private int courseId;

    public StudentDto() {}
    
    public StudentDto(int studentId, String studentName, String regNo, String contactDetails, int courseId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.regNo = regNo;
        this.contactDetails = contactDetails;
        this.courseId = courseId;
    }
    
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }
    public String getContactDetails() { return contactDetails; }
    public void setContactDetails(String contactDetails) { this.contactDetails = contactDetails; }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
}

/**
 * Student.java
 * The entity class for a Student.
 */
class Student {
    private int studentId;
    private String studentName;
    private String regNo;
    private String contactDetails;
    private int courseId;

    public Student() {}
    public Student(int studentId, String studentName, String regNo, String contactDetails, int courseId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.regNo = regNo;
        this.contactDetails = contactDetails;
        this.courseId = courseId;
    }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }
    public String getContactDetails() { return contactDetails; }
    public void setContactDetails(String contactDetails) { this.contactDetails = contactDetails; }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
}

/**
 * SuperDao.java
 * Marker interface for all DAO classes.
 */
interface SuperDao {}

/**
 * CrudDao.java
 * Generic DAO interface for CRUD operations.
 */
interface CrudDao<T, ID> extends SuperDao {
    boolean save(T t) throws Exception;
    boolean update(T t) throws Exception;
    boolean delete(ID id) throws Exception;
    T search(ID id) throws Exception;
    ArrayList<T> getAll() throws Exception;
}

/**
 * CrudUtil.java
 * A utility class to handle generic JDBC operations, reducing boilerplate code.
 */
class CrudUtil {
    private static java.sql.PreparedStatement getStatement(String sql, Object... args) throws Exception {
        java.sql.Connection connection = DBConnection.getInstance().getConnection();
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                statement.setObject((i + 1), args[i]);
            }
        }
        return statement;
    }
    
    public static boolean executeUpdate(String sql, Object... args) throws Exception {
        return getStatement(sql, args).executeUpdate() > 0;
    }
    
    public static ResultSet executeQuery(String sql, Object... args) throws Exception {
        return getStatement(sql, args).executeQuery();
    }
}

/**
 * StudentDao.java
 * The interface for the Student DAO.
 */
interface StudentDao extends CrudDao<Student, Integer> {}

/**
 * StudentDaoImpl.java
 * The implementation of the StudentDao interface.
 */
class StudentDaoImpl implements StudentDao {
    @Override
    public boolean save(Student student) throws Exception {
        String sql = "INSERT INTO Student (studentName, regNo, contactDetails, courseId) VALUES (?, ?, ?, ?)";
        return CrudUtil.executeUpdate(sql, student.getStudentName(), student.getRegNo(), student.getContactDetails(), student.getCourseId());
    }
    
    @Override
    public boolean update(Student student) throws Exception {
        String sql = "UPDATE Student SET studentName = ?, regNo = ?, contactDetails = ?, courseId = ? WHERE studentId = ?";
        return CrudUtil.executeUpdate(sql, student.getStudentName(), student.getRegNo(), student.getContactDetails(), student.getCourseId(), student.getStudentId());
    }
    
    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "DELETE FROM Student WHERE studentId = ?";
        return CrudUtil.executeUpdate(sql, id);
    }
    
    @Override
    public Student search(Integer id) throws Exception {
        String sql = "SELECT * FROM Student WHERE studentId = ?";
        ResultSet resultSet = CrudUtil.executeQuery(sql, id);
        if (resultSet.next()) {
            return new Student(
                resultSet.getInt("studentId"),
                resultSet.getString("studentName"),
                resultSet.getString("regNo"),
                resultSet.getString("contactDetails"),
                resultSet.getInt("courseId")
            );
        }
        return null;
    }
    
    @Override
    public ArrayList<Student> getAll() throws Exception {
        String sql = "SELECT * FROM Student";
        ResultSet resultSet = CrudUtil.executeQuery(sql);
        ArrayList<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            students.add(new Student(
                resultSet.getInt("studentId"),
                resultSet.getString("studentName"),
                resultSet.getString("regNo"),
                resultSet.getString("contactDetails"),
                resultSet.getInt("courseId")
            ));
        }
        return students;
    }
}

/**
 * DaoFactory.java
 * Factory to create DAO instances.
 */
class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory() {}
    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }
    
    public enum DaoTypes {
        COURSE, STUDENT, LECTURER, CLASS, ATTENDANCE
    }
    
    public SuperDao getDao(DaoTypes type) {
        switch (type) {
            case STUDENT: return new StudentDaoImpl();
            // Other DAOs would be here
            default: return null;
        }
    }
}

/**
 * StudentServiceImpl.java
 *
 * Implements the StudentService interface, providing the concrete business logic
 * for student management. This class uses the DaoFactory to obtain a StudentDao
 * instance and delegates the data access operations to it.
 */
public class StudentServiceImpl implements StudentService {
    
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private StudentDao studentDao = (StudentDao) daoFactory.getDao(DaoFactory.DaoTypes.STUDENT);
    
    @Override
    public String saveStudent(StudentDto studentDto) throws Exception {
        Student student = new Student(0, studentDto.getStudentName(), studentDto.getRegNo(), studentDto.getContactDetails(), studentDto.getCourseId());
        if (studentDao.save(student)) {
            return "Success";
        } else {
            return "Fail";
        }
    }
    
    @Override
    public StudentDto getStudent(Integer studentId) throws Exception {
        Student student = studentDao.search(studentId);
        if (student != null) {
            return new StudentDto(student.getStudentId(), student.getStudentName(), student.getRegNo(), student.getContactDetails(), student.getCourseId());
        }
        return null;
    }
    
    @Override
    public List<StudentDto> getAllStudents() throws Exception {
        List<Student> students = studentDao.getAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            studentDtos.add(new StudentDto(student.getStudentId(), student.getStudentName(), student.getRegNo(), student.getContactDetails(), student.getCourseId()));
        }
        return studentDtos;
    }
    
    @Override
    public String updateStudent(StudentDto studentDto) throws Exception {
        Student student = new Student(studentDto.getStudentId(), studentDto.getStudentName(), studentDto.getRegNo(), studentDto.getContactDetails(), studentDto.getCourseId());
        if (studentDao.update(student)) {
            return "Success";
        } else {
            return "Fail";
        }
    }
    
    @Override
    public String deleteStudent(Integer studentId) throws Exception {
        if (studentDao.delete(studentId)) {
            return "Success";
        } else {
            return "Fail";
        }
    }
}

