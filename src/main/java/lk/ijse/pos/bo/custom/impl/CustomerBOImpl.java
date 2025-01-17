package lk.ijse.pos.bo.custom.impl;


import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.dao.custom.impl.UserDAOImpl;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public List<String> getCid() throws SQLException, ClassNotFoundException {
        return customerDAO.getCid();
    }

    @Override
    public CustomerDTO searchByID(String C_id) throws SQLException, ClassNotFoundException {
       Customer customer = customerDAO.searchByID(C_id);
       CustomerDTO customerDTO = new CustomerDTO(
               customer.getCid(),
               customer.getUid(),
               customer.getCname(),
               customer.getAddress(),
               customer.getTelNo()
       );
       return customerDTO;
    }

    @Override
    public boolean add(CustomerDTO dto) throws SQLException, ClassNotFoundException {
      Customer customer = new Customer(
              dto.getCid(),
              dto.getUid(),
              dto.getCname(),
              dto.getAddress(),
              dto.getTelNo()
      );
      return customerDAO.add(customer);
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
       Customer customer = new Customer(
               dto.getCid(),
               dto.getUid(),
               dto.getCname(),
               dto.getAddress(),
               dto.getTelNo()

       );
       return customerDAO.update(customer);
    }

    @Override
    public boolean delete(String cid) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(cid);
    }

    @Override
    public List<String> getTelNo() throws SQLException, ClassNotFoundException {
        return customerDAO.getTelNo();
    }

    @Override
    public CustomerDTO searchByTelNo(String telNo) throws SQLException, ClassNotFoundException {
       Customer customer = customerDAO.searchByTelNo(telNo);
       CustomerDTO customerDTO = new CustomerDTO(
               customer.getCid(),
               customer.getUid(),
               customer.getCname(),
               customer.getAddress(),
               customer.getTelNo()
       );
       return customerDTO;
    }

    @Override
    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Customer> customerList = customerDAO.getAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customerList){
            CustomerDTO customerDTO = new CustomerDTO(
                customer.getCid(),
                customer.getUid(),
                customer.getCname(),
                customer.getAddress(),
                customer.getTelNo()
        );
            customerDTOS.add(customerDTO);

        }
        return customerDTOS;
    }

    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        return customerDAO.currentId();
    }

    @Override
    public List<String> getUserId() throws SQLException, ClassNotFoundException {
       return userDAO.getId();
    }

    @Override
    public UserDTO searchByuserID(String id) throws SQLException, ClassNotFoundException {
        User user = userDAO.searchByID(id);
        UserDTO userDTO = new UserDTO(
                user.getUid(),
                user.getUName(),
                user.getUPasswrod()
        );
        return userDTO;
    }
}
