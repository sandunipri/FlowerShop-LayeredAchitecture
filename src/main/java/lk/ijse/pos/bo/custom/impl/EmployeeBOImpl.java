package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.EmployeeBO;
import lk.ijse.pos.dao.custom.EmployeeDAO;
import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.pos.dao.custom.impl.UserDAOImpl;
import lk.ijse.pos.dto.EmployeeDTO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.entity.Employee;
import lk.ijse.pos.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    UserDAO userDAO = new UserDAOImpl();


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
    public String currentId() throws SQLException, ClassNotFoundException {
        return employeeDAO.currentId();
    }

    @Override
    public List<EmployeeDTO> getEmployeeAll() throws SQLException, ClassNotFoundException {
        List<Employee> employeeList = employeeDAO.getAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employeeList) {
            employeeDTOS.add(new EmployeeDTO(
                    employee.getEid(),
                    employee.getUid(),
                    employee.getEname(),
                    employee.getAddress(),
                    employee.getEmail(),
                    employee.getJobRole(),
                    employee.getTelNo()
            ));
        }
        return employeeDTOS;

    }

    @Override
    public UserDTO searchByUserID(String id) throws SQLException, ClassNotFoundException {
        User user = userDAO.searchByID(id);
        UserDTO userDTO = new UserDTO(
                user.getUid(),
                user.getUName(),
                user.getUPasswrod()
        );
        return userDTO;
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {
       return userDAO.getId();
    }
}
