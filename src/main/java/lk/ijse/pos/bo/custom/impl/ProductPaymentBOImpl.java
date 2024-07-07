package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ProductPaymentBO;
import lk.ijse.pos.dao.custom.ProductPaymentDAO;
import lk.ijse.pos.dao.custom.impl.ProductPaymentDAOImpl;
import lk.ijse.pos.dto.ProductPaymentDTO;
import lk.ijse.pos.entity.ProductPayment;

import java.sql.SQLException;

public class ProductPaymentBOImpl implements ProductPaymentBO {
    ProductPaymentDAO productPaymentDAO = new ProductPaymentDAOImpl();
    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
      return productPaymentDAO.currentId();
    }

    @Override
    public boolean add(ProductPaymentDTO dto) throws SQLException, ClassNotFoundException {
     ProductPayment productPayment = new ProductPayment(
         dto.getPaymentid(),
         dto.getCID(),
         dto.getPOID(),
         dto.getDate(),
         dto.getPaidPayment(),
         dto.getAmount(),
         dto.getBalance()
     );

     return productPaymentDAO.add(productPayment);
    }
}
