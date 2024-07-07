package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.ProductDTO;
import lk.ijse.pos.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductBO {
    public List<String> getProductName() throws SQLException, ClassNotFoundException ;

    public ProductDTO searchByName(String name) throws SQLException, ClassNotFoundException ;

    public  String currentId() throws SQLException, ClassNotFoundException ;

    public  boolean add(ProductDTO dto) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String pcode) throws SQLException, ClassNotFoundException ;;
}
