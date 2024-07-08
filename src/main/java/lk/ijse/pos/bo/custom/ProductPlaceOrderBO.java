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

    List<String> getStockCodes() throws SQLException, ClassNotFoundException;

    List<String> getFlowerName() throws  SQLException, ClassNotFoundException;

    List<String> getProductName() throws SQLException, ClassNotFoundException;

    List<String> getTelNo() throws SQLException, ClassNotFoundException;

    CustomerDTO searchByTelNo(String telNo) throws SQLException, ClassNotFoundException;

    ProductDTO searchByName(String name) throws SQLException, ClassNotFoundException;

    FlowerDTO searchByFlowerName(String name) throws SQLException, ClassNotFoundException;
}
