package lk.ijse.FlowerShop.dao.custom;

import lk.ijse.FlowerShop.dao.SQLUtill;
import lk.ijse.FlowerShop.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO {
    public  List<String> getCid() throws SQLException, ClassNotFoundException ;
    public  Customer searchByID(String C_id) throws SQLException, ClassNotFoundException ;

    public  boolean add(Customer customer) throws SQLException, ClassNotFoundException ;

    public  boolean update(Customer customer) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String cid) throws SQLException, ClassNotFoundException;

    public  List<String> getTelNo() throws SQLException, ClassNotFoundException ;

    public  Customer searchByTelNo(String telNo) throws SQLException, ClassNotFoundException ;

    public  List<Customer> getAll() throws SQLException, ClassNotFoundException;

    public  String currentId() throws SQLException, ClassNotFoundException;

}
