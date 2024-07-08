package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductDAO extends SuperDAO {
    public  List<String> getProductName() throws SQLException, ClassNotFoundException ;

    public  Product searchByName(String name) throws SQLException, ClassNotFoundException ;

    public  String currentId() throws SQLException, ClassNotFoundException ;

    public  boolean add(Product product) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String pcode) throws SQLException, ClassNotFoundException ;;
}


