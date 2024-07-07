package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.UpdateBO;
import lk.ijse.pos.dao.custom.StockDAO;
import lk.ijse.pos.dao.custom.SupplierDAO;
import lk.ijse.pos.dao.custom.impl.StockDAOImpl;
import lk.ijse.pos.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.dto.SupplierDTO;
import lk.ijse.pos.dto.UpdateDTO;
import lk.ijse.pos.entity.Supplier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdateBOImpl implements UpdateBO {

    SupplierDAO supplierDAO = new SupplierDAOImpl();
    StockDAO stockDAO = new StockDAOImpl();
    public  boolean update(UpdateDTO updateDTO) throws SQLException {

        List<SupplierDTO> dtoList = updateDTO.getSupplierList();
        List<Supplier> supplierList = new ArrayList<>();
        for (SupplierDTO supplierDTO : dtoList) {
            Supplier supplier = new Supplier(
                    supplierDTO.getSID(),
                    supplierDTO.getStockID(),
                    supplierDTO.getUID(),
                    supplierDTO.getSName(),
                    supplierDTO.getAddress(),
                    supplierDTO.getTelNo(),
                    supplierDTO.getFcode(),
                    supplierDTO.getQTY(),
                    supplierDTO.getPrice()
            );
            supplierList.add(supplier);
        }

        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try{  boolean isSupplierSaved = supplierDAO.save(supplierList);
            if (isSupplierSaved) {
                System.out.println("wwwwwwwww");
                boolean isStockUpdate = stockDAO.UpdateQTY(supplierList);

                System.out.println("eeee");

                if (isStockUpdate) {
                    connection.commit();
                    return true;

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

