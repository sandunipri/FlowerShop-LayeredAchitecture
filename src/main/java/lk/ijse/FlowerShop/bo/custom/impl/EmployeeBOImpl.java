package lk.ijse.FlowerShop.bo.custom.impl;

import lk.ijse.FlowerShop.bo.custom.EmployeeBO;
import lk.ijse.FlowerShop.dao.custom.EmployeeDAO;
import lk.ijse.FlowerShop.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.FlowerShop.dto.EmployeeDTO;
import lk.ijse.FlowerShop.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = new EmployeeDAOImpl();


    @Override
    public List<String> getEid() throws SQLException, ClassNotFoundException {
        return employeeDAO.getEid();
    }

    @Override
    public EmployeeDTO searchByID(String E_id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String eid) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
