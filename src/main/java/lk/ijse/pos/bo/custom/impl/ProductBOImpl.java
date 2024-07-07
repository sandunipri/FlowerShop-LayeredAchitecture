package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ProductBO;
import lk.ijse.pos.dao.custom.ProductDAO;
import lk.ijse.pos.dao.custom.impl.ProductDAOImpl;
import lk.ijse.pos.dto.ProductDTO;
import lk.ijse.pos.entity.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public List<String> getProductName() throws SQLException, ClassNotFoundException {
       return productDAO.getProductName();
    }

    @Override
    public ProductDTO searchByName(String name) throws SQLException, ClassNotFoundException {
       Product product = new Product();
       ProductDTO productDTO = new ProductDTO(
               product.getPCode(),
               product.getPName(),
               product.getPrice()
       );
       return productDTO;
    }

    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
      return productDAO.currentId();
    }

    @Override
    public boolean add(ProductDTO dto) throws SQLException, ClassNotFoundException {
        Product product = new Product(
                dto.getPCode(),
                dto.getPName(),
                dto.getPrice()
        );
        return productDAO.add(product);
    }

    @Override
    public boolean delete(String pcode) throws SQLException, ClassNotFoundException {
       return productDAO.delete(pcode);
    }
}
