package lk.ijse.FlowerShop.bo.custom;

import lk.ijse.FlowerShop.dao.SQLUtill;
import lk.ijse.FlowerShop.dto.EmployeeDTO;
import lk.ijse.FlowerShop.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO {
    public List<String> getEid() throws SQLException, ClassNotFoundException;

    public EmployeeDTO searchByID(String E_id) throws SQLException, ClassNotFoundException ;

    public  boolean add(EmployeeDTO employee) throws SQLException, ClassNotFoundException ;

    public  boolean update(EmployeeDTO employee) throws SQLException, ClassNotFoundException;

    public  boolean delete(String eid) throws SQLException, ClassNotFoundException ;

    public  List<Employee> getAll() throws SQLException, ClassNotFoundException ;

    public  String currentId() throws SQLException, ClassNotFoundException ;

}
