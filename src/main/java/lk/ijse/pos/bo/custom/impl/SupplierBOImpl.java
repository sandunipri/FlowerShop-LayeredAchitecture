package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.SupplierBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.FlowerDAO;
import lk.ijse.pos.dao.custom.StockDAO;
import lk.ijse.pos.dao.custom.SupplierDAO;
import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.dao.custom.impl.FlowerDAOImpl;
import lk.ijse.pos.dao.custom.impl.StockDAOImpl;
import lk.ijse.pos.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.pos.dao.custom.impl.UserDAOImpl;
import lk.ijse.pos.dto.FlowerDTO;
import lk.ijse.pos.dto.SupplierDTO;
import lk.ijse.pos.dto.UpdateDTO;
import lk.ijse.pos.entity.Flower;
import lk.ijse.pos.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    FlowerDAO flowerDAO = (FlowerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FLOWER);

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    StockDAO stockDAO = (StockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);

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
        return supplierDAO.add(supplier);
    }

    @Override
    public boolean save(List<SupplierDTO> supplierList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getFcode() throws SQLException, ClassNotFoundException {
        return flowerDAO.getFcode();
    }

    @Override
    public List<String> getuserId() throws SQLException, ClassNotFoundException {
       return userDAO.getId();
    }

    @Override
    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        return stockDAO.getCodes();
    }

    @Override
    public FlowerDTO searchByFlowerCode(String fcode) throws SQLException, ClassNotFoundException {
        Flower flower = flowerDAO.searchByCode(fcode);
        FlowerDTO dto = new FlowerDTO(
             flower.getFCode(),
             flower.getFName()

        );
        return dto;
    }

}
