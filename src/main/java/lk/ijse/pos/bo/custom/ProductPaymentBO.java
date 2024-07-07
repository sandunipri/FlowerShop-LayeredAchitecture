package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.ProductPaymentDTO;
import lk.ijse.pos.entity.ProductPayment;

import java.sql.SQLException;

public interface ProductPaymentBO {
    public  String currentId() throws SQLException, ClassNotFoundException ;

    public boolean add(ProductPaymentDTO dto) throws SQLException, ClassNotFoundException;
}
