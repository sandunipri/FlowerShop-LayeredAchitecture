package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.ProductPayment;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductPaymentDAO extends CrudDAO<ProductPayment> {
    public  String currentId() throws SQLException, ClassNotFoundException ;

/*    public boolean add(ProductPayment productPayment) throws SQLException, ClassNotFoundException;*/
}
