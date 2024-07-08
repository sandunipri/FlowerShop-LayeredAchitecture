package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.ProductOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductOrderDAO extends SuperDAO {
    public  String currentId() throws SQLException, ClassNotFoundException ;

    public  boolean save(ProductOrder productOrder) throws SQLException, ClassNotFoundException ;

    public  List<String> getid();

    public  ProductOrder searchByPOID(String id) throws SQLException, ClassNotFoundException ;
}
