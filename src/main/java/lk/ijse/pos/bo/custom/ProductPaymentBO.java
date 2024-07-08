package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.ProductOrderDTO;
import lk.ijse.pos.dto.ProductPaymentDTO;
import lk.ijse.pos.entity.ProductPayment;

import java.sql.SQLException;
import java.util.List;

public interface ProductPaymentBO extends SuperBO {
    public  String currentId() throws SQLException, ClassNotFoundException ;

    public boolean add(ProductPaymentDTO dto) throws SQLException, ClassNotFoundException;

    List<String> getid();

    ProductOrderDTO searchByPOID(String id) throws SQLException, ClassNotFoundException;

}
