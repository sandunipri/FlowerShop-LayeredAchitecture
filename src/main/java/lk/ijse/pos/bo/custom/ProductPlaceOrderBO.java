package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ProductPlaceOrderBO {
    //product order details methods
    public  boolean save(List<ProductOrderDetailDTO> productOrderDetailList) throws SQLException, ClassNotFoundException;
    public   boolean saved(ProductOrderDetailDTO dto) throws SQLException, ClassNotFoundException;

    //product order methods
    public  String currentId() throws SQLException, ClassNotFoundException ;

    public  boolean save(ProductOrderDTO dto) throws SQLException, ClassNotFoundException ;

    public  List<String> getid();

    public  ProductOrderDTO searchByPOID(String id) throws SQLException, ClassNotFoundException ;
     boolean update(ProductPlaceDTO productPlace) throws SQLException;
}
