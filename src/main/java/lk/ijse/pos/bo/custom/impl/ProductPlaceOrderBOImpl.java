package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ProductPlaceOrderBO;
import lk.ijse.pos.dao.custom.ProductOrderDAO;
import lk.ijse.pos.dao.custom.ProductOrderDetailDAO;
import lk.ijse.pos.dao.custom.StockDAO;
import lk.ijse.pos.dao.custom.impl.ProductOrderDAOImpl;
import lk.ijse.pos.dao.custom.impl.ProductOrderDetailDAOImpl;
import lk.ijse.pos.dao.custom.impl.StockDAOImpl;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.dto.ProductOrderDTO;
import lk.ijse.pos.dto.ProductOrderDetailDTO;
import lk.ijse.pos.dto.ProductPlaceDTO;
import lk.ijse.pos.entity.ProductOrder;
import lk.ijse.pos.entity.ProductOrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductPlaceOrderBOImpl implements ProductPlaceOrderBO {

    ProductOrderDetailDAO productOrderDetailDAO = new ProductOrderDetailDAOImpl();
    ProductOrderDAO productOrderDAO = new ProductOrderDAOImpl();
    StockDAO stockDAO = new StockDAOImpl();

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
}
