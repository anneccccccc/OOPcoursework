/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.service.User.impl;

/**
 *
 * @author Chanuri Fernando
import edu.ijse.layerd.service.custom.ClassService;
import edu.ijse.layerd.dto.ClassDto;
import edu.ijse.layerd.dao.DaoFactory;
import edu.ijse.layerd.dao.SuperDao;
import edu.ijse.layerd.dao.custom.ClassDao;
import edu.ijse.layerd.entity.Class;
import edu.ijse.layerd.db.DBConnection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * SuperService.java
 * Marker interface for all service interfaces.
 */
interface SuperService {
}

/**
 * ClassService.java
 * Defines the contract for the ClassService.
 */
public interface ClassService extends SuperService {
    String saveClass(ClassDto classDto) throws Exception;
    ClassDto getClass(Integer classId) throws Exception;
    List<ClassDto> getAllClasses() throws Exception;
    String updateClass(ClassDto classDto) throws Exception;
    String deleteClass(Integer classId) throws Exception;
}

/**
 * ClassDto.java
 * DTO for the Class entity.
 */
class ClassDto {
    private int classId;
    private Date classDate;
    private int subjectId;
    private int lecturerId;

    public ClassDto() {}

    public ClassDto(int classId, Date classDate, int subjectId, int lecturerId) {
        this.classId = classId;
        this.classDate = classDate;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
    }

    public int getClassId() { return classId; }
    public void setClassId(int classId) { this.classId = classId; }
    public Date getClassDate() { return classDate; }
    public void setClassDate(Date classDate) { this.classDate = classDate; }
    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }
    public int getLecturerId() { return lecturerId; }
    public void setLecturerId(int lecturerId) { this.lecturerId = lecturerId; }
}

/**
 * Class.java
 * The entity class for a Class.
 */
class Class {
    private int classId;
    private Date classDate;
    private int subjectId;
    private int lecturerId;

    public Class() {}

    public Class(int classId, Date classDate, int subjectId, int lecturerId) {
        this.classId = classId;
        this.classDate = classDate;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
    }
    public int getClassId() { return classId; }
    public void setClassId(int classId) { this.classId = classId; }
    public Date getClassDate() { return classDate; }
    public void setClassDate(Date classDate) { this.classDate = classDate; }
    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }
    public int getLecturerId() { return lecturerId; }
    public void setLecturerId(int lecturerId) { this.lecturerId = lecturerId; }
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
 * ClassDao.java
 * The interface for the Class DAO.
 */
interface ClassDao extends CrudDao<Class, Integer> {}

/**
 * ClassDaoImpl.java
 * The implementation of the ClassDao interface.
 */
class ClassDaoImpl implements ClassDao {
    @Override
    public boolean save(Class classEntity) throws Exception {
        String sql = "INSERT INTO Class (classDate, subjectId, lecturerId) VALUES (?, ?, ?)";
        return CrudUtil.executeUpdate(sql, new java.sql.Date(classEntity.getClassDate().getTime()), classEntity.getSubjectId(), classEntity.getLecturerId());
    }
    
    @Override
    public boolean update(Class classEntity) throws Exception {
        String sql = "UPDATE Class SET classDate = ?, subjectId = ?, lecturerId = ? WHERE classId = ?";
        return CrudUtil.executeUpdate(sql, new java.sql.Date(classEntity.getClassDate().getTime()), classEntity.getSubjectId(), classEntity.getLecturerId(), classEntity.getClassId());
    }
    
    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "DELETE FROM Class WHERE classId = ?";
        return CrudUtil.executeUpdate(sql, id);
    }
    
    @Override
    public Class search(Integer id) throws Exception {
        String sql = "SELECT * FROM Class WHERE classId = ?";
        ResultSet resultSet = CrudUtil.executeQuery(sql, id);
        if (resultSet.next()) {
            return new Class(
                resultSet.getInt("classId"),
                resultSet.getDate("classDate"),
                resultSet.getInt("subjectId"),
                resultSet.getInt("lecturerId")
            );
        }
        return null;
    }
    
    @Override
    public ArrayList<Class> getAll() throws Exception {
        String sql = "SELECT * FROM Class";
        ResultSet resultSet = CrudUtil.executeQuery(sql);
        ArrayList<Class> classes = new ArrayList<>();
        while (resultSet.next()) {
            classes.add(new Class(
                resultSet.getInt("classId"),
                resultSet.getDate("classDate"),
                resultSet.getInt("subjectId"),
                resultSet.getInt("lecturerId")
            ));
        }
        return classes;
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
            case CLASS: return new ClassDaoImpl();
            // Other DAOs would be here
            default: return null;
        }
    }
}

/**
 * ClassServiceImpl.java
 *
 * Implements the ClassService interface, providing the concrete business logic
 * for class schedule management. This class uses the DaoFactory to obtain a ClassDao
 * instance and delegates the data access operations to it.
 */
public class ClassServiceImpl implements ClassService {
    
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private ClassDao classDao = (ClassDao) daoFactory.getDao(DaoFactory.DaoTypes.CLASS);
    
    @Override
    public String saveClass(ClassDto classDto) throws Exception {
        Class classEntity = new Class(0, classDto.getClassDate(), classDto.getSubjectId(), classDto.getLecturerId());
        if (classDao.save(classEntity)) {
            return "Success";
        } else {
            return "Fail";
        }
    }
    
    @Override
    public ClassDto getClass(Integer classId) throws Exception {
        Class classEntity = classDao.search(classId);
        if (classEntity != null) {
            return new ClassDto(classEntity.getClassId(), classEntity.getClassDate(), classEntity.getSubjectId(), classEntity.getLecturerId());
        }
        return null;
    }
    
    @Override
    public List<ClassDto> getAllClasses() throws Exception {
        List<Class> classes = classDao.getAll();
        List<ClassDto> classDtos = new ArrayList<>();
        for (Class classEntity : classes) {
            classDtos.add(new ClassDto(classEntity.getClassId(), classEntity.getClassDate(), classEntity.getSubjectId(), classEntity.getLecturerId()));
        }
        return classDtos;
    }
    
    @Override
    public String updateClass(ClassDto classDto) throws Exception {
        Class classEntity = new Class(classDto.getClassId(), classDto.getClassDate(), classDto.getSubjectId(), classDto.getLecturerId());
        if (classDao.update(classEntity)) {
            return "Success";
        } else {
            return "Fail";
        }
    }
    
    @Override
    public String deleteClass(Integer classId) throws Exception {
        if (classDao.delete(classId)) {
            return "Success";
        } else {
            return "Fail";
        }
    }
}
