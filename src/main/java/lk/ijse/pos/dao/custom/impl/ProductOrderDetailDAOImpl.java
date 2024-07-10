package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.custom.ProductOrderDetailDAO;
import lk.ijse.pos.dao.custom.ProductPaymentDAO;
import lk.ijse.pos.entity.ProductOrderDetail;
/*
import lk.ijse.FlowerShop.model.ProductOrderDetail;
*/

import java.sql.SQLException;
import java.util.List;

public class ProductOrderDetailDAOImpl implements ProductOrderDetailDAO {
    @Override
    public  boolean save(List<ProductOrderDetail> productOrderDetailList) throws SQLException, ClassNotFoundException {

        for (ProductOrderDetail productOrderDetail: productOrderDetailList) {
            if(!add(productOrderDetail)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public  boolean add(ProductOrderDetail productOrderDetail) throws SQLException, ClassNotFoundException {

       /* String sql = "INSERT INTO P_OrderDetail VALUES(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, productOrderDetail.getPOID());
        pstm.setString(2, productOrderDetail.getStockID());
        pstm.setString(3, productOrderDetail.getPCode());
        pstm.setInt(4, productOrderDetail.getIssuedFlowers());
        pstm.setDouble(5, productOrderDetail.getPrice());
        pstm.setInt(6, productOrderDetail.getQty());
        pstm.setDouble(7,productOrderDetail.getAmount());


        return pstm.executeUpdate() > 0;*/
        return SQLUtill.execute("INSERT INTO P_OrderDetail VALUES(?, ?, ?, ?, ?, ?, ?)",
                productOrderDetail.getPOID(),
                productOrderDetail.getStockID(),
                productOrderDetail.getPCode(),
                productOrderDetail.getIssuedFlowers(),
                productOrderDetail.getPrice(),
                productOrderDetail.getQty(),
                productOrderDetail.getAmount());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ProductOrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ProductOrderDetail searchByID(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
