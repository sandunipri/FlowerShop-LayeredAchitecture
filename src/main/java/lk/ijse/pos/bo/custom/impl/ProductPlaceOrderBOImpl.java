package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ProductPlaceOrderBO;
import lk.ijse.pos.dao.custom.*;
import lk.ijse.pos.dao.custom.impl.*;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.dto.*;
import lk.ijse.pos.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductPlaceOrderBOImpl implements ProductPlaceOrderBO {

    ProductOrderDetailDAO productOrderDetailDAO = new ProductOrderDetailDAOImpl();
    ProductOrderDAO productOrderDAO = new ProductOrderDAOImpl();

    ProductDAO productDAO = new ProductDAOImpl();
    StockDAO stockDAO = new StockDAOImpl();
    FlowerDAO flowerDAO = new FlowerDAOImpl();
    CustomerDAO customerDAO = new CustomerDAOImpl();


    @Override
    public boolean save(List<ProductOrderDetailDTO> productOrderDetailList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saved(ProductOrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(ProductOrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getid() {
        return null;
    }

    @Override
    public ProductOrderDTO searchByPOID(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(ProductPlaceDTO productPlace) throws SQLException {
          ProductOrderDTO dto = productPlace.getProductOrder();
          ProductOrder productOrder = new ProductOrder(
                  dto.getPOID(),
                  dto.getCID()
          );

          List<ProductOrderDetailDTO> dtoList = productPlace.getProductOrderDetailList();
          List<ProductOrderDetail> orderlist = new ArrayList<>();
          for (ProductOrderDetailDTO dto1 : dtoList) {
              ProductOrderDetail productOrderDetail = new ProductOrderDetail(
                      dto1.getPOID(),
                      dto1.getStockID(),
                      dto1.getPCode(),
                      dto1.getIssuedFlowers(),
                      dto1.getPrice(),
                      dto1.getQty(),
                      dto1.getAmount()

              );
              orderlist.add(productOrderDetail);
          }


            Connection connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            try{  boolean isProductOrderSaved = productOrderDAO.save(productOrder);
                if (isProductOrderSaved) {
                    System.out.println("r");
                    boolean isOrderDetailSaved = productOrderDetailDAO.save(orderlist);
                    System.out.println("eeee");
                    if (isOrderDetailSaved) {
                        boolean isStockUpdate = stockDAO.Updateqty(orderlist);
                        System.out.println("ggggg");
                        if (isStockUpdate) {
                            connection.commit();
                            return true;

                        }
                    }
                }
                connection.rollback();
                return false;
            }catch (Exception e){
                connection.rollback();
                return false;

            }finally {
                connection.setAutoCommit(true);
            }

    }

    @Override
    public List<String> getStockCodes() throws SQLException, ClassNotFoundException {
        return stockDAO.getCodes();
    }

    @Override
    public List<String> getFlowerName() throws SQLException, ClassNotFoundException {
        return flowerDAO.getFlowerName();
    }

    @Override
    public List<String> getProductName() throws SQLException, ClassNotFoundException {
        return productDAO.getProductName();
    }

    @Override
    public List<String> getTelNo() throws SQLException, ClassNotFoundException {
        return customerDAO.getTelNo();
    }

    @Override
    public CustomerDTO searchByTelNo(String telNo) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.searchByTelNo(telNo);
        CustomerDTO customerDTO = new CustomerDTO(
                customer.getCid(),
                customer.getUid(),
                customer.getCname(),
                customer.getAddress(),
                customer.getTelNo()
        );
        return customerDTO;
           }

    @Override
    public ProductDTO searchByName(String name) throws SQLException, ClassNotFoundException {
        Product product = productDAO.searchByName(name);
        ProductDTO productDTO = new ProductDTO(
                product.getPCode(),
                product.getPName(),
                product.getPrice()
        );
        return productDTO;
    }

    @Override
    public FlowerDTO searchByFlowerName(String name) throws SQLException, ClassNotFoundException {
        Flower flower = flowerDAO.searchByFlowerName(name);
        FlowerDTO flowerDTO = new FlowerDTO(
                flower.getFCode(),
                flower.getFName()
        );
        return flowerDTO;
    }
}
