package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.entity.ProductOrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface ProductOrderDetailDAO {
    public  boolean save(List<ProductOrderDetail> productOrderDetailList) throws SQLException, ClassNotFoundException;
    public   boolean saved(ProductOrderDetail productOrderDetail) throws SQLException, ClassNotFoundException;
}
