package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.SupplierBO;
import lk.ijse.pos.dao.custom.SupplierDAO;
import lk.ijse.pos.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.pos.dto.SupplierDTO;
import lk.ijse.pos.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = new SupplierDAOImpl();
    @Override
    public String currentSId() throws SQLException, ClassNotFoundException {
       return supplierDAO.currentSId();
    }

    @Override
    public boolean save(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        Supplier supplier = new Supplier(
                dto.getSID(),
                dto.getStockID(),
                dto.getUID(),
                dto.getSName(),
                dto.getAddress(),
                dto.getTelNo(),
                dto.getFcode(),
                dto.getQTY(),
                dto.getPrice()
        );
        return supplierDAO.save(supplier);
    }

    @Override
    public boolean save(List<SupplierDTO> supplierList) throws SQLException, ClassNotFoundException {
        return false;
    }
}
