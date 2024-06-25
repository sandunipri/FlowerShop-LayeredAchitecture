package lk.ijse.pos.dao.custom;

import lk.ijse.pos.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    public  List<String> getEid() throws SQLException, ClassNotFoundException ;
    public  Employee searchByID(String E_id) throws SQLException, ClassNotFoundException;

    public  boolean add(Employee employee) throws SQLException, ClassNotFoundException ;

    public  boolean update(Employee employee) throws SQLException, ClassNotFoundException ;
    public
    boolean delete(String eid) throws SQLException, ClassNotFoundException ;

    public  List<Employee> getAll() throws SQLException, ClassNotFoundException ;

    public  String currentId() throws SQLException, ClassNotFoundException ;

}
