package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dto.SupplierDTO;
import lk.ijse.pos.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SupplierBO {
    public  String currentSId() throws SQLException, ClassNotFoundException ;
    public  boolean save(SupplierDTO dto) throws SQLException, ClassNotFoundException;
    public  boolean save(List<SupplierDTO> supplierList) throws SQLException, ClassNotFoundException;


}
