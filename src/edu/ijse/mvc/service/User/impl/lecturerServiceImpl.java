/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.service.User.impl;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.service.custom.LecturerService;
import edu.ijse.layerd.dto.LecturerDto;
import edu.ijse.layerd.dao.DaoFactory;
import edu.ijse.layerd.dao.SuperDao;
import edu.ijse.layerd.dao.custom.LecturerDao;
import edu.ijse.layerd.entity.Lecturer;
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
 * LecturerService.java
 * Defines the contract for the LecturerService.
 */
public interface LecturerService extends SuperService {
    String saveLecturer(LecturerDto lecturerDto) throws Exception;
    LecturerDto getLecturer(Integer lecturerId) throws Exception;
    List<LecturerDto> getAllLecturers() throws Exception;
    String updateLecturer(LecturerDto lecturerDto) throws Exception;
    String deleteLecturer(Integer lecturerId) throws Exception;
}

/**
 * LecturerDto.java
 * DTO for the Lecturer entity.
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
 * Lecturer.java
 * The entity class for a Lecturer.
 */
class Lecturer {
    private int lecturerId;
    private String lecturerName;

    public Lecturer() {}
    public Lecturer(int lecturerId, String lecturerName) {
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
    }
    public int getLecturerId() { return lecturerId; }
    public void setLecturerId(int lecturerId) { this.lecturerId = lecturerId; }
    public String getLecturerName() { return lecturerName; }
    public void setLecturerName(String lecturerName) { this.lecturerName = lecturerName; }
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
 * LecturerDao.java
 * The interface for the Lecturer DAO.
 */
interface LecturerDao extends CrudDao<Lecturer, Integer> {}

/**
 * LecturerDaoImpl.java
 * The implementation of the LecturerDao interface.
 */
class LecturerDaoImpl implements LecturerDao {
    @Override
    public boolean save(Lecturer lecturer) throws Exception {
        String sql = "INSERT INTO Lecturer (lecturerName) VALUES (?)";
        return CrudUtil.executeUpdate(sql, lecturer.getLecturerName());
    }
    
    @Override
    public boolean update(Lecturer lecturer) throws Exception {
        String sql = "UPDATE Lecturer SET lecturerName = ? WHERE lecturerId = ?";
        return CrudUtil.executeUpdate(sql, lecturer.getLecturerName(), lecturer.getLecturerId());
    }
    
    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "DELETE FROM Lecturer WHERE lecturerId = ?";
        return CrudUtil.executeUpdate(sql, id);
    }
    
    @Override
    public Lecturer search(Integer id) throws Exception {
        String sql = "SELECT * FROM Lecturer WHERE lecturerId = ?";
        ResultSet resultSet = CrudUtil.executeQuery(sql, id);
        if (resultSet.next()) {
            return new Lecturer(
                resultSet.getInt("lecturerId"),
                resultSet.getString("lecturerName")
            );
        }
        return null;
    }
    
    @Override
    public ArrayList<Lecturer> getAll() throws Exception {
        String sql = "SELECT * FROM Lecturer";
        ResultSet resultSet = CrudUtil.executeQuery(sql);
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        while (resultSet.next()) {
            lecturers.add(new Lecturer(
                resultSet.getInt("lecturerId"),
                resultSet.getString("lecturerName")
            ));
        }
        return lecturers;
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
            case LECTURER: return new LecturerDaoImpl();
            // Other DAOs would be here
            default: return null;
        }
    }
}

/**
 * LecturerServiceImpl.java
 *
 * Implements the LecturerService interface, providing the concrete business logic
 * for lecturer management. This class uses the DaoFactory to obtain a LecturerDao
 * instance and delegates the data access operations to it.
 */
public class LecturerServiceImpl implements LecturerService {
    
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private LecturerDao lecturerDao = (LecturerDao) daoFactory.getDao(DaoFactory.DaoTypes.LECTURER);
    
    @Override
    public String saveLecturer(LecturerDto lecturerDto) throws Exception {
        Lecturer lecturer = new Lecturer(0, lecturerDto.getLecturerName());
        if (lecturerDao.save(lecturer)) {
            return "Success";
        } else {
            return "Fail";
        }
    }
    
    @Override
    public LecturerDto getLecturer(Integer lecturerId) throws Exception {
        Lecturer lecturer = lecturerDao.search(lecturerId);
        if (lecturer != null) {
            return new LecturerDto(lecturer.getLecturerId(), lecturer.getLecturerName());
        }
        return null;
    }
    
    @Override
    public List<LecturerDto> getAllLecturers() throws Exception {
        List<Lecturer> lecturers = lecturerDao.getAll();
        List<LecturerDto> lecturerDtos = new ArrayList<>();
        for (Lecturer lecturer : lecturers) {
            lecturerDtos.add(new LecturerDto(lecturer.getLecturerId(), lecturer.getLecturerName()));
        }
        return lecturerDtos;
    }
    
    @Override
    public String updateLecturer(LecturerDto lecturerDto) throws Exception {
        Lecturer lecturer = new Lecturer(lecturerDto.getLecturerId(), lecturerDto.getLecturerName());
        if (lecturerDao.update(lecturer)) {
            return "Success";
        } else {
            return "Fail";
        }
    }
    
    @Override
    public String deleteLecturer(Integer lecturerId) throws Exception {
        if (lecturerDao.delete(lecturerId)) {
            return "Success";
        } else {
            return "Fail";
        }
    }
}
