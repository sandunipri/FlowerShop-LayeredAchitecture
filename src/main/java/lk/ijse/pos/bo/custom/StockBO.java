package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.StockDTO;
import lk.ijse.pos.entity.EventOrderDetail;
import lk.ijse.pos.entity.ProductOrderDetail;
import lk.ijse.pos.entity.Stock;
import lk.ijse.pos.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface StockBO {
    public boolean add(StockDTO dto) throws SQLException, ClassNotFoundException;
    public List<String> getCodes() throws SQLException, ClassNotFoundException;
    public StockDTO searchById(String stockID) throws SQLException, ClassNotFoundException;
    public boolean UpdateQTY(List<Supplier> supplierList) throws SQLException, ClassNotFoundException;
    public boolean UpdateQTY(Supplier supplier) throws SQLException, ClassNotFoundException;
    public boolean Updateqty(List<ProductOrderDetail> productOrderDetailList) throws SQLException, ClassNotFoundException;
    public boolean Updateqty(ProductOrderDetail productOrderDetail) throws SQLException, ClassNotFoundException;
    public boolean UPDATEQTY(List<EventOrderDetail> eventOrderDetailList) throws SQLException, ClassNotFoundException;
    public boolean UPDATEQTY(EventOrderDetail eventOrderDetail) throws SQLException, ClassNotFoundException;
    public boolean ADD(StockDTO dto) throws SQLException, ClassNotFoundException;
    public boolean delete(String stockid) throws SQLException, ClassNotFoundException;
}
