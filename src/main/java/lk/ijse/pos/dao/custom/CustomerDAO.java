package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    public  List<String> getCid() throws SQLException, ClassNotFoundException ;
  /*  public  Customer searchByID(String C_id) throws SQLException, ClassNotFoundException ;
*/
   /* public  boolean add(Customer customer) throws SQLException, ClassNotFoundException ;*/

    /*public  boolean update(Customer customer) throws SQLException, ClassNotFoundException ;*/

    /*public  boolean delete(String cid) throws SQLException, ClassNotFoundException;*/

    public  List<String> getTelNo() throws SQLException, ClassNotFoundException ;

    public  Customer searchByTelNo(String telNo) throws SQLException, ClassNotFoundException ;

    public  List<Customer> getAll() throws SQLException, ClassNotFoundException;

    public  String currentId() throws SQLException, ClassNotFoundException;

}
