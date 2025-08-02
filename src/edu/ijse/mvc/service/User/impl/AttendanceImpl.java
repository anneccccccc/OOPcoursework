/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.mvc.service.User.impl;

/**
 *
 * @author Chanuri Fernando
 */
import edu.ijse.layerd.service.custom.AttendanceService;
import edu.ijse.layerd.dto.AttendanceDto;
import edu.ijse.layerd.dao.DaoFactory;
import edu.ijse.layerd.dao.SuperDao;
import edu.ijse.layerd.dao.custom.AttendanceDao;
import edu.ijse.layerd.entity.Attendance;
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
 * AttendanceService.java
 * Defines the contract for the AttendanceService.
 */
public interface AttendanceService extends SuperService {
    String saveAttendance(AttendanceDto attendanceDto) throws Exception;
    AttendanceDto getAttendance(Integer studentId, Integer classId) throws Exception;
    List<AttendanceDto> getAllAttendances() throws Exception;
    String updateAttendance(AttendanceDto attendanceDto) throws Exception;
    String deleteAttendance(Integer studentId, Integer classId) throws Exception;
}

/**
 * AttendanceDto.java
 * DTO for the Attendance entity.
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
 * Attendance.java
 * The entity class for Attendance.
 */
class Attendance {
    private int studentId;
    private int classId;
    private boolean isPresent;

    public Attendance() {}

    public Attendance(int studentId, int classId, boolean isPresent) {
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
 * AttendanceDao.java
 * The interface for the Attendance DAO.
 */
interface AttendanceDao extends CrudDao<Attendance, Integer> {
    // Custom search method for Attendance, as it has a composite key.
    Attendance search(Integer studentId, Integer classId) throws Exception;

    // Custom delete method for Attendance.
    boolean delete(Integer studentId, Integer classId) throws Exception;
}

/**
 * AttendanceDaoImpl.java
 * The implementation of the AttendanceDao interface.
 */
class AttendanceDaoImpl implements AttendanceDao {
    @Override
    public boolean save(Attendance attendance) throws Exception {
        String sql = "INSERT INTO Attendance (studentId, classId, isPresent) VALUES (?, ?, ?)";
        return CrudUtil.executeUpdate(sql, attendance.getStudentId(), attendance.getClassId(), attendance.isPresent());
    }
    
    @Override
    public boolean update(Attendance attendance) throws Exception {
        String sql = "UPDATE Attendance SET isPresent = ? WHERE studentId = ? AND classId = ?";
        return CrudUtil.executeUpdate(sql, attendance.isPresent(), attendance.getStudentId(), attendance.getClassId());
    }
    
    // Note: The generic delete method is not applicable here due to the composite key.
    @Override
    public boolean delete(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported for composite keys.");
    }

    // Note: The generic search method is not applicable here due to the composite key.
    @Override
    public Attendance search(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported for composite keys. Use search(studentId, classId) instead.");
    }
    
    @Override
    public Attendance search(Integer studentId, Integer classId) throws Exception {
        String sql = "SELECT * FROM Attendance WHERE studentId = ? AND classId = ?";
        ResultSet resultSet = CrudUtil.executeQuery(sql, studentId, classId);
        if (resultSet.next()) {
            return new Attendance(
                resultSet.getInt("studentId"),
                resultSet.getInt("classId"),
                resultSet.getBoolean("isPresent")
            );
        }
        return null;
    }
    
    @Override
    public boolean delete(Integer studentId, Integer classId) throws Exception {
        String sql = "DELETE FROM Attendance WHERE studentId = ? AND classId = ?";
        return CrudUtil.executeUpdate(sql, studentId, classId);
    }
    
    @Override
    public ArrayList<Attendance> getAll() throws Exception {
        String sql = "SELECT * FROM Attendance";
        ResultSet resultSet = CrudUtil.executeQuery(sql);
        ArrayList<Attendance> attendances = new ArrayList<>();
        while (resultSet.next()) {
            attendances.add(new Attendance(
                resultSet.getInt("studentId"),
                resultSet.getInt("classId"),
                resultSet.getBoolean("isPresent")
            ));
        }
        return attendances;
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
            case ATTENDANCE: return new AttendanceDaoImpl();
            // Other DAOs would be here
            default: return null;
        }
    }
}

/**
 * AttendanceServiceImpl.java
 *
 * Implements the AttendanceService interface, providing the concrete business logic
 * for attendance management. This class uses the DaoFactory to obtain an AttendanceDao
 * instance and delegates the data access operations to it.
 */
public class AttendanceServiceImpl implements AttendanceService {
    
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private AttendanceDao attendanceDao = (AttendanceDao) daoFactory.getDao(DaoFactory.DaoTypes.ATTENDANCE);
    
    @Override
    public String saveAttendance(AttendanceDto attendanceDto) throws Exception {
        Attendance attendance = new Attendance(attendanceDto.getStudentId(), attendanceDto.getClassId(), attendanceDto.isPresent());
        if (attendanceDao.save(attendance)) {
            return "Success";
        } else {
            return "Fail";
        }
    }
    
    @Override
    public AttendanceDto getAttendance(Integer studentId, Integer classId) throws Exception {
        Attendance attendance = attendanceDao.search(studentId, classId);
        if (attendance != null) {
            return new AttendanceDto(attendance.getStudentId(), attendance.getClassId(), attendance.isPresent());
        }
        return null;
    }
    
    @Override
    public List<AttendanceDto> getAllAttendances() throws Exception {
        List<Attendance> attendances = attendanceDao.getAll();
        List<AttendanceDto> attendanceDtos = new ArrayList<>();
        for (Attendance attendance : attendances) {
            attendanceDtos.add(new AttendanceDto(attendance.getStudentId(), attendance.getClassId(), attendance.isPresent()));
        }
        return attendanceDtos;
    }
    
    @Override
    public String updateAttendance(AttendanceDto attendanceDto) throws Exception {
        Attendance attendance = new Attendance(attendanceDto.getStudentId(), attendanceDto.getClassId(), attendanceDto.isPresent());
        if (attendanceDao.update(attendance)) {
            return "Success";
        } else {
            return "Fail";
        }
    }
    
    @Override
    public String deleteAttendance(Integer studentId, Integer classId) throws Exception {
        if (attendanceDao.delete(studentId, classId)) {
            return "Success";
        } else {
            return "Fail";
        }
    }
}
