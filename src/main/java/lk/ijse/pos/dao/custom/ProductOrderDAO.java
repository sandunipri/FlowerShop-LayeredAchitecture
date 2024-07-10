package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.ProductOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductOrderDAO extends CrudDAO<ProductOrder> {
    public  String currentId() throws SQLException, ClassNotFoundException ;

  /*  public  boolean add(ProductOrder productOrder) throws SQLException, ClassNotFoundException ;
*/
    public  List<String> getid();

    public  ProductOrder searchByID(String id) throws SQLException, ClassNotFoundException ;
}
