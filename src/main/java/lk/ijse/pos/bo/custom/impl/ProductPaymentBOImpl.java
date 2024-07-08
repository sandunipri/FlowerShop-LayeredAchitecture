package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ProductPaymentBO;
import lk.ijse.pos.dao.custom.ProductOrderDAO;
import lk.ijse.pos.dao.custom.ProductPaymentDAO;
import lk.ijse.pos.dao.custom.impl.ProductOrderDAOImpl;
import lk.ijse.pos.dao.custom.impl.ProductPaymentDAOImpl;
import lk.ijse.pos.dto.ProductOrderDTO;
import lk.ijse.pos.dto.ProductPaymentDTO;
import lk.ijse.pos.entity.ProductOrder;
import lk.ijse.pos.entity.ProductPayment;

import java.sql.SQLException;
import java.util.List;

public class ProductPaymentBOImpl implements ProductPaymentBO {
    ProductPaymentDAO productPaymentDAO = new ProductPaymentDAOImpl();

    ProductOrderDAO productOrderDAO = new ProductOrderDAOImpl();

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

    @Override
    public List<String> getid() {
       return productOrderDAO.getid();
    }

    @Override
    public ProductOrderDTO searchByPOID(String id) throws SQLException, ClassNotFoundException {
        ProductOrder productOrder = productOrderDAO.searchByPOID(id);
        ProductOrderDTO productOrderDTO = new ProductOrderDTO(
                productOrder.getPOID(),
                productOrder.getCID()
        );
        return productOrderDTO;
    }
}
