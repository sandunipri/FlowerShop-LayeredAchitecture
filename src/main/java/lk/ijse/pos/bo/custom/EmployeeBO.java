package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.EmployeeDTO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO {
    public List<String> getEid() throws SQLException, ClassNotFoundException;

    public EmployeeDTO searchByID(String E_id) throws SQLException, ClassNotFoundException ;

    public  boolean add(EmployeeDTO employee) throws SQLException, ClassNotFoundException ;

    public  boolean update(EmployeeDTO employee) throws SQLException, ClassNotFoundException;

    public  boolean delete(String eid) throws SQLException, ClassNotFoundException ;

    public  String currentId() throws SQLException, ClassNotFoundException ;

    List<EmployeeDTO> getEmployeeAll() throws SQLException, ClassNotFoundException;

    UserDTO searchByUserID(String id) throws SQLException, ClassNotFoundException;

    List<String> getId() throws SQLException, ClassNotFoundException;
}
