/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.service.User.impl;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.service.custom.CourseService;
import edu.ijse.layerd.dto.CourseDto;
import edu.ijse.layerd.dao.DaoFactory;
import edu.ijse.layerd.dao.SuperDao;
import edu.ijse.layerd.dao.custom.CourseDao;
import edu.ijse.layerd.entity.Course;
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
 * CourseService.java
 * Defines the contract for the CourseService.
 */
public interface CourseService extends SuperService {
    String saveCourse(CourseDto courseDto) throws Exception;
    CourseDto getCourse(Integer courseId) throws Exception;
    List<CourseDto> getAllCourses() throws Exception;
    String updateCourse(CourseDto courseDto) throws Exception;
    String deleteCourse(Integer courseId) throws Exception;
}

/**
 * CourseDto.java
 * DTO for the Course entity.
 */
class CourseDto {
    private int courseId;
    private String courseName;
    
    public CourseDto() {}
    public CourseDto(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
}

/**
 * Course.java
 * The entity class for a Course.
 */
class Course {
    private int courseId;
    private String courseName;

    public Course() {}
    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
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
 * CourseDao.java
 * The interface for the Course DAO.
 */
interface CourseDao extends CrudDao<Course, Integer> {}

/**
 * CourseDaoImpl.java
 * The implementation of the CourseDao interface.
 */
class CourseDaoImpl implements CourseDao {
    @Override
    public boolean save(Course course) throws Exception {
        String sql = "INSERT INTO Course (courseName) VALUES (?)";
        return CrudUtil.executeUpdate(sql, course.getCourseName());
    }
    
    @Override
    public boolean update(Course course) throws Exception {
        String sql = "UPDATE Course SET courseName = ? WHERE courseId = ?";
        return CrudUtil.executeUpdate(sql, course.getCourseName(), course.getCourseId());
    }
    
    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "DELETE FROM Course WHERE courseId = ?";
        return CrudUtil.executeUpdate(sql, id);
    }
    
    @Override
    public Course search(Integer id) throws Exception {
        String sql = "SELECT * FROM Course WHERE courseId = ?";
        ResultSet resultSet = CrudUtil.executeQuery(sql, id);
        if (resultSet.next()) {
            return new Course(
                resultSet.getInt("courseId"),
                resultSet.getString("courseName")
            );
        }
        return null;
    }
    
    @Override
    public ArrayList<Course> getAll() throws Exception {
        String sql = "SELECT * FROM Course";
        ResultSet resultSet = CrudUtil.executeQuery(sql);
        ArrayList<Course> courses = new ArrayList<>();
        while (resultSet.next()) {
            courses.add(new Course(
                resultSet.getInt("courseId"),
                resultSet.getString("courseName")
            ));
        }
        return courses;
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
            case COURSE: return new CourseDaoImpl();
            // Other DAOs would be here
            default: return null;
        }
    }
}

/**
 * CourseServiceImpl.java
 *
 * Implements the CourseService interface, providing the concrete business logic
 * for course management. This class uses the DaoFactory to obtain a CourseDao
 * instance and delegates the data access operations to it.
 */
public class CourseServiceImpl implements CourseService {
    
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private CourseDao courseDao = (CourseDao) daoFactory.getDao(DaoFactory.DaoTypes.COURSE);
    
    @Override
    public String saveCourse(CourseDto courseDto) throws Exception {
        Course course = new Course(0, courseDto.getCourseName());
        if (courseDao.save(course)) {
            return "Success";
        } else {
            return "Fail";
        }
    }
    
    @Override
    public CourseDto getCourse(Integer courseId) throws Exception {
        Course course = courseDao.search(courseId);
        if (course != null) {
            return new CourseDto(course.getCourseId(), course.getCourseName());
        }
        return null;
    }
    
    @Override
    public List<CourseDto> getAllCourses() throws Exception {
        List<Course> courses = courseDao.getAll();
        List<CourseDto> courseDtos = new ArrayList<>();
        for (Course course : courses) {
            courseDtos.add(new CourseDto(course.getCourseId(), course.getCourseName()));
        }
        return courseDtos;
    }
    
    @Override
    public String updateCourse(CourseDto courseDto) throws Exception {
        Course course = new Course(courseDto.getCourseId(), courseDto.getCourseName());
        if (courseDao.update(course)) {
            return "Success";
        } else {
            return "Fail";
        }
    }
    
    @Override
    public String deleteCourse(Integer courseId) throws Exception {
        if (courseDao.delete(courseId)) {
            return "Success";
        } else {
            return "Fail";
        }
    }
}
