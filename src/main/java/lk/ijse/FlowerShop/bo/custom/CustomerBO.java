package lk.ijse.FlowerShop.bo.custom;

import lk.ijse.FlowerShop.dto.CustomerDTO;
import lk.ijse.FlowerShop.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO {
    public List<String> getCid() throws SQLException, ClassNotFoundException ;
    public CustomerDTO searchByID(String C_id) throws SQLException, ClassNotFoundException ;

    public  boolean add(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public  boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String cid) throws SQLException, ClassNotFoundException;

    public  List<String> getTelNo() throws SQLException, ClassNotFoundException ;

    public  CustomerDTO searchByTelNo(String telNo) throws SQLException, ClassNotFoundException ;

    public  List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;

    public  String currentId() throws SQLException, ClassNotFoundException;
}
