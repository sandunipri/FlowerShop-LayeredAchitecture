package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.EmployeeBO;
import lk.ijse.pos.dao.custom.EmployeeDAO;
import lk.ijse.pos.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.pos.dto.EmployeeDTO;
import lk.ijse.pos.entity.Employee;

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
       Employee employee = new Employee();
       EmployeeDTO employeeDTO = new EmployeeDTO(
               employee.getEid(),
               employee.getUid(),
               employee.getEname(),
               employee.getAddress(),
               employee.getEmail(),
               employee.getJobRole(),
               employee.getTelNo()

       );
       return employeeDTO;
    }

    @Override
    public boolean add(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
      Employee employee = new Employee(
              dto.getEid(),
              dto.getUid(),
              dto.getEname(),
              dto.getAddress(),
              dto.getEmail(),
              dto.getJobRole(),
              dto.getTelNo()
      );
      return employeeDAO.add(employee);
    }

    @Override
    public boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
      Employee employee = new Employee(
              dto.getEid(),
              dto.getUid(),
              dto.getEname(),
              dto.getAddress(),
              dto.getEmail(),
              dto.getJobRole(),
              dto.getTelNo()
      );
      return employeeDAO.update(employee);
    }

    @Override
    public boolean delete(String eid) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(eid);
    }

    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAll();
    }

    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        return employeeDAO.currentId();
    }
}
