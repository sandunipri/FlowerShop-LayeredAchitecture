package lk.ijse.FlowerShop.bo.custom;

import lk.ijse.FlowerShop.dto.CustomerDTO;
import lk.ijse.FlowerShop.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO {
    List<String> getCid() throws SQLException, ClassNotFoundException;
    CustomerDTO searchByID(String C_id) throws SQLException, ClassNotFoundException;
    boolean add(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    boolean delete(String cid) throws SQLException, ClassNotFoundException;
    List<String> getTelNo() throws SQLException, ClassNotFoundException;
    CustomerDTO searchByTelNo(String telNo) throws SQLException, ClassNotFoundException;
    List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;
    String currentId() throws SQLException, ClassNotFoundException;
}
