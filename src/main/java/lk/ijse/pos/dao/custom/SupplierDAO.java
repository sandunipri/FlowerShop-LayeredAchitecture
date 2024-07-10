package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO  extends CrudDAO<Supplier> {
    public  String currentSId() throws SQLException, ClassNotFoundException ;
   /* public  boolean  add(Supplier supplierList) throws SQLException, ClassNotFoundException ;*/
    public  boolean save(List<Supplier> supplierList) throws SQLException, ClassNotFoundException;


}
