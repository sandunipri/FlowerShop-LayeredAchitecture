package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.StockBO;
import lk.ijse.pos.dao.custom.StockDAO;
import lk.ijse.pos.dao.custom.impl.StockDAOImpl;
import lk.ijse.pos.dto.StockDTO;
import lk.ijse.pos.entity.EventOrderDetail;
import lk.ijse.pos.entity.ProductOrderDetail;
import lk.ijse.pos.entity.Stock;
import lk.ijse.pos.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public class StockBOImpl implements StockBO {

    StockDAO stockDAO = new StockDAOImpl();
    @Override
    public boolean add(StockDTO dto) throws SQLException, ClassNotFoundException {
        Stock stock = new Stock(
                dto.getStockId(),
                dto.getFCode(),
                dto.getQtyOnHand()
        );
        return stockDAO.add(stock);
    }

    @Override
    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        return stockDAO.getCodes();
    }

    @Override
    public StockDTO searchById(String stockID) throws SQLException, ClassNotFoundException {
      Stock stock = new Stock();
      StockDTO stockDTO = new StockDTO(
              stock.getStockId(),
              stock.getFCode(),
              stock.getQtyOnHand()
      );
      return stockDTO;
    }

    @Override
    public boolean UpdateQTY(List<Supplier> supplierList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean UpdateQTY(Supplier supplier) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean Updateqty(List<ProductOrderDetail> productOrderDetailList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean Updateqty(ProductOrderDetail productOrderDetail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean UPDATEQTY(List<EventOrderDetail> eventOrderDetailList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean UPDATEQTY(EventOrderDetail eventOrderDetail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean ADD(StockDTO dto) throws SQLException, ClassNotFoundException {
        Stock stock = new Stock(
                dto.getStockId(),
                dto.getFCode(),
                dto.getQtyOnHand()
        );
        return stockDAO.add(stock);
    }

    @Override
    public boolean delete(String stockid) throws SQLException, ClassNotFoundException {
     return stockDAO.delete(stockid);
    }
}
